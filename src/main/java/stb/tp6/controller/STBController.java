package stb.tp6.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoURI;

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
import stb.tp6.config.DAO_LW2;
import stb.tp6.model.Client;
import stb.tp6.model.Equipe;
import stb.tp6.model.ExigenceFonctionnelle;
import stb.tp6.model.Fonctionnalite;



@RestController
public class STBController {

	private STBList stbList = new STBList();
	
	public STBController(){
		
		Client c1 = new Client("Entité 1","Brodier Quentin",76100);
	
		Equipe e1 = new Equipe(true,"Jean","Jaques");
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
		
		try {
			
			// On sérialise notre objet STB en XML
			StringWriter sw = new StringWriter();
			JAXBContext jaxbContext = JAXBContext.newInstance(STB.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(stb, sw);

			// On valide l'XML par rapport au XSD
			InputStream stream = new ByteArrayInputStream(sw.toString().getBytes(StandardCharsets.UTF_8));
			boolean isValid = validateXMLSchema("src/main/ressources/stb.xsd", stream);
			
			if(isValid){
				
				// Persistence dans la BD
				DAO_LW2 db = new DAO_LW2();
				db.insert(stb);
				return new ResponseEntity<STB>(stb, HttpStatus.OK);
				
			}else{
				System.out.println("XML Non Valide !!");
			}

	    } catch (JAXBException e) {
	    	e.printStackTrace();
	    } catch (Exception e){
	    	e.printStackTrace();
	    }
		
		return new ResponseEntity<STB>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/resume/{id}")
	@ResponseBody
    public ResponseEntity<STB> getSTBByVersion (@PathVariable("id") int id) 
    {   
		DAO_LW2 db = new DAO_LW2();
		STB stb = db.find(id);
		if(stb != null){
			return new ResponseEntity<STB>(stb, HttpStatus.OK);
		}else{
			System.out.println("TEST");
			return new ResponseEntity<STB>(HttpStatus.NOT_FOUND);
		}
		
    }
	
	public static boolean validateXMLSchema(String xsdPath, InputStream stream){
        
		try {
            SchemaFactory factory = 
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(stream));
        } catch (IOException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        } catch (SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
	
}
