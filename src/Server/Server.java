package Server;

import static Server.DAOCustomer.addCustomer;
import static Server.DAOCustomer.deleteCustomer;
import static Server.DAOCustomer.loadCustomer;
import static Server.DAOCustomer.loadProfileById;
import static Server.DAOCustomer.updateCustomer;
import static Server.DAOHisto.loadHisto;
import static Server.DAOProduct.addProduct;
import static Server.DAOProduct.deleteProduct;
import static Server.DAOProduct.loadProductStore;
import static Server.DAOProduct.updateProduct;
import static Server.DAOSale.addSale;
import static Server.DAOSale.loadSale;
import static Server.DAOStore.loadStores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private static int port = 2009;
    private static ConnectionPool pool = new ConnectionPool();

    public static void main(String[] args) {
        ServerSocket server = null;
        pool.initPool();
        try {
            // Server launch
            server = new ServerSocket(port);
            // Infinite listening loop, waiting for a client request
            while (true) {
//                System.out.println(pool.getFreeConnection());
                Socket client = server.accept();
                Connection con = pool.getConnection();
                if (con != null) {
                    //a thread per client
                    Thread t1 = new Thread(new AccepterClient(client, con));
                    t1.start();
                }
//                System.out.println(pool.getFreeConnection());
            }
        } catch (IOException ex) {
            System.out.println("start server impossible.");
            // If for any reason there's an issue, the server will close
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
        //In for the input flows, Out for the output flows
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
            ex.printStackTrace();

        } finally {
            // closing INPUT and OUTPUT flows from the server side and giving back the connection to the pool 
            try {
                in.close();
                out.close();
                socket.close();
                Server.getPool().releaseConnection(con);
                System.out.println("client disconnected ");
                System.out.println(Server.getPool().getFreeConnection());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method process the request from the client
     *
     * @param request
     * @param out
     * @throws SQLException
     */
    public void receive(String request, PrintWriter out) throws SQLException {
        try {
            Json j = new Json();
            //We retrieve the action which can be "add", "delete" or "update"
            Map<String, String> m = j.deSerializationMap(request);
            String type = m.get("actionType");
            System.out.println(type);
            //Shop attributes
            String designation;
            String description;
            int idType;
            int idMagasin;
            //Customer attributes
            int idClient;
            String nom, prenom, adresse, cp, ville, mail, sexe;
            //Product attributes
            int idProduit, qte, idm;
            String libelle;
            float prix;
            //Histo attributes
            int ida,idb,idrc,idrf,idp,qteH;
            Date date;
            String action;
            //Sale attributes
            int idc,idps,qteS;
            String reponse = null;
            switch (type) {
                case "listCustomer":
                    List<Customer> listCustomer = loadCustomer(con);
                    System.out.println("requete");
                     {
                        try {
                            reponse = j.serialization(listCustomer);
                        } catch (IOException ex) {
                            Logger.getLogger(AccepterClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    send(reponse, out);
                    break;
                case "addCustomer":
                    System.out.println("add");
                    nom = m.get("nom");
                    prenom = m.get("prenom");
                    adresse = m.get("adresse");
                    cp = m.get("cp");
                    ville = m.get("ville");
                    mail = m.get("mail");
                    sexe = m.get("sexe");
                    System.out.println("added successfully");
                    addCustomer(con, nom, prenom, adresse, cp, ville, mail, sexe);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;
                case "updateCustomer":
                    System.out.println("add");
                    idClient = Integer.parseInt(m.get("idClient"));
                    nom = m.get("nom");
                    prenom = m.get("prenom");
                    adresse = m.get("adresse");
                    cp = m.get("cp");
                    ville = m.get("ville");
                    mail = m.get("mail");
                    sexe = m.get("sexe");
                    System.out.println("updated successfully");
                    updateCustomer(con, idClient, nom, prenom, adresse, cp, ville, mail, sexe);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;
                case "deleteCustomer":
                    idClient = Integer.parseInt(m.get("idClient"));
                    deleteCustomer(con, idClient);
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;
                case "listbyid":
                    idClient = Integer.parseInt(m.get("idClient"));
                    List<String> listbyid = loadProfileById(con, idClient);
                    System.out.println("requete");
                     {
                        try {
                            reponse = j.serialization(listbyid);
                        } catch (IOException ex) {
                            Logger.getLogger(AccepterClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    send(reponse, out);
                    break;
//                same thing for stores:
                case "listMagasin":
                    List<Store> l = loadStores(con);
                    System.out.println("requete");
                    reponse = j.serialization(l);
                    send(reponse, out);
                    break;
                    
                    case "listProduct":
                    idProduit = Integer.parseInt(m.get("idProduit"));
                    List<Product> listProduct = loadProductStore(idProduit,con);
                    System.out.println("requete");
                     {
                        try {
                            reponse = j.serialization(listProduct);
                        } catch (IOException ex) {
                            Logger.getLogger(AccepterClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    send(reponse, out);
                    break;
                    
                case "addProduct":
                    System.out.println("add");
                    designation = m.get("designation");
                    prix = Float.parseFloat(m.get("prix"));
                    qte = Integer.parseInt(m.get("qte"));
                    idm = Integer.parseInt(m.get("Magasin_idMagasin"));
                    System.out.println("added successfully");
                    addProduct(designation,prix,qte,idm,con);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;
                    
                case "updateProduct":
                    System.out.println("update");
                    idProduit = Integer.parseInt(m.get("idProduit"));
                    designation = m.get("designation");
                    prix = Float.parseFloat(m.get("prix"));
                    qte = Integer.parseInt(m.get("qte"));
                    idm = Integer.parseInt(m.get("Magasin_idMagasin"));
                    System.out.println("updated successfully");
                    updateProduct(idProduit,designation,prix,qte,idm,con);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;
                    
                case "deleteProduct":
                    idProduit = Integer.parseInt(m.get("idProduit"));
                    deleteProduct(idProduit,con);
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;
                    
                case "listHisto":
                    List<Histo> h = loadHisto(con);
                    System.out.println("requete");
                    reponse = j.serialization(h);
                    send(reponse, out);
                    break;
                 
                 case "listSale":
                    List<Sale> s = loadSale(con);
                    System.out.println("requete");
                    reponse = j.serialization(s);
                    send(reponse, out);
                    break;                         
                    
                case "addSale":
                    System.out.println("add");
                    idps = Integer.parseInt(m.get("Produit_idProduit"));
                    qteS = Integer.parseInt(m.get("qte"));
                    idc = Integer.parseInt(m.get("Client_idClient"));
                    System.out.println("added successfully");
                    addSale(idps,qteS,idc,con);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;   
                    

            }
        } catch (IOException ex) {
            Logger.getLogger(AccepterClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method will send the string to the client
     *
     * @param S
     * @param out
     */
    public void send(String S, PrintWriter out) {
        out.println(S);
        out.flush();
    }

}
