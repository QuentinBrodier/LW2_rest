package stb.tp6.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Equipe")
@XmlAccessorType(XmlAccessType.NONE)
public class ExigenceFonctionnelle {

    @XmlElement
    private String identifiant;

    @XmlElement
    private String description;

    @XmlElement
    private int priorite;
    
    public ExigenceFonctionnelle(String identifiant, String description, int priorite){
    	this.identifiant = identifiant;
    	this.description = description;
    	this.priorite = priorite;
    }
    
    public ExigenceFonctionnelle(){
    	
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }
}
