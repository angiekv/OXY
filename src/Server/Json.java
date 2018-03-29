/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Model.Customer;
import Model.Magasin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author OXY
 * @param <T> can be a store, a client, etc.
 */
public class Json<T> {

    Writer w;

    public Json(Writer w) {
        this.w = w;
    }

    public String serialization(T t) throws IOException {
//        Writer w =  new FileWriter("D:\\Profile\\badiakite\\Desktop\\customer.json");
        Gson gson = new GsonBuilder().create();
        String result = gson.toJson(t);
        gson.toJson(t, t.getClass(), w);
        w.close();
        return result;
    }

    public List deSerialization(String answer, Type listType) throws FileNotFoundException, IOException {
        Gson g = new Gson();
        List<Type> list = g.fromJson(answer, listType);
        return list;
    }
      
    public Map deSerializationMap(String answer) throws FileNotFoundException, IOException {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Gson g = new Gson();
        Map<String, String> list = g.fromJson(answer, type);
        return list;
    }

    public static void main(String[] args) throws IOException {
//        Json j = new Json(new FileWriter("D:\\Profile\\badiakite\\Desktop\\customer.json"));
//         Customer c = new Customer(1, "t", "t", "t", "t", "t", "t", "t");
////            Customer c2 = new Customer(2, "t", "t", "t", "t", "t",  "t", "t");
////            ArrayList<Customer> lesc = new ArrayList<Customer>();
////            lesc.add(c);
////            lesc.add(c2);
////            j.serialization(c);
//        j.deSerialization();
    }
}
