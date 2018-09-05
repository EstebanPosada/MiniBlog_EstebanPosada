package com.estebanposada.service;

import java.util.List;

import com.estebanposada.model.Persona;
import com.estebanposada.model.PublicadorSeguidor;

public interface ISeguidorService {
	
	Integer registrarPublicadoresSeguidores(List<Persona> seguidores, List<Persona> publicadores);
	List<PublicadorSeguidor> listarSeguidores(Persona per);
	Integer dejarSeguir(List<Persona> seguidores, List<Persona> publicadores);
	//List<ReporteSeguidor> listarSeguidores();
	List<PublicadorSeguidor> listarSeguidos(Persona per);
}
