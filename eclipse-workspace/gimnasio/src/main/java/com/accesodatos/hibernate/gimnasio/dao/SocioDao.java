package com.accesodatos.hibernate.gimnasio.dao;

import com.accesodatos.hibernate.gimnasio.dominio.Socio;

public class SocioDao extends AbstractDao<Socio> {
	public SocioDao() {
		setClazz(Socio.class);
	}
}