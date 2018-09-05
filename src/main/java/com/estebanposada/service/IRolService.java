package com.estebanposada.service;

import java.util.List;

import com.estebanposada.model.Rol;
import com.estebanposada.model.Usuario;
import com.estebanposada.model.UsuarioRol;

public interface IRolService extends IService<Rol> {
	
	Integer asignar(Usuario usuario, List<Rol> roles);
	List<UsuarioRol> listarRolesPorUsuario(Usuario usuario);

}
