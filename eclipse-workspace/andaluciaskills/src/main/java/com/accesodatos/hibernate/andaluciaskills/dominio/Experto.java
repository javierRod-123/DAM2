package com.accesodatos.hibernate.andaluciaskills.dominio;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Experto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// Evita duplicar nombres de expertos en la base de datos
	@Column(unique = true)
	private String nombre;

	// Lado propietario de la relación 1:1 con Especialidad
	@OneToOne
	@JoinColumn
	private Especialidad especialidad;

	// Lado propietario de la relación 1:1 con Usuario
	@OneToOne
	@JoinColumn
	private Usuario usuario;

	public Experto() {
		super();
	}

	public Experto(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Experto(String nombre, Especialidad especialidad, Usuario usuario) {
		super();
		this.nombre = nombre;
//		setEspecialidad(especialidad);
		setUsuario(usuario);
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

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	// Mantiene sincronizada la relación bidireccional con Especialidad
//	public void setEspecialidad(Especialidad especialidad) {
//		this.especialidad = especialidad;
//		if (especialidad != null && (especialidad.getExperto() == null || !especialidad.getExperto().equals(this))) {
//			especialidad.setExperto(this);
//		}
//	}

	public Usuario getUsuario() {
		return usuario;
	}

	// Mantiene sincronizada la relación bidireccional con Usuario
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		if (usuario != null && (usuario.getExperto() == null || !usuario.getExperto().equals(this))) {
			usuario.setExperto(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(especialidad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Experto other = (Experto) obj;
		return Objects.equals(especialidad, other.especialidad) && Objects.equals(nombre, other.nombre);
	}

//	@Override
//	public String toString() {
//		// Comprobamos nulos para evitar NullPointerException si aún no se asignaron
//		String nombreEsp = (especialidad != null) ? especialidad.getNombre() : "sin especialidad";
//		String userName = (usuario != null) ? usuario.getUsername() : "sin usuario";
//		return "Experto [id=" + id + ", nombre=" + nombre + ", especialidad=" + nombreEsp + ", usuario=" + userName + "]";
//	}
}