package com.accesodatos.hibernate.otrareunionmas.dominio;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private int numeroEmpleado;

	private String nombre;
	private String apellidos;

	// Relación Muchos a Muchos con Reunión. Usamos Set en lugar de List para evitar
	// duplicados y orden innecesario.
	@ManyToMany
	private Set<Reunion> reuniones;

	public Persona() {
		this.reuniones = new HashSet<Reunion>();
	}

	public Persona(int numeroEmpleado, String nombre, String apellidos) {
		this();
		this.numeroEmpleado = numeroEmpleado;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	// Método bidireccional (Opción C del PDF - Página 26)
	public void addReunion(Reunion reunion) {
		this.reuniones.add(reunion);
		if (!reunion.getParticipantes().contains(this)) {
			reunion.addParticipante(this);
		}
	}

	public void removeReunion(Reunion reunion) {
		this.reuniones.remove(reunion);
		if (reunion.getParticipantes().contains(this)) {
			reunion.removeParticipante(this);
		}
	}

	// Getters y Setters...
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public void setNumeroEmpleado(int numeroEmpleado) {
		this.numeroEmpleado = numeroEmpleado;
	}

	String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Set<Reunion> getReuniones() {
		return reuniones;
	}
}