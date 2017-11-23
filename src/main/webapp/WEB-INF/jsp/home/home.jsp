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
	   	<script src="<c:url value="/resource/js/bootstrap.min.js"/>"></script>
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
					<h2>
						Bem vindo, ${usuario}.
					</h2>
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