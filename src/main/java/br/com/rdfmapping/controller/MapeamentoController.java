package br.com.rdfmapping.controller;

import javax.inject.Inject;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.rdfmapping.jena.RDFConstructor;
import br.com.rdfmapping.model.Mapeamento;
import br.com.rdfmapping.persistence.DBDAO;
import br.com.rdfmapping.persistence.UsuarioLogado;


@Controller
public class MapeamentoController {

	private final Result result;
	private final UsuarioLogado usuarioLogado;
	
	/**
	 * @deprecated CDI eyes only
	 */
	protected MapeamentoController() {
		this(null, null);
	}
		
	@Inject
	public MapeamentoController(Result result, UsuarioLogado usuarioLogado) {
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}

	@Path("/mapeamento")
	public void mapeamento() {
		if (usuarioLogado.isLogado()){
			result.include("tables", DBDAO.GetTables());
		}else{
			result.redirectTo("/login");
		}
	}
	
	@Get("/mapeamento/getcolumns/")	
	public void getcolumns(String table){
		result.include("ColumnsName", DBDAO.GetColumns(table));
	}
	
	@Post("/mapeamento/map")
	public void map(Mapeamento mapeamento){
		RDFConstructor.rdfBuild(mapeamento);
		result.redirectTo("/files");
	}
	
}