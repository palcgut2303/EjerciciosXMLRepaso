/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.Fecha;
import Modelos.Hechos;
import Modelos.hechos_historicos;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Pablo Alcudia
 */
public class Controlador {

    private static final String db = "neumaticos";
    private static final String url = "jdbc:mysql://localhost/";
    private static final String sDriver = "com.mysql.cj.jdbc.Driver";
    private Connection conn = null;
    private static final String db_user = "root";
    private static final String db_pw = "";
    Statement stmt = null;
    ArrayList<Hechos> hechos = null;
    public java.sql.Connection getConexion() {
        return (java.sql.Connection) conn;
    }

    public boolean close() throws IOException, SQLException {

        if (conn != null) {
            conn.close();
            return true;
        }
        return false;
    }

    public boolean conectar() {
        try {
            conn = (Connection) DriverManager.getConnection(url + db, db_user, db_pw);
            return true;
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return false;
    }

    public ArrayList<Hechos> leerXMLTabla() {

        
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(hechos_historicos.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            File file = new File("./src/main/java/XML/Hechos.xml");
            hechos_historicos hechos_historicos = unmarshal(file);

            hechos = hechos_historicos.getHechos();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return hechos;
    }

    public boolean escrituraXML(String descripcion, String fecha) throws JAXBException {
        try {
            String[] datosFecha = fecha.split("/");

            hechos = leerXMLTabla();
            
            File file = new File("./src/main/java/XML/Hechos.xml");
            hechos_historicos hechos_hist = unmarshal(file);

            Hechos hechoNuevo = new Hechos();
            hechoNuevo.setDescripcion(descripcion);
            
            Fecha fechaNueva = new Fecha();
            fechaNueva.setDia(datosFecha[0]);
            fechaNueva.setMes(datosFecha[1]);
            fechaNueva.setAño(datosFecha[2]);
            hechoNuevo.setFecha(fechaNueva);
            
            hechos.add(hechoNuevo);
            hechos_hist.setHechos(hechos);
            marshal(hechos_hist, file);
            return true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static hechos_historicos unmarshal(File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(hechos_historicos.class);
        return (hechos_historicos) context.createUnmarshaller().unmarshal(file);
    }

    private static void marshal(hechos_historicos hechosHistoricos, File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(hechos_historicos.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(hechosHistoricos, file);
    }

}
