/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import conexion.Conexion;
import entidades.Disciplina;
import org.bson.Document;

/**
 *
 * @author rjsaa
 */
public class DisciplinaDAO {

    Conexion conexion;
    
    public DisciplinaDAO() {
        this.conexion = new Conexion();
    }
    
    public boolean agregarDisciplina(Disciplina disciplina,String idMacro){
        
        try {
            MongoClient mongoClient = conexion.crearConexion();
        
            MongoDatabase database = mongoClient.getDatabase("polideportivo");
        
            MongoCollection<Document> collection = database.getCollection("macrociclos");
            
            Document document = new Document()
                    .append("disciplina", disciplina.getNombre())
                    .append("volumenPreparativa", disciplina.getPreparativa())
                    .append("volumenEspecial", disciplina.getEspecial())
                    .append("volumenPrecom", disciplina.getPrecom())
                    .append("volumenCompetitiva", disciplina.getCompetitiva())
                    .append("total", disciplina.getTotal())
                    .append("idMacro", idMacro);
            
            collection.insertOne(document);
            
            mongoClient.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
}
