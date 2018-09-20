package lhos.daw.tarefas.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import lhos.daw.tarefas.dao.TarefaDAO;
import lhos.daw.tarefas.model.Tarefa;

@Controller
public class TarefasController {
	
	private TarefaDAO daoTarefa;
	
	public TarefasController() {
		daoTarefa = new TarefaDAO();
	}
	
	@RequestMapping("/nova-tarefa")
	public String formulario() {
		
		return "/tarefas/formulario";
	}
	
	@RequestMapping("/adiciona-tarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult bindingResult) {
		
		if(bindingResult.hasFieldErrors("descricao"))
			return "/tarefas/formulario";
		
		daoTarefa.insert(tarefa);
		
		return "/tarefas/adicionada";
	}
	
	@RequestMapping("/lista")
	public String listaTarefas(Model modelo) {
		
		modelo.addAttribute("tarefas", daoTarefa.taskList());
		
		return "/tarefas/lista";
	}
	
	@RequestMapping("/finalizar")
	public String finalizar(Tarefa tarefa) {
		
		return "redirect:lista";
	}
	
	@RequestMapping("/alterar-tarefa")
	public String alterar(Tarefa tarefa) {
		
		return "redirect:lista";
	}
	
	@RequestMapping("/mostrar-tarefa")
	public String mostraTarefa(Long id, Model model) {
		
		model.addAttribute("tarefa", daoTarefa.retrive(id));
		
		return "tarefas/mostra";
	}
}
