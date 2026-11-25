package com.accesodatos.hibernate.libros.dominio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "prestamo")
public class Prestamo implements Serializable {

	@EmbeddedId
	private UsuarioEjemplarId id = new UsuarioEjemplarId();

	@ManyToOne
	@MapsId("usuarioId")
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@MapsId("ejemplarId")
	@JoinColumn(name = "ejemplar_id", nullable = false)
	private Ejemplar ejemplar;

	@Column(name = "fecha_prestamo")
	private LocalDateTime fechaPrestamo;

	@Column(name = "fecha_devolucion")
	private LocalDateTime fechaDevolucion;

	public Prestamo() {
		super();
	}

	public Prestamo(Usuario usuario, Ejemplar ejemplar, LocalDateTime fechaPrestamo, LocalDateTime fechaDevolucion) {
		super();
		this.usuario = usuario;
		this.ejemplar = ejemplar;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
	}

	public void asociarExtremos() {
		if (this.usuario != null && !this.usuario.containsPrestamo(this)) {
			this.usuario.addPrestamo(this);
		}
		if (this.ejemplar != null && !this.ejemplar.containsPrestamo(this)) {
			this.ejemplar.addPrestamo(this);
		}
	}

	public UsuarioEjemplarId getId() {
		return id;
	}

	public void setId(UsuarioEjemplarId id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ejemplar getEjemplar() {
		return ejemplar;
	}

	public void setEjemplar(Ejemplar ejemplar) {
		this.ejemplar = ejemplar;
	}

	public LocalDateTime getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDateTime getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ejemplar, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestamo other = (Prestamo) obj;
		return Objects.equals(ejemplar, other.ejemplar) && Objects.equals(usuario, other.usuario);
	}

}
