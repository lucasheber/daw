package lhos.daw.livraria.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lhos.daw.livraria.dao.PedidoDAO;
import lhos.daw.livraria.model.Usuario;

@Controller
public class PedidosController {
	private PedidoDAO pedidoDAO;
	
	public PedidosController() {
		pedidoDAO = new PedidoDAO();
	}
	
	@RequestMapping("/pedidos")
	public String meusPedidos(HttpSession session, Model model) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (usuario == null || usuario.getId() == null)
			return "redirect:login";
			
		model.addAttribute("pedidos", pedidoDAO.listaTodosDoUsuario(usuario.getId()));
		
		return "pedidos";
	}

}
