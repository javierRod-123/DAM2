package com.accesodatos.hibernate.gimnasio.dominio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

	@EmbeddedId
	private SocioClaseId id = new SocioClaseId();

	@ManyToOne(cascade = CascadeType.MERGE)
	@MapsId("socioId")
	@JoinColumn(name = "socio_id", nullable = false)
	private Socio socio;

	@ManyToOne(cascade = CascadeType.MERGE)
	@MapsId("claseId")
	@JoinColumn(name = "clase_id", nullable = false)
	private Clase clase;

	@Column(name = "fecha_reserva")
	private LocalDateTime fechaReserva;

	@Column(name = "asistio")
	private boolean asistio;

	public Reserva() {
		super();
	}

	public Reserva(Socio socio, Clase clase, LocalDateTime fechaReserva, boolean asistio) {
		this.socio = socio;
		this.clase = clase;
		this.fechaReserva = fechaReserva;
		this.asistio = asistio;
		asociarExtremos();
	}

	public void asociarExtremos() {
		if (this.socio != null && !this.socio.containsReserva(this)) {
			this.socio.addReserva(this);
		}
		
		if (this.clase != null && !this.clase.containsReserva(this)) {
			this.clase.addReserva(this);
		}
	}

	public SocioClaseId getId() {
		return id;
	}

	public void setId(SocioClaseId id) {
		this.id = id;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public LocalDateTime getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(LocalDateTime fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public boolean isAsistio() {
		return asistio;
	}

	public void setAsistio(boolean asistio) {
		this.asistio = asistio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clase, socio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(clase, other.clase) && Objects.equals(socio, other.socio);
	}

}
