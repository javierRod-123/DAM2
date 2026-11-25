package com.accesodatos.hibernate.andaluciaskills.dao;

import java.util.List;
import javax.persistence.TypedQuery;

import com.accesodatos.hibernate.andaluciaskills.dominio.Competidor;
import com.accesodatos.hibernate.andaluciaskills.dominio.PuntuacionPrueba;

public class PuntuacionPruebaDao extends AbstractDao<PuntuacionPrueba> {

	public PuntuacionPruebaDao() {
		setClazz(PuntuacionPrueba.class);
	}

	public String obtenerPuntuacionesPorCompetidor() {
		String jpql = "SELECT c.nombre, pp.prueba.nombre, pp.puntuacion "
				+ "FROM " + Competidor.class.getName() + " c "
				+ "JOIN c.puntuaciones pp";

		TypedQuery<Object[]> query = getEntityManager().createQuery(jpql, Object[].class);
		List<Object[]> results = query.getResultList();
		String resultados = "";
		for (Object[] result : results) {
			String competidorNombre = (String) result[0];
			String pruebaNombre = (String) result[1];
			int puntuacion = (int) result[2];

			resultados += "Competidor: " + competidorNombre + ", Prueba: " + pruebaNombre + ", Puntuación: "
					+ puntuacion + "\n";
		}
		return resultados;
	}

	public String obtenerPuntuacionesPorCompetidorCriteria() {
		String res = "";
		List<PuntuacionPrueba> puntuaciones = getAll();

		for (PuntuacionPrueba pp : puntuaciones) {
			res += "\nCompetidor: " + pp.getCompetidor().getNombre() + ", Prueba: " + pp.getPrueba().getNombre()
					+ ", Puntuación: " + pp.getPuntuacion();
		}
		return res;
	}
}