package com.accesodatos.hibernate.gimnasio.dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Entrenador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(unique = true, nullable = false)
	private String especialidad;
	
	@OneToMany(mappedBy = "entrenador")
	private Set<Clase> clase = new HashSet<Clase>();

	public Entrenador() {
		super();
	}

	public Entrenador(String nombre, String especialidad) {
		super();
		this.nombre = nombre;
		this.especialidad = especialidad;
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

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public Set<Clase> getClase() {
		return clase;
	}

	public void setClase(Set<Clase> clase) {
		this.clase = clase;
	}
	
	public void addClase(Clase clase) {
		if (clase != null) {
			this.clase.add(clase);
			if (clase.getEntrenador() != this) {
				clase.setEntrenador(this);
			}
		}
	}
	
	public boolean containsClase(Clase clase) {
		return this.clase.contains(clase);
	}
	
	public void removeClase(Clase clase) {
		if (clase != null && this.clase.remove(clase)) {
			if (clase.getEntrenador() == this) {
				clase.setEntrenador(null);
			}
		}
	}
	
}
