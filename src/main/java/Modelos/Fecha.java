/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import com.mycompany.LecturaXML2.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author PabloAlcudiia
 */
@XmlRootElement(name="fecha")
@XmlType(propOrder = {"dia","mes","año"})
public class Fecha {
    
    private String dia;
    private String mes;
    private String año;

    public Fecha(String dia, String mes, String año) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    public Fecha() {
    }

    @XmlElement(name="dia")
    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
 @XmlElement(name="mes")
    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
     @XmlElement(name="año")
    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    @Override
    public String toString() {
        return  dia + " " + mes + " " + año ;
    }
    
    
    
}
