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

}