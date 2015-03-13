/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.lib.graphique;

/**
 *
 * @author jonathan
 */
public interface InfoTableObjectProvider<T> {
    public String[] getHead();
    public Object get(T o, int column);
    public void set(T o,Object value, int column);

    public Class<?> getColumnClass(int columnIndex);

    public boolean isCellEditable(int rowIndex, int columnIndex);
}
