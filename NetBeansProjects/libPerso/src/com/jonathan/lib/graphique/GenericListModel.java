/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.lib.graphique;

import java.util.List;
import java.util.ListIterator;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

/**
 *
 * @author jonathan
 */
public class GenericListModel<T> extends AbstractListModel<T> implements MutableComboBoxModel<T>{
    private List<T> l;
    private Object selectedObject;

    public GenericListModel(List<T> l){
        this.l=l;
    }
    @Override
    public int getSize() {
        return l.size();
    }

    public T getElementAt(int index) {
        if ( index >= 0 && index < l.size() )
            return l.get(index);
        else
            return (T )null;
    }

    @Override
    public void addElement(T item) {
        l.add(item);
        fireIntervalAdded(this,l.size()-1, l.size()-1);
        if ( l.size() == 1 && selectedObject == null && item != null ) {
            setSelectedItem( item );
        }
    }
 public void insertElementAt(T anObject,int index) {
        ListIterator<T> listIterator = l.listIterator(index);
        T next = listIterator.next();
        T tmp;
        listIterator.set(anObject);
        while(listIterator.hasNext()){
            tmp = listIterator.next();
            listIterator.set(next);
            next = tmp;
        }
        fireIntervalAdded(this, index, index);
    }

    // implements javax.swing.MutableComboBoxModel
    public void removeElementAt(int index) {
        if ( getElementAt( index ) == selectedObject ) {
            if ( index == 0 ) {
                setSelectedItem( getSize() == 1 ? null : getElementAt( index + 1 ) );
            }
            else {
                setSelectedItem( getElementAt( index - 1 ) );
            }
        }

        l.remove(index);

        fireIntervalRemoved(this, index, index);
    }

    // implements javax.swing.MutableComboBoxModel
    public void removeElement(Object anObject) {
        int index = l.indexOf(anObject);
        if ( index != -1 ) {
            removeElementAt(index);
        }
    }

    /**
     * Empties the list.
     */
    public void removeAllElements() {
        if ( l.size() > 0 ) {
            int firstIndex = 0;
            int lastIndex = l.size() - 1;
            l.clear();
            selectedObject = null;
            fireIntervalRemoved(this, firstIndex, lastIndex);
        } else {
            selectedObject = null;
        }
    }
     public void setSelectedItem(Object anObject) {
        if ((selectedObject != null && !selectedObject.equals( anObject )) ||
            selectedObject == null && anObject != null) {
            selectedObject = anObject;
            fireContentsChanged(this, -1, -1);
        }
    }

    // implements javax.swing.ComboBoxModel
    public Object getSelectedItem() {
        return selectedObject;
    }
}
