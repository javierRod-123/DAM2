package com.accesodatos.hibernate.libros.dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String nombre;
	
	@ManyToMany(mappedBy = "autores")
	private Set<Libro> libros = new HashSet<Libro>();

	public Autor() {
	}

	public Autor(String nombre) {
		this.nombre = nombre;
	}
	

	public Autor(String nombre, Set<Libro> libros) {
		super();
		this.nombre = nombre;
		setLibros(libros);
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

	public Set<Libro> getLibros() {
		return libros;
	}

	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}
	
	public void addLibro(Libro libro) {
		if(libro != null) {
			if (this.libros == null) {
				this.libros = new HashSet<Libro>();
			}
			this.libros.add(libro);
			
			if (!libro.getAutores().contains(this)) {
				libro.addAutor(this);
			}
		}
	}
	
	public void removeLibro(Libro libro) {
		if (libro != null && this.libros != null) {
			if (this.libros.remove(libro) && libro.getAutores().contains(this)) {
				libro.removeAutor(this);
			}
		}
	}
	
	public boolean containsLibro(Libro libro) {
	    return this.libros != null && this.libros.contains(libro);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(nombre, other.nombre);
	}
}
