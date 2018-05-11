package Server;

import static Server.DAOCustomer.addCustomer;
import static Server.DAOCustomer.addCustomerProfile;
import static Server.DAOCustomer.deleteCustomer;
import static Server.DAOCustomer.loadCustomer;
import static Server.DAOCustomer.loadProfileById;
import static Server.DAOCustomer.updateCustomer;
import static Server.DAOHisto.loadHistoProduct;
import static Server.DAOLocation.loadLocations;
import static Server.DAOLocation.loadStoreAndAffectation;
import static Server.DAOProduct.addProduct;
import static Server.DAOProduct.deleteProduct;
import static Server.DAOProduct.loadProductStore;
import static Server.DAOProduct.updateProduct;
import static Server.DAOReturncustomer.addReturncustomer;
import static Server.DAOReturnprovider.addReturnprovider;
import static Server.DAOSale.addSale;
import static Server.DAOStore.addShop;
import static Server.DAOStore.deleteStore;
import static Server.DAOStore.loadStores;
import static Server.DAOStore.loadStoresNotAffectToLocation;
import static Server.DAOStore.updateShop;
import static Server.DAOType.getIdType;
import static Server.DAOType.loadType;
import static Server.ProductOrderHisto.order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
            int idStore;
            String designation;
            String description;
            int floor, rent, surface;
            int oldType1, oldType2;
            String newType1, newType2;
            int idNewType1, idNewType2;
            String localisation;
            //Customer attributes
            int idClient;
            int idProfile;
            String nom, prenom, adresse, cp, ville, mail, sexe;
            //Product attributes
            int idProduit, qte, idm;
            String libelle;
            float prix;
            //Histo attributes
            int ida, idb, idrc, idrf, idp, qteH;
            Date date;
            String action;
            //Sale attributes
            int idc, idps, qteS;
            //Return customer attributes
            int idcrc, idprc, qterc;
            //Return provider attributes
            int idcrf, idprf, qterf;
            // order attribute
            int idorder;
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
                case "addCustomerProfile":
                    System.out.println("add");
                    nom = m.get("nom");
                    prenom = m.get("prenom");
                    adresse = m.get("adresse");
                    cp = m.get("cp");
                    ville = m.get("ville");
                    mail = m.get("mail");
                    sexe = m.get("sexe");
                    System.out.println("added successfully");
                    //we now need to tell our customer's profile
                    //by default, a customer has all 10 profiles
                    List<Integer> profileList = new ArrayList<>();
                    for (int i = 1; i <= 10; i++) {
                        profileList.add(i);
                    }
                    addCustomerProfile(con, nom, prenom, adresse, cp, ville, mail, sexe, profileList);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
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
                case "listProfileById":
                    idClient = Integer.parseInt(m.get("idClient"));
                    List<String> listProfileById = loadProfileById(con, idClient);
                    System.out.println("requete");
                     {
                        try {
                            reponse = j.serialization(listProfileById);
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
                    List<Product> listProduct = loadProductStore(idProduit, con);
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
                    addProduct(designation, prix, qte, idm, con);
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
                    updateProduct(idProduit, designation, prix, qte, idm, con);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;

                case "deleteProduct":
                    idProduit = Integer.parseInt(m.get("idProduit"));
                    deleteProduct(idProduit, con);
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;

                case "listHisto":
                    idp = Integer.parseInt(m.get("idProduit"));
                    List<Histo> listHisto = loadHistoProduct(idp, con);
                    System.out.println("requete");
                     {
                        try {
                            reponse = j.serialization(listHisto);
                        } catch (IOException ex) {
                            Logger.getLogger(AccepterClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    send(reponse, out);
                    break;

                case "addSale":
                    System.out.println("add");
                    idps = Integer.parseInt(m.get("Produit_idProduit"));
                    qteS = Integer.parseInt(m.get("qte"));
                    idc = Integer.parseInt(m.get("Client_idClient"));
                    System.out.println("added successfully");
                    addSale(idps, qteS, idc, con);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;

                case "addReturncustomer":
                    System.out.println("add");
                    idprc = Integer.parseInt(m.get("Produit_idProduit"));
                    qterc = Integer.parseInt(m.get("qte"));
                    idcrc = Integer.parseInt(m.get("Client_idClient"));
                    System.out.println("added successfully");
                    addReturncustomer(idprc, qterc, idcrc, con);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;

                case "addReturnprovider":
                    System.out.println("add");
                    idprf = Integer.parseInt(m.get("Produit_idProduit"));
                    qterf = Integer.parseInt(m.get("qte"));
                    idcrf = Integer.parseInt(m.get("Fournisseur_idFournisseur"));
                    System.out.println("added successfully");
                    addReturnprovider(idprf, qterf, idcrf, con);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;

                case "listLocationAndStore":
                    System.out.println("list magasin avec et sans affectation ");
                    List<Location> listLocationStore = loadStoreAndAffectation(con);
                    System.out.println("end of request");
                    reponse = j.serialization(listLocationStore);
                    send(reponse, out);
                    break;
                case "affectStoreToLocation":
                    List<Store> listOfstore = loadStoresNotAffectToLocation(con);
                    AffectLocation a = new AffectLocation();
                    for (Store S : listOfstore) {
                        a.affectLocation(S, con);
                    }
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;
                case "listLocation":
                    List<Location> listOfLocation = loadLocations(con);
                    reponse = j.serialization(listOfLocation);
                    send(reponse, out);
                    break;

                case "appro":
                    idorder = Integer.parseInt(m.get("idBon"));
                    order(idorder, con);
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;
                case "deleteStore":
                    idStore = Integer.parseInt(m.get("idStore"));
                    deleteStore(idStore, con);

                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;
                case "listeTypeStore":
                    List<TypeStore> listTypeStore = loadType(con);
                    System.out.println("end of request");
                    reponse = j.serialization(listTypeStore);
                    send(reponse, out);
                    break;

                case "updateStore":
                    System.out.println("update store");
                    idStore = Integer.parseInt(m.get("idShop"));
                    designation = m.get("designation");
                    description = m.get("description");
                    floor = Integer.parseInt(m.get("niveau"));
                    rent = Integer.parseInt(m.get("loyer"));
                    surface = Integer.parseInt(m.get("surface"));
                    oldType1 = Integer.parseInt(m.get("oldType1"));
                    oldType2 = Integer.parseInt(m.get("oldType2"));
                    // list Old type 
                    List<Integer> oldLst = new ArrayList<>();
                    oldLst.add(oldType1);
                    oldLst.add(oldType2);
                    //list new type 
                    newType1 = m.get("newType1");
                    idNewType1 = getIdType(con, newType1);
                    newType2 = m.get("newType2");
                    idNewType2 = 0;
                    if (!newType2.equals("aucun")) {
                        idNewType2 = getIdType(con, newType2);
                    }
                    List<Integer> newLst = new ArrayList<>();
                    newLst.add(idNewType1);
                    newLst.add(idNewType2);
                    localisation = m.get("localisation");
                    // request to update shop
                    updateShop(idStore, designation, description, rent, surface, floor, localisation, oldLst, newLst, con);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;
                case "addStore":
                    System.out.println("Add store");
                    designation = m.get("designation");
                    description = m.get("description");
                    floor = Integer.parseInt(m.get("niveau"));
                    rent = Integer.parseInt(m.get("loyer"));
                    surface = Integer.parseInt(m.get("surface"));
                    //list new type 
                    newType1 = m.get("newType1");
                    idNewType1 = getIdType(con, newType1);
                    newType2 = m.get("newType2");
                    idNewType2 = 0;
                    if (!newType2.equals("aucun")) {
                        idNewType2 = getIdType(con, newType2);
                    }
                    List<Integer> typeLst = new ArrayList<>();
                    typeLst.add(idNewType1);
                    typeLst.add(idNewType2);
                    localisation = m.get("localisation");
                    // request to update shop
                    addShop(designation, description, rent, surface, floor, localisation, typeLst, con);
                    System.out.println("end of request");
                    reponse = j.serialization("ok");
                    send(reponse, out);
                    break;
                // Les méthodes à mario, merci de ne pas toucher :)
                case "affectClientToProfile":
                    AffectProfile affect = new AffectProfile();
                    affect.algo();
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
