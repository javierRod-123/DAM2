package com.accesodatos.hibernate.otrareunionmas.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Sala {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "nombre", unique = true)
	private String nombre;
	private String descripcion;
	private int capacidad;

	@OneToMany(mappedBy = "sala")
	private List<Reunion> reuniones;

}
