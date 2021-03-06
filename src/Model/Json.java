/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.FileNotFoundException;
import java.io.Writer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
/**
 *
 * @author OXY
 * @param <T> can be a store, a client, etc.
 */
public class Json<T> {

//    Writer w;
//
//    public Json(Writer w) {
//        this.w = w;
//    }

    public String serialization(T t) throws IOException {
        Gson gson = new GsonBuilder().create();
        String result = gson.toJson(t);
        gson.toJson(t, t.getClass());
        return result;
    }
 
    public List deSerialization(String answer, Type listType) throws FileNotFoundException, IOException {
        Gson g = new Gson();
        List<Type> list = g.fromJson(answer, listType);
        return list;
    }

    public static void main(String[] args) throws IOException {
//        Json j = new Json(new FileWriter("D:\\Profile\\badiakite\\Desktop\\customer.json"));
//        Customer c = new Customer(1, "t", "t", "t", "t", "t", "t", "t");
////            Customer c2 = new Customer(2, "t", "t", "t", "t", "t",  "t", "t");
////            ArrayList<Customer> lesc = new ArrayList<Customer>();
////            lesc.add(c);
////            lesc.add(c2);
////            j.serialization(c);
//         j.deSerialization();
    }
}
