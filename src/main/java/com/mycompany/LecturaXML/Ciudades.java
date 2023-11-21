/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LecturaXML;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author PabloAlcudiia
 */
@XmlRootElement(name = "ciudades")
@XmlType(propOrder = {"listaCiudades"})
public class Ciudades {

    private ArrayList<ciudad> listaCiudades = new ArrayList<>();

    @XmlElement(name = "ciudad")
    public ArrayList<ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(ArrayList<ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

}
