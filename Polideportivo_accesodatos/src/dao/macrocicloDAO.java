/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.Conexion;
import entidades.Macrociclo;
import org.bson.Document;

/**
 *
 * @author rjsaa
 */
public class macrocicloDAO {

    Conexion conexion;
    
    public macrocicloDAO() {
        conexion = new Conexion();
    }
    
    /**
     * Método que guarda en la BD el Macrociclo enviado como parametro.
     * @param macrociclo
     * @return 
     */
    public boolean guardarMacrociclo(Macrociclo macrociclo){
        
        
        try{
            MongoClient mongoClient = conexion.crearConexion();
        
            MongoDatabase database = mongoClient.getDatabase("polideportivo");
        
            MongoCollection<Document> collection = database.getCollection("macrociclos");
            
            Document document = new Document()
                .append("fechaInicio", macrociclo.getFechaInicio())
                .append("fechaFin", macrociclo.getFechaFin())
                .append("deporte", macrociclo.getDeporte())
                .append("rama", macrociclo.getRama())
                .append("jefeRama", macrociclo.getJefeRama())
                .append("preparadorFis", macrociclo.getPreparadorFis())
                .append("Metodologo", macrociclo.getMetodologo())
                .append("periodoPreparativo", macrociclo.getPeriodoPreparativo())
                .append("periodoCompetitivo", macrociclo.getPeriodoCompetitivo())
                .append("totalSemanas", macrociclo.getTotalSemanas())
                .append("etapaPreparativa", macrociclo.getEtapaPreparativa())
                .append("etapaEspecial", macrociclo.getEtapaEspecial())
                .append("etapaPrecompetitiva", macrociclo.getEtapaPrecompetitiva())
                .append("etapaCompetitiva", macrociclo.getEtapaCompetitiva())
                .append("distribucionPreparativa", macrociclo.getDistribucionPreparativa())
                .append("distribucionEspecial", macrociclo.getDistribucionEspecial())
                .append("distribucionPrecom", macrociclo.getDistribucionPrecom())
                .append("distribucionCompetitiva", macrociclo.getDistribucionCompetitiva())
                .append("ciclicidadPreparativa", macrociclo.getCiclicidadPreparativa())
                .append("ciclicidadEspecial", macrociclo.getCiclicidadEspecial())
                .append("ciclicidadPrecom", macrociclo.getCiclicidadPrecom())
                .append("ciclicidadCompetitiva", macrociclo.getCiclicidadCompetitiva());

            collection.insertOne(document);
            
            mongoClient.close();

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        
        return true;
    }
    
        public boolean guardarMacrocicloD(Macrociclo macrociclo){
        
        
        try{
            //MongoClient mongoClient = conexion.crearConexion();
        
           // MongoDatabase database = mongoClient.getDatabase("polideportivo");
        
            //MongoCollection<Document> collection = database.getCollection("macrociclos");
            
            Document document = new Document()
                .append("fechaInicio", macrociclo.getFechaInicio())
                .append("fechaFin", macrociclo.getFechaFin())
                .append("deporte", macrociclo.getDeporte())
                .append("rama", macrociclo.getRama())
                .append("jefeRama", macrociclo.getJefeRama())
                .append("preparadorFis", macrociclo.getPreparadorFis())
                .append("Metodologo", macrociclo.getMetodologo())
                .append("periodoPreparativo", macrociclo.getPeriodoPreparativo())
                .append("periodoCompetitivo", macrociclo.getPeriodoCompetitivo())
                .append("totalSemanas", macrociclo.getTotalSemanas())
                .append("etapaPreparativa", macrociclo.getEtapaPreparativa())
                .append("etapaEspecial", macrociclo.getEtapaEspecial())
                .append("etapaPrecompetitiva", macrociclo.getEtapaPrecompetitiva())
                .append("etapaCompetitiva", macrociclo.getEtapaCompetitiva())
                .append("distribucionPreparativa", macrociclo.getDistribucionPreparativa())
                .append("distribucionEspecial", macrociclo.getDistribucionEspecial())
                .append("distribucionPrecom", macrociclo.getDistribucionPrecom())
                .append("distribucionCompetitiva", macrociclo.getDistribucionCompetitiva())
                .append("ciclicidadPreparativa", macrociclo.getCiclicidadPreparativa())
                .append("ciclicidadEspecial", macrociclo.getCiclicidadEspecial())
                .append("ciclicidadPrecom", macrociclo.getCiclicidadPrecom())
                .append("ciclicidadCompetitiva", macrociclo.getCiclicidadCompetitiva());

            //collection.insertOne(document);
            
            //mongoClient.close();

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        
        return true;
    }
}
