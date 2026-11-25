package com.accesodatos.hibernate.libros.dominio;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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

	@ManyToMany
	@JoinTable(name = "autor_libro", joinColumns = @JoinColumn(name = "libros_id"), inverseJoinColumns = @JoinColumn(name = "autores_id"))
	private Set<Autor> autores = new HashSet<Autor>();

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
}
