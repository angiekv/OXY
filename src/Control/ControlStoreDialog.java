/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.DAOStore;
import Model.TypeStore;
import Views.StoreDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class ControlStoreDialog implements ActionListener {

    private StoreDialog vue;
    private DAOStore DAOStore;

    public ControlStoreDialog(StoreDialog vue, DAOStore D) {
        this.vue = vue;
        this.DAOStore = D;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vue.getSave() == e.getSource()) {
//            sauvegarderMagasin();

            if (vue.isUpdateMode()) {
                List<Integer> lstOld = new ArrayList<>();
                for(TypeStore t : vue.getStoreSelected().getListIdType()){
                    lstOld.add(t.getIdType());
                }if(lstOld.size()==1){
                    lstOld.add(0);
                }
                //element to in the dialog 
                int idStore = vue.getStoreSelected().getId();
                String designation = vue.getDesignationTextField().getText();
                String description = vue.getDescriptionTextArea().getText();
                int rent = Integer.parseInt(vue.getRentTextField().getText());
                int floor = Integer.parseInt(vue.getFloorTextField().getText());
                int surface = Integer.parseInt(vue.getSurfaceTextField().getText());
                String localisation = null;
                if(vue.getIndiferrentRadio().isSelected()){
                    localisation = vue.getIndiferrentRadio().getText();
                }else{
                    localisation = vue.getSortieEntreeRadio().getText();
                }
                
//                System.out.println(localisation);
                List<String> lstNew = new ArrayList<>();
                String t1 = vue.getLstType().getSelectedItem().toString();
                String t2 = vue.getLstType2().getSelectedItem().toString();
                if(t2.equals("")){
                    t2="aucun";
                }
                lstNew.add(t1);
                lstNew.add(t2);
                System.out.println(lstNew.toString());
//
                try {
                    vue.getDaoStore().updateStore(idStore, designation, description, rent, surface, floor, localisation, lstOld, lstNew);
                } catch (IOException ex) {
                    Logger.getLogger(ControlStoreDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                JOptionPane.showMessageDialog(vue,
                        "Modification effectué",
                        "Magasin modifié",
                        JOptionPane.INFORMATION_MESSAGE);

                vue.setVisible(false);
                vue.dispose();
                // refresh the view  
                vue.getStoreView().getControleur().refreshStore();

            }else{
                //element to in the dialog 
                String designation = vue.getDesignationTextField().getText();
                String description = vue.getDescriptionTextArea().getText();
                int rent = Integer.parseInt(vue.getRentTextField().getText());
                int floor = Integer.parseInt(vue.getFloorTextField().getText());
                int surface = Integer.parseInt(vue.getSurfaceTextField().getText());
                String localisation = null;
                if(vue.getIndiferrentRadio().isSelected()){
                    localisation = vue.getIndiferrentRadio().getText();
                }else{
                    localisation = vue.getSortieEntreeRadio().getText();
                }
                
//                System.out.println(localisation);
                List<String> lstNew = new ArrayList<>();
                String t1 = vue.getLstType().getSelectedItem().toString();
                String t2 = vue.getLstType2().getSelectedItem().toString();
                if(t2.equals("")){
                    t2="aucun";
                }
                lstNew.add(t1);
                lstNew.add(t2);
                
                try {
                    vue.getDaoStore().addStore(designation, description, floor, surface, rent, localisation, lstNew);
                } catch (IOException ex) {
                    Logger.getLogger(ControlStoreDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                JOptionPane.showMessageDialog(vue,
                        "Ajout effectué",
                        "Magasin Ajouter",
                        JOptionPane.INFORMATION_MESSAGE);

                vue.setVisible(false);
                vue.dispose();
                // refresh the view  
                vue.getStoreView().getControleur().refreshStore();
                
            }


        }

        if (vue.getCancel() == e.getSource()) {
            vue.setVisible(false);
            vue.dispose();

        }

    }


}
