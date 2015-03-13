package com.jonathan.lib.graphique;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class GenericTableModel<T> extends AbstractTableModel {

    private final List<T> datas = new ArrayList<>();
    private final InfoTableObjectProvider<T> infoObjectProvider;

    private final String[] entetes;

    public GenericTableModel(InfoTableObjectProvider<T> itop) {
        super();
        infoObjectProvider = itop;
        entetes = itop.getHead();
        
    }

    public int getRowCount() {
        return datas.size();
    }

    public int getColumnCount() {
        return entetes.length;
    }

    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex); 
        infoObjectProvider.set(datas.get(rowIndex), aValue, columnIndex);
        
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       return infoObjectProvider.isCellEditable( rowIndex,  columnIndex);
       
    }

    
    public Object getValueAt(int rowIndex, int columnIndex) {
        T get = datas.get(rowIndex);
        Object o;
        o = infoObjectProvider.get(get, columnIndex);
        return o;
    }

    public void addData(T data) {
        datas.add(data);

        fireTableRowsInserted(datas.size() - 1, datas.size() - 1);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return infoObjectProvider.getColumnClass(columnIndex);
        
    }

    public void removeData(int rowIndex) {
        datas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
     public void removeData(T obToRemove) {
        int indexOf = datas.indexOf(obToRemove);
        fireTableRowsDeleted(indexOf, indexOf);
    }
//     public void removeData(int beginIndex,int endIndex) {
//        datas.remove(rowIndex);
//
//        fireTableRowsDeleted(rowIndex, rowIndex);
//    }
     public void removaAllData() {
       
        int size = datas.size();
          datas.clear();
         fireTableRowsDeleted(0, size-1);
    }

    public static void main(String[] args) {
        System.out.println("coucou");
        JFrame j = JFrameGiver.getJFrameAutoClosable("test table");
        InfoTableObjectProvider<Personne> itop = new InfoTableObjectProvider<Personne>() {

            @Override
            public String[] getHead() {
                return new String[]{"nom", "prénom", "age"};
            }

            @Override
            public Object get(Personne o, final int column) {
                switch (column) {
                    case 0:
                        return o.name;
                    case 1:
                        return o.prenom;
                    case 2:
                        return o.age;
                        default:return null;
                }
            }

            @Override
            public void set(Personne o, Object value, int column) {
                
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return true;
            }
        };
        GenericTableModel<Personne> gtm = new GenericTableModel<>(itop);
        JTable jt = new JTable(gtm);
        Personne p = new Personne("bonnet", "Jonathan", 30);
        gtm.addData(p);
        JScrollPane jsp = new JScrollPane(jt);
        j.getContentPane().add(jsp);
        j.pack();
        j.setVisible(true);
        
    }

   

    public static class Personne {

        public Personne(String name, String prenom, int age) {
            this.name = name;
            this.prenom = prenom;
            this.age = age;
        }
        

        private String name;
        private String prenom;
        private int age;

    }
}
