package com.estebanposada.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.estebanposada.dao.IRolDAO;
import com.estebanposada.model.Rol;
import com.estebanposada.model.Usuario;
import com.estebanposada.model.UsuarioRol;
import com.estebanposada.service.IRolService;

@Named
public class RolServiceImpl implements IRolService, Serializable {

	@EJB
	private IRolDAO dao;
	
	@Override
	public Integer registrar(Rol t) throws Exception {
		return dao.registrar(t);
	}

	@Override
	public Integer modificar(Rol t) throws Exception {
		return dao.modificar(t);
	}


	@Override
	public List<Rol> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Rol listarPorId(Rol t) throws Exception {
		return dao.listarPorId(t);
	}

	@Override
	public Integer asignar(Usuario usuario, List<Rol> roles) {
		List<UsuarioRol> usuario_roles = new ArrayList<UsuarioRol>();
		try {
			roles.forEach(r->{
				UsuarioRol ur = new UsuarioRol();
				ur.setUsuario(usuario);
				ur.setRol(r);
				usuario_roles.add(ur);
			});
			
			dao.asignar(usuario, usuario_roles);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
	
	@Override
	public List<UsuarioRol> listarRolesPorUsuario(Usuario usuario) {
		return dao.listarRolesPorUsuario(usuario);
	}

}
