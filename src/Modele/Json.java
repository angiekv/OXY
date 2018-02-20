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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OXY
 * @param <T> can be a store, a client, etc.
 */
public class Json<T> {

    public static void serialization() throws IOException{
        Magasin m = new Magasin(1, "Zara", "Vêtements", 1);
        Writer w =  new FileWriter("C:\\Users\\u21510116\\Documents\\2017_2018\\PDS\\magasin.json");
        Gson gson = new GsonBuilder().create();
        gson.toJson(m, Magasin.class,w);
        w.close();
    }
    
    public static void deSerialization() throws FileNotFoundException, IOException{
        JsonReader jsonReader = new JsonReader(new FileReader("C:\\Users\\u21510116\\Documents\\2017_2018\\PDS\\magasin.json"));
        Gson g = new Gson();
        Magasin m = g.fromJson(jsonReader, Magasin.class);
        System.out.println(m.toString());
//        System.out.println(m.getDesignation());
    }
    
        public static void main (String[] args) throws IOException{
//        serialization();
        deSerialization();
    }
}