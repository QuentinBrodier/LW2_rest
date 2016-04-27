package stb.tp6.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement (name = "equipe")
@XmlAccessorType(XmlAccessType.NONE)
public class Equipe {

	@XmlElement
    private String nom;

    @XmlElement
    private String prenom;

    @XmlElement
    private boolean gender;
    
    public Equipe(String nom, String prenom, boolean gender){
    	this.nom = nom;
    	this.prenom = prenom;
    	this.gender = gender;
    }

    public Equipe(){
    	
    }
    
    //Setters and Getters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
	
}
