<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Usuario</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>
	<h1>Criar novo contato</h1>
	<form name="frmContato" action="update">
	<table>
	<tr>
	<td><input type="text" name="id" placeholder="Id" id="caixa3" readonly value="<%out.println(request.getAttribute("id"));%>"></td>
	<tr>
	<td><input type="text" name="nome" placeholder="Nome" class="Caixa1" value="<% out.println(request.getAttribute("nome"));%>"></td>
	<tr>
	<td><input type="text" name="email" placeholder="Mail" class="Caixa1" value="<% out.println(request.getAttribute("email"));%>"></td>
	<tr>
	<td><input type="password" name="senha" placeholder="senha" class="Caixa1" value="<% out.println(request.getAttribute("senha"));%>"></td>
	<tr>
	<td><input type="text" name="telefone" placeholder="telefone" class="Caixa2" value="<% out.println(request.getAttribute("telefone"));%>"></td>
	
	
	</table>
	<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>