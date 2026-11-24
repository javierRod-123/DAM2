package com.accesodatos.hibernate.andaluciaskills.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Prueba {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// Nombre de la prueba (por ejemplo, Prueba 1, Prueba 2...)
	@Column(unique = true)
	private String nombre;

	// Una Prueba aparece en muchas filas de PuntuacionPrueba.
	// mappedBy = "prueba" apunta al atributo "prueba" de PuntuacionPrueba.
	// cascade = CascadeType.ALL y orphanRemoval = true propagan operaciones sobre sus puntuaciones.
	@OneToMany(mappedBy = "prueba", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PuntuacionPrueba> puntuaciones = new ArrayList<>();

	public Prueba() {
		super();
	}

	public Prueba(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PuntuacionPrueba> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(List<PuntuacionPrueba> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

	// Mantiene sincronizada la lista de puntuaciones de la prueba y la referencia de vuelta
	public void addPuntuacion(PuntuacionPrueba puntuacion) {
		if (puntuacion != null && !puntuaciones.contains(puntuacion)) {
			puntuaciones.add(puntuacion);
			puntuacion.setPrueba(this);
		}
	}

	public void removePuntuacion(PuntuacionPrueba puntuacion) {
		if (puntuacion != null && puntuaciones.remove(puntuacion)) {
			puntuacion.setPrueba(null);
		}
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
		Prueba other = (Prueba) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Prueba [id=" + id + ", nombre=" + nombre + "]";
	}
}