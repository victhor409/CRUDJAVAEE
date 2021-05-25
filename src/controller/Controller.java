package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select","/update","/deletar"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	DAO dao = new DAO();
	JavaBeans usuario = new JavaBeans();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarUsuarios(request, response);
		}else if (action.equals("/update")) {
			editarUsuario(request,response);
		}
		else if (action.equals("/deletar")) {
			deletarUsuario(request,response);
		}
		else {
			response.sendRedirect("login.jsp");
		}
		
	}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// criando um objeto que ira receber da classe javabeans
		ArrayList<JavaBeans> lista = dao.listarUsuarios();
		// teste de recebimento da lista
		request.setAttribute("usuario", lista);
		RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
		rd.forward(request, response);
	}

	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setas as variaveis java beans

		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setTelefone(request.getParameter("telefone"));
		// invocar o metodo inserir passando o objeto contato
		dao.inserirUsuario(usuario);
		response.sendRedirect("main");

	}
	
	//editar
	protected void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");
		usuario.setId(Integer.parseInt(id));
		dao.selecionarUsuario(usuario);
		request.setAttribute("id", usuario.getId());
		request.setAttribute("nome", usuario.getNome());
		request.setAttribute("email", usuario.getEmail());
		request.setAttribute("senha", usuario.getSenha());
		request.setAttribute("telefone", usuario.getTelefone());
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request,response);
		
	}
	protected void editarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		usuario.setId(Integer.parseInt(request.getParameter("id")));
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setTelefone(request.getParameter("telefone"));
		
		dao.updateUsuario(usuario);
		
		response.sendRedirect("main");
		
	}
	
	protected void deletarUsuario(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String id = request.getParameter("id");
		usuario.setId(Integer.parseInt(id));
		dao.deleteUsuario(usuario);
		request.setAttribute("id", usuario.getId());
		response.sendRedirect("main");
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String login = request.getParameter("email");
		String password = request.getParameter("senha");
		
		usuario.setEmail(login);
		usuario.setSenha(password);
		
		try {
			if(dao.validate(usuario)) {
				response.sendRedirect("main");
			}else {
				HttpSession session = request.getSession();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
		
	}

