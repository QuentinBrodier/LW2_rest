package stb.tp6.model;

import java.util.Date;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "stb")
@XmlAccessorType(XmlAccessType.NONE)
public class STB implements Serializable 
{
	@XmlElement
	private String titreProjet;
	@XmlElement
	private String version;
	@XmlElement
	private Date date;
	@XmlElement
	private String description;
     
    @XmlElement
    private String firstName;
     
    @XmlElement
    private String lastName;
     
    @XmlElement
    private String email;
     
    public STB(String titreProjet, String version, Date date, String description) {
        super();
        this.titreProjet = titreProjet;
        this.version = version;
        this.date = date;
        this.description = description;
    }
     
    public STB(){
         
    }
 
    //Setters and Getters
 
    @Override
    public String toString() {
        return "STB [titreProjet=" + titreProjet + ", version=" + version
                + ", date=" + date + ", description=" + description + "]";
    }
}