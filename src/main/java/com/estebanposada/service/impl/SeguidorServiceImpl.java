package com.estebanposada.service.impl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.estebanposada.dao.ISeguidorDAO;
import com.estebanposada.model.Persona;
import com.estebanposada.model.PublicadorSeguidor;
import com.estebanposada.service.ISeguidorService;

@Named
public class SeguidorServiceImpl implements Serializable, ISeguidorService {

	@EJB
	private ISeguidorDAO dao;

	@Override
	public Integer registrarPublicadoresSeguidores(List<Persona> seguidores, List<Persona> publicadores) {
		List<PublicadorSeguidor> publicadores_seguidores = new ArrayList<PublicadorSeguidor>();
		try {
			publicadores.forEach(p -> {
				seguidores.forEach(s -> {
					PublicadorSeguidor ps = new PublicadorSeguidor();
					ps.setPublicador(p);
					ps.setSeguidor(s);
					ps.setFecha(LocalDateTime.now());
					publicadores_seguidores.add(ps);
				});
			});

			dao.registrarPublicadoresSeguidores(publicadores_seguidores);
		} catch (Exception e) {
			return 0;
		}

		return 1;
	}

	@Override
	public List<PublicadorSeguidor> listarSeguidores(Persona per) {
		return dao.listarSeguidores(per);
	}

	@Override
	public Integer dejarSeguir(List<Persona> seguidores, List<Persona> publicadores) {
		List<PublicadorSeguidor> publicadores_seguidores = new ArrayList<PublicadorSeguidor>();
		try {
			publicadores.forEach(p -> {
				seguidores.forEach(s -> {
					PublicadorSeguidor ps = new PublicadorSeguidor();
					ps.setPublicador(p);
					ps.setSeguidor(s);
					ps.setFecha(LocalDateTime.now());
					publicadores_seguidores.add(ps);
				});
			});

			dao.dejarSeguir(publicadores_seguidores);
		} catch (Exception e) {
			return 0;
		}

		return 1;
	}

	/*@Override
	public List<ReporteSeguidor> listarSeguidores() {
		return dao.listarSeguidores();
	}*/

	@Override
	public List<PublicadorSeguidor> listarSeguidos(Persona per) {
		return dao.listarSeguidos(per);
	}

}
