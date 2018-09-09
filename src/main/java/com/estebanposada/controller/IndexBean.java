package com.estebanposada.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.estebanposada.model.Usuario;
import com.estebanposada.service.IUsuarioService;


@Named
@ViewScoped
public class IndexBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private IUsuarioService service;
	private Usuario us;

	@PostConstruct
	public void init() {
		this.us = new Usuario();
	}

	public String login() {
		String redireccion = null;
		try {
			Usuario us = this.service.login(this.us);

			if (us != null && us.getEstado().equalsIgnoreCase("A")) {
				// Almacenar en la sesión de JSF
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
				//redireccion = "/protegido/roles?faces-redirect=true";
				redireccion = "/protegido/usuarios?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales incorrectas"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redireccion;
	}

	public Usuario getUs() {
		return us;
	}

	public void setUs(Usuario us) {
		this.us = us;
	}

}