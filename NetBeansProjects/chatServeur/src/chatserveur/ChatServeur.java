/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Message;

/**
 *
 * @author jonathan
 */
public class ChatServeur extends Thread {

    private ServerSocket serveurSocket;
    private List<ClientSocketProcessor> clients;

    public ChatServeur(int port) throws IOException {
        serveurSocket = new ServerSocket(port);
        clients = Collections.synchronizedList(new LinkedList<>());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        try {
            System.out.println("démarage...");
            ChatServeur chatServeur = new ChatServeur(Integer.parseInt(args[0]));
            chatServeur.start();

        } catch (Exception e) {
            System.out.println("erreur : " + e.getMessage());
            PrintStream printStream = new PrintStream(System.out);
            e.printStackTrace(printStream);
        }

    }

    public void run() {
        try {
            System.out.println("attente de client");
            while (true) {
                
                Socket clientSocket = serveurSocket.accept();
                ClientSocketProcessor csp = new ClientSocketProcessor(clientSocket);
                clients.add(csp);
                csp.start();

            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServeur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void receive(Message m, ClientSocketProcessor aThis) {
        for (ClientSocketProcessor c : clients) {

            c.receive(m);

        }
    }

    public class ClientSocketProcessor extends Thread {

        private Socket clientSocket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private String nameClient;

        public ClientSocketProcessor() {
        }

        public ClientSocketProcessor(Socket clientSocket) throws IOException {
            this.clientSocket = clientSocket;
            this.ois = new ObjectInputStream(clientSocket.getInputStream());
            this.oos = new ObjectOutputStream(clientSocket.getOutputStream());
        }

        public void run() {
            try {
                System.out.println("nb client : " + clients.size());
                while (true) {

                    System.out.println("boucle");
                    Message m = (Message) ois.readObject();
                    nameClient = m.getSource();
                    System.out.println(m);
                    ChatServeur.this.receive(m, this);

                }
            } catch (IOException ex) {
                clients.remove(this);
                for(ClientSocketProcessor c : clients){
                    Message m = new Message(this.nameClient+" c'est déconnecté");
                    m.setSource("admin");
                    c.receive(m);
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChatServeur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void receive(Message m) {
            try {
                oos.writeObject(m);
            } catch (IOException ex) {
                clients.remove(this);
            }
        }
    }
}
