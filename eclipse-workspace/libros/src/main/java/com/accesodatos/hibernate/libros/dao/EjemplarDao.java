package com.accesodatos.hibernate.libros.dao;

import com.accesodatos.hibernate.libros.dominio.Ejemplar;

public class EjemplarDao extends AbstractDao<Ejemplar> {
	public EjemplarDao() {
		setClazz(Ejemplar.class);
	}
}