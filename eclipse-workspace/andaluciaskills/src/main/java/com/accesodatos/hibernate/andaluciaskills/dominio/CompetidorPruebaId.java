package com.accesodatos.hibernate.andaluciaskills.dominio;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

// @Embeddable indica que esta clase no es una tabla por sí sola, sino una clave compuesta
// que se va a incrustar (@EmbeddedId) dentro de otra entidad (PuntuacionPrueba).
// Debe implementar Serializable y tener equals() y hashCode().
@Embeddable
public class CompetidorPruebaId implements Serializable {

	private int competidorId;
	private int pruebaId;

	public CompetidorPruebaId() {
		super();
	}

	public CompetidorPruebaId(int competidorId, int pruebaId) {
		this.competidorId = competidorId;
		this.pruebaId = pruebaId;
	}

	public int getCompetidorId() {
		return competidorId;
	}

	public void setCompetidorId(int competidorId) {
		this.competidorId = competidorId;
	}

	public int getPruebaId() {
		return pruebaId;
	}

	public void setPruebaId(int pruebaId) {
		this.pruebaId = pruebaId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CompetidorPruebaId that = (CompetidorPruebaId) o;
		return competidorId == that.competidorId && pruebaId == that.pruebaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(competidorId, pruebaId);
	}
}