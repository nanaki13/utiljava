/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class Client implements Command{
    private Socket socket;
    
    public static void main(String[] args){
         Client c = new Client();
         c.start();
             
         
     }
    private BufferedReader br;
    public Client(){
        try {
            socket = new Socket("127.0.0.1", 8080);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public void start(){
        try {
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
             br = new BufferedReader(isr);
//            System.out.println((char)socket.getInputStream().read());
              System.out.println(br.readLine());
              String comd = getStringUtil();
              while(true){
                 Object o = execMethod(comd);
                  System.out.println(o);
                 comd = getStringUtil();
              }
           
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String ditBonjour(String args[]){
        try {
            socket.getOutputStream().write("ditBonjour\n".getBytes());
            socket.getOutputStream().flush();
            return br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "erreur...";
    }
    public String getMemos(String[] args){
     try {
            socket.getOutputStream().write("getMemos\n".getBytes());
            socket.getOutputStream().flush();
            return br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "erreur...";
         }
    
     public void close(){
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
      private static String getStringUtil() {
        StringBuilder b = new StringBuilder();
        try {
            InputStream is = System.in;
            char c = (char) is.read();
            while (c != '\n') {
                b.append(c);
                c = (char) is.read();
            }
        } catch (IOException ex) {
            Logger.getLogger(Memos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b.toString();
    }
     
     
}
