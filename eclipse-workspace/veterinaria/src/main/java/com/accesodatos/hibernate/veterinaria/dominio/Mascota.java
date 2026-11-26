package com.accesodatos.hibernate.veterinaria.dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Mascota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String especie;

	private double peso;

	@OneToOne(mappedBy = "mascota", optional = true)
	private Microchip microchip;

	@ManyToOne
	@JoinColumn(name = "propietario_id", nullable = false)
	private Propietario propietario;

	@ManyToMany
	@JoinTable(name = "mascota_tratamiento", joinColumns = @JoinColumn(name = "mascota_id"), inverseJoinColumns = @JoinColumn(name = "tratamiento_id"))
	private Set<Tratamiento> tratamientos = new HashSet<Tratamiento>();

	public Mascota() {
		super();
	}

	public Mascota(String nombre, String especie, double peso, Propietario propietario) {
		super();
		this.nombre = nombre;
		this.especie = especie;
		this.peso = peso;
		setPropietario(propietario);
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

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Microchip getMicrochip() {
		return microchip;
	}

	public void setMicrochip(Microchip microchip) {
		this.microchip = microchip;
		if (microchip != null && (microchip.getMascota() == null || !microchip.getMascota().equals(this))) {
			microchip.setMascota(this);
		}
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public void addTratamiento(Tratamiento tratamiento) {
		if (tratamiento != null) {
			this.tratamientos.add(tratamiento);
			if (!tratamiento.containsMascota(this)) {
				tratamiento.addMascota(this);
			}
		}
	}

	public void removeTratamiento(Tratamiento tratamiento) {
		if (tratamiento != null && this.tratamientos.remove(tratamiento)) {
			if (tratamiento.containsMascota(this)) {
				tratamiento.removeMascota(this);
			}
		}
	}

	public boolean containsTratamiento(Tratamiento tratamiento) {
		return this.tratamientos.contains(tratamiento);
	}

	@Override
	public int hashCode() {
		return Objects.hash(especie, nombre, Double.valueOf(peso));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mascota other = (Mascota) obj;
		return Objects.equals(especie, other.especie) && Objects.equals(nombre, other.nombre)
				&& Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso);
	}

	@Override
	public String toString() {
		return "Mascota [id=" + id + ", nombre=" + nombre + ", especie=" + especie + ", peso=" + peso + ", microchip="
				+ microchip + ", propietario=" + propietario + ", tratamientos=" + tratamientos + "]";
	}

}
