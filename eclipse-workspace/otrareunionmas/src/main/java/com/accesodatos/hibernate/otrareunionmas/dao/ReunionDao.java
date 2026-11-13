package com.accesodatos.hibernate.otrareunionmas.dao;

import com.accesodatos.hibernate.otrareunionmas.dominio.Reunion;

public class ReunionDao extends AbstractDao<Reunion> {
	public ReunionDao() {
		setClazz(Reunion.class);
	}
}
