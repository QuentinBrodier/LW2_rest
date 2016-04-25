package stb.tp6.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement (name = "client")
@XmlAccessorType(XmlAccessType.NONE)
public class Client {
	
	@XmlElement
	private String entite;
	
	@XmlElement
	private String contact;
	
	@XmlElement
	private int codePostal;
	
	public Client(String entite, String contact, int codePostal) {
        super();
        this.entite = entite;
        this.contact = contact;
        this.codePostal = codePostal;
    }
     
    public Client(){
         
    }
 
    //Setters and Getters
 
    @Override
    public String toString() {
        return "Client [entite=" + entite + ", contact=" + contact
                + ", codePostal=" + codePostal + "]";
    }

}
