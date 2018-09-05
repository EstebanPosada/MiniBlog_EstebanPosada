package com.estebanposada.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import com.estebanposada.model.Persona;
import com.estebanposada.model.Rol;
import com.estebanposada.model.Usuario;
import com.estebanposada.service.IPersonaService;
import com.estebanposada.service.IRolService;

@Named
@ViewScoped
public class AsignarBean implements Serializable {
	
	@Inject
	private IRolService rolService;
	@Inject
	private IPersonaService personaService;
	
	private Persona persona;
	private DualListModel<Rol> dual;
	private List<Persona> personas;
	
	@PostConstruct
	public void init() {
		this.persona = new Persona();
		this.personas = new ArrayList<Persona>();
		this.listarRoles();
		this.listarPersonas();
	}
	
	public void listarPersonas() {
		try {
			this.personas = this.personaService.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void listarRoles() {
		List<Rol> roles;
		try {
			roles = rolService.listar();
			this.dual = new DualListModel<>(roles, new ArrayList<Rol>());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void asignar() {
		try {
			Usuario us = new Usuario();
			us.setId(this.persona.getIdPersona());
			us.setPersona(this.persona);
			rolService.asignar(us, this.dual.getTarget());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public DualListModel<Rol> getDual() {
		return dual;
	}

	public void setDual(DualListModel<Rol> dual) {
		this.dual = dual;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
	

}
