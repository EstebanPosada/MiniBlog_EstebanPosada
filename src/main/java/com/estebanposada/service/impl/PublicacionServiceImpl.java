package com.estebanposada.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.estebanposada.dao.IPublicacionDAO;
import com.estebanposada.model.Mencion;
import com.estebanposada.model.Persona;
import com.estebanposada.model.Publicacion;
import com.estebanposada.model.Tag;
import com.estebanposada.service.IPublicacionService;

@Named
public class PublicacionServiceImpl implements Serializable, IPublicacionService {

	@EJB
	private IPublicacionDAO dao;

	@Override
	public Integer registrar(Publicacion t) throws Exception {

		List<Tag> tags = new ArrayList<>();
		List<Mencion> menciones = new ArrayList<>();

		String texto = t.getCuerpo();
		texto = texto.replaceAll(",", " ");  
		String[] arreglo = texto.split(" ");
		for (String x : arreglo) {
			if (x.startsWith("@")) { 
				x = x.substring(1, x.length());
				menciones.add(new Mencion(t, x));
			}

			if (x.startsWith("#")) {
				x = x.substring(1, x.length());
				tags.add(new Tag(t, x));
			}
		}
		t.setTags(tags);		
		t.setMenciones(menciones);
		int rpta = dao.registrar(t);		
		return rpta > 0 ? 1 : 0;		
	}

	@Override
	public Integer modificar(Publicacion t) throws Exception {
		int rpta = dao.modificar(t);
		return rpta > 0 ? 1 : 0;
	}

	@Override
	public List<Publicacion> listar() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publicacion listarPorId(Publicacion t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publicacion> listarPublicacionesPorPublicador(Persona publicador) throws Exception {
		return dao.listarPublicacionesPorPublicador(publicador);
	}

	@Override
	public List<Publicacion> listarPublicacionesDeSeguidores(Persona per) {		
		return dao.listarPublicacionesDeSeguidores(per);
	}

}
