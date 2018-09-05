package com.estebanposada.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.estebanposada.dao.IRolDAO;
import com.estebanposada.model.Rol;
import com.estebanposada.model.Usuario;
import com.estebanposada.model.UsuarioRol;

@Stateless
public class RolDAOImpl implements IRolDAO, Serializable {

	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;

	@Override
	public Integer registrar(Rol t) throws Exception {
		// Logica de spring jdbc
		em.persist(t);
		// INSERT INTO Rol (id, nombre) VALUES (?, ?);
		return t.getId();
	}

	@Override
	public Integer modificar(Rol t) throws Exception {
		em.merge(t);
		return t.getId();
	}

	@Override
	public Integer eliminar(Rol t) throws Exception {
		em.remove(t);
		return 1;
	}

	@Override
	public List<Rol> listar() throws Exception {
		List<Rol> lista = new ArrayList<>();

		// JPQL
		Query q = em.createQuery("SELECT r FROM Rol r");
		lista = (List<Rol>) q.getResultList();
		return lista;
	}

	@Override
	public Rol listarPorId(Rol t) throws Exception {
		Rol rol = new Rol();
		List<Rol> lista = new ArrayList<>();

		Query q = em.createQuery("FROM Rol r where r.id = ?1");
		q.setParameter(1, t.getId());

		lista = (List<Rol>) q.getResultList();

		return lista != null && !lista.isEmpty() ? lista.get(0) : new Rol();
	}

	@Override
	public Integer asignar(Usuario us, List<UsuarioRol> roles) {
		
		try {
			Query query = em.createNativeQuery("DELETE FROM usuario_rol ur where ur.id_usuario =?1");
			query.setParameter(1,  us.getPersona().getIdPersona());
			query.executeUpdate();
			
			int[] iarr = { 0 };
			roles.forEach(r -> {
				em.persist(r);
				if (iarr[0] % 100 == 0) {
					em.flush();
					em.clear();
				}
				iarr[0]++;
			});
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public List<UsuarioRol> listarRolesPorUsuario(Usuario usuario) {
		List<UsuarioRol> lista = new ArrayList<UsuarioRol>();
		try {
			Query query = em.createQuery("FROM UsuarioRol ur where ur.usuario.persona.idPersona =?1");
			query.setParameter(1, usuario.getPersona().getIdPersona());

			lista = (List<UsuarioRol>) query.getResultList();
			
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

}
