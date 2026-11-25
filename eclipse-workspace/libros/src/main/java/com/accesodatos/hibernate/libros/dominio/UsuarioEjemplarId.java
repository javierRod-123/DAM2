package com.accesodatos.hibernate.libros.dominio;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class UsuarioEjemplarId implements Serializable {

	private int usuarioId;
	private int ejemplarId;

	public UsuarioEjemplarId() {
		super();
	}

	public UsuarioEjemplarId(int usuarioId, int ejemplarId) {
		super();
		this.usuarioId = usuarioId;
		this.ejemplarId = ejemplarId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getEjemplarId() {
		return ejemplarId;
	}

	public void setEjemplarId(int ejemplarId) {
		this.ejemplarId = ejemplarId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(ejemplarId), Integer.valueOf(usuarioId));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioEjemplarId other = (UsuarioEjemplarId) obj;
		return ejemplarId == other.ejemplarId && usuarioId == other.usuarioId;
	}

}
