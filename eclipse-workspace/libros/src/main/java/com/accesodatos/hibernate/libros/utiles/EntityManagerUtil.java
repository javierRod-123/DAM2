package com.accesodatos.hibernate.libros.utiles;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	/*
	 * clase sencilla de utilidad que nos ayude a establecer y manejar las
	 * conexiones con la base de datos
	 */
	public static EntityManager getEntityManager() {
// en la siguiente línea se pone lo que pusimos en <persistence-unit
//name="OtraReunionMas" en el fichero persistence.xml
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bibliotecas");
		EntityManager manager = factory.createEntityManager();
		return manager;
	}
}
