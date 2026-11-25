package com.accesodatos.hibernate.gimnasio.dominio;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class SocioClaseId implements Serializable {

	private int socioId;
	private int claseId;

	public SocioClaseId() {
		super();
	}

	public SocioClaseId(int socioId, int claseId) {
		super();
		this.socioId = socioId;
		this.claseId = claseId;
	}

	public int getSocioId() {
		return socioId;
	}

	public void setSocioId(int socioId) {
		this.socioId = socioId;
	}

	public int getClaseId() {
		return claseId;
	}

	public void setClaseId(int claseId) {
		this.claseId = claseId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(claseId), Integer.valueOf(socioId));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocioClaseId other = (SocioClaseId) obj;
		return claseId == other.claseId && socioId == other.socioId;
	}

}
