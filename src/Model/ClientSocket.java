package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {

    private Socket socketClient;
    private BufferedReader in;
    private PrintWriter out;

    public void startConnection() {
        try {
            socketClient = new Socket("127.0.0.1", 2009);
            in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            out = new PrintWriter(socketClient.getOutputStream());
            System.out.println("");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String sendAndRecieve(String s) {

        Gson gson = new GsonBuilder().create();
        gson.toJson(s);
        out.println(s);
        out.flush();
        System.out.println("envoyé");
        try {
            System.out.println("attente");
            String reponse = in.readLine();
            System.out.println("recu");
             System.out.println(reponse);
            return reponse;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            socketClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
