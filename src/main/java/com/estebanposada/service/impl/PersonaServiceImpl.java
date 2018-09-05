package com.estebanposada.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.estebanposada.dao.IPersonaDAO;
import com.estebanposada.model.Persona;
import com.estebanposada.service.IPersonaService;

@Named
public class PersonaServiceImpl  implements IPersonaService, Serializable{
	
	@EJB
	private IPersonaDAO dao;
		
	@Override
	public Integer registrar(Persona per) throws Exception {
		int rpta = dao.registrar(per);
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public Integer modificar(Persona per) throws Exception {
		int rpta = dao.modificar(per);
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public List<Persona> listar() throws Exception {
		return dao.listar();
	}

	@Override
	public Persona listarPorId(Persona t) throws Exception {
		return dao.listarPorId(t);
	}


}
