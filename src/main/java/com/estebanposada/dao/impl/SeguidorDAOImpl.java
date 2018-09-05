package com.estebanposada.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.estebanposada.dao.ISeguidorDAO;
import com.estebanposada.model.Persona;
import com.estebanposada.model.PublicadorSeguidor;

@Stateless
public class SeguidorDAOImpl implements ISeguidorDAO, Serializable {

	@PersistenceContext(unitName = "blogPU")
	private EntityManager em;

	@Override
	public Integer registrarPublicadoresSeguidores(List<PublicadorSeguidor> publicadores_seguidores) {
		try {
			int[] iarr = { 0 };
			publicadores_seguidores.forEach(ps -> {
				em.persist(ps);
				if (iarr[0] % 100 == 0) {
					em.flush();
					em.clear();
				}
				iarr[0]++;
			});
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	@Override
	public List<PublicadorSeguidor> listarSeguidores(Persona per) {
		List<PublicadorSeguidor> lista = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM PublicadorSeguidor p where p.publicador.idPersona =?1");
			query.setParameter(1, per.getIdPersona());

			lista = (List<PublicadorSeguidor>) query.getResultList();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	@Override
	public List<PublicadorSeguidor> listarSeguidos(Persona per) {
		List<PublicadorSeguidor> lista = new ArrayList<>();
		try {
			Query query = em.createQuery("FROM PublicadorSeguidor p where p.seguidor.idPersona =?1");
			query.setParameter(1, per.getIdPersona());

			lista = (List<PublicadorSeguidor>) query.getResultList();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	@Override
	public Integer dejarSeguir(List<PublicadorSeguidor> publicadores_seguidores) {
		int rpta = 0;
		try {

			publicadores_seguidores.forEach(ps -> {
				Query query = em.createQuery(
						"DELETE FROM PublicadorSeguidor WHERE publicador.idPersona =?1 AND seguidor.idPersona = ?2");
				query.setParameter(1, ps.getPublicador().getIdPersona());
				query.setParameter(2, ps.getSeguidor().getIdPersona());

				query.executeUpdate();
			});
			rpta = 1;

		} catch (Exception e) {
			rpta = 0;
		}
		return rpta;
	}

	//https://www.thoughts-on-java.org/jpa-native-queries/
	/*@Override
	public List<ReporteSeguidor> listarSeguidores() {
		List<ReporteSeguidor> lista = new ArrayList<>();
		try {
			Query query = em.createNativeQuery("SELECT * FROM fn_listarSeguidores()");
			// query.setParameter(1, per.getIdPersona());

			List<Object[]> data = query.getResultList();
			
			0) [1, Jaime]
			  1) [2, Jaime]
			  3) [3, Kaise]

			data.forEach(x -> {
				int cantidad = Integer.parseInt(String.valueOf(x[0]));
				String publicador = String.valueOf(x[1]);
				lista.add(new ReporteSeguidor(cantidad, publicador));
			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}*/

}
