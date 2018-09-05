package com.estebanposada.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.estebanposada.model.Persona;
import com.estebanposada.model.Publicacion;
import com.estebanposada.model.Usuario;
import com.estebanposada.service.IPublicacionService;

@Named
@ViewScoped
public class PublicacionBean implements Serializable{

	@Inject
	private IPublicacionService service;
	private List<Publicacion> publicaciones;
	private Publicacion publicacion;
	private Usuario us;
	
	@PostConstruct
	public void init() {
		this.publicacion = new Publicacion();

		FacesContext context = FacesContext.getCurrentInstance();
		this.us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

		this.listarPublicaciones();		
	}
	
	public void publicar() {
		try {

			Persona p = new Persona();
			p.setIdPersona(us.getPersona().getIdPersona());
			this.publicacion.setPublicador(p);
			int rpta = this.service.registrar(this.publicacion);
			if (rpta == 1) {
				//mensajeManager.mostrarMensaje("AVISO", "Se publicó", "INFO");
			} else {
				//mensajeManager.mostrarMensaje("AVISO", "No se publicó", "WARN");
			}
			//push.sendMessage();		
		} catch (Exception e) {
			//mensajeManager.mostrarMensaje("AVISO", "Error al publicar", "ERROR");
			System.out.println(e.getMessage());
		} finally {
			this.publicacion = new Publicacion();
			this.listarPublicaciones();			
		}

	}
	
	public void listarPublicaciones() {
		try {
			//this.publicaciones = this.service.listarPublicacionesPorPublicador(this.us.getPersona());
		} catch (Exception e) {
			//mensajeManager.mostrarMensaje("AVISO", "Error al publicar", "ERROR");
		}
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}
	
	
}
