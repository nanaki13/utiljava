/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille.serveur;

import libjeux.manille.object.JoueurInterface;
import libjeux.manille.object.Carte;
import libjeux.manille.object.Equipe;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
class JoueurSocket implements JoueurInterface, Runnable, Serializable {

    private static final long serialVersionUID = 1l;
    private transient final Socket socket;
    private transient final ObjectInputStream ois;
    private transient final ObjectOutputStream oos; 
    private String name;
    private Integer id;
    private Equipe equipe;
    private ServeurManille parent;
    private JoueurInterface equipier;

    public JoueurSocket(Socket accept, int id) throws IOException {
        this.socket = accept;
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
        this.id = id;
    }

    public String getName() {
//        try {       oos.writeObject("getName");
//            return (String)  ois.readObject();
//        } catch (IOException ex) {
//            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
        return name;
    }

    public void setCartes(List<Carte> cartes) {
        try {
            oos.writeObject("setCartes");
             wait0();
            oos.writeObject(cartes);
             wait0();
           
        } catch (IOException ex) {
            parent.notifyException(this, ex);
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carteJouer(Carte c, JoueurInterface j, Integer numTour) {
        try {
            oos.writeObject("carteJouer");
             wait0();
            oos.writeObject(c);
             wait0();
            oos.writeObject(j);
             wait0();
            System.out.println("carteJouer derveur Socket c :  +"+c+",  j : "+j);
            oos.writeObject(numTour);
            wait0();
//             if(!confirm()){
//                 carteJouer(c, j, numTour);
//            }
        } catch (IOException ex) {
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Carte joux() {
        try {
            oos.writeObject("joux");
             wait0();
            return (Carte) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Equipe getEquipe() {
        return equipe;
      
    }

    public void setEquipe(Equipe e) {
         equipe = e;
//        try {
//            oos.writeObject("setEquipe");
//             wait0();
//           
//            if(equipe.joueur1 == this){
//                equipier = equipe.joueur2;
//            }else{
//                equipier = equipe.joueur1;
//            }
//            oos.writeObject(e);
//             wait0();
//        } catch (IOException ex) {
//            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public Carte.CouleurCarte chooseAtout() {
        try {
            oos.writeObject("chooseAtout");
             wait0();
            return (Carte.CouleurCarte) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public JoueurInterface getEquipier() {
        return equipier;
//        try {
//            oos.writeObject("getEquipier");
//            return (JoueurInterface) ois.readObject();
//        } catch (IOException ex) {
//            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
    }

    public Carte.CouleurCarte chooseAtoutEnFace() {
        try {
            oos.writeObject("chooseAtoutEnFace");
             wait0();
            return (Carte.CouleurCarte) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void message(String string) {
        try {
            oos.writeObject("message");
             wait0();
            oos.writeObject(string);
             wait0();
        } catch (IOException ex) {
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setAtout(Carte.CouleurCarte atout, String string) {
        try {
            oos.writeObject("setAtout");
             wait0();
            oos.writeObject(atout);
             wait0();
            oos.writeObject(string);
             wait0();
        } catch (IOException ex) {
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        try {
            initConnection();
        } catch (IOException ex) {
           parent.notifyException(this, ex);
        } catch (ClassNotFoundException ex) {
             parent.notifyException(this, ex);
        }
    }

    private void initConnection() throws IOException, ClassNotFoundException {
        setId(id);
        oos.writeObject("getName");
         wait0();
        Object readObject = ois.readObject();
        name = (String) readObject;
        System.out.println(name+ " s'est connecté");
//              oos.writeObject("attente d'autre joueurs");
        oos.writeObject("message");
         wait0();
        oos.writeObject("attente d'autre joueurs ...");
         wait0();

    }

    public void setId(Integer id) {
        try {
            oos.writeObject("setId");
            wait0();
            oos.writeObject(id);
             wait0();

        } catch (IOException ex) {
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return name +" "+ equipe ;
    }
    
    public static void wait0(){
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
//    public boolean confirm() throws IOException{
//        return ois.readBoolean();
//    }

    public void setEquipes(Equipe mine, Equipe other) {
         try {
             equipe = mine;
             if(mine.getJoueur1() == this){
                 equipier = mine.getJoueur2();
             }else{
                 equipier = mine.getJoueur1();
             }
             
            oos.writeObject("setEquipes");
            wait0();
            oos.writeObject(mine);
             wait0();
              oos.writeObject(other);
             wait0();

        } catch (IOException ex) {
            Logger.getLogger(JoueurSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
