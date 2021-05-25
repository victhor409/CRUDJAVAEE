<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
    
    <%@ page import = "model.JavaBeans" %>
    <%@ page import = "model.DAO"  %>
    <%@ page import = "controller.Controller" %>
    
    
    <%Controller ct = new Controller(); %>
    <%JavaBeans usuario = new JavaBeans(); %>
    <%DAO dao = new DAO(); 
    Connection con = dao.conectar();
    %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>


<h1>Usuarios</h1>
	<div>
	<form action="main" method="POST">
	<a>Login</a>
	<input type="text" name="login" placeholder="login" class="Caixa1"><br>
	<a>Senha</a>
	<input type="password" name="password" placeholder="senha" class="Caixa1"><br>
	<%
	if(con != null){
		if((request.getParameter("login") != null)&& (request.getParameter("password") != null)){
			String login, senha;
			String read = "select email, senha from usuario";
			login = request.getParameter("login");
			senha = request.getParameter("senha");
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				response.sendRedirect("main");
			}
			
		}else{
			out.println("Não foi possivel logar");
		}
	}
	
	%>
	
	 <input type="submit" value="Submit" />
	</form>
	</div>

</body>
</html>