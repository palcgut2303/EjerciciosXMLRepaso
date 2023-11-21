/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import com.mycompany.LecturaXML2.*;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name="hechos_historicos")
@XmlType(propOrder = {"hechos"})
public class hechos_historicos {
    
    private ArrayList<Hechos> hechos = new ArrayList<>();

    @XmlElement(name="hecho")
    public ArrayList<Hechos> getHechos() {
        return hechos;
    }

    public void setHechos(ArrayList<Hechos> hechos) {
        this.hechos = hechos;
    }
    
    
    
}
