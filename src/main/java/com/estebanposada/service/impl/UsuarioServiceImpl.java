package com.estebanposada.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import org.mindrot.jbcrypt.BCrypt;

import com.estebanposada.dao.IUsuarioDAO;
import com.estebanposada.model.Usuario;
import com.estebanposada.service.IUsuarioService;

public class UsuarioServiceImpl implements Serializable, IUsuarioService {

	@EJB
	private IUsuarioDAO dao;
	
	@Override
	public Integer registrar(Usuario t) throws Exception {
		int rpta = dao.registrar(t);
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public Integer modificar(Usuario t) throws Exception {
		int rpta = dao.modificar(t);
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public List<Usuario> listar() throws Exception {
		// TODO Auto-generated method stub
		return dao.listar();
	}

	@Override
	public Usuario listarPorId(Usuario t) throws Exception {
		// TODO Auto-generated method stub
		return dao.listarPorId(t);
	}
	

	@Override
	public List<Usuario> listarPorUsuario(String bus) {
		// TODO Auto-generated method stub
		return dao.listarPorUsuario(bus);
	}

	@Override
	public Usuario login(Usuario us) {
		String clave = us.getContrasena();
		
		String claveHash = dao.traerPassHashed(us.getUsuario());
		if(BCrypt.checkpw(clave, claveHash)) {
			us.setContrasena(claveHash);
			return dao.login(us);
		}
		return new Usuario();
	}

	@Override
	public boolean verificar(String pass, Usuario us) {
		//String myClaveHash = BCrypt.hashpw(pass, BCrypt.gensalt());
		if(BCrypt.checkpw(pass, us.getContrasena())) {
		return true;
		}
		return false;
	}


}
