/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import message.Message;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class ClientControleur extends Socket implements Runnable {

    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String name;
    private BufferedReader bis;
    private VueInterface vue;
    private final Thread sender;

    public static void main(String[] args) {
        try {
            ClientControleur clientConsole = new ClientControleur("badway.ddns.net", 1234);
            Thread t = new Thread(clientConsole);
            clientConsole.vue = new VueJFrame("- chat - ");
            t.start();
        } catch (IOException ex) {
            Logger.getLogger(ClientControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ClientControleur(String host, int port) throws IOException {
        super(host, port);

         
        sender = new Thread(new Runnable() {

//            @Override
            public void run() {
               
                vue.print("bienvenu " + name);
                System.out.println("bienvenu " + name);
                try {
                    while (true) {

                        String readLine = vue.read();
                        if(readLine!=null && readLine.length()!=0)
                            ClientControleur.this.sendMessage(readLine);

                    }
                } catch (IOException ex) {
                    Logger.getLogger(ClientControleur.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    public void sendMessage(String text) throws IOException {
        Message m = new Message(text);
        m.setSource(name);
        oos.writeObject(m);
    }

//    @Override
    public void run() {
        try {
            name = vue.getClientName();
            oos = new ObjectOutputStream(getOutputStream());
            Message m = new Message(name + " vient d'arrivé");
            m.setSource("info");
            oos.writeObject(m);
            ois = new ObjectInputStream(getInputStream());

            bis = new BufferedReader(new InputStreamReader(System.in));
            sender.start();
            while (true) {

                Object readObject = ois.readObject();
                vue.print(((Message) readObject).getSource() + " : " + ((Message) readObject).getContent());
                System.out.println(((Message) readObject).getSource() + " : " + ((Message) readObject).getContent());

            }
        } catch (IOException ex){
            
                }catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientControleur.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("erreur grave comme tu vois, salut. status code 67");
        }
    }

}
