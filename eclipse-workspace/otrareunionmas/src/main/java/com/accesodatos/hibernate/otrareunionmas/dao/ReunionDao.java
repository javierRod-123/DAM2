package com.accesodatos.hibernate.otrareunionmas.dao;

import javax.persistence.Query;

import com.accesodatos.hibernate.otrareunionmas.dominio.Reunion;

public class ReunionDao extends AbstractDao<Reunion> {
	public ReunionDao() {
		setClazz(Reunion.class);
	}
	
	public Reunion getProximaReunion() {
		String qlString = "FROM" + Reunion.class.getName() + "WHERE fecha> now() ORDER BY fecha";
		
		Query query = getEntityManager().createQuery(qlString).setMaxResults(1);
		
		return (Reunion) query.getSingleResult();
		
	}
}
