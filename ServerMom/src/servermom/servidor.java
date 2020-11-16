/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermom;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author johne
 */
public class servidor implements Runnable  
{

    public servidor() 
    {
       Thread hilo = new Thread(this);
       
       hilo.start();
    }
    

    @Override
    public void run()
    {
       JOptionPane.showMessageDialog(null, "Levantado Server");
      consumerservidor();
    
    }
    
    public void  consumerservidor()
    {
        try {
            // System.out.println("Estoy A La Escucha");
            ServerSocket server = new ServerSocket(9999);
             while(true)
            {
            Socket misocket = server.accept();//Acepte conexiones
            DataInputStream flujoentrada = new DataInputStream(misocket.getInputStream());
            
            String mensaje= flujoentrada.readUTF();
            VentanaServerMom ventana =  new VentanaServerMom();
            ventana.mensaje(mensaje);
            System.out.println("Mensaje Del Cliente Pasado Por Mom:");
            System.out.println(mensaje);
            
            misocket.close();
            }
        } catch (IOException ex) 
        {
            
           Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
