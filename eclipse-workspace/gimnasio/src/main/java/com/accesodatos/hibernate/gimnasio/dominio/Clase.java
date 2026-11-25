package com.accesodatos.hibernate.gimnasio.dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
public class Clase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private int duracionMin;

	@ManyToOne
	@JoinColumn(name = "entrenador_id", nullable = false)
	private Entrenador entrenador;

	@OneToMany(mappedBy = "clase")
	private Set<Reserva> reserva = new HashSet<Reserva>();

	public Clase() {
		super();
	}

	public Clase(String titulo, int duracionMin) {
		super();
		this.titulo = titulo;
		this.duracionMin = duracionMin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracionMin() {
		return duracionMin;
	}

	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public Set<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(Set<Reserva> reserva) {
		this.reserva = reserva;
	}

	public void addReserva(Reserva r) {
		if (r != null) {
			this.reserva.add(r);
			if (r.getClase() != this)
				r.setClase(this);
		}
	}

	public void removeReserva(Reserva r) {
		if (r != null && this.reserva.remove(r)) {
			if (r.getClase() == this)
				r.setClase(null);
		}
	}

	public boolean containsReserva(Reserva reserva) {
		return this.reserva != null && reserva != null && this.reserva.contains(reserva);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(duracionMin), titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clase other = (Clase) obj;
		return duracionMin == other.duracionMin && Objects.equals(titulo, other.titulo);
	}

}
