package com.estebanposada.dao;

import java.util.List;

import javax.ejb.Local;

import com.estebanposada.model.Rol;
import com.estebanposada.model.Usuario;
import com.estebanposada.model.UsuarioRol;

@Local
public interface IRolDAO extends ICRUD<Rol> {

	Integer asignar(Usuario us, List<UsuarioRol> roles);
	List<UsuarioRol> listarRolesPorUsuario(Usuario usuario);
	
}
