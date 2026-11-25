package com.accesodatos.hibernate.andaluciaskills.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import com.accesodatos.hibernate.andaluciaskills.dominio.Experto;
import com.accesodatos.hibernate.andaluciaskills.dominio.Usuario;

public class UsuarioDao extends AbstractDao<Usuario> {

	public UsuarioDao() {
		setClazz(Usuario.class);
	}

	public List<Usuario> getUsuariosExperto() {
		String queryJpql = "FROM " + Usuario.class.getName() + " u WHERE u.id IN (SELECT e.usuario.id FROM "
				+ Experto.class.getName() + " e)";
		TypedQuery<Usuario> query = getEntityManager().createQuery(queryJpql, Usuario.class);
		return query.getResultList();
	}

	public List<Usuario> getUsuariosExpertoCriteria() {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);

		Root<Usuario> usuarioRoot = criteriaQuery.from(Usuario.class);
		Join<Usuario, Experto> expertoJoin = usuarioRoot.join("experto");

		criteriaQuery.select(usuarioRoot);
		criteriaQuery.where(criteriaBuilder.isNotNull(expertoJoin));

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}

	public List<String> getUsernamesUsuarioExpertos() {
		String jpql = "SELECT u.username FROM Usuario u WHERE u.id IN (SELECT e.usuario.id FROM Experto e)";
		TypedQuery<String> query = getEntityManager().createQuery(jpql, String.class);
		return query.getResultList();
	}

	public List<String> getUsernamesUsuarioExpertosCriteria() {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);

		Root<Usuario> usuarioRoot = criteriaQuery.from(Usuario.class);
		Join<Usuario, Experto> expertoJoin = usuarioRoot.join("experto");

		criteriaQuery.select(usuarioRoot.get("username"));
		criteriaQuery.where(criteriaBuilder.isNotNull(expertoJoin));

		return getEntityManager().createQuery(criteriaQuery).getResultList();
	}
}