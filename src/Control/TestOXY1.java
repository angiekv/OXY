/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Views.AllocationView;
import Views.CustomerView;
import java.awt.EventQueue;

/**
 *
 * @author Michel
 */
public class TestOXY1 {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //AllocationView frame = new AllocationView();
                    //frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
