/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Model.Customer;
import Model.Magasin;
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

    public Json() {
        
    }
    
    

    public void serialization(T t) throws IOException{
        Writer w =  new FileWriter("D:\\Profile\\badiakite\\Desktop\\magasin.json");
        Gson gson = new GsonBuilder().create();
        gson.toJson(t, t.getClass(),w);
        w.close();
    }
    
    public void deSerialization() throws FileNotFoundException, IOException{
        JsonReader jsonReader = new JsonReader(new FileReader("D:\\Profile\\badiakite\\Desktop\\magasin.json"));
        Gson g = new Gson();
        Customer c = g.fromJson(jsonReader, Customer.class);
//        ArrayList<Customer> c = g.fromJson(jsonReader, ArrayList.class);
        System.out.println(c.toString());
//        Customer customer = c.get(1);
//        System.out.println(customer.getPrenom());
//        System.out.println(m.getDesignation());
    }
    
        public static void main (String[] args) throws IOException{
            
            Json j = new Json();
            Customer c = new Customer(1, "t", "t", "t", "t", "t",  "t", "t");
//            Customer c2 = new Customer(2, "t", "t", "t", "t", "t",  "t", "t");
//            ArrayList<Customer> lesc = new ArrayList<Customer>();
//            lesc.add(c);
//            lesc.add(c2);
//            j.serialization(c);
        j.deSerialization();
    }
}