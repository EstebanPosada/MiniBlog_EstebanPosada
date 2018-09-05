package com.estebanposada.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.estebanposada.model.Persona;
import com.estebanposada.model.Usuario;
import com.estebanposada.service.IUsuarioService;

@Named
@ViewScoped
public class UsuarioBean implements Serializable {
	
	@Inject
	private IUsuarioService usuarioService;
	private Usuario us;
	private List<Usuario> usuarios;
	private boolean verificado;
	private String buscar;
	private String oldPass;
	private String nuevoPass;

	public String getNuevoPass() {
		return nuevoPass;
	}

	public void setNuevoPass(String nuevoPass) {
		this.nuevoPass = nuevoPass;
	}

	@PostConstruct
	public void init() {
		this.us = new Usuario();
		this.usuarios = new ArrayList<Usuario>();
		this.listar();
		this.verificado = false;
	}
	
	public void listar() {
		try {
			this.usuarios = this.usuarioService.listar();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void mostrarData(Usuario us) {
		this.us = us;
	}
	
	public void busqueda() {
		try {
			this.usuarios = (List<Usuario>) this.usuarioService.listarPorUsuario(this.buscar);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void limpiarControles() {
		//this.us = new Usuario();
	}
	
	public void verificar() {
		this.verificado = this.usuarioService.verificar(this.oldPass, this.us);
	}
	
	public void modificar() {
		try {
			this.usuarioService.modificar(this.us);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Usuario getUs() {
		return us;
	}

	public void setUs(Usuario us) {
		this.us = us;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
	}

	public boolean isVerificado() {
		return verificado;
	}

	public void setVerificado(boolean verificado) {
		this.verificado = verificado;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}	
	
	

}
