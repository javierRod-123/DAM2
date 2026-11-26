package com.accesodatos.hibernate.veterinaria.dao;

import com.accesodatos.hibernate.veterinaria.dominio.Microchip;

public class MicrochipDao extends AbstractDao<Microchip> {
	public MicrochipDao() {
		setClazz(Microchip.class);
	}
}
