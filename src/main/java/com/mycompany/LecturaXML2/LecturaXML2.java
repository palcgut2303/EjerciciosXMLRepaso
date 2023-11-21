/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.LecturaXML2;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author PabloAlcudiia
 */
public class LecturaXML2 {

    public static void main(String[] args) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(hechos_historicos.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("./src/main/java/com/mycompany/LecturaXML2/Hechos.xml");
            hechos_historicos hechos_historicos = unmarshal(file);

            ArrayList<Hechos> hechos = hechos_historicos.getHechos();

            for (Hechos hecho : hechos) {
                System.out.println(hecho.getDescripcion() + " " + hecho.getFecha().toString());
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    
    private static hechos_historicos unmarshal(File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(hechos_historicos.class);
        return (hechos_historicos) context.createUnmarshaller().unmarshal(file);
    }

}
