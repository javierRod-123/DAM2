package com.accesodatos.hibernate.andaluciaskills.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

	// Código y nombre de la especialidad según el modelo
	@Column(unique = true)
	private String codigo;

	private String nombre;

	// Relación 1:1 con Experto: Experto tiene @JoinColumn en su atributo "especialidad"
	@OneToOne(mappedBy = "especialidad")
	private Experto experto;

	// Relación 1:N con Competidor: una Especialidad tiene muchos Competidores
	// mappedBy = "especialidad" indica que el atributo "especialidad" en Competidor lleva la FK
	@OneToMany(mappedBy = "especialidad")
	private List<Competidor> competidores = new ArrayList<>();

	public Especialidad() {
		super();
	}

	public Especialidad(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	// Mantiene sincronizada la relación 1:1 con Experto
	public void setExperto(Experto experto) {
		this.experto = experto;
		if (experto != null && (experto.getEspecialidad() == null || !experto.getEspecialidad().equals(this))) {
			experto.setEspecialidad(this);
		}
	}

	public List<Competidor> getCompetidores() {
		return competidores;
	}

	public void setCompetidores(List<Competidor> competidores) {
		this.competidores = competidores;
	}

	// Método de ayuda para añadir un competidor y mantener ambos lados sincronizados
	public void addCompetidor(Competidor competidor) {
		if (competidor != null) {
			if (!this.competidores.contains(competidor)) {
				this.competidores.add(competidor);
			}
			if (competidor.getEspecialidad() != this) {
				competidor.setEspecialidad(this);
			}
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
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
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Especialidad [id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + "]";
	}
}