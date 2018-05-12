/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelique
 */
public class DTOPath {
    private ClientSocket c;

    public DTOPath(ClientSocket c) {
        this.c = c;
    }

    /**
     * This method selects all shops from database and to show a path
     *
     * @return the list of all customers
     * @throws SQLException
     */
    public List<String> generateAndShowPath(int idClient) throws IOException {
        // list of shop names
        List<String> listCustomer = new ArrayList<>();

        Map<String, String> MapPath = new HashMap<String, String>();
        MapPath.put("actionType", "generateAndShowPath");
        MapPath.put("idClient", Integer.toString(idClient));
        Json j = new Json();
        String json = j.serialization(MapPath);
        String answer = null;
        try {
            answer = c.sendAndRecieve(json);
        } catch (IOException ex) {
            System.out.println("to be finished");
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        listCustomer = j.deSerialization(answer, listType);
        return listCustomer;
    }
    public static void main(String [] args) throws IOException{
        ClientSocket c = new ClientSocket();
        c.startConnection();
        DTOPath dto = new DTOPath(c);        
        dto.generateAndShowPath(24);
    }

}
