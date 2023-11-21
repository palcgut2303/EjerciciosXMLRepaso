/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.LecturaXML;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author PabloAlcudiia
 */
public class EjerciciosXMLRepaso {

    public static void main(String[] args) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Ciudades.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Ciudades ciudad = (Ciudades) unmarshaller.unmarshal(new File("./src/main/java/com/mycompany/LecturaXML/FicheroXML.xml"));

            ArrayList<ciudad> ciudades = ciudad.getListaCiudades();

            for (ciudad object : ciudades) {
                System.out.println(object.getNombre() + " " + object.getPais() + " " + object.getContinente());
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
