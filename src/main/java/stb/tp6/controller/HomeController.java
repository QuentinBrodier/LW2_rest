package stb.tp6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	@ResponseBody
	public String home() {
		return "<p>Après avoir déployé le WAR, voici les URL accessibles : </p>"
				+ "<p>/resume : Obtenir la liste des STB</p>"
				+ "<p>/resume/{version} : Retourne une STB à la date du jour avec la version en paramètre dans l'URL</p>"
				+ "<p>/insert : Retourne une STB passée dans le Body de la requète HTTP POST !</p>";
	}
	
}
