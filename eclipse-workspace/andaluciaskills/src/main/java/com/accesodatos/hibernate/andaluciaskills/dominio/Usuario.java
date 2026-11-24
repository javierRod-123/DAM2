package com.accesodatos.hibernate.andaluciaskills.dominio;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	// Evita dos usuarios con el mismo username
	@Column(unique = true)
	private String username;

	private String password;

	// Lado inverso: "usuario" es el nombre del atributo en Experto que lleva @JoinColumn
	@OneToOne(mappedBy = "usuario", optional = true)
	private Experto experto;

	public Usuario() {
		super();
	}

	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Experto getExperto() {
		return experto;
	}

	// Sincroniza el otro lado si no está ya apuntando a este Usuario
	public void setExperto(Experto experto) {
		this.experto = experto;
		if (experto != null && (experto.getUsuario() == null || !experto.getUsuario().equals(this))) {
			experto.setUsuario(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		String expertoPresente = (experto != null) ? ", experto=" + experto.getNombre() : "";
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + expertoPresente + "]";
	}
}