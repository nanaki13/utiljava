/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture.core;

import com.jonathan.application.AbstractControleur;
import mousecapture.action.EffacerAction;
import com.jonathan.json2.JsonWriter;
import com.jonathan.lib.graphique.JpanelGiver;
import com.jonathan.application.Context;
import com.jonathan.application.ContextProvider;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mousecapture.AnimationArdoise;
import mousecapture.Ardoise;
import mousecapture.DataStoring;
import mousecapture.GroupeMouvement;
import mousecapture.MouvementData;
import mousecapture.TableMouvementData;
import mousecapture.Point;
import mousecapture.Timer;
import mousecapture.action.SaveMouvement;
import mousecapture.view.listener.MousePressedListener;

/**
 *
 * @author jonathan
 */
public class MouseCapture extends AbstractControleur{

//    public  List<MouvementData> mvts = Collections.synchronizedList(new LinkedList<>());
//    public  List<GroupeMouvement> grps = Collections.synchronizedList(new LinkedList<>());
   
  

   

    
    
  
    private static void handle(String where, Exception e) {
        System.err.println(where + ":");
        e.printStackTrace(System.err);
    }

    public  void saveMvt() {
        FileWriter fw = null;
        if (anim == null) {
            anim = JOptionPane.showInputDialog(ardoise, "enter un nom pour l'animation");
        }
        try {
            DataStoring.set(anim + "Data", contextProvider.getContext().getObject((String) null));
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MouseCapture.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            File f = new File("js");
            if (f.exists()) {
                f = new File("js/animations_" + anim + ".js");
                fw = new FileWriter(f, true);
            } else {
                f.mkdir();
                f = new File("js/animations_" + anim + ".js");
                f.createNewFile();
                fw = new FileWriter(f);
            }

            jsonWriter.setOut(fw);
            Integer animCount = 0;
            try {
                animCount = DataStoring.get("animCount_" + anim);
                if (animCount == null) {
                    animCount = 0;

                }
            } catch (ClassNotFoundException ex) {
                handle("saveMvt", ex);
            }
            jsonWriter.writeVarDeclaration("anime" + animCount);
            try {
                DataStoring.set("animCount" + anim, (animCount + 1));
            } catch (ClassNotFoundException ex) {
                handle("saveMvt", ex);
            }
            jsonWriter.write(mvts);
            jsonWriter.writeEndLine();
            fw.close();
            fw = new FileWriter("./js/animData" + anim + ".js");
            fw.append("var animations=[];\n");
            for (Integer i = 0; i < animCount + 1; i++) {
                fw.append("animations.push(anime").append(i.toString()).append(");\n");
            }
            fw.close();
            InputStream resourceAsStream = MouseCapture.class.getResourceAsStream("/template/lecteur.html");
            BufferedReader br = new BufferedReader(new InputStreamReader(resourceAsStream));
            String line;
            StringBuilder out = new StringBuilder();
            while ((line = br.readLine()) != null) {
                out.append(line).append('\n');
            }
            br.close();
            resourceAsStream.close();
            String replace = out.toString().replace("<!--{SCRIPT}-->", "<script  type=\"text/javascript\" src=\"../js/animations_" + anim + ".js\"></script><script  type=\"text/javascript\" src=\"../js/animData" + anim + ".js\"></script>");
            try (FileWriter fwHtml = new FileWriter("html/animation" + anim + ".html")) {
                fwHtml.write(replace);
            }
            resourceAsStream = MouseCapture.class.getResourceAsStream("/html/motion.js");
            br = new BufferedReader(new InputStreamReader(resourceAsStream));

            try (FileWriter fwHtml = new FileWriter("js/motion.js")) {
                while ((line = br.readLine()) != null) {
                    fwHtml.append(line).append('\n');
                }

            }
            br.close();
            resourceAsStream.close();
        } catch (IOException e) {
            handle("saveMvt", e);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    
    private  void closingOperation() {
        System.exit(0);
    }

    
    public  void mousePressed(MouseEvent e) {
        synchronized (mutex) {
            press = true;
            coutPoint = 0;
        }
    }

    public  void mouseReleased(MouseEvent e) {
        synchronized (mutex) {
//                    if (play) {
            press = false;
            panelMouvementData.add(currentMvt);
            countMouvement++;
//                    }

        }
    }

    public  void dynamicAnimationFinished() {
        play = !play;
//                countMouvement = 0;
        if (play) {
            recB.setText("stop");
//            ardoise.setAnimationArdoise(AnimationArdoise.DYNAMIC);

        } else {
            recB.setText("play");
//            ardoise.setAnimationArdoise(AnimationArdoise.STATIC);

        }
    }

    public  void selectedItemInTable() {
        if(effaceSelBut == null){
             effaceSelBut = new JButton(effaceAction);
             
             east.add(JpanelGiver.makeJPanelWithIn(effaceSelBut),BorderLayout.SOUTH);
             east.validate();
             east.repaint();
        }
//        effaceAction.setSelectedIndexs(selectedIndexs);
        east.setVisible(true);
       
        
        
    }

    public  void effacerAction() {
       panelMouvementData.effacerAction();
        
    }

}
