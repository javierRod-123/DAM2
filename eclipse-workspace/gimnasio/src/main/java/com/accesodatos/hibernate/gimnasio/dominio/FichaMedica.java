package com.accesodatos.hibernate.gimnasio.dominio;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class FichaMedica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = true)
	private String grupoSangrineo;

	@Column
	private boolean apto;

	@OneToOne
	@JoinColumn(name = "socio_id")
	private Socio socio;

	public FichaMedica() {
		super();
	}

	public FichaMedica(String grupoSangrineo, boolean apto) {
		super();
		this.grupoSangrineo = grupoSangrineo;
		this.apto = apto;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGrupoSangrineo() {
		return grupoSangrineo;
	}

	public void setGrupoSangrineo(String grupoSangrineo) {
		this.grupoSangrineo = grupoSangrineo;
	}

	public boolean isApto() {
		return apto;
	}

	public void setApto(boolean apto) {
		this.apto = apto;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
		if (socio != null && (socio.getFichaMedica() == null || !socio.getFichaMedica().equals(this))) {
			socio.setFichaMedica(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(Boolean.valueOf(apto), grupoSangrineo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FichaMedica other = (FichaMedica) obj;
		return apto == other.apto && Objects.equals(grupoSangrineo, other.grupoSangrineo);
	}

}
