package lhos.daw.tarefas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {
	
	@RequestMapping("/olamundospring")
	public String execute() {
		return "ola-mundo";
	}
}
