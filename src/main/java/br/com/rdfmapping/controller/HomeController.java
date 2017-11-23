package br.com.rdfmapping.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;


@Controller
public class HomeController {

	private final Result result;
	private final HttpServletRequest request;

	/**
	 * @deprecated CDI eyes only
	 */
	protected HomeController() {
		this(null, null);
	}
		
	@Inject
	public HomeController(Result result, HttpServletRequest request) {
		this.result = result;
		this.request = request;
	}

	@Path("/home")
	public void home() {
		
		if (request.getAttribute("usuario")==null){
			result.redirectTo("/login");
			result.include("teste","teste");
		}else{
			result.include("usuario","teste");
		}
	}
	
	
}