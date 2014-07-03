/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author jonathan
 */
public class Ring<T> /*implements List<T>*/{
//    private int size = 0;
//    private RingElement<T> firstAdd = null;
//    private RingElement<T> lastAdd = null;
//    @Override
//    public boolean add(T o){
//        if(size == 0){
//            RingElement re = new RingElement();
//            re.me = o;
//            re.after = re;
//            re.before = re;
//            firstAdd = re;
//            lastAdd = re;
//            size = 1;
//        }
//        else{
//            RingElement re = new RingElement();
//            lastAdd.after = re;
//            firstAdd.before = re; 
//            re.me = o;
//            re.after = firstAdd;
//            re.before = lastAdd;
//            lastAdd = re;
//            size++;
//        }
//        return true;
//    }
//    @Override
//    public boolean remove(Object o){
//        RingElement<T> loop = firstAdd;
//        if(size == 0){
//            return false;
//        }
//        while (true) {            
//            if(loop.me.equals(o)){
//                loop.after.before = loop.before.after;
//                size--;
//                return true;
//            }else{
//                loop = loop.after;
//                if(loop.equals(firstAdd)){
//                    return false;
//                }
//            }
//        }
//    } 
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    @Override
//    public boolean contains(Object o) {
//    }
//
//    @Override
//    public Iterator<T> iterator() {
//    }
//
//    @Override
//    public Object[] toArray() {
//    }
//
//    @Override
//    public <T> T[] toArray(T[] a) {
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> c) {
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends T> c) {
//    }
//
//    @Override
//    public boolean addAll(int index, Collection<? extends T> c) {
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> c) {
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> c) {
//    }
//
//    @Override
//    public void clear() {
//    }
//
//    @Override
//    public T get(int index) {
//    }
//
//    @Override
//    public T set(int index, T element) {
//    }
//
//    @Override
//    public void add(int index, T element) {
//    }
//
//    @Override
//    public T remove(int index) {
//    }
//
//    @Override
//    public int indexOf(Object o) {
//    }
//
//    @Override
//    public int lastIndexOf(Object o) {
//    }
//
//    @Override
//    public ListIterator<T> listIterator() {
//    }
//
//    @Override
//    public ListIterator<T> listIterator(int index) {
//    }
//
//    @Override
//    public List<T> subList(int fromIndex, int toIndex) {
//    }
//    private static class RingElement<T> {
//        T me;
//        RingElement<T> after;
//        RingElement<T> before;
//    }
}
