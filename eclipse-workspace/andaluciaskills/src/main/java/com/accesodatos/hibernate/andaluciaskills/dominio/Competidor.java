package com.accesodatos.hibernate.andaluciaskills.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
public class Competidor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String nombre;

	private String centro;

	// Relación N:1 con Especialidad: Competidor lleva la FK hacia Especialidad
	@ManyToOne
	@JoinColumn
	private Especialidad especialidad;

	// Relación 1:N con PuntuacionPrueba (parte del N:M con atributo puntuacion)
	@OneToMany(mappedBy = "competidor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PuntuacionPrueba> puntuaciones = new ArrayList<>();

	public Competidor() {
		super();
	}

	public Competidor(int id, String nombre, String centro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.centro = centro;
	}

	public Competidor(String nombre, String centro, Especialidad especialidad) {
		super();
		this.nombre = nombre;
		this.centro = centro;
		setEspecialidad(especialidad);
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

	public String getCentro() {
		return centro;
	}

	public void setCentro(String centro) {
		this.centro = centro;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
		if (especialidad != null && !especialidad.containsCompetidor(this)) {
			especialidad.addCompetidor(this);
		}
	}

	public void addPuntuacion(PuntuacionPrueba puntuacion) {
		if (puntuacion != null && !puntuaciones.contains(puntuacion)) {
			puntuaciones.add(puntuacion);
			puntuacion.setCompetidor(this);
		}
	}

	public void removePuntuacion(PuntuacionPrueba puntuacion) {
		if (puntuacion != null && puntuaciones.remove(puntuacion)) {
			puntuacion.setCompetidor(null);
		}
	}

	public List<PuntuacionPrueba> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(List<PuntuacionPrueba> puntuaciones) {
		this.puntuaciones = puntuaciones;
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
		Competidor other = (Competidor) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		String nomEsp = (especialidad != null) ? especialidad.getNombre() : "sin especialidad";
		return "Competidor [id=" + id + ", nombre=" + nombre + ", centro=" + centro + ", especialidad=" + nomEsp + "]";
	}
}