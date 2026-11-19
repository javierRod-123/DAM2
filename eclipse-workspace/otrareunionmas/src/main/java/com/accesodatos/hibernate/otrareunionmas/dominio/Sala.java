package com.accesodatos.hibernate.otrareunionmas.dominio;

import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table
public class Sala {
	@Id
	@Column(length = 20)
	private String id;

	private String descripcion;
	private int capacidad;

	// Relación 1:N -> Una sala tiene muchas reuniones
	// mappedBy="sala" indica que el atributo 'sala' de la clase Reunion es eldueño
	// de la relación.
	@OneToMany(mappedBy = "sala")
	private List<Reunion> reuniones;

	public Sala() {
	}

	public Sala(String id, String descripcion, int capacidad) {
		this.id = id;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
	}

	public void addReunion(Reunion r) {
		if (this.reuniones == null) {
			reuniones = new ArrayList<Reunion>();
		}

		reuniones.add(r);

		if (!r.getSala().equals(this)) {
			r.setSala(this);
		}
	}

	public void removeReunion(Reunion r) {
		reuniones.remove(r);
		r.setSala(null);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public List<Reunion> getReuniones() {
		return reuniones;
	}

	public void setReuniones(List<Reunion> reuniones) {
		this.reuniones = reuniones;
	}

}
