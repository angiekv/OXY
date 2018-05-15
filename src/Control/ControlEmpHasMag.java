/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.EmpHasMag;
import Model.EmpHasMagTableModel;
import Views.EmpHasMagView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author BaDiakite
 */
public class ControlEmpHasMag implements ActionListener{
    private EmpHasMagView vue;
    private EmpHasMagTableModel m;

    public ControlEmpHasMag(EmpHasMagView vue, EmpHasMagTableModel m) {
        this.vue = vue;
        this.m = m;
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(vue.getB() == e.getSource()){
            try {
                System.out.println("cpossible");
                vue.getDaorent().calcul();
            } catch (IOException ex) {
                Logger.getLogger(ControlEmpHasMag.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.refreshEmpHasMag();
        }
    }
    
    public void refreshEmpHasMag() {

        try {
            List<EmpHasMag> p = vue.getDAOEmpHasMag().loadEmpHasMag();

            // create the model and update the "table"
            EmpHasMagTableModel model = new EmpHasMagTableModel(p);

            vue.getTable().setModel(model);
            this.m = model;
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(vue, "Error: " + exc, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }


}
