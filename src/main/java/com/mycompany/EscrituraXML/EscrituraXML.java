/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.EscrituraXML;

import Modelos.hechos_historicos;
import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author PabloAlcudiia
 */
public class EscrituraXML {
    
    
    
    public static void main(String[] args) throws JAXBException {
        JAXBContext context;
        context = JAXBContext.newInstance(Libreria.class);
        
        Marshaller marshall = context.createMarshaller();
        
        
        Libreria libreria = new Libreria();
        libreria.setNombre("Garci Mendez");
        Libros libro1 = new Libros("1654653A", "La vuelta al mundo2", "JoseMiguel");
        Libros libro2 = new Libros("78946AD", "EduardoManosTijeras2", "Pedro");
        Libros libro3 = new Libros("456131D", "Aventuras2", "Sergio");
        
        ArrayList<Libros> listaLibros = new ArrayList<>();
        listaLibros.add(libro1);
        listaLibros.add(libro2);
        listaLibros.add(libro3);
        
        libreria.setLibros(listaLibros);
        
        marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshall.marshal(libreria, new File("src/main/java/com/mycompany/EscrituraXML/libreria.xml"));
    }
    
    
    
}
