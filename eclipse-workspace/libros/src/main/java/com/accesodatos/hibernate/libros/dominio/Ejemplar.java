package com.accesodatos.hibernate.libros.dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ejemplar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String codigo;

	@Column(nullable = true)
	private String localizacion;

	// Relación N:1 con Libro: muchos ejemplares pertenecen a un único libro
	@ManyToOne
	@JoinColumn(name = "libro_id", nullable = false)
	private Libro libro;

	// Lado inverso de la relación 1:N con Prestamo (parte del N:M Usuario-Ejemplar).
	// "ejemplar" es el atributo @ManyToOne en Prestamo.
	@OneToMany(mappedBy = "ejemplar", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Prestamo> prestamos = new HashSet<>();

	public Ejemplar() {
		super();
	}

	public Ejemplar(String codigo, String localizacion) {
		super();
		this.codigo = codigo;
		this.localizacion = localizacion;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
		if (libro != null && !libro.containsEjemplar(this)) {
			libro.addEjemplar(this);
		}
	}

	public Set<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(Set<Prestamo> prestamos) {
		this.prestamos = (prestamos != null) ? prestamos : new HashSet<>();
	}

	// --- Métodos auxiliares para mantener consistencia bidireccional con Prestamo ---

	public void addPrestamo(Prestamo prestamo) {
		if (prestamo != null) {
			if (this.prestamos == null) {
				this.prestamos = new HashSet<>();
			}
			this.prestamos.add(prestamo);
			if (prestamo.getEjemplar() != this) {
				prestamo.setEjemplar(this);
			}
		}
	}

	public void removePrestamo(Prestamo prestamo) {
		if (prestamo != null && this.prestamos != null) {
			if (this.prestamos.remove(prestamo) && prestamo.getEjemplar() == this) {
				prestamo.setEjemplar(null);
			}
		}
	}

	public boolean containsPrestamo(Prestamo prestamo) {
		return this.prestamos != null && this.prestamos.contains(prestamo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, localizacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ejemplar other = (Ejemplar) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(localizacion, other.localizacion);
	}
}