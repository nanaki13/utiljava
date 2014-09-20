/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memos;

import com.jonathan.lib.Utilitaires;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class Memos implements Command {

    private static List<MemoDao> l;
    private static final Logger logger = Logger.getLogger(Memos.class.toString());
    private static int idLibre;

    static {
        try {
            logger.setUseParentHandlers(false);
            FileHandler fileHandler = new FileHandler();
//            fileHandler.
            Arrays.asList(logger.getHandlers()).forEach((e) -> logger.removeHandler(e));
            logger.addHandler(new FileHandler());
//            logger.
        } catch (IOException ex) {
            Logger.getLogger(Memos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Memos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Memos memos = new Memos();
        try {
            l = Manger.getMemos();
//            System.out.println(l);
            idLibre = Id.getFreeId(l);
        } catch (ManagerException ex) {
            l = new LinkedList<>();
        }
        print("taper show help pour afficher l'aide");
        Command.run(memos);   
//        Command.main(args);
        
    }

    private static void afficheInstruction() {
        System.out.println("exit : quitter\nfindkeys a,b,c : trouver les mémos avec les mots clefs");
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

    private static void print(String entrer_le_memos) {
        System.out.println(entrer_le_memos);
    }

    private static String getStringUtil(String question) {
        print(question);
        return getStringUtil();
    }

    public void save(String[] args) {
        try {
            Manger.saveMemos(l);
            System.out.println("sauvegarde ok");
        } catch (ManagerException ex) {
            Logger.getLogger(Memos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    public void removeAll(String[] args) {
        l.clear();
    }

    @Override
    public void exit(String[] args) {
        try {
            Manger.saveMemos(l);

        } catch (ManagerException ex) {
            Logger.getLogger(Memos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    public void show(String[] args) {
        if (args.length < 2) {
            System.out.println("show what ?");
            return;
        }
        if (args[1].equals("help")) {
            afficheInstruction();
        }else  if (args[1].equals("all")) {
            l.forEach((e)-> System.out.println(e+"\n\n======================="));
        }
    }

    public void add(String[] args) {
        String readString = getStringUtil("entrer le mémo");
        MemoDao m = new MemoDao(readString, LocalDate.now(),idLibre);
        idLibre++;
        l.add(m);
        readString = getStringUtil("mot clefs ");
        String[] split = readString.split(",");
        for (String split1 : split) {
            m.getMotClef().add(split1.trim());
        }
    }

    public void findkeys(String[] args) {
        
        for (MemoDao m : l) {
            List<String> asList = Arrays.asList(args[1].split(","));
            if (m.getMotClef().containsAll(asList)) {
                System.out.println(m);
            }

        }
    }
    
    public void size(String[] args) {
        System.out.println("taille des mémos : "+l.size());
    }

}
