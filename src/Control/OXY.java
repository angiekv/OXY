/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Views.MagasinView;
import java.awt.EventQueue;

/**
 *
 * @author aberkane
 */
public class OXY {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MagasinView fenetre = new MagasinView();
                    
                    fenetre.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
