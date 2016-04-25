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

@RestController
public class STBController {

	@RequestMapping(value = "/resume")
    public STBList getAllSTB() 
    {
		
		STBList stbList = new STBList();
		Client c1 = new Client("Entité 1","Brodier Quentin",76100);
		Client c2 = new Client("Entité 2","Coulon Matthieu",76000);
        
		stbList.getSTBs().add(new STB("STB 0.1 ","0.1",new Date(139340084),"La premiere version !!",c1));
		stbList.getSTBs().add(new STB("STB 0.2 ","0.2",new Date(1460454507),"La deuxième version !!",c1));
		stbList.getSTBs().add(new STB("STB 1.0 ","1.0",new Date(),"Version finale !!",c2));
         
        return stbList;
    }
	
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	@ResponseBody 
	public ResponseEntity<STB> addSTB(@RequestBody STB stb) {
		return new ResponseEntity<STB>(stb, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/resume/{version}")
    public ResponseEntity<STB> getSTBByVersion (@PathVariable("version") String version) 
    {   
        STB stb = new STB("STB " + version,version,new Date(),"Super description !!",new Client("Entité test","Quentin Test",76110));
        return new ResponseEntity<STB>(stb, HttpStatus.OK);
    }
	
}
