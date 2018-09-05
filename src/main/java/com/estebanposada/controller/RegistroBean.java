package com.estebanposada.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;

import com.estebanposada.model.Persona;
import com.estebanposada.model.Rol;
import com.estebanposada.model.Usuario;
import com.estebanposada.service.IPersonaService;
import com.estebanposada.service.IRolService;

@Named
@ViewScoped
public class RegistroBean implements Serializable {
	
	@Inject
	private IPersonaService personaService;
	@Inject
	private IRolService rolservice; 
	private Persona persona;
	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		this.persona = new Persona();
		this.usuario = new Usuario();
	}
	
	@Transactional
	public String registrar() {
		String redireccion = null;
		try {
			String clave = this.usuario.getContrasena();
			String claveHash = BCrypt.hashpw(clave, BCrypt.gensalt());
			
			this.usuario.setContrasena(claveHash);
			this.usuario.setEstado("A");
			this.persona.setUs(this.usuario);
			this.usuario.setPersona(this.persona);
			
			this.personaService.registrar(this.persona);
			
			List<Rol> roles = new ArrayList<Rol>();
			int TIPO_USUARIO = 1;
			Rol r = new Rol();
			r.setId(TIPO_USUARIO);
			roles.add(r);
			rolservice.asignar(this.usuario, roles);
			
			redireccion = "index?faces-redirect=true";
			
		} catch (Exception e) {
			
		}
		return redireccion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
