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

/**
 *
 * @author BaDiakite
 */
public class DAOEmpHasMag {
     private ClientSocket c;

    public DAOEmpHasMag(ClientSocket c) {
        this.c = c;
    }

    public List<EmpHasMag> loadEmpHasMag() throws IOException {
        // list of stores
        List<EmpHasMag> listEmp = new ArrayList<>();

        Map<String, String> MapEmp = new HashMap<String, String>();
        MapEmp.put("actionType", "listEmp");
        Json j = new Json();
        String json = j.serialization(MapEmp);
        String answer = null;
        answer = c.sendAndRecieve(json);
        Type listType = new TypeToken<List<EmpHasMag>>() {
        }.getType();
        listEmp = j.deSerialization(answer, listType);
        return listEmp;
    }
//    
//    public static void main(String [] args ) throws IOException{
//        ClientSocket client = new ClientSocket();
//        client.startConnection();
//        DAOEmpHasMag d = new DAOEmpHasMag(client);
//        List<EmpHasMag> Emps = d.loadEmpHasMag();
//        System.out.println(Emps);
//    }

}
