package com.accesodatos.hibernate.otrareunionmas.dominio;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name = "reunion")

public class Reunion {

	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDateTime fecha;
	
	private String asunto;
	
	@OneToOne(mappedBy = "acta")
	private Acta acta;

	public Reunion() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	private Sala sala;
	
	
	public Sala getSala() {
		return sala;
	}
	
	public void setSala(Sala sala) {
		this.sala = sala;
		if (sala != null && !sala.getReuniones().contains(this)) {
			sala.addReunion(this);
		}
	}
	
	// Dentro de Reunion.java:
	// CascadeType.ALL significa que si creas una reunión nueva con participantes, estos se guardan automáticos en BD.
	@ManyToMany(mappedBy = "reuniones", cascade = javax.persistence.CascadeType.ALL)
	private Set<Persona> participantes = new HashSet<Persona>();

	public void addParticipante(Persona p) {
	    if (this.participantes == null) {
	        this.participantes = new HashSet<Persona>();
	    }
	    this.participantes.add(p);
	    if (!p.getReuniones().contains(this)) {
	        p.addReunion(this);
	    }
	}

	public void removeParticipante(Persona p) {
	    this.participantes.remove(p);
	    if (p.getReuniones().contains(this)) {
	        p.removeReunion(this);
	    }
	}

	public Set<Persona> getParticipantes() { return participantes; }
}
