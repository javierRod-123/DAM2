package com.accesodatos.hibernate.andaluciaskills;

import java.util.List;

import com.accesodatos.hibernate.andaluciaskills.dao.*;
import com.accesodatos.hibernate.andaluciaskills.dominio.*;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		UsuarioDao usuarioDao = new UsuarioDao();
		EspecialidadDao especialidadDao = new EspecialidadDao();
		ExpertoDao expertoDao = new ExpertoDao();
		CompetidorDao competidorDao = new CompetidorDao();
		PruebaDao pruebaDao = new PruebaDao();
		PuntuacionPruebaDao puntuacionPruebaDao = new PuntuacionPruebaDao();

		// Crear Usuario
		Usuario user1 = new Usuario("Zabala", "1234a");
		usuarioDao.save(user1);

		Usuario user2 = new Usuario("CaÃ±as", "4321b");
		usuarioDao.save(user2);

		Usuario user3 = new Usuario("JaviLala", "1111k");
		usuarioDao.save(user3);

		// Crear Especialidad
		Especialidad especialidad1 = new Especialidad(1111, "Desarrollo web");
		especialidadDao.save(especialidad1);

		Especialidad especialidad2 = new Especialidad(2222, "Fotografia");
		especialidadDao.save(especialidad2);

		Especialidad especialidad3 = new Especialidad(3333, "Soldadura");
		especialidadDao.save(especialidad3);

		// Recuperamos los datos anteriores de todo esto

//		Especialidad especialidad1 = especialidadDao.get(1).isPresent()?especialidadDao.get(1).get():null;
//		Especialidad especialidad2 = especialidadDao.get(2).isPresent()?especialidadDao.get(2).get():null;
//		Especialidad especialidad3 = especialidadDao.get(3).isPresent()?especialidadDao.get(3).get():null;
//	
//		Usuario user1 = usuarioDao.get(1).isPresent()?usuarioDao.get(1).get():null;
//		Usuario user2 = usuarioDao.get(2).isPresent()?usuarioDao.get(2).get():null;
//		Usuario user3 = usuarioDao.get(3).isPresent()?usuarioDao.get(3).get():null;
//		

		// Crear Experto
		Experto experto1 = new Experto("Carlos Zabala", especialidad1, user1);
		expertoDao.save(experto1);

		Experto experto2 = new Experto("Mario CaÃ±as", especialidad2, user2);
		expertoDao.save(experto2);

		Experto experto3 = new Experto("Javier Villaverde", especialidad3, user3);
		expertoDao.save(experto3);

		// Crear Competidor
		Competidor competidor1 = new Competidor("Javier ProJ", "IES Hnos Machado", especialidad1);
		competidorDao.save(competidor1);

		Competidor competidor2 = new Competidor("Paula Poza", "IES Vedruna", especialidad1);
		competidorDao.save(competidor2);

		Competidor competidor3 = new Competidor("Gonzalo Lite", "IES RamÃ³n Carande", especialidad2);
		competidorDao.save(competidor3);

		Competidor competidor4 = new Competidor("Mario Magiyo", "IES Galileo Galilei", especialidad2);
		competidorDao.save(competidor4);

		Competidor competidor5 = new Competidor("Jose Cepeda", "IES Antonio Machado", especialidad3);
		competidorDao.save(competidor5);

		Competidor competidor6 = new Competidor("Miriam Vega", "IES Vedruna", especialidad3);
		competidorDao.save(competidor6);

		// Recuperamos los Competidor para introducirlos en las Prueba
//		Competidor competidor1 =competidorDao.get(1).isPresent()?competidorDao.get(1).get():null;
//		Competidor competidor2 = competidorDao.get(2).isPresent()?competidorDao.get(2).get():null;
//		Competidor competidor3 = competidorDao.get(3).isPresent()?competidorDao.get(3).get():null;
//		Competidor competidor4 = competidorDao.get(4).isPresent()?competidorDao.get(4).get():null;
//		Competidor competidor5 = competidorDao.get(5).isPresent()?competidorDao.get(5).get():null;
//		Competidor competidor6= competidorDao.get(6).isPresent()?competidorDao.get(6).get():null;

		// Crear Prueba
		Prueba prueba1 = new Prueba("Prueba 1 DAW");
		pruebaDao.save(prueba1);
		new PuntuacionPrueba(competidor1, prueba1, 8);
		new PuntuacionPrueba(competidor2, prueba1, 9);
		pruebaDao.update(prueba1);

		Prueba prueba2 = new Prueba("Prueba 2 DAW");
		pruebaDao.save(prueba2);
		new PuntuacionPrueba(competidor1, prueba2, 9);
		new PuntuacionPrueba(competidor2, prueba2, 9);
		pruebaDao.update(prueba2);
		
//		// Crear Prueba 
//		Prueba prueba1 = new Prueba("Prueba 1 DAW");
//		pruebaDao.save(prueba1);
//		prueba1.addPuntuacion(new PuntuacionPrueba(competidor1, prueba1, 8));
//		prueba1.addPuntuacion(new PuntuacionPrueba(competidor2, prueba1, 9));
//		pruebaDao.update(prueba1);
////
//		Prueba prueba2 = new Prueba("Prueba 2 DAW");
//		pruebaDao.save(prueba2);
//		prueba2.addPuntuacion(new PuntuacionPrueba(competidor1, prueba2, 9));
//		prueba2.addPuntuacion(new PuntuacionPrueba(competidor2, prueba2, 9));
//		pruebaDao.update(prueba2);
//
//		Prueba prueba3 = new Prueba("Prueba 1 Fotografia");
//		prueba1.addCompetidor(competidor3);
//		prueba1.addCompetidor(competidor4);
//		pruebaDao.save(prueba3);
//
//		Prueba prueba4 = new Prueba("Prueba 2 Fotografia");
//		prueba1.addCompetidor(competidor3);
//		prueba1.addCompetidor(competidor4);
//		pruebaDao.save(prueba4);
//
//		Prueba prueba5 = new Prueba("Prueba 1 Soldadura");
//		prueba1.addCompetidor(competidor5);
//		prueba1.addCompetidor(competidor6);
//		pruebaDao.save(prueba5);
//
//		Prueba prueba6 = new Prueba("Prueba 2 Soldadura");
//		prueba1.addCompetidor(competidor5);
//		prueba1.addCompetidor(competidor6);
//		pruebaDao.save(prueba6);

		// AÃ±adir datos puntuaciones por prueba y por competidor
//		PuntuacionPrueba pp1 = new PuntuacionPrueba(competidor1, prueba1, 8);
//		PuntuacionPruebaDao.save(pp1);
//		PuntuacionPrueba pp2 = new PuntuacionPrueba(competidor2, prueba1, 9);
//		PuntuacionPruebaDao.save(pp2);
//		PuntuacionPrueba pp3 = new PuntuacionPrueba(competidor1, prueba2, 7);
//		PuntuacionPruebaDao.save(pp3);
//		PuntuacionPrueba pp4 = new PuntuacionPrueba(competidor2, prueba2, 6);
//		PuntuacionPruebaDao.save(pp4);
//
//		PuntuacionPrueba pp5 = new PuntuacionPrueba(competidor3, prueba3, 5);
//		PuntuacionPruebaDao.save(pp5);
//		PuntuacionPrueba pp6 = new PuntuacionPrueba(competidor4, prueba3, 9);
//		PuntuacionPruebaDao.save(pp6);
//		PuntuacionPrueba pp7 = new PuntuacionPrueba(competidor3, prueba4, 8);
//		PuntuacionPruebaDao.save(pp7);
//		PuntuacionPrueba pp8 = new PuntuacionPrueba(competidor4, prueba4, 4);
//		PuntuacionPruebaDao.save(pp8);
//
//		PuntuacionPrueba pp9 = new PuntuacionPrueba(competidor5, prueba5, 5);
//		PuntuacionPruebaDao.save(pp9);
//		PuntuacionPrueba pp10 = new PuntuacionPrueba(competidor6, prueba5, 4);
//		PuntuacionPruebaDao.save(pp10);
//		PuntuacionPrueba pp11 = new PuntuacionPrueba(competidor5, prueba6, 8);
//		PuntuacionPruebaDao.save(pp11);
//		PuntuacionPrueba pp12 = new PuntuacionPrueba(competidor6, prueba6, 9);
//		PuntuacionPruebaDao.save(pp12);

		// Creo dos Usuario que no son Experto
		Usuario usuario4 = new Usuario("noSoyExperto", "3333");
		usuarioDao.save(usuario4);

		Usuario usuario5 = new Usuario("MariPili", "8765");
		usuarioDao.save(usuario5);

		// Mostrar Todos los Usuario
//		List<Usuario> usuarios = usuarioDao.getAll();
//		System.out.println("\nTodos los Usuario: " + usuarios );
//		System.out.println("-------------------------------");
//		
//		//Mostrar Todos los Experto
//		List<Experto> expertos = expertoDao.getAll();
//		System.out.println("\nTodos los Experto: " + expertos );
//		System.out.println("-------------------------------");
//		
//		//Mostrar todas las Especialidad
//		List<Especialidad> especialidades = especialidadDao.getAll();
//		System.out.println("\nTodas las Especialidades: " +especialidades);
//		System.out.println("-------------------------------");
//		
//		// Mostrar Todos los Competidor
//		List<Competidor> competidores = competidorDao.getAll();
//		System.out.println("\nTodos los Competidores: " + competidores);
//		System.out.println("-------------------------------");
//		
//		//Mostrar todas las Prueba
//		List<Prueba> pruebas = pruebaDao.getAll();
//		System.out.println("\nTodas las Pruebas: \n" +pruebas);	
//		System.out.println("-------------------------------");
//		
//		//Centros que compiten
//		System.out.println("\nTodos los Centros que compiten: \n" +competidorDao.obtenerCentrosUnicos());
//		System.out.println("\nTodos los Centros que compiten con CriteriaQuery: \n" +competidorDao.obtenerCentrosUnicosCriteria());
//		System.out.println("-------------------------------");
//		
//		
//		//Obtener Usuario que son Experto
//		System.out.println("\nLos usuarios que son Experto: \n" +usuarioDao.getUsuariosExperto());
//		System.out.println("\nLos usuarios que son Experto con Criteria: \n" +usuarioDao.getUsuariosExpertoCriteria());
//		System.out.println("-------------------------------");
//		
//		//Solo el username de los Usuario que son Experto
//		System.out.println("\nUsername de los Usuarios Expertos: \n"+usuarioDao.getUsernamesUsuarioExpertos());
//		System.out.println("\nUsername de los Usuarios Expertos con Criteria: \n"+usuarioDao.getUsernamesUsuarioExpertosCriteria());
//		System.out.println("-----------------------------------------|");
//		
//		Competidor competidor1recu = competidorDao.get(3).get();
//		System.out.println(competidor1recu);
//		
//		System.out.println("\nPuntuaciones Competidores: \n" +puntuacionPruebaDao.obtenerPuntuacionesPorCompetidor());
//		System.out.println("\nPuntuaciones Competidores con Criteria: \n" +puntuacionPruebaDao.obtenerPuntuacionesPorCompetidorCriteria());
//		
//		
		// Actualizar el centro del competidor 3
//		competidor3.setCentro("IES Hnos Machado");
//		competidorDao.update(competidor3);
//		System.out.println("Competidor 3 actualizado");

		// Actualizar contraseÃ±a del usuario 2
		Usuario usu1 = usuarioDao.get(2).get();
		if (usu1 != null) {
			usu1.setPassword("aloPresidente");
			usuarioDao.update(usu1);
			System.out.println("Password del usuario con id 2 actualizado");
		}
	}
}
