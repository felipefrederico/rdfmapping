<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logar-se no Sistema de Mapeamento RDF</title>
</head>
<body>
	<h2>Efetue login para continuar</h2>
	<form action="${linkTo[LoginController].authentication}" method="POST" name="login-form">
		<input type="text" name="usuario.nome" placeholder="insira o nome do usuário"> <br>
		<input type="password" name="usuario.senha" placeholder="Insira a senha do usuário"> <br>
		<input type="submit" value="Entrar">
	</form>
	${teste}		
</html>