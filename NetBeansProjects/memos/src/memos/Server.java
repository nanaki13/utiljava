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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class Server extends Thread {

    private ServerSocket ss;

    public Server() {
        try {
            ss = new ServerSocket(8080);

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                Socket accept = ss.accept();
                (new ThreadClient(accept)).start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static class ThreadClient extends Thread implements Command {

        private final Socket socket;
        private PrintWriter pw;

        public ThreadClient(Socket accept) {
            this.socket = accept;
        }

        public void run() {
            try {
                System.out.println("un client");
                try (OutputStream outputStream = socket.getOutputStream()) {
                    pw = new PrintWriter(outputStream);
                    pw.append("salut\n");
                    pw.flush();
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String readLine = br.readLine();
                    System.out.println("readLine : " + readLine);

                    while (true) {
                        execMethod(readLine);
                        readLine = br.readLine();
                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void exit(String[] args) {
            pw.append("salut");
            pw.flush();
            pw.close();
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         public void ditBonjour(String[] args){
             pw.append("bonjour, vous êtes connecté\n");
            pw.flush();
//            pw.close();
           
         }
         
          public void getMemos(String[] args){
            try {
                System.out.println(Manger.getMemos().toString()+"\n");
                
                pw.append(Manger.getMemos().toString()+"\n");
                pw.flush();
            } catch (ManagerException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            pw.close();
           
         }

    }

    public static void main(String[] args) throws InterruptedException {
        Server s = new Server();
        s.start();
        Client c = new Client();
        c.start();

    }

}
