/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.client;

import libjeux.util.reflect.SimpleMethodCaller;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import libjeux.manille.object.JoueurDefault;

/**
 *
 * @author jonathan
 */
public class JoueurClient extends JoueurDefault  {

    private static void rebour(int i) {
        for (int j = i; j != -1; j--) {
            try {
                if(j!=i)
                    Thread.sleep(1000);
                System.out.print(j + "..");
            } catch (InterruptedException ex) {
                Logger.getLogger(JoueurClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Socket s;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private SimpleMethodCaller caller;

   
     public JoueurClient(String name) throws IOException {
        super(name);
         
    }

     public JoueurClient() throws IOException {
        super();
        
        
    }

   

    public void run() throws IOException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    
            this.s = new Socket("localhost", 3214);

        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());

        caller = new SimpleMethodCaller(this, oos, ois);
        
            caller.start();

    }

    public static void main(String[] args) {
        JoueurClient j = null;
        while (true) {
            try {
                if(j!=null){
                    j = new JoueurClient(j.getName());
                    
                }else{
                    j = new JoueurClient();
                    j.getName();
                }
                
                j.run();
            } catch (IOException ex) {
                System.out.println("problème de connexion");
                System.out.println("reconnexion dans : ");
                rebour(5);
                System.out.println("retry : ");
//                Logger.getLogger(JoueurClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JoueurClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(JoueurClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(JoueurClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(JoueurClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


}
