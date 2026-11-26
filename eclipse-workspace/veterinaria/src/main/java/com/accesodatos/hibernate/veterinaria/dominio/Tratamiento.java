package com.accesodatos.hibernate.veterinaria.dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tratamiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String nombre;

	@Column(nullable = false)
	private double coste;

	private String descripcion;

	@ManyToMany(mappedBy = "tratamiento")
	private Set<Mascota> mascotas = new HashSet<Mascota>();

	public Tratamiento() {
		super();
	}

	public Tratamiento(String nombre, double coste, String descripcion) {
		super();
		this.nombre = nombre;
		this.coste = coste;
		this.descripcion = descripcion;
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

	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set<Mascota> getMascotas() {
		return mascotas;
	}

	public void setMascotas(Set<Mascota> mascotas) {
		this.mascotas = mascotas;
	}

	public void addMascota(Mascota mascota) {
		if (mascota != null) {
			this.mascotas.add(mascota);
			if (!mascota.containsTratamiento(this)) {
				mascota.addTratamiento(this);
			}
		}
	}

	public void removeMascota(Mascota mascota) {
		if (mascota != null && this.mascotas.remove(mascota)) {
			if (mascota.containsTratamiento(this)) {
				mascota.removeTratamiento(this);
			}
		}
	}

	public boolean containsMascota(Mascota mascota) {
		return this.mascotas.contains(mascota);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Double.valueOf(coste), descripcion, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tratamiento other = (Tratamiento) obj;
		return Double.doubleToLongBits(coste) == Double.doubleToLongBits(other.coste)
				&& Objects.equals(descripcion, other.descripcion) && Objects.equals(nombre, other.nombre);
	}

}
