package br.ufc.quixada.npi.we.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.bonitasoft.engine.bpm.BonitaObject;

/**
 * Entidade Bonita User {@link BonitaObject}
 * 
 * @author Thiago Pereira Rosa - thiagor@engineer.com
 */
@Entity
public class BonitaUser {

	@Id
	private Integer id;

	@Size(min = 3, max = 15)
	private String usuario;

	@Size(min = 3, max = 15)
	private String senha;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString(){
		return "id: " + this.getId() + ", usuario: " + this.getUsuario() + ", senha: " + this.getSenha();
	}
}
