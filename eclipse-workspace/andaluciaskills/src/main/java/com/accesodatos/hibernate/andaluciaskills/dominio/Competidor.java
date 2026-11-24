package com.accesodatos.hibernate.andaluciaskills.dominio;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Competidor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// Nombre y centro del competidor según el modelo
	private String nombre;
	private String centro;

	// Relación N:1 (lado "muchos" de 1:N): muchos Competidores pertenecen a una Especialidad.
	// @JoinColumn indica que la clave foránea de la especialidad está en la tabla de Competidor.
	@ManyToOne
	@JoinColumn
	private Especialidad especialidad;

	public Competidor() {
		super();
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

	// Mantiene sincronizada la relación con Especialidad
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
		if (especialidad != null && !especialidad.getCompetidores().contains(this)) {
			especialidad.getCompetidores().add(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, centro);
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
		return id == other.id && Objects.equals(nombre, other.nombre) && Objects.equals(centro, other.centro);
	}

	@Override
	public String toString() {
		String nomEsp = (especialidad != null) ? especialidad.getNombre() : "sin especialidad";
		return "Competidor [id=" + id + ", nombre=" + nombre + ", centro=" + centro + ", especialidad=" + nomEsp + "]";
	}
}