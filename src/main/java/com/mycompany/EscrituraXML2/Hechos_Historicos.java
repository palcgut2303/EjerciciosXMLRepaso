/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.EscrituraXML2;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author PabloAlcudiia
 */
@XmlRootElement(name = "hechos_historicos")
@XmlType(propOrder = {"misHechos"})
public class Hechos_Historicos {
    
    
    private ArrayList<Hecho> misHechos = new ArrayList<>();

    @XmlElement(name = "hechos")
    public ArrayList<Hecho> getMisHechos() {
        return misHechos;
    }

    public void setMisHechos(ArrayList<Hecho> misHechos) {
        this.misHechos = misHechos;
    }

    public Hechos_Historicos() {
    }
    
    
}
