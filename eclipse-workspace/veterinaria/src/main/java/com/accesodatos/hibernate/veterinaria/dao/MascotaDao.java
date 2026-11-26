package com.accesodatos.hibernate.veterinaria.dao;

import com.accesodatos.hibernate.veterinaria.dominio.Mascota;

public class MascotaDao extends AbstractDao<Mascota> {
	public MascotaDao() {
		setClazz(Mascota.class);
	}
}
