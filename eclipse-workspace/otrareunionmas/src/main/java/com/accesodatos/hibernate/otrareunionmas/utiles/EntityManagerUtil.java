package com.accesodatos.hibernate.otrareunionmas.utiles;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// El trabajo de está clase es abrir el canal de comunicación entre Java y BBDD 
// usando el nombre que le puse en el perssitence.xml
public class EntityManagerUtil {
	public static EntityManager getEntityManagerUtil() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("OtraReunionMas");
		EntityManager manager = factory.createEntityManager();
		
		return manager;
	}
}
