package Server;

import static Server.DAOMagasin.AjouterMagasin;
import static Server.DAOMagasin.chargeMagasin;
import static Server.DAOMagasin.modifierMagasin;
import static Server.DAOMagasin.supprimerMagasin;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class Server {

    private static int port = 2009;

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            while (true) {

                //un thread pour chaque client
                Thread t1 = new Thread(new AccepterClient(server.accept()));
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
}

class AccepterClient implements Runnable {

    private Socket socket;

    AccepterClient(Socket socket) {
        this.socket = socket;
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
            System.out.println("erreur reception ");
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
     * @param request requete
     * @param out
     * @throws SQLException
     */
    public void receive(String request, PrintWriter out) throws SQLException {
        Gson g = new Gson();
        JsonObject jobj = new Gson().fromJson(request, JsonObject.class);
        String type = jobj.get("typeAction").getAsString();
        System.out.println(type);
        String designation ;
        String description;
        int idType ;
        int idMagasin;
        
        String reponse;

        switch (type) {
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
