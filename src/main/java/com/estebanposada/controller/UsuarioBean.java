package com.estebanposada.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

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
	private String verificar;

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
			this.usuarios = (List<Usuario>) usuarioService.listarPorUsuario(this.buscar);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void verificar() {
		this.verificado = this.usuarioService.verificar(this.verificar, this.us);
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

	public String getVerificar() {
		return verificar;
	}

	public void setVerificar(String verificar) {
		this.verificar = verificar;
	}	

}
