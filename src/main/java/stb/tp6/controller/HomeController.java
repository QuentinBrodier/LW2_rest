package stb.tp6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import stb.tp6.config.DAO_LW2;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	@ResponseBody
	public String home() {
		DAO_LW2 db = new DAO_LW2();
		int nbStbs = db.countSTB();
		return "<p>Quentin Brodier & Matthieu Coulon :</p>"
				+ "<p>/resume : Obtenir la liste des STB (versions allégées)</p>"
				+ "<p>/resume/{id} : Retourne une STB correspond à l'id (version détaillée)</p>"
				+ "<p>/depot : Insert une STB à l'aide de la requète HTTP POST !</p>"
				+ "<p>Il y a actuellement "+nbStbs+" STB stockée(s)</p>";
	}
	
}
