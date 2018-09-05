package com.estebanposada.dao;

import java.util.List;

import javax.ejb.Local;

import com.estebanposada.model.Usuario;

@Local
public interface IUsuarioDAO extends ICRUD<Usuario> {
	
	String traerPassHashed(String us);
	Usuario login(Usuario us);
	List<Usuario> listarPorUsuario(String bus);

}
