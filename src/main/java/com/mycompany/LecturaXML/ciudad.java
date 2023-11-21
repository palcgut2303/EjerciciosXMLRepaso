package com.mycompany.LecturaXML;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "ciudad")
@XmlType(propOrder = {"nombre","pais","continente"})
public class ciudad {

    private String nombre;
    private String pais;
    private String continente;

    public ciudad() {
    }

    @XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlElement(name = "pais")
    public String getPais() {
        return pais;
    }

    public void setPais(String Pais) {
        this.pais = Pais;
    }

    @XmlAttribute(name = "continente")
    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

}
