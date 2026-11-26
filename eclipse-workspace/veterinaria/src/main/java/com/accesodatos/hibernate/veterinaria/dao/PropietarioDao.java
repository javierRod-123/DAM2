package com.accesodatos.hibernate.veterinaria.dao;

import com.accesodatos.hibernate.veterinaria.dominio.Propietario;

public class PropietarioDao extends AbstractDao<Propietario> {
	public PropietarioDao() {
		setClazz(Propietario.class);
	}
}
