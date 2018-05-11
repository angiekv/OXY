/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Views.CustomerView;
import Views.HomeView;
import Views.StoreView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 *
 * @author Aberkane
 */
public class ControlHome implements ActionListener, WindowListener {
   
    private HomeView view ;
    public ControlHome(HomeView view) {
        this.view = view;
    }

    //when we click
    @Override
    public void actionPerformed(ActionEvent e) {

        // show Store
        if (view.getStore() == e.getSource()) {

            StoreView viewStore = new StoreView(view.getClient());

            viewStore.setVisible(true);

        }
        //show customer 
        if (view.getCustomer() == e.getSource()) {

                CustomerView viewCustomer= new CustomerView(view.getClient());

                viewCustomer.setVisible(true);
                
           

        }

    }

    /**
     * this function will refresh the list of magasin
     */

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Deconexion");
        view.getClient().stopConnection();

    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }
}

