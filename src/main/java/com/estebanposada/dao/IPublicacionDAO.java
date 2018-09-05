package com.estebanposada.dao;

import java.util.List;

import com.estebanposada.model.Persona;
import com.estebanposada.model.Publicacion;

public interface IPublicacionDAO extends ICRUD<Publicacion> {

	List<Publicacion> listarPublicacionesPorPublicador(Persona publicador) throws Exception;
	List<Publicacion> listarPublicacionesDeSeguidores(Persona per);
	
}
