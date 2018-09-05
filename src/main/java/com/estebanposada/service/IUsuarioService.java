package com.estebanposada.service;

import java.util.List;

import com.estebanposada.model.Usuario;

public interface IUsuarioService extends IService<Usuario> {
	
	Usuario login(Usuario us);

	boolean verificar(String pass, Usuario us);

	List<Usuario> listarPorUsuario(String bus);

}
