package com.accesodatos.hibernate.gimnasio.dao;

import java.util.List;

import com.accesodatos.hibernate.gimnasio.dominio.Reserva;

public class ReservaDao extends AbstractDao<Reserva> {
	public ReservaDao() {
		setClazz(Reserva.class);
	}

	public List<Reserva> getReservasSinAsistencia() {

		String jpql = "SELECT r FROM Reservas WHERE r.asistio =  false";

		return getEntityManager().createQuery(jpql, Reserva.class).getResultList();
	}
}