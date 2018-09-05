package com.estebanposada.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.estebanposada.model.Persona;
import com.estebanposada.service.IPersonaService;

@Named
@ViewScoped
public class PersonaBean implements Serializable {
	
	@Inject
	private IPersonaService personaService;
	private Persona persona;
	private List<Persona> lista;
	private String tipoDialog;
	private UploadedFile foto;
	
	@PostConstruct
	public void init() {
		this.persona = new Persona();
		this.lista = new ArrayList<Persona>();
		this.listar();
	}
	
	public void listar() {
		try {
			this.lista = this.personaService.listar();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void operar(String accion) {
		try {
			
			if(foto != null) {
				this.persona.setFoto(this.foto.getContents());	
			}
			
			if (accion.equalsIgnoreCase("R")) {
				this.personaService.registrar(this.persona);
			} else if (accion.equalsIgnoreCase("M")) {
				this.personaService.modificar(this.persona);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			this.limpiarControles();
		}
	}
	
	public void mostrarData(Persona p) {
		this.persona = p;
		this.tipoDialog = "Modificar";
	}
	
	public void limpiarControles() {
		this.persona = new Persona();
		this.tipoDialog = "Nuevo";
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Persona> getLista() {
		return lista;
	}

	public void setLista(List<Persona> lista) {
		this.lista = lista;
	}

	public String getTipoDialog() {
		return tipoDialog;
	}

	public void setTipoDialog(String tipoDialog) {
		this.tipoDialog = tipoDialog;
	}

	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile foto) {
		this.foto = foto;
	}

}
