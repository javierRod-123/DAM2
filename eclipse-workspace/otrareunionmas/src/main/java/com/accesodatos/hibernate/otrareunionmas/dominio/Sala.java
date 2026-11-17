package com.accesodatos.hibernate.otrareunionmas.dominio;

import javax.persistence.Table;

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
	
}
