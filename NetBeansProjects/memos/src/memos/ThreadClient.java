/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memos;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
class ThreadClient extends Thread implements Command {

        private final Socket socket;
        private int idd;
        private static int id_0;
        private Connection c;
        private AbstractServer server;
        static{
            id_0 = 0;
        }

        public int getIdd() {
            return idd;
        }
        
        public ThreadClient(Socket accept,AbstractServer server) {
            this.socket = accept;
            this.server = server;
            idd = id_0;
            id_0++;
            try {
                c = new Connection(socket);
            } catch (IOException ex) {
                Logger.getLogger(AbstractServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void run() {
            try {
//                c.send("salut");
                System.out.println("un client");
                while(true){
                   MessageClient messageClient = (MessageClient) c.recoit();
                   System.out.println("un message client: "+messageClient);
                   MessageClientProcessor mcp = new MessageClientProcessor(this, server);
                   mcp.exec(messageClient);
                    
                }
              
            } catch (IOException ex) {
                Logger.getLogger(AbstractServer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AbstractServer.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    Socket getSocket() throws IOException {
        return socket;
    }

    public Connection getC() {
        return c;
    }
    

        
    }
