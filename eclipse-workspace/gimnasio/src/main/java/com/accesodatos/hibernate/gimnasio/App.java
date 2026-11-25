package com.accesodatos.hibernate.gimnasio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.accesodatos.hibernate.gimnasio.dao.ClaseDao;
import com.accesodatos.hibernate.gimnasio.dao.EntrenadorDao;
import com.accesodatos.hibernate.gimnasio.dao.FichaMedicaDao;
import com.accesodatos.hibernate.gimnasio.dao.ReservaDao;
import com.accesodatos.hibernate.gimnasio.dao.SocioDao;
import com.accesodatos.hibernate.gimnasio.dominio.Clase;
import com.accesodatos.hibernate.gimnasio.dominio.Entrenador;
import com.accesodatos.hibernate.gimnasio.dominio.FichaMedica;
import com.accesodatos.hibernate.gimnasio.dominio.Reserva;
import com.accesodatos.hibernate.gimnasio.dominio.Socio;

public class App {

	public static void main(String[] args) {

		ClaseDao claseDao = new ClaseDao();
		EntrenadorDao entrenadorDao = new EntrenadorDao();
		FichaMedicaDao fichaMedicaDao = new FichaMedicaDao();
		ReservaDao reservaDao = new ReservaDao();
		SocioDao socioDao = new SocioDao();

		// 1. Entrenadores (1:N con Clase) -> guardar primero en BD
		Entrenador entrenador1 = new Entrenador("Paco", "baloncesto");
		Entrenador entrenador2 = new Entrenador("Sara", "natacion");

		entrenadorDao.save(entrenador1);
		entrenadorDao.save(entrenador2);

		// 2. Clases -> asociar al entrenador ya guardado y guardar en BD
		Clase c1 = new Clase("Baloncesto Premium", 60);
		Clase c2 = new Clase("Waterpolo", 30);
		Clase c3 = new Clase("Tecnificacion Baloncesto", 50);

		entrenador1.addClase(c1);
		entrenador1.addClase(c3);
		entrenador2.addClase(c2);

		claseDao.save(c1);
		claseDao.save(c2);
		claseDao.save(c3);

		// 3. Socios y Fichas Medicas (1:1) -> asociar y guardar en BD
		Socio socio1 = new Socio("Jay", "javistarwars04@gmail.com", 22);
		Socio socio2 = new Socio("Marta", "marta04@gmail.com", 35);

		FichaMedica f1 = new FichaMedica("A+", true);
		FichaMedica f2 = new FichaMedica("AB+", false);

		socio1.setFichaMedica(f1);
		socio2.setFichaMedica(f2);

		socioDao.save(socio1);
		socioDao.save(socio2);

		// 4. Reservas (N:M con atributos) -> guardar en BD
		Reserva r1 = new Reserva(socio1, c1, LocalDateTime.of(2026, 11, 26, 15, 30), true);
		Reserva r2 = new Reserva(socio2, c2, LocalDateTime.of(2026, 11, 16, 18, 30), false);
		Reserva r3 = new Reserva(socio2, c3, LocalDateTime.of(2026, 11, 20, 17, 00), true);

		reservaDao.save(r1);
		reservaDao.save(r2);
		reservaDao.save(r3);

		// 5. Recuperar socio1 desde la BD
		Optional<Socio> recuperado = socioDao.get(socio1.getId());
		if (recuperado.isPresent()) {
			Socio socioBD = recuperado.get();
			System.out.println("Socio recuperado: " + socioBD.getNombre() + " (" + socioBD.getEmail() + ")");
		}

		// 6. Actualizar edad del socio2 y su ficha medica
		socio2.setEdad(36);
		f2.setApto(true);
		socioDao.update(socio2);
		fichaMedicaDao.update(f2);

		// 7. Borrar socio1 (borrando antes su reserva r1 y su ficha f1)
		reservaDao.delete(r1);
		socio1.setFichaMedica(null);
		f1.setSocio(null);
		socioDao.delete(socio1);
		fichaMedicaDao.delete(f1);
		System.out.println("Socio1 eliminado correctamente.");

		// 8. Metodo DAO 1: Reservas sin asistencia
		List<Reserva> reservasSinAsistencia = reservaDao.getReservasSinAsistencia();
		System.out.println("\n--- Reservas sin asistencia ---");
		for (Reserva r : reservasSinAsistencia) {
			System.out.println("Clase: " + r.getClase().getTitulo() + " | Socio: " + r.getSocio().getNombre()
					+ " | Fecha: " + r.getFechaReserva());
		}

		// 9. Metodo DAO 2: Clases largas (> 45 min) de una especialidad
		List<Clase> clasesLargas = claseDao.getClasesLargasdeEspecialidad("baloncesto", 45);
		System.out.println("\n--- Clases largas (>45 min) de especialidad 'baloncesto' ---");
		for (Clase c : clasesLargas) {
			System.out.println("Clase: " + c.getTitulo() + " (" + c.getDuracionMin() + " min)");
		}
	}
}