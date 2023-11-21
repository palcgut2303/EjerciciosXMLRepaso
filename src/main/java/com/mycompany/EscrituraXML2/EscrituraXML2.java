/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.EscrituraXML2;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author PabloAlcudiia
 */
public class EscrituraXML2 {
 
    public static void main(String[] args) throws JAXBException {
        
        JAXBContext context;
        context = JAXBContext.newInstance(Hechos_Historicos.class);
        
        Marshaller marshall = context.createMarshaller();
        
        Hechos_Historicos misHechosHistoricos = new Hechos_Historicos();
        Fecha fecha1 = new Fecha("9", "12", "1520");
        Fecha fecha2 = new Fecha("9", "12", "1520");
        Fecha fecha3 = new Fecha("9", "12", "1520");
        Hecho hecho1 = new Hecho("levantada del Imperio Romano", fecha1);
        Hecho hecho2 = new Hecho("Invención de la escritura", fecha2);
        Hecho hecho3 = new Hecho("Invento de la bombilla", fecha3);
     
        ArrayList<Hecho> listaHechos = new ArrayList<>();
        
        listaHechos.add(hecho1);
        listaHechos.add(hecho2);
        listaHechos.add(hecho3);
        
        
        misHechosHistoricos.setMisHechos(listaHechos);
        
        marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshall.marshal(misHechosHistoricos, new File("src/main/java/com/mycompany/EscrituraXML2/EscrituraXML2.xml"));
    }
    
}
