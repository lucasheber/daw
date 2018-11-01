package lhos.daw.livraria.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lhos.daw.livraria.dao.LivroDAO;
import lhos.daw.livraria.model.Livro;

@Controller
public class ProdutosController {
	
	private LivroDAO livroDAO;
	
	public ProdutosController() {
		livroDAO = new LivroDAO();
	}
	
	@RequestMapping("/produtos")
	public String livros(Model model) {
		List<Livro> livros = livroDAO.listaTodos();
		model.addAttribute("livros", livros);		
		return "produtos";
	}
	
	@RequestMapping("/pesquisarTitulo")
	public String perquisarLivro(String titulo, Model model) {
		List<Livro> livros = livroDAO.pesquisarPorTitulo(titulo);
		model.addAttribute("livros", livros);		
		return "produtos";		
	}
	
	@RequestMapping("/pesquisarCategoria")
	public String livrosCategoria(String categoria, Model model) {
		List<Livro> livros = livroDAO.listaTodosPorCategoria(categoria);
		model.addAttribute("livros", livros);		
		return "produtos";
	}
	
	@RequestMapping("/cadastrarProduto")
	public String cadastrarProduto(Model model, HttpSession session, Livro livro) {
		
		
		if (livro.getId() != null)
			livroDAO.altera(livro);
		else 
			livroDAO.adiciona(livro);
		
		return "redirect:produtos";
	}
	
	@RequestMapping("/alterCart")
	public String alterCart(Long id, HttpSession session, Model model) {
		
		model.addAttribute("livro", livroDAO.pesquisa(id));
		return "manager";
	}
}
