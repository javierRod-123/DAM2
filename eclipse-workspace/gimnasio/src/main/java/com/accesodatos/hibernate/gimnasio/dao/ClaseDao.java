package com.accesodatos.hibernate.gimnasio.dao;

import java.util.List;

import com.accesodatos.hibernate.gimnasio.dominio.Clase;

public class ClaseDao extends AbstractDao<Clase> {
	public ClaseDao() {
		setClazz(Clase.class);
	}

	public List<Clase> getClasesLargasdeEspecialidad(String especialidad, int minDuracion) {

		String jpql = "SELECT c FROM Clase c JOIN c.entrenador e "
				+ "WHERE c.duracion_min > :minDuracion AND e.especialidad = :especialidad";

		return getEntityManager().createQuery(jpql, Clase.class).setParameter("minDuracion", minDuracion)
				.setParameter("especialidad", especialidad).getResultList();
	}
}
