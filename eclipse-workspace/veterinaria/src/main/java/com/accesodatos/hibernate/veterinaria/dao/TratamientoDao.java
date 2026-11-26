package com.accesodatos.hibernate.veterinaria.dao;

import com.accesodatos.hibernate.veterinaria.dominio.Tratamiento;

public class TratamientoDao extends AbstractDao<Tratamiento> {
	public TratamientoDao() {
		setClazz(Tratamiento.class);
	}
}