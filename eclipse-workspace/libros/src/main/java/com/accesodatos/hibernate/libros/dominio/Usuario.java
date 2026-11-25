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
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String nombre;

	// Lado inverso de la relación 1:N con Prestamo (parte del N:M Usuario-Ejemplar).
	// "usuario" es el atributo @ManyToOne en Prestamo.
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Prestamo> prestamos = new HashSet<>();

	public Usuario() {
		super();
	}

	public Usuario(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
			if (prestamo.getUsuario() != this) {
				prestamo.setUsuario(this);
			}
		}
	}

	public void removePrestamo(Prestamo prestamo) {
		if (prestamo != null && this.prestamos != null) {
			if (this.prestamos.remove(prestamo) && prestamo.getUsuario() == this) {
				prestamo.setUsuario(null);
			}
		}
	}

	public boolean containsPrestamo(Prestamo prestamo) {
		return this.prestamos != null && this.prestamos.contains(prestamo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(nombre, other.nombre);
	}
}