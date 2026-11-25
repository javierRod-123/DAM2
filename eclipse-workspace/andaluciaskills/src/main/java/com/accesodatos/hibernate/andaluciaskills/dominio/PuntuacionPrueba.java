package com.accesodatos.hibernate.andaluciaskills.dominio;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

// Entidad intermedia que representa la relación N:M entre Competidor y Prueba
// y añade el atributo "puntuacion" del modelo.
@Entity
@Table(name = "puntuacion_prueba")
public class PuntuacionPrueba implements Serializable {

	// Clave primaria compuesta por (competidorId, pruebaId)
	@EmbeddedId
	private CompetidorPruebaId id = new CompetidorPruebaId();

	// Muchas puntuaciones pueden pertenecer a un mismo Competidor.
	// @MapsId("competidorId") vincula este objeto con el campo competidorId de CompetidorPruebaId.
	@ManyToOne
	@MapsId("competidorId")
	@JoinColumn(name = "competidor_id")
	private Competidor competidor;

	// Muchas puntuaciones pueden corresponder a una misma Prueba.
	// @MapsId("pruebaId") vincula este objeto con el campo pruebaId de CompetidorPruebaId.
	@ManyToOne
	@MapsId("pruebaId")
	@JoinColumn(name = "prueba_id")
	private Prueba prueba;

	// Atributo propio de la relación en la pizarra
	@Column(name = "puntuacion")
	private int puntuacion;

	public PuntuacionPrueba() {
		super();
	}

	public PuntuacionPrueba(Competidor competidor, Prueba prueba, int puntuacion) {
		super();
		this.competidor = competidor;
		this.prueba = prueba;
		this.puntuacion = puntuacion;
		if (competidor != null && prueba != null) {
			this.id = new CompetidorPruebaId(competidor.getId(), prueba.getId());
		}
		asociarExtremos();
	}

	// Sincroniza esta PuntuacionPrueba tanto en Competidor como en Prueba en memoria
	public void asociarExtremos() {
		if (this.competidor != null && !this.competidor.getPuntuaciones().contains(this)) {
			this.competidor.addPuntuacion(this);
		}
		if (this.prueba != null && !this.prueba.getPuntuaciones().contains(this)) {
			this.prueba.addPuntuacion(this);
		}
	}

	public CompetidorPruebaId getId() {
		return id;
	}

	public void setId(CompetidorPruebaId id) {
		this.id = id;
	}

	public Competidor getCompetidor() {
		return competidor;
	}

	public void setCompetidor(Competidor competidor) {
		this.competidor = competidor;
	}

	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(competidor, prueba);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuntuacionPrueba other = (PuntuacionPrueba) obj;
		return Objects.equals(competidor, other.competidor) && Objects.equals(prueba, other.prueba);
	}

	@Override
	public String toString() {
		String nomComp = (competidor != null) ? competidor.getNombre() : "sin competidor";
		String nomPrueba = (prueba != null) ? prueba.getNombre() : "sin prueba";
		return "PuntuacionPrueba [competidor=" + nomComp + ", prueba=" + nomPrueba + ", puntuacion=" + puntuacion + "]";
	}
}