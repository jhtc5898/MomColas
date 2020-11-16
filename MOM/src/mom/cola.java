/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author johne
 */
public class cola implements Runnable 
{

  

    @Override
    public void run() 
    {
        JOptionPane.showMessageDialog(null, "Levantado Midelware");
         try {
            // System.out.println("Estoy A La Escucha");
            ServerSocket colaMom = new ServerSocket(9997);
            Hashtable<String, Integer> datos = new Hashtable<>();// De esta forma asocio a una persona con su edad en el diccionario
            while(true)
            {
            Socket misocket = colaMom.accept();//Acepte conexiones
            DataInputStream flujoentrada = new DataInputStream(misocket.getInputStream());
            String mensaje= flujoentrada.readUTF();
           
            datos.put(mensaje, 0);
            System.out.println(datos.toString());
            //ArrayList<String> cola = new ArrayList<>();
            //cola.add(mensaje);
            //System.out.println(mensaje.toString());
           envioserver(datos);
            misocket.close();
            
            }
            
           
            
            
        } catch (IOException ex) {
            Logger.getLogger(cola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void envioserver(Hashtable<String, Integer> cola)
    {
        try
            {
                //
                try ( // Conexion Con El Server
                    Socket socket = new Socket( "192.168.1.120", 9999 ))
                {
                    DataOutputStream flujoSalida= new DataOutputStream(socket.getOutputStream());
                    //Mensaje Que Se Envia al Servidor
                    flujoSalida.writeUTF(cola.toString());
                    flujoSalida.close();
                }
            }
            catch( Exception e )
            {
                envioserver(cola);
            }
    }
    
}
