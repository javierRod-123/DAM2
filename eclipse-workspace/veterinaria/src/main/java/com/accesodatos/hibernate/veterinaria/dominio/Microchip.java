package com.accesodatos.hibernate.veterinaria.dominio;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Microchip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String codigo;

	LocalDate fechaImplantacion;

	@OneToOne
	@JoinColumn(name = "mascota_id")
	private Mascota mascota;

	public Microchip() {
		super();
	}

	public Microchip(String codigo, LocalDate fechaImplantacion, Mascota mascota) {
		super();
		this.codigo = codigo;
		this.fechaImplantacion = fechaImplantacion;
		setMascota(mascota);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDate getFechaImplantacion() {
		return fechaImplantacion;
	}

	public void setFechaImplantacion(LocalDate fechaImplantacion) {
		this.fechaImplantacion = fechaImplantacion;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, fechaImplantacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Microchip other = (Microchip) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(fechaImplantacion, other.fechaImplantacion);
	}

}
