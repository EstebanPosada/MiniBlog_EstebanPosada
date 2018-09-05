package com.estebanposada.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.estebanposada.dao.IUsuarioDAO;
import com.estebanposada.model.Usuario;

@Stateless
public class UsuarioDAOImpl implements IUsuarioDAO, Serializable {
	
	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;

	@Override
	public Integer registrar(Usuario us) throws Exception {
		em.persist(us);
		return us.getPersona().getIdPersona();
	}

	@Override
	public Integer modificar(Usuario us) throws Exception {
		em.merge(us);
		return us.getPersona().getIdPersona();
	}

	@Override
	public Integer eliminar(Usuario t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listar() throws Exception {
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			Query query = em.createQuery("SELECT p FROM Usuario p");
			lista = (List<Usuario>) query.getResultList();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	@Override
	public Usuario listarPorId(Usuario usuario) throws Exception {
		Usuario us = new Usuario();
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			Query query = em.createQuery("FROM Usuario p where p.idUsuario = ?1");
			query.setParameter(1,  usuario.getPersona().getIdPersona());
			
			lista = (List<Usuario>) query.getResultList();
			if(lista != null && !lista.isEmpty()) {
				us = lista.get(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return us;
	}
	

	@Override
	public List<Usuario> listarPorUsuario(String bus) {
		Usuario user = new Usuario();
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			Query query = em.createQuery("FROM Usuario u where u.usuario LIKE ?1");
			query.setParameter(1, "%"+bus+"%");
			
			lista = (List<Usuario>) query.getResultList();
		
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}
	

	@Override
	public String traerPassHashed(String us) {
		Usuario usuario = null;
		String consulta;
		try {
			consulta = "FROM Usuario us WHERE us.usuario = ?1";
			Query query = em.createQuery(consulta);
			query.setParameter(1,  us);
			
			List<Usuario> lista = query.getResultList();
			if(!lista.isEmpty()) {
				usuario = lista.get(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return usuario != null ? usuario.getContrasena() : "";
	}

	@Override
	public Usuario login(Usuario us) {
		Usuario usuario = new Usuario();
		String consulta;
		try {
			//Consulta = "FROM Persona p WHERE p.us.usuario = ?1 and p.us.contrasena = ?2";
			consulta = "FROM Usuario us WHERE us.usuario = ?1 and us.contrasena = ?2";
			Query query = em.createQuery(consulta);
			query.setParameter(1, us.getUsuario());
			query.setParameter(2, us.getContrasena());
			
			List<Usuario> lista = query.getResultList();
			if(!lista.isEmpty()) {
				usuario = lista.get(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return usuario;
	}

}
