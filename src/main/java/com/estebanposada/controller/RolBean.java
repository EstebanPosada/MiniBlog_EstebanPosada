package com.estebanposada.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import com.estebanposada.model.Rol;
import com.estebanposada.service.IRolService;

@Named
@ViewScoped
public class RolBean implements Serializable {

	@Inject
	private IRolService service;
	private List<Rol> lista;
	private Rol rol;

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@PostConstruct
	public void init() {
		this.rol = new Rol();
		this.listar();
	}

	public void listar() {
		try {
			lista = service.listar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void operar(String accion) {
		try {
			if (accion.equalsIgnoreCase("R")) {
				this.service.registrar(this.rol);
			} else if (accion.equalsIgnoreCase("M")) {
				this.service.modificar(this.rol);
			}
			this.listar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			this.limpiarControles();
		}
	}

	public void onRowEdit(RowEditEvent event) {
		try {
			this.service.modificar((Rol) event.getObject());
			FacesMessage msg = new FacesMessage("Se modificó", ((Rol) event.getObject()).getTipo());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void verRol(Rol rol) {
		this.rol = rol;

	}

	private void limpiarControles() {
		this.rol = new Rol();
	}

	public List<Rol> getLista() {
		return lista;
	}

	public void setLista(List<Rol> lista) {
		this.lista = lista;
	}

}
