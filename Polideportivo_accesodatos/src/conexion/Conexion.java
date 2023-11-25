/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 *
 * @author rjsaa
 */
public class Conexion {
    
    private static String uri = "mongodb://127.0.0.1/";
    private MongoClient mongoClient;
    
    public Conexion() {
    }
    
    /**
     * Método que crea la conexion con la base de datos
     * @return 
     */
    public MongoClient crearConexion(){
        try{
            mongoClient = MongoClients.create(uri);
            return mongoClient;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }    
    
}
