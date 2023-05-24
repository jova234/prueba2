/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba2;

import java.sql.Connection;

public class Prueba2 {

   
    public static void main(String[] args) {
       Conexion con=Conexion.getInstance();
        Connection objetoConexion = con.getConexion();
        
        /*interfaz formulario = new interfaz();
        formulario.setVisible(true);*/
        
        InterfazModelos formulario=new InterfazModelos();
        formulario.setVisible(true);
    }
    
}
