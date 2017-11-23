package br.com.rdfmapping.controller;

import javax.inject.Inject;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.rdfmapping.model.Usuario;
import br.com.rdfmapping.persistence.UsuarioLogado;


@Controller
public class IndexController {

	private final Result result;
	private final UsuarioLogado usuarioLogado;
	/**
	 * @deprecated CDI eyes only
	 */
	protected IndexController() {
		this(null, null);
	}
		
	@Inject
	public IndexController(Result result, UsuarioLogado usuarioLogado) {
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}

	@Path("/")
	public void index()  {
		if (usuarioLogado.isLogado()){
			Usuario us = usuarioLogado.getUsuario();	
			result.include("usuario",us.getNome());
			result.redirectTo("/home");
		}else{
			result.redirectTo("/login");
		}
	}
	
	
	
}