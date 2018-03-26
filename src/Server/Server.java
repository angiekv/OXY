package Server;

import static Server.DAOCustomer.addCustomer;
import static Server.DAOCustomer.deleteCustomer;
import static Server.DAOCustomer.loadCustomer;
import static Server.DAOCustomer.updateCustomer;
import static Server.DAOMagasin.AjouterMagasin;
import static Server.DAOMagasin.chargeMagasin;
import static Server.DAOMagasin.modifierMagasin;
import static Server.DAOMagasin.supprimerMagasin;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Server {

    private static int port = 2009;
    private static ConnectionPool pool = new ConnectionPool();
    public static void main(String[] args) {
        ServerSocket server = null;
        pool.InitPool();
        try {
            server = new ServerSocket(port);
            while (true) {
                //a thread per client
                Connection con = pool.getConnection();
                Thread t1 = new Thread(new AccepterClient(server.accept(),con));
                t1.start();
            }
        } catch (IOException ex) {
            System.out.println("start server impossible.");
        } finally {
            try {
                if (server != null) {
                    server.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static ConnectionPool getPool() {
        return pool;
    }
}

class AccepterClient implements Runnable {

    private Socket socket;
    private Connection con;

    AccepterClient(Socket socket, Connection con) {
        this.socket = socket;
        this.con = con;
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            String request;
            while ((request = in.readLine()) != null) {
                try {
                    receive(request, out);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        } catch (IOException ex) {
            System.out.println("client disconnected ");
            
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     *
     * @param request 
     * @param out
     * @throws SQLException
     */
    public void receive(String request, PrintWriter out) throws SQLException {
        //we use Gson to convert from Json to Java object
        Gson g = new Gson();
        //conversion
        JsonObject jobj = new Gson().fromJson(request, JsonObject.class);
        //we want to know what action is requested (load, add, insert or delete)
        String type = jobj.get("actionType").getAsString();
        System.out.println(type);
        //Shop attributes
        String designation ;
        String description;
        int idType ;
        int idMagasin;
        //Customer attributes
        int idClient;
        String nom, prenom, adresse, cp, ville, mail, sexe;
        String reponse;

        switch (type) {
            case "listCustomer": 
                List<Customer> listCustomer = loadCustomer(con);
                System.out.println("requete");
                reponse = g.toJson(listCustomer);
                send(reponse, out);
                break;
            case "addCustomer" :
                System.out.println("add");
                 nom = jobj.get("nom").getAsString();
                 prenom = jobj.get("prenom").getAsString();
                 adresse = jobj.get("adresse").getAsString();
                 cp = jobj.get("cp").getAsString();
                 ville = jobj.get("ville").getAsString(); 
                 mail = jobj.get("mail").getAsString();
                 sexe = jobj.get("sexe").getAsString();
                System.out.println("added successfully");
                addCustomer(con, nom,  prenom,  adresse,  cp,  ville,  mail,  sexe);
                System.out.println("end of request");
                reponse = g.toJson("ok");
                send(reponse, out);
                break;
            case "updateCustomer" : 
                System.out.println("add");
                idClient = jobj.get("idClient").getAsInt();
                 nom = jobj.get("nom").getAsString();
                 prenom = jobj.get("prenom").getAsString();
                 adresse = jobj.get("adresse").getAsString();
                 cp = jobj.get("cp").getAsString();
                 ville = jobj.get("ville").getAsString(); 
                 mail = jobj.get("mail").getAsString();
                 sexe = jobj.get("sexe").getAsString();
                System.out.println("updated successfully");
                updateCustomer(con, idClient, nom,  prenom,  adresse,  cp,  ville,  mail,  sexe);
                System.out.println("end of request");
                reponse = g.toJson("ok");
                send(reponse, out);
                break;
            case "deleteCustomer" :
                idClient = jobj.get("idClient").getAsInt();
                deleteCustomer(con, idClient);
                reponse = g.toJson("ok");
                send(reponse, out);
                break;
            //same thing for stores:
            case "listeMagasin":
                List<Magasin> l = chargeMagasin();
                System.out.println("requete");
                 reponse = g.toJson(l);
                send(reponse, out);
                break;
            case "add":
                System.out.println("ajout");
                designation = jobj.get("designation").getAsString();
                description = jobj.get("description").getAsString();
                idType = jobj.get("idType").getAsInt();
                //action ajout execution effectué 
                AjouterMagasin(designation, description, idType);
                System.out.println("fin requete envoie rep ");
                reponse = g.toJson("ok");
                send(reponse, out);
                break;
            case "modifMagasin":
              
                idMagasin = jobj.get("idMagasin").getAsInt();
                designation = jobj.get("designation").getAsString();
                description = jobj.get("description").getAsString();
                idType = jobj.get("idType").getAsInt();
                //action ajout execution effectué 
                modifierMagasin(idMagasin,designation, description, idType);
                System.out.println("fin requete envoie rep ");
                reponse = g.toJson("ok");
                send(reponse, out);
                break;
            case "supprimer":
                idMagasin = jobj.get("idMagasin").getAsInt();
                supprimerMagasin(idMagasin);
                reponse = g.toJson("ok");
                send(reponse, out);
            default:
                break;
        }

    }

    public void send(String S, PrintWriter out) {
        out.println(S);
        out.flush();
    }
}
