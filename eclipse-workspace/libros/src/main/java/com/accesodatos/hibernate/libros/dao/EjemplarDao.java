package com.accesodatos.hibernate.libros.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import com.accesodatos.hibernate.libros.dominio.Ejemplar;

public class EjemplarDao extends AbstractDao<Ejemplar> {

	public EjemplarDao() {
		setClazz(Ejemplar.class);
	}

	public List<Ejemplar> getEjemplaresLibrosMasPaginas(int paginas) {
		String jpql = "SELECT e FROM Ejemplar e WHERE e.libro.paginas > :paginas";
		return getEntityManager().createQuery(jpql, Ejemplar.class).setParameter("paginas", paginas).getResultList();
	}

}