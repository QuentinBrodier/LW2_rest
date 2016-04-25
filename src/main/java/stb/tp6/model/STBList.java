package stb.tp6.model;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="stbs")
public class STBList implements Serializable 
{    
	@XmlElement(name="stb")
    private ArrayList<STB> stbs = new ArrayList<STB>();
 
    public ArrayList<STB> getSTBs() {
        return stbs;
    }
 
    public void setEmployees(ArrayList<STB> stbs) {
        this.stbs = stbs;
    }
}
