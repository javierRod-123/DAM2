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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Socio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String nombre;

	@Column(nullable = true)
	private String email;

	@Column (nullable = false)
	private int edad;

	@OneToOne(mappedBy = "socio", optional = true, cascade = CascadeType.ALL)
	private FichaMedica fichaMedica;

	@OneToMany(mappedBy = "socio")
	private Set<Reserva> reserva = new HashSet<Reserva>();

	public Socio() {
		super();
	}

	public Socio(String nombre, String email, int edad) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.edad = edad;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public FichaMedica getFichaMedica() {
		return fichaMedica;
	}

	public void setFichaMedica(FichaMedica fichaMedica) {
		this.fichaMedica = fichaMedica;
		if (fichaMedica != null && (fichaMedica.getSocio() == null || !fichaMedica.getSocio().equals(this))) {
			fichaMedica.setSocio(this);
		}
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
			if (r.getSocio() != this)
				r.setSocio(this);
		}
	}

	public void removeReserva(Reserva r) {
		if (r != null && this.reserva.remove(r)) {
			if (r.getSocio() == this)
				r.setSocio(null);
		}
	}

	public boolean containsReserva(Reserva reserva) {
		return this.reserva != null && reserva != null && this.reserva.contains(reserva);
	}

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(edad), email, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Socio other = (Socio) obj;
		return edad == other.edad && Objects.equals(email, other.email) && Objects.equals(nombre, other.nombre);
	}

}
