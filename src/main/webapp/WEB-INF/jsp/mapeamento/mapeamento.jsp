<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Sistema de Mapeamento RDF</title>
		 <!-- Bootstrap -->
    	<link href="<c:url value="/resource/css/bootstrap.min.css"/>" rel="stylesheet">
    	<link href="<c:url value="/resource/css/style.css"/>" rel="stylesheet">
    	 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	    <!-- Include all compiled plugins (below), or include individual files as needed -->
	   	<script type="text/javascript" src="<c:url value="/resource/js/bootstrap.min.js"/>"></script>
 		<script type="text/javascript" src="<c:url value="/resource/js/functions.js"/>"></script>
 		<!-- Include necessary for jQueryUpload Plugin -->
 		<link href="http://hayageek.github.io/jQuery-Upload-File/4.0.10/uploadfile.css" rel="stylesheet">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
		<script src="http://hayageek.github.io/jQuery-Upload-File/4.0.10/jquery.uploadfile.min.js"></script>
 	
	   	<script type="text/javascript">

		   	$(document).ready(function(){
	   		    $("#tables").change(function(){
	   		  		table = $('#tables option:selected').val();
	   		   		$.get("${linkTo[MapeamentoController].getcolumns}",{table : table},function(jsVar) {
	   		   			insertInputRDFType();
	   		   			insertColTable(jsVar);	   		    			
	   		   		});
	   		   	});
   		    
   		    
	   		var max_fields      = 10; //maximum input boxes allowed
	   	    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
	   	    var add_button      = $(".add_field_button"); //Add button ID
	   	   
	   	    var x = 1; //initlal text box count
	   	    $(add_button).click(function(e){ //on add input button click
	   	        e.preventDefault();
	   	        if(x < max_fields){ //max input box allowed
	   	            x++; //text box increment
	   	            
	   	            var myhtml = "<div id='row-ns' class='row'>"+
									"<div class='col-xs-1'>"+
										"<label>Prefix</label>"+
										"<input type='tex' class='form-control' name='mapeamento.prefix_namespaces[]'>"+
									"</div>"+	
									"<div class='col-xs-5'>"+
										"<label>URL</label>"+
										"<input type='text' class='form-control' name='mapeamento.url_namespaces[]'>"+
									"</div>"+
									"<div class='col-xs-1' id='btn-del'>"+
										"<br><button id='bnt-rm-ns' class='btn btn-danger remove_field'><strong>x</string></button>"+
									"</div>"+
								"</div>";
						$(wrapper).append(myhtml); //add input box
	   	        }
	   	    });
	   	   
	   	    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
	   	        e.preventDefault(); $('#btn-del').parent('div').remove(); x--;
	   	    });
      	});
	   	</script>
	   	
	</head>
	<body>	
		<header class="container-fluid">
			<div class="row">
				<div class="col-xs-12 header"> 
					Sistema de Mapeamento RDF 
				</div>
			</div>
		</header>
		
		<div class="container-fluid">
			<div class="row">
				<nav class="col-xs-2">
						<ul>
							<li><a href="${linkTo[HomeController].home}"> Início </a></li>
							<li><a href="${linkTo[MapeamentoController].mapeamento}"> Mapear RDF </a></li>
							<li><a href="${linkTo[FilesController].files}">Listar Arquivos</a></li>
							<li><a href="${linkTo[LoginController].logoff}"> Sair </a></li>
						</ul>
				</nav>
				<main class="col-xs-10 col-xs-offset-2">
				<form method="post" action="${linkTo[MapeamentoController].map}" OnSubmit="return validaCheckbox();">
					<div class="row">
						<div class="col-xs-6 col-xs-offset-3">
							<h2>
								Mapeamento RDB para RDF
							</h2>
						</div>	
					</div>
						<div class="row">
							<div class="col-xs-6">
								<h4>Título do RDF</h4>
								<input type="text" class="form-control" name="mapeamento.rdfTitle" required value="" OnKeyUp="defineRDFTitle(this.value);">
							</div>
						</div>
														
						<div class="row">
							<div class="col-xs-6">
								<h4>Namespaces</h4>
							</div>
						</div>
							<div class="input_fields_wrap">
								<div class="row">
									<div class="col-xs-1">
										<label>Prefix</label>
										<input disabled type="text" class="form-control" name="mapeamento.prefix_namespaces[]" value="eg">
									</div>	
									<div class="col-xs-5">
										<label>URL</label>	
										<input id="ns-url" disabled type="text" class="form-control" name="mapeamento.url_namespaces[]" value="<c:url value="http://localhost:8080/resource/rdf/myrdf.rdf#"/>">
									</div>
								</div>
							</div>
						<div class="row">
							<div class="col-xs-1">
								<button id="bnt-add-ns" class="btn btn-primary add_field_button"> <strong>+</strong></button>
							</div>
						</div>
											
							<div class="row">
								<div class="col-xs-4">		
									<h4> Selecione a tabela:</h4>
									<select required class="form-control" id="tables" name="mapeamento.table" > 
				   						<option value="" disabled selected>Selecione a tabela</option>
				   							<c:forEach var="table" items="${tables}">
												<option value="${table}"> ${table} </option>
											</c:forEach>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-6">
									<div id="RDFType">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-8">		
										<div id="columns">
										</div>
								</div>
							</div>
						</form>					
				</main>	
			</div>
		</div>	
		<footer class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					Sistema de Mapeamento RDF
				</div>
			</div>
		</footer>	
				
	</body>
</html>