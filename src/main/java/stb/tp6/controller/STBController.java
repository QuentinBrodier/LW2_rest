package stb.tp6.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
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
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

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
		
	}
	
	@RequestMapping(value = "/resume")
    public STBList getAllSTB() 
    {   
        return stbList;
    }
	
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	@ResponseBody 
	public ResponseEntity<STB> addSTB(@RequestBody STB stb) {
		
		System.out.println("Coucou");
		
		try {
			
			// On sérialise notre objet STB en XML
			StringWriter sw = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(STB.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(stb, sw);
			
			// On valide l'XML par rapport au XSD
			File xsdFile = new File("src/main/ressources/stb.xsd");
			
			if(xsdFile == null)
				System.out.println("Fichier vide !!");
			
			InputStream stream = new ByteArrayInputStream(sw.toString().getBytes(StandardCharsets.UTF_8));
			Source xmlFile = new StreamSource(stream);
			SchemaFactory schemaFactory = SchemaFactory
			    .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(xsdFile);
			Validator validator = schema.newValidator();
			
			System.out.println("On passe ici");
			
			try {
			  validator.validate(xmlFile);
			  System.out.println("XML valid");
			} catch (SAXException e) {
			  System.out.println(xmlFile.getSystemId() + " is NOT valid");
			  System.out.println("Reason: " + e.getLocalizedMessage());
			}

	    } catch (JAXBException e) {
	    	e.printStackTrace();
	    } catch (Exception e){
	    	e.printStackTrace();
	    }
		
		stbList.getSTBs().add(stb);
		ResponseEntity re = new ResponseEntity<STB>(stb, HttpStatus.OK);
		return re;
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
