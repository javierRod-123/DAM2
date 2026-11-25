package com.accesodatos.hibernate.andaluciaskills.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.accesodatos.hibernate.andaluciaskills.dominio.Competidor;

public class CompetidorDao extends AbstractDao<Competidor> {

	public CompetidorDao() {
		setClazz(Competidor.class);
	}

	public List<String> obtenerCentrosUnicos() {
		String jpql = "SELECT DISTINCT centro FROM Competidor";
		TypedQuery<String> query = getEntityManager().createQuery(jpql, String.class);
		return query.getResultList();
	}

	public List<String> obtenerCentrosUnicosCriteria() {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);

		Root<Competidor> root = criteriaQuery.from(Competidor.class);
		criteriaQuery.select(root.get("centro")).distinct(true);

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}
}