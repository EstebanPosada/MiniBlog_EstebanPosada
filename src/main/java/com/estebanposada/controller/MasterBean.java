package com.estebanposada.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.estebanposada.model.Usuario;
import com.estebanposada.model.UsuarioRol;
import com.estebanposada.service.IRolService;

@Named
@ViewScoped
public class MasterBean implements Serializable {

	@Inject
	private IRolService service;

	public void verificarSesion() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

			if (us == null) {
				context.getExternalContext().redirect("./../index.xhtml");
			} else {
				// Verificacion de roles
				String viewId = context.getViewRoot().getViewId();
				boolean rpta = this.verificarMenu(viewId);
				if(!rpta) {
					context.getExternalContext().redirect("./../403.xhtml");
				}
			}

		} catch (Exception e) {

		}
	}

	public boolean verificarMenu(String viewId) {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

		List<UsuarioRol> roles = service.listarRolesPorUsuario(us);
		
		String rol = "";
		switch (viewId) {
		case "/protegido/principal.xhtml":
			rol = "ADMIN,USER";
			break;
		case "/protegido/asignar.xhtml":
			rol = "ADMIN";
			break;
		case "/protegido/personas.xhtml":
			rol = "ADMIN,USER";
			break;
		case "/protegido/publicaciones.xhtml":
			rol = "ADMIN,USER";
			break;
		case "/protegido/roles.xhtml":
			rol = "ADMIN";
			break;
		case "/protegido/seguidores.xhtml":
			rol = "ADMIN";
			break;
		case "/protegido/me_sigue.xhtml":
			rol = "ADMIN,USER";
			break;
		case "/protegido/seguir.xhtml":
			rol = "ADMIN,USER";
			break;
		case "/protegido/usuarios.xhtml":
			rol = "ADMIN";
			break;
		case "/protegido/rep_seguidores.xhtml":
			rol = "ADMIN,USER";
			break;
		case "/protegido/final.xhtml":
			rol = "ADMIN,USER";
			break;
		default:
			break;
		}
		String arreglo[] = rol.split(",");
		int[] iarr = { 0 };
		roles.forEach(r -> {
			for (String x : arreglo) {
				if (r.getRol().getTipo().equals(x)) {
					iarr[0]++;
				}
			}
		});
		if (iarr[0] == 0) {
			return false;
		}
		return true;
	}
	
	public void cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

}
