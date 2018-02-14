/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author BaDiakite
 */
public class json {
    public static void main (String[] args) throws IOException{
//        Serialization();
        deSerialization();
    }
    
    
    private static void Serialization() throws IOException{
        Type type = new Type(1,"cpossible");
        Gson g = new Gson();
        String json = g.toJson(type, Type.class);
        Writer w =  new FileWriter("D:\\Profile\\badiakite\\Documents\\NetBeansProjects\\testJson\\results.json");
        Gson gson = new GsonBuilder().create();
        gson.toJson(type, Type.class,w);
        w.close();
    }
    
    
    private static void deSerialization() throws FileNotFoundException, IOException{
        JsonReader jsonReader = new JsonReader(new FileReader("D:\\Profile\\badiakite\\Documents\\NetBeansProjects\\testJson\\results.json"));
        //String fileData = new String(Files.readAllBytes(Paths.get("\"D:\\\\Profile\\\\badiakite\\\\Documents\\\\NetBeansProjects\\\\testJson\\\\results.json\"")));
        Gson g = new Gson();
        Type t = g.fromJson(jsonReader, Type.class);
        System.out.println(t.getDesignation());
    }
}
