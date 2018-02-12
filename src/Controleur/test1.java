/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.ConnectionPool;
import java.sql.Connection;

/**
 *
 * @author mario
 */
public class test1 {
    
        public static void main(String[] args){
            ConnectionPool pool = new ConnectionPool();
                pool.InitPool();
                
                Connection p = pool.getConnection();
                Connection p1 = pool.getConnection();
                Connection p2 = pool.getConnection();
                Connection p3 = pool.getConnection();
                System.out.println(pool.getFreeConnection());
        }
    
}
