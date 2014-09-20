/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.compress.xml;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jonathan
 */
public class XmlText implements XmlElement{
    private String text;

    public XmlText(String text) {
        this.text = text;
    }
    
    @Override
    public void write(Appendable a) throws IOException {
        a.append(text).append('\n');
    }

    @Override
    public boolean isIndented() {
        return false;
    }

    @Override
    public int getIndent() {
        return 0;
    }

    @Override
    public void setIndent(int i) {
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<XmlElement> getChild() {
        return null;
    }

    @Override
    public void setChild(List<XmlElement> child) {
    }

    @Override
    public boolean haveChild() {
        return false;
    }

    @Override
    public Map<String, String> getAttribut() {
        return null;
    }

    @Override
    public void setAttribut(Map<String, String> attribut) {
    }

    @Override
    public boolean isClosable() {
        return false;
    }
    
}
