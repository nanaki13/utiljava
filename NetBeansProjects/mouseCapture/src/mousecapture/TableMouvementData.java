/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mousecapture;

import com.jonathan.factory.Provider;
import com.jonathan.lib.graphique.GenericTableModel;
import com.jonathan.lib.graphique.InfoTableObjectProvider;
import com.jonathan.utils.Warp;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import mousecapture.core.MouseCapture;

/**
 *
 * @author jonathan
 */
public class TableMouvementData extends JScrollPane implements TableModelListener, ListSelectionListener {

//    private List<MouvementData> mouvementDatas;
    private JTable jTable = new JTable();
    private final Object lock = new Object();
    private final GenericTableModel<MouvementData> dtm;
    private ArrayList<Boolean> selectedIndexs = new ArrayList<Boolean>();
    private List<GroupeMouvement> grps;

    public TableMouvementData(List<GroupeMouvement> grps) {
        this.grps = grps;
        dtm = new GenericTableModel<>(new InfoTableObjectProvider<MouvementData>() {
            private final String[] head = new String[]{"numéro", "nom", "nombre de point", "couleur", "groupe", "start (ms)"};

            @Override
            public String[] getHead() {
                return head;
            }

            @Override
            public Object get(MouvementData o, int column) {
                switch (column) {
                    case 0:
                        return o.getNb();
                    case 1:
                        return o.getNom();
                    case 2:
                        return o.getPoints().size();
                    case 3:
                        return o.getCouleur();
                    case 4:
                        return o.getGroup();
                    case 5:
                        return o.getStart();
                }
                return null;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return Color.class;
                    case 4:
                        return GroupeMouvement.class;
                    case 5:
                        return Integer.class;
                }
                return null;
            }

            @Override
            public void set(MouvementData o, Object value, int column) {
                switch (column) {
                    case 0:
                        o.setNb((Integer) value);
                        break;
//                    case 1 : o.set
                    case 1:
                        o.setNom((String) value);
                        break;
                    case 3:
                        o.setColor((Color) value);
                        break;
                    case 4:
                        o.setGroup((GroupeMouvement) value);
                        break;
                    case 5:
                        if (value != null) {
                            o.setStart((Integer) value);
                        }
                        break;
                }

            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return true;
                    case 1:
                        return true;
                    case 2:
                        return true;
                    case 3:
                        return true;
                    case 4:
                        return true;
                    case 5:
                        return true;
                    default:
                        return false;
                }
            }
        });

        jTable = new JTable(dtm);
        jTable.setDefaultRenderer(Color.class, new ColorCellRenderer());
        jTable.setDefaultEditor(Color.class, new ColorCellEditor());
        Provider<GroupeMouvement> providerGroupe = new Provider<GroupeMouvement>() {

            @Override
            public GroupeMouvement newInstance() {
                return new GroupeMouvement();
            }

            @Override
            public GroupeMouvement provide(Object... params) {
                Warp<Boolean> booleann = new Warp<>(false);
                TableMouvementData.this.grps.stream().forEach((g) -> {
                    if (g.getName().equals(params[0])) {
                        booleann.value = true;
                    }
                });
                if (booleann.value) {
                    return null;
                } else {
                    return new GroupeMouvement(params[0].toString());
                }
            }
        };
        jTable.setDefaultEditor(GroupeMouvement.class, new GroupeMouvementCellEditor(grps, providerGroupe));
        setViewportView(jTable);
        dtm.addTableModelListener(this);

        jTable.getSelectionModel().addListSelectionListener(this);

    }

    public void add(MouvementData m) {
        dtm.addData(m);

    }

    public void removeAllMvts() {
        dtm.removaAllData();
    }

    @Override
    public void tableChanged(TableModelEvent e) {
//        System.out.println(""+e);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        synchronized (lock) {
            System.out.println("" + e);
            boolean oneTrue = false;
            int rowCount = dtm.getRowCount();
            selectedIndexs.ensureCapacity(rowCount);
            if (selectedIndexs.size() < rowCount) {
                for (int i = selectedIndexs.size(); i < rowCount; i++) {
                    selectedIndexs.add(null);
                }
            }
            for (int i = 0; i < rowCount; i++) {
                boolean selectedIndex = jTable.getSelectionModel().isSelectedIndex(i);
                oneTrue = selectedIndex || oneTrue;
                selectedIndexs.set(i, selectedIndex);
            }
            if (oneTrue) {
                MouseCapture.selectedItemInTable();
            }
        }

    }

    public void removeMvts(int i) {
        dtm.removeData(i);
    }

    public void effacerAction() {
        synchronized (lock) {
            int rowCount = dtm.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                boolean selectedIndex = jTable.getSelectionModel().isSelectedIndex(i);
                if (selectedIndex) {
                    dtm.removeData(i);
                    i--;
                }

            }
        }
    }

}
