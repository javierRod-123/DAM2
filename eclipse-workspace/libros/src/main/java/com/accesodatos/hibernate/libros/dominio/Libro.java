package com.accesodatos.hibernate.libros.dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = true)
	private String editorial;

	@Column(unique = true, nullable = false)
	private String isbn;

	@Column(nullable = false)
	private int paginas;

	@Column(nullable = true)
	private String titulo;

	// Lado propietario de la relación N:M con Autor (sin entidad intermedia).
	// "autores" coincide con mappedBy = "autores" en la clase Autor.
	@ManyToMany
	@JoinTable(
		name = "autor_libro",
		joinColumns = @JoinColumn(name = "libros_id"),
		inverseJoinColumns = @JoinColumn(name = "autores_id")
	)
	private Set<Autor> autores = new HashSet<>();

	// Relación 1:N con Ejemplar: un libro tiene varios ejemplares.
	// "libro" es el atributo @ManyToOne de la clase Ejemplar.
	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
	private Set<Ejemplar> ejemplares = new HashSet<>();

	public Libro() {
		super();
	}

	public Libro(String editorial, String isbn, int paginas, String titulo) {
		super();
		this.editorial = editorial;
		this.isbn = isbn;
		this.paginas = paginas;
		this.titulo = titulo;
	}

	public Libro(String editorial, String isbn, int paginas, String titulo, Set<Autor> autores,
			Set<Ejemplar> ejemplares) {
		super();
		this.editorial = editorial;
		this.isbn = isbn;
		this.paginas = paginas;
		this.titulo = titulo;
		this.autores = (autores != null) ? autores : new HashSet<>();
		this.ejemplares = (ejemplares != null) ? ejemplares : new HashSet<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Set<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}

	public Set<Ejemplar> getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(Set<Ejemplar> ejemplares) {
		this.ejemplares = ejemplares;
	}

	// --- Métodos auxiliares para mantener consistencia bidireccional con Autor ---

	public void addAutor(Autor autor) {
		if (autor != null) {
			if (this.autores == null) {
				this.autores = new HashSet<>();
			}
			this.autores.add(autor);
			if (!autor.containsLibro(this)) {
				autor.addLibro(this);
			}
		}
	}

	public void removeAutor(Autor autor) {
		if (autor != null && this.autores != null) {
			if (this.autores.remove(autor) && autor.containsLibro(this)) {
				autor.removeLibro(this);
			}
		}
	}

	public boolean containsAutor(Autor autor) {
		return this.autores != null && this.autores.contains(autor);
	}

	// --- Métodos auxiliares para mantener consistencia bidireccional con Ejemplar ---

	public void addEjemplar(Ejemplar ejemplar) {
		if (ejemplar != null) {
			if (this.ejemplares == null) {
				this.ejemplares = new HashSet<>();
			}
			this.ejemplares.add(ejemplar);
			// En 1:N, el Ejemplar tiene un único Libro (setLibro), no una colección
			if (ejemplar.getLibro() != this) {
				ejemplar.setLibro(this);
			}
		}
	}

	public void removeEjemplar(Ejemplar ejemplar) {
		if (ejemplar != null && this.ejemplares != null) {
			if (this.ejemplares.remove(ejemplar) && ejemplar.getLibro() == this) {
				ejemplar.setLibro(null);
			}
		}
	}

	public boolean containsEjemplar(Ejemplar ejemplar) {
		return this.ejemplares != null && this.ejemplares.contains(ejemplar);
	}

	@Override
	public int hashCode() {
		return Objects.hash(editorial, isbn, Integer.valueOf(paginas), titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(editorial, other.editorial) && Objects.equals(isbn, other.isbn)
				&& paginas == other.paginas && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", isbn=" + isbn + ", paginas=" + paginas + "]";
	}
}