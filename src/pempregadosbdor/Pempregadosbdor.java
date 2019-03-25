/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pempregadosbdor;

import java.sql.SQLException;

/**
 *
 * @author oracle
 */
public class Pempregadosbdor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Conexion con=new Conexion();
        con.buscar_por_clave(4);
//        con.insertar(4,"pepito",30,1000);
//        con.listar();
        con.close();
        
    }
    
}
