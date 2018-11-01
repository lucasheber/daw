package lhos.daw.livraria.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lhos.daw.livraria.dao.UsuarioDAO;
import lhos.daw.livraria.model.Usuario;

@Controller
public class LoginController {
	
	private UsuarioDAO usuarioDAO;
	
	public LoginController() {
		usuarioDAO = new UsuarioDAO();
	}
	
	@RequestMapping("/login")
	public String paginaLogin() {
		return "login";
	}
	
	@RequestMapping("/logar")
	public String logar(Usuario usuario, HttpSession session, Model model) {
		String url = "home";
		
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
		
		if (usuarioLogado == null) {
			usuarioLogado  = usuarioDAO.autenticarUsuario(usuario);
			
			if(usuarioLogado == null) {
				session.setAttribute("usuariologado", false);
				model.addAttribute("error_login", "Usuario ou senha incorretos.");
				
				return "redirect:login";
			}
			
			session.setAttribute("usuariologado", true);
			session.setAttribute("perfil", usuarioLogado.getPerfil());
			session.setAttribute("usuario", usuarioLogado);
		}
		
		if (usuarioLogado.getPerfil().equalsIgnoreCase("GERENTE"))
			url = "manager";
		
		return "redirect:" + url;
	}
	
	@RequestMapping("/logout")
	public String sair(Usuario usuario, HttpSession session) {
		session.invalidate();
		
		return "redirect:home";
	}
	
	@RequestMapping("/admin")
	public String paginaCadastrarUsuario(HttpSession session, Model model) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
		
		String url = "";
		
		if (usuarioLogado != null) {
			if (usuarioLogado.getPerfil().equalsIgnoreCase("ADMIN"))
				url = "admin";
			else 
				url = "redirect:produtos";
		} else 
			url = "redirect:login";
		
		return url;
	}
	
	@RequestMapping("/registerUser")
	public String criarUsuario(Usuario usuario, HttpSession session, Model model) {
		
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
		
		if(usuarioLogado == null) {
			usuarioDAO.adicionar(usuario);
			model.addAttribute("success_user", "Usuario cadastrado com sucesso!");
			
		}else {
			usuario.setId(usuarioLogado.getId());
			
			usuarioDAO.alterar(usuario);
			model.addAttribute("success_user", "Usuario alterado com sucesso!");
		}
		
		return "redirect:login";
	}
	
	@RequestMapping("/registerManager")
	public String registerManager(Usuario usuario, HttpSession session, Model model) {
		
		usuarioDAO.adicionar(usuario);
		model.addAttribute("success_user", "Usuario cadastrado com sucesso!");
			
		return "home";
	}
	
	@RequestMapping("/register")
	public String resgister(Model model) {
		
		return "register";
	}
	
	@RequestMapping("/alter")
	public String alter(Model model) {
		return "register";
	}
	
	@RequestMapping("/manager")
	public String manager(Model model, HttpSession session) {
		Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
		String url = "redirect:login";
		
		if (usuarioLogado == null) {
			model.addAttribute("error_login", "Voce deve logar para realizar a operacao!");
			
		} else {
			url = "manager";
		}
		
		return url;
	}
	
}
