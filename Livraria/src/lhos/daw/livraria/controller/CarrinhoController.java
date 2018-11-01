package lhos.daw.livraria.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lhos.daw.livraria.dao.LivroDAO;
import lhos.daw.livraria.dao.PedidoDAO;
import lhos.daw.livraria.model.Item;
import lhos.daw.livraria.model.Livro;
import lhos.daw.livraria.model.Pedido;
import lhos.daw.livraria.model.Usuario;

@Controller
public class CarrinhoController {
	
	private LivroDAO livroDAO;
	
	public CarrinhoController() {
		livroDAO = new LivroDAO();
	}

	@RequestMapping("/carrinho")
	public String carrinho(HttpSession session, Model model) {
		@SuppressWarnings("rawtypes")
		Map carrinho = (Map) session.getAttribute("carrinho");
		
		if(carrinho == null || carrinho.isEmpty()) {
			model.addAttribute("error_message", "O carrinho esta vazio!");
			return "redirect:home";
		}
		
		return "carrinho";
	}
	
	@RequestMapping("/addCart")
	public String adicionar(Long id, HttpSession session, Model model) {
		@SuppressWarnings("unchecked")
		Map<Long, Item> carrinho = (Map<Long, Item>) session.getAttribute("carrinho");
		Float total = (Float) session.getAttribute("carrinho_total");
		
		Livro livro = livroDAO.pesquisa(id);
		if(carrinho == null) { 
			carrinho = new HashMap<>();
			total = 0f;
		}
	
		if(livro.getQuantidade() < 1) {
			model.addAttribute("error_message", "Não existe mais livros em estoque.");
			
			return "redirect:produtos";
		}
			
		if(!carrinho.containsKey(id)) {
			Item item = new Item();
			item.setLivro(livro);
			item.setQuant(1);
			item.setValor(livro.getValor());
			item.setValor_total(livro.getValor());
			
			carrinho.put(livro.getId(), item);
			session.setAttribute("carrinho", carrinho);
			
			total += item.getValor_total();
			session.setAttribute("carrinho_total", total);
		}
		
		return "redirect:carrinho";
	}
	
	@RequestMapping("/remCart")
	public String remover(Long id, HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<Long, Item> carrinho = (Map<Long, Item>) session.getAttribute("carrinho");
		Float total = (Float) session.getAttribute("carrinho_total");
				
		if(carrinho.containsKey(id)) {
			Item item = carrinho.remove(id);
			session.setAttribute("carrinho", carrinho);
			
			total -= item.getValor_total();
			session.setAttribute("carrinho_total", total);
		}
		
		return "redirect:carrinho";
	}
	
	@RequestMapping("/finCart")
	public String finalizar(HttpSession session, Model model){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		@SuppressWarnings("unchecked")
		Map<Long, Item> carrinho = (Map<Long, Item>) session.getAttribute("carrinho");
		
		if(usuario == null) {
			session.setAttribute("msg_login", "Você deve logar para finalizar a compra.");
			return "redirect:login";
		}
		
		Pedido pedido = new Pedido();
		pedido.setId_usuario(usuario.getId());
		pedido.getItens().addAll(carrinho.values());
		
		// Verifica se tem livros disponiveis.
		for(Item item : pedido.getItens()) {
			int quantDisponivel = livroDAO.pesquisa(item.getLivro().getId()).getQuantidade();
			if(quantDisponivel < item.getQuant()) {
				System.out.println("Existem apenas %d livro(s) '%s' em estoque.");
				model.addAttribute("msg", String.format("Existem apenas %d livro(s) '%s' em estoque.",
										  quantDisponivel,
										  item.getLivro().getTitulo()));
				return "carrinho";
			}
		}
		
		PedidoDAO pedidoDAO = new PedidoDAO();
		pedidoDAO.adiciona(pedido);
		
		session.setAttribute("carrinho", null);
		session.setAttribute("carrinho_total", null);
		
		return "redirect:produtos";
	}
	
	@RequestMapping("/pushPrdCart")
	public String aumentarLivro(Long id, HttpSession session, Model model) {
		@SuppressWarnings("unchecked")
		Map<Long, Item> carrinho = (Map<Long, Item>) session.getAttribute("carrinho");
		Float total = (Float) session.getAttribute("carrinho_total");
		Item item = carrinho.get(id);
		
		int quantDisponivel = livroDAO.pesquisa(item.getLivro().getId()).getQuantidade();
		if(quantDisponivel == item.getQuant()) {
			System.out.println("Não existe mais livros em estoque.");
			model.addAttribute("msg", String.format("Não existe mais livros em estoque."));
			return "carrinho";
		}
		
		item.setQuant(item.getQuant()+1);
		item.setValor_total(item.getValor()*item.getQuant());
		
		carrinho.replace(id, item);
		session.setAttribute("carrinho", carrinho);
		
		total += item.getValor();
		session.setAttribute("carrinho_total", total);
		
		return "carrinho";
	}
	
	@RequestMapping("/popProdCart")
	public String diminuirLivro(Long id, HttpSession session) {
		@SuppressWarnings("unchecked")
		Map<Long, Item> carrinho = (Map<Long, Item>) session.getAttribute("carrinho");
		Float total = (Float) session.getAttribute("carrinho_total");
		
		Item item = carrinho.get(id);
		
		if(item.getQuant() == 1)
			return "carrinho";
		
		item.setQuant(item.getQuant()-1);
		item.setValor_total(item.getValor()*item.getQuant());
		
		carrinho.replace(id, item);
		session.setAttribute("carrinho", carrinho);
		
		total -= item.getValor();
		session.setAttribute("carrinho_total", total);
		
		return "carrinho";
	}
}
