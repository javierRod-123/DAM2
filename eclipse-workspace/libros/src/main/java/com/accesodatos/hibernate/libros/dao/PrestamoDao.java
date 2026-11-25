package com.accesodatos.hibernate.libros.dao;

import java.util.List;
import java.util.Optional;
import javax.persistence.TypedQuery;
import com.accesodatos.hibernate.libros.dominio.Prestamo;
import com.accesodatos.hibernate.libros.dominio.UsuarioEjemplarId;

public class PrestamoDao extends AbstractDao<Prestamo> {

	public PrestamoDao() {
		setClazz(Prestamo.class);
	}
	
	public List<Prestamo> getPrestamoNoDevueltos(){
		String jpql = "FROM Prestamo p WHERE p.fechaDevolucion IS NULL";
		return getEntityManager().createQuery(jpql, Prestamo.class).getResultList();
	}

}