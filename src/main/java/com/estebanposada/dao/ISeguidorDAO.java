package com.estebanposada.dao;

import java.util.List;

import javax.ejb.Local;

import com.estebanposada.model.Persona;
import com.estebanposada.model.PublicadorSeguidor;

@Local
public interface ISeguidorDAO {
	
	Integer registrarPublicadoresSeguidores(List<PublicadorSeguidor> publicadores_seguidores);
	List<PublicadorSeguidor> listarSeguidores(Persona per);	
	Integer dejarSeguir(List<PublicadorSeguidor> publicadores_seguidores);	
	//List<ReporteSeguidor> listarSeguidores();
	List<PublicadorSeguidor> listarSeguidos(Persona per);
}
