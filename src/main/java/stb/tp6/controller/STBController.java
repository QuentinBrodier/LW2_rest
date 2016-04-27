package stb.tp6.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import stb.tp6.model.STB;
import stb.tp6.model.STBList;
import stb.tp6.model.Client;
import stb.tp6.model.Equipe;
import stb.tp6.model.ExigenceFonctionnelle;
import stb.tp6.model.Fonctionnalite;

@RestController
public class STBController {

	private STBList stbList = new STBList();
	
	public STBController(){
		
		Client c1 = new Client("Entité 1","Brodier Quentin",76100);
	
		Equipe e1 = new Equipe("Jean","Jaques",true);
		ArrayList<Equipe> alE = new ArrayList<Equipe>();
		alE.add(e1);
		
		ArrayList<Fonctionnalite> alF = new ArrayList<Fonctionnalite>();
		ArrayList<ExigenceFonctionnelle> alEF = new ArrayList<ExigenceFonctionnelle>();
		ExigenceFonctionnelle ee1 = new ExigenceFonctionnelle(1, "Créer un sujet", 1);
		ExigenceFonctionnelle ee2 = new ExigenceFonctionnelle(2, "Répondre à un sujet", 1);
		alEF.add(ee1);
		alEF.add(ee2);
		Fonctionnalite f1 = new Fonctionnalite("Forum", 1, alEF);
		alF.add(f1);
        
		stbList.getSTBs().add(new STB("STB 0.1 ","0.1","20/04/2016","La premiere version !!",c1,alE,alF));
		stbList.getSTBs().add(new STB("STB 0.2 ","0.2","21/04/2016","La deuxième version !!",c1,alE,alF));
		stbList.getSTBs().add(new STB("STB 1.0 ","1.0","21/04/2016","Version finale !!",c1,alE,alF));
		
		System.out.println("TEST PRINT OK");
		
	}
	
	@RequestMapping(value = "/resume")
    public STBList getAllSTB() 
    {   
        return stbList;
    }
	
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	@ResponseBody 
	public ResponseEntity<STB> addSTB(@RequestBody STB stb) {
		return new ResponseEntity<STB>(stb, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/resume/{id}")
	@ResponseBody
    public ResponseEntity<STB> getSTBByVersion (@PathVariable("id") int id) 
    {   
		for(STB stb : stbList.getSTBs()) {
            if (stb.getId() == id) {
            	return new ResponseEntity<STB>(stb, HttpStatus.OK);
            }
        }
		return new ResponseEntity<STB>(HttpStatus.NOT_FOUND);
    }
	
}
