package br.com.rdfmapping.controller;

import javax.inject.Inject;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.rdfmapping.model.Usuario;
import br.com.rdfmapping.persistence.UsuarioDAO;
import br.com.rdfmapping.persistence.UsuarioLogado;


@Controller
public class LoginController {

	private final Result result;
	private final UsuarioLogado usuarioLogado;
	
	/**
	 * @deprecated CDI eyes only
	 */
	protected LoginController() {
		this(null, null);
	}

	@Inject
	public LoginController(Result result, UsuarioLogado usuarioLogado) {
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}
		
	@Path("/login")
	public void login(){
			if (usuarioLogado.isLogado()){
			Usuario us = usuarioLogado.getUsuario();	
			result.include("usuario",us.getNome());
			result.redirectTo("/home");
		}
	}
	
	@Path("/logoff")
	public void logoff(){
		usuarioLogado.desloga();
		result.redirectTo("/login");
	}
	
	@Path("/authentication")
	public void authentication(Usuario usuario){
		if(UsuarioDAO.Login(usuario)){
			usuarioLogado.fazLogin(usuario);
			result.include("usuario",usuario.getNome());
			result.redirectTo("/home");
		}else{
			result.redirectTo("/login");
		}
	}
}