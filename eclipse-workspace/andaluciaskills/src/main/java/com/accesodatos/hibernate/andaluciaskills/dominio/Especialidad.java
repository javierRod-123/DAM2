package com.accesodatos.hibernate.andaluciaskills.dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Especialidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private int codigo;

	private String nombre;

	// Lado inverso del 1:1 con Experto (Experto tiene @JoinColumn en "especialidad")
	@OneToOne(mappedBy = "especialidad")
	private Experto experto;

	// Lado inverso del 1:N con Competidor (Competidor tiene @JoinColumn en "especialidad")
	@OneToMany(mappedBy = "especialidad")
	private Set<Competidor> competidores = new HashSet<>();

	public Especialidad() {
		super();
	}

	public Especialidad(int codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public Especialidad(int codigo, String nombre, Experto experto) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		setExperto(experto);
	}

	public int getId() {
		return id;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Experto getExperto() {
		return experto;
	}

	public void setExperto(Experto experto) {
		this.experto = experto;
		if (experto != null && (experto.getEspecialidad() == null || !experto.getEspecialidad().equals(this))) {
			experto.setEspecialidad(this);
		}
	}

	public Set<Competidor> getCompetidores() {
		return competidores;
	}

	public void addCompetidor(Competidor competidor) {
		if (this.competidores == null) {
			this.competidores = new HashSet<>();
		}
		this.competidores.add(competidor);
		if (competidor != null && (competidor.getEspecialidad() == null || !competidor.getEspecialidad().equals(this))) {
			competidor.setEspecialidad(this);
		}
	}

	public void removeCompetidor(Competidor competidor) {
		this.competidores.remove(competidor);
		if (competidor != null) {
			competidor.setEspecialidad(null);
		}
	}

	public boolean containsCompetidor(Competidor competidor) {
		return this.competidores != null && this.competidores.contains(competidor);
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
		Especialidad other = (Especialidad) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		String nomExperto = (experto != null) ? experto.getNombre() : "sin experto";
		return "Especialidad [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", experto=" + nomExperto + "]";
	}
}