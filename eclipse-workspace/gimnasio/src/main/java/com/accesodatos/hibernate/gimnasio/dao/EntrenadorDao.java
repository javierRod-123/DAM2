package com.accesodatos.hibernate.gimnasio.dao;


import com.accesodatos.hibernate.gimnasio.dominio.Entrenador;

public class EntrenadorDao extends AbstractDao<Entrenador> {
	public EntrenadorDao() {
		setClazz(Entrenador.class);
	}
}