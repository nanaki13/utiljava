/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class ServeurManille implements ActionManilleListener{

    private  ServerSocket serverSocket;
    private  List<JoueurSocket> joueurs;
    private  int count = 0;

    public static void main(String[] args) throws IOException{
        ServeurManille serveurManille = new ServeurManille();
        serveurManille.run();
    }
    public void run() throws IOException {
        serverSocket = new ServerSocket(3214);
        joueurs = new ArrayList<JoueurSocket>();

        while (true) {
            try {
                Socket accept = serverSocket.accept();
                JoueurSocket j = new JoueurSocket(accept, count);
                j.run();
                joueurs.add(j);
                System.out.println("jouers : " + joueurs);
                for (JoueurInterface ji : joueurs) {
                    if (ji != j) {
                        ji.message(j.getName() + " vous à rejoint. encore " + (4 - joueurs.size()));
                    } else {
                        if (joueurs.size() != 1) {
                            StringBuilder builder = new StringBuilder();
                            boolean first = true;
                            for (JoueurInterface ji2 : joueurs) {
                                
                                if(!first){
                                    builder.append(", ");
                                }
                                first = false;
                                if (ji2 != j) {
                                    builder.append(ji2.getName());
                                }
                            }
                            j.message("vous avez rejoint : "+builder+".");
                        }  
                    }
                }
                if (joueurs.size() == 4) {
                    Manille m;
                    m = new Manille(this);
                    m.setJoueurs(joueurs.get(0), joueurs.get(1), joueurs.get(2), joueurs.get(3));
                     System.out.println("jouers : " + joueurs);
                    m.run();
                    joueurs.clear();
                }
                count++;

//                        Thread joueurThread = new Thread(j);
//                        joueurThread.start();
//            j.s
            } catch (IOException ex) {
                Logger.getLogger(ServeurManille.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    void notifyException(JoueurSocket aThis, Exception ex) {
        joueurs.remove(aThis);

    }

    public void settingEquipe(JoueurInterface j11, JoueurInterface j21, JoueurInterface j12, JoueurInterface j22, Equipe e1, Equipe e2) {
            
            ((JoueurSocket )j11).setEquipe(e1);
            ((JoueurSocket )j21).setEquipe(e1);
            ((JoueurSocket )j12).setEquipe(e2);
            ((JoueurSocket )j22).setEquipe(e2);
    }
}
