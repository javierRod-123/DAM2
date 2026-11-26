package com.accesodatos.hibernate.veterinaria.dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Propietario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String dni;

	@Column(nullable = false)
	private String nombre;

	private String telefono;

	@OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL)
	private Set<Mascota> mascota = new HashSet<Mascota>();

	public Propietario() {
		super();
	}

	public Propietario(String dni, String nombre, String telefono) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Set<Mascota> getMascota() {
		return mascota;
	}

	public void setMascota(Set<Mascota> mascota) {
		this.mascota = mascota;
	}

	public void addMascota(Mascota mascota) {
		if (mascota != null) {
			this.mascota.add(mascota);
			if (mascota.getPropietario() != this) {
				mascota.setPropietario(this);
			}
		}
	}

	public void removeMascota(Mascota mascota) {
		if (mascota != null && this.mascota.remove(mascota)) {
			if (mascota.getPropietario() == this) {
				mascota.setPropietario(this);
			}
		}
	}

	public boolean containsMascota(Mascota mascota) {
		return this.mascota.contains(mascota);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni, mascota, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Propietario other = (Propietario) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(mascota, other.mascota)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(telefono, other.telefono);
	}

}
