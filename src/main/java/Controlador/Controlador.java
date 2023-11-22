/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.Fecha;
import Modelos.Hechos;
import Modelos.hechos_historicos;
import com.mysql.cj.xdevapi.Result;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

    private static final String db = "hechos";
    private static final String url = "jdbc:mysql://localhost/";
    private static final String sDriver = "com.mysql.cj.jdbc.Driver";
    private Connection conn = null;
    private static final String db_user = "root";
    private static final String db_pw = "";
    Statement stmt = null;
    ArrayList<Hechos> hechos = null;
    int contador = 0;
    
    
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
            //Unmarshaller unmarshaller = context.createUnmarshaller();
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
    
    public ArrayList<Hechos> leerDatosBD(){
        
        ArrayList<Hechos> listaHechos = new ArrayList<>();
        
        String query = "SELECT descripcion,fecha from hechos_historicos";
        try {
            
            stmt = conn.createStatement();
            ResultSet resultset = stmt.executeQuery(query);
            
            while(resultset.next()){
                Hechos miHecho = new Hechos();
                miHecho.setDescripcion(resultset.getString("descripcion"));
                String fecha = resultset.getString("fecha");
                String[] datosFecha = fecha.split(" ");
                Fecha miFecha = new Fecha(datosFecha[0], datosFecha[1], datosFecha[2]);
                miHecho.setFecha(miFecha);
                
                listaHechos.add(miHecho);
                
            }
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return listaHechos;
    }

    public boolean escrituraDatosBD(){
        
        ArrayList<Hechos> listaHechos = leerXMLTabla();
        boolean b = false;
        
        int id = 0;
        if(!listaHechos.isEmpty()){
            for (Hechos item : listaHechos) {
                    String descripcion = item.getDescripcion();
                    String fecha = item.getFecha().toString();
                     id = id + 1;
                try {
                    String query = "INSERT INTO `hechos_historicos` (`id`,`descripcion`,`fecha`) VALUES ('"+ id +"', '"+ descripcion +"', '"+fecha+"' )";
                    stmt = conn.createStatement();
                    stmt.executeUpdate(query);
                    b = true;
                } catch (SQLException ex) {
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                
                
            }
        }
        return b;
    }
    
}
