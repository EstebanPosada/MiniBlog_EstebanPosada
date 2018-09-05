package com.estebanposada.service;

import java.util.List;

import com.estebanposada.model.Persona;
import com.estebanposada.model.Publicacion;

public interface IPublicacionService extends IService<Publicacion> {

	List<Publicacion> listarPublicacionesPorPublicador(Persona publicador) throws Exception;
	List<Publicacion> listarPublicacionesDeSeguidores(Persona per);
}
