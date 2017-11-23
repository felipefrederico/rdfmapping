package br.com.rdfmapping.controller;

import javax.inject.Inject;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.rdfmapping.model.FilesModeling;
import br.com.rdfmapping.persistence.UsuarioLogado;

@Controller
public class FilesController {
	private final Result result;
	private final UsuarioLogado usuarioLogado;
	
	protected FilesController() {
		this(null, null);
	}
		
	@Inject
	public FilesController(Result result, UsuarioLogado usuarioLogado) {
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}
		
	@Path("/files")
	public void files() {
		if(!usuarioLogado.isLogado()){
			result.redirectTo("/login");
		}else{
   	    	result.include("file_rdf_names", FilesModeling.getFiles("rdf"));
   	    	result.include("file_ttl_names", FilesModeling.getFiles("ttl"));
	    }
	}
}
