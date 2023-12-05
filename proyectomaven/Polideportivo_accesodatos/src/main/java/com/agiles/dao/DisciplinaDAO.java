/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agiles.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.agiles.conexion.Conexion;
import com.agiles.entidades.Disciplina;
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

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Método que almacene en la BD las disciplinas enviadas como parametros
     *
     * @param disciplina
     * @param idMacro
     * @return
     */
    public boolean agregarDisciplina(Disciplina disciplina, String idMacro) {

        try {
            MongoClient mongoClient = conexion.crearConexion();

            MongoDatabase database = mongoClient.getDatabase("polideportivo");

            MongoCollection<Document> collection = database.getCollection("disciplina");

            Document document = new Document()
                    .append("disciplina", disciplina.getNombre())
                    .append("volumenPreparativa", disciplina.getPreparativa().toDocument())
                    .append("volumenEspecial", disciplina.getEspecial().toDocument())
                    .append("volumenPrecom", disciplina.getPrecom().toDocument())
                    .append("volumenCompetitiva", disciplina.getCompetitiva().toDocument())
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

    /**
     * Método que permite agregar una disciplina al macrociclo que contiene el ID enviado por parámetro
     * @param disciplina
     * @param idMacro
     * @return 
     */
    public boolean agregarDisciplinaD(Disciplina disciplina, String idMacro) {

        try {
            //MongoClient mongoClient = conexion.crearConexion();

            //MongoDatabase database = mongoClient.getDatabase("polideportivo");
            //MongoCollection<Document> collection = database.getCollection("disciplina");
            Document document = new Document()
                    .append("disciplina", disciplina.getNombre())
                    .append("volumenPreparativa", disciplina.getPreparativa().toDocument())
                    .append("volumenEspecial", disciplina.getEspecial().toDocument())
                    .append("volumenPrecom", disciplina.getPrecom().toDocument())
                    .append("volumenCompetitiva", disciplina.getCompetitiva().toDocument())
                    .append("total", disciplina.getTotal())
                    .append("idMacro", idMacro);

            System.out.println(document);
            //collection.insertOne(document);

            //mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
