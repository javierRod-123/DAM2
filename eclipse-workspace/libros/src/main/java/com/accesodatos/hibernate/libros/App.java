package com.accesodatos.hibernate.libros;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.accesodatos.hibernate.libros.dao.AutorDao;
import com.accesodatos.hibernate.libros.dao.EjemplarDao;
import com.accesodatos.hibernate.libros.dao.LibroDao;
import com.accesodatos.hibernate.libros.dao.PrestamoDao;
import com.accesodatos.hibernate.libros.dao.UsuarioDao;
import com.accesodatos.hibernate.libros.dominio.Autor;
import com.accesodatos.hibernate.libros.dominio.Ejemplar;
import com.accesodatos.hibernate.libros.dominio.Libro;
import com.accesodatos.hibernate.libros.dominio.Prestamo;
import com.accesodatos.hibernate.libros.dominio.Usuario;
import com.accesodatos.hibernate.libros.dominio.UsuarioEjemplarId;

public class App {

	public static void main(String[] args) {
		// Instanciamos los DAOs
		AutorDao autorDao = new AutorDao();
		LibroDao libroDao = new LibroDao();
		EjemplarDao ejemplarDao = new EjemplarDao();
		UsuarioDao usuarioDao = new UsuarioDao();
		PrestamoDao prestamoDao = new PrestamoDao();

		// 1. Crear dos autores y un libro, asociarlos y volcar en BD
		Autor a1 = new Autor("Ana García");
		Autor a2 = new Autor("Luis Pérez");
		// Ojo al orden del constructor: (editorial, isbn, paginas, titulo)
		Libro libro = new Libro("Marvel", "978-84-00001-1-1", 150, "Avengers Secret Wars");

		libro.addAutor(a1);
		libro.addAutor(a2);

		autorDao.save(a1);
		autorDao.save(a2);
		libroDao.save(libro);

		// 2. Crear dos ejemplares del libro anterior y volcar en BD
		Ejemplar ej1 = new Ejemplar("COD-1", "Biblioteca Sevilla");
		ej1.setLibro(libro);

		Ejemplar ej2 = new Ejemplar("COD-2", "Biblioteca Málaga");
		ej2.setLibro(libro);

		ejemplarDao.save(ej1);
		ejemplarDao.save(ej2);

		// 3. Crear dos usuarios y prestar un ejemplar a cada uno
		// (ambos con fecha de préstamo; solo p1 con fecha de devolución)
		Usuario user1 = new Usuario("Javier");
		Usuario user2 = new Usuario("Jiro");

		usuarioDao.save(user1);
		usuarioDao.save(user2);

		Prestamo p1 = new Prestamo();
		p1.setUsuario(user1);
		p1.setEjemplar(ej1);
		p1.setId(new UsuarioEjemplarId(user1.getId(), ej1.getId()));
		p1.setFechaPrestamo(LocalDateTime.now().minusDays(3));
		p1.setFechaDevolucion(LocalDateTime.now()); // Este sí está devuelto
		p1.asociarExtremos();
		prestamoDao.save(p1);

		Prestamo p2 = new Prestamo();
		p2.setUsuario(user2);
		p2.setEjemplar(ej2);
		p2.setId(new UsuarioEjemplarId(user2.getId(), ej2.getId()));
		p2.setFechaPrestamo(LocalDateTime.now().minusDays(1));
		p2.setFechaDevolucion(null); // Este aún no se ha devuelto
		p2.asociarExtremos();
		prestamoDao.save(p2);

		// 4. Recuperar de BD uno de los objetos creados anteriormente
		Optional<Autor> autorRecuperadoOpt = autorDao.get(a2.getId());
		if (autorRecuperadoOpt.isPresent()) {
			Autor autorRecuperado = autorRecuperadoOpt.get();
			System.out.println("Objeto recuperado de BD: " + autorRecuperado);

			// 5. Desasociar de sus libros y borrarlo
			for (Libro l : new java.util.HashSet<>(autorRecuperado.getLibros())) {
				l.removeAutor(autorRecuperado);
				libroDao.update(l);
			}
			autorDao.delete(autorRecuperado);
			System.out.println("Objeto borrado de BD.");
		}

		// 6. Consultar los préstamos que han sido devueltos y mostrarlos
		List<Prestamo> prestamosDevueltos = prestamoDao.getEntityManager()
				.createQuery("FROM Prestamo p WHERE p.fechaDevolucion IS NOT NULL", Prestamo.class).getResultList();
		System.out.println("\n--- Préstamos devueltos ---");
		for (Prestamo p : prestamosDevueltos) {
			System.out.println(p);
		}

		// 7. Consultar los ejemplares de los libros de más de 30 páginas y mostrarlos
		List<Ejemplar> ejemplaresMas30 = ejemplarDao.getEjemplaresLibrosMasPaginas(30);
		System.out.println("\n--- Ejemplares de libros de más de 30 páginas ---");
		for (Ejemplar e : ejemplaresMas30) {
			System.out.println(e);
		}
	}
}