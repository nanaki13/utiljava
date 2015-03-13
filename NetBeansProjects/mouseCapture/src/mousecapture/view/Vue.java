/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture.view;

import com.jonathan.application.AbstractVue;
import com.jonathan.application.Controleur;
import com.jonathan.application.UserRequest;
import com.jonathan.json2.JsonWriter;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mousecapture.AnimationArdoise;
import mousecapture.Ardoise;
import mousecapture.DataStoring;
import mousecapture.MouvementData;
import mousecapture.Point;
import mousecapture.TableMouvementData;
import mousecapture.Timer;
import mousecapture.action.EffacerAction;
import mousecapture.action.SaveMouvement;
import mousecapture.core.MouseCapture;
import mousecapture.view.listener.MousePressedListener;

/**
 *
 * @author jonathan
 */
public class Vue extends AbstractVue{

      private  JFrame jFrame;
       private  Ardoise ardoise;
        private  final MousePressedListener mousePressedListener = new MousePressedListener();
    private  TableMouvementData panelMouvementData;
     private  Container contentPane;
    private  String itemSelected;
     private   SaveMouvement saveMouvement ;
    private  EffacerAction effaceAction = new EffacerAction("effacer");
      private  JButton recB;
    private  JPanel east;
    
    private  JButton effaceSelBut;
     public  MouvementData currentMvt;
    public  Integer mvtCount = 0;
    public  int dt = 1000 / 10;
   
    private  long iniTimeMove;
    private  int countMouvement;
    private  boolean play = false;
    private  boolean press = false;
    private  int coutPoint = 0;
    public  final Object mutex = new Object();
   
    private  String anim;
   
  
    private  boolean first = true;
   
    private  int countTimerFirst;
    @Override
    public void receiveData(Object data) {
    }

    @Override
    public void show() {
         jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

//        ardoise.setMouvements(mvts);
        countTimerFirst = 0;
        Timer t = new Timer(() -> {
            if (countTimerFirst < 5 && first) {
                countTimerFirst++;
                ardoise.repaint();
                if (countTimerFirst >= 5) {
                    first = false;
                }
            }

            boolean pressQuick;
            synchronized (mutex) {
                pressQuick = press;
            }

            if (pressQuick) {
                java.awt.Point mousePosition = ardoise.getMousePosition();
                if (mousePosition != null) {
                    rec(mousePosition.x, mousePosition.y);
                }
            }
        }, dt);
        t.start();
        ardoise.revalidate();
        ardoise.validate();
        ardoise.repaint();
        jFrame.pack();
    }
    public Vue(){
        
      
       
        jFrame = new JFrame("mouse capture");
        jFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                UserRequest request = UserRequest.CLOSING_REQUEST;
                controleur.userRequest(request);
            }

        });
        populate(jFrame);
       
    }
    private  void populate(JFrame j) {
        ardoise = new Ardoise();
        ardoise.setPreferredSize(new Dimension(700, 700));
        contentPane = j.getContentPane();
        contentPane.setLayout(new BorderLayout(3, 3));
        contentPane.add(ardoise);
        JButton jb = new JButton(saveMouvement);
        JPanel south = new JPanel();
        south.add(jb);
        //bouton qui lit un enregistrement
        recB = new JButton();
        recB.setAction(new AbstractAction("play") {

            @Override
            public void actionPerformed(ActionEvent e) {
                play = !play;
//                countMouvement = 0;
                if (play) {
                    recB.setText("stop");
                    ardoise.setAnimationArdoise(AnimationArdoise.DYNAMIC);

                } else {
                    recB.setText("play");
                    ardoise.setAnimationArdoise(AnimationArdoise.STATIC);

                }
            }
        ;
        });
        south.add(recB);
//        p = new JPanel();
        jb = new JButton(new AbstractAction("efface") {

            @Override
            public void actionPerformed(ActionEvent e) {
//                ardoise.clear();
                mvts.clear();
                panelMouvementData.removeAllMvts();
                ardoise.repaint();
            }
        });
        south.add(jb);
        jb = new JButton(new AbstractAction("load") {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (itemSelected != null && !itemSelected.isEmpty()) {

                    (new Timer(() -> {
                        try {
                            mvts.clear();
                            panelMouvementData.removeAllMvts();

                            Collection<MouvementData> get = DataStoring.get(itemSelected + "Data");
                            if (get != null) {
                                mvts.addAll(get);
                                mvts.stream().forEach((mv) -> {
                                    panelMouvementData.add(mv);
                                });
                            }
                            ardoise.validate();
                            ardoise.repaint();
//                                mvts = DataStoring.get(itemSelected + "Data");
//                                ardoise.clear();

                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(MouseCapture.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }, 1000, 1)).start();

                }

            }
        });
        south.add(jb);
        File f = new File("./data");
        File[] listFiles = f.listFiles((File dir, String name) -> {
//            System.out.println("name : " + name);
            return name.contains("Data");
        });
        JComboBox<String> jComboBox = new JComboBox<>();
        jComboBox.addItem("");
        for (File ff : listFiles) {
            jComboBox.addItem(ff.getName().replace("Data", ""));
        }
//                jComboBox.setBounds(0, 0, 100, 100);
        jComboBox.addItemListener((ItemEvent e) -> {
            itemSelected = (String) e.getItem();
        });
        south.add(jComboBox);
        contentPane.add(south, BorderLayout.SOUTH);
        panelMouvementData = new TableMouvementData(grps);
        east = new JPanel(new BorderLayout());
        east.add(panelMouvementData);
       
        contentPane.add(east, BorderLayout.EAST);

        //Ecoute press et release sourie
        ardoise.addMouseListener(mousePressedListener);

    }

    public  void rec(int x, int y) {
        if (coutPoint == 0) {
            currentMvt = new MouvementData();
            currentMvt.setNb(countMouvement);
            synchronized (mutex) {
                mvts.add(currentMvt);
            }

            if (countMouvement == 0) {
                iniTimeMove = System.currentTimeMillis();
                currentMvt.setStart(0);

            } else {
                currentMvt.setStart((int) (System.currentTimeMillis() - iniTimeMove));
            }
        }

        coutPoint++;
//        System.out.println("(" + x + "," + y + ")");
        currentMvt.add(new Point(x, y));

        ardoise.repaint();

    }

    @Override
    public void setControleur(Controleur controleur) {
        super.setControleur(controleur); 
       saveMouvement = new SaveMouvement("save",controleur);
    }
    

    
}
