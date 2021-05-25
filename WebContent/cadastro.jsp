<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="model.JavaBeans" %>
    <%@ page import = "java.util.ArrayList" %>
    <%
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>)
    request.getAttribute("usuario");
   
    
    %>
<!DOCTYPE html>
<html lang = "pt-br">
<head>
<meta charset="UTF-8">
<title>Cadastro de Usuario</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	
	<h1>Cadastro de Usuatios</h1>
	<a href = "novo.html" class="Botao1">Novo Contato</a>
	<table class="tabela">
	<thead>
	<tr>
	<th>Id</th>
	<th>Nome</th>
	<th>email</th>
	<th>Telefone</th>
	<th>Opções</th>
		
	
	</thead>
	<tbody>
	<%for(int i=0; i<lista.size(); i++){%>
	<tr>
	<td><%=lista.get(i).getId() %></td>
	<td><%=lista.get(i).getNome() %></td>
	<td><%=lista.get(i).getEmail() %></td>
	<td><%=lista.get(i).getTelefone() %></td>
	<th><a href="select?id=<%=lista.get(i).getId() %>" class="Botao1">Editar</a></th>
	<th><a href="deletar?id=<%=lista.get(i).getId() %>" class="Botao1">Deletar</a></th>
	
	</tr>
	
	
	
	<%} %>
	</tbody>
	</table>
</body>
</html>