package lhos.daw.livraria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String start() {
		return "index";
	}
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
}
