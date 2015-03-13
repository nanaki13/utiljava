/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.compress.xml;

import com.jonathan.application.ArrayMap;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public abstract class Xml implements XmlElement {

    protected Map<String, String> attribut;
    protected int indent;
    protected List<XmlElement> child;

    public Xml() {
        child = new LinkedList<>();
        attribut = new ArrayMap<>(3);
    }

    @Override
    public void write(Appendable a) throws IOException {
        if (isIndented()) {
            writeIndent(a);
        }
        a.append("<").append(getName());
        if (!attribut.isEmpty()) {
            
            a.append(' ');
            attribut.forEach((k, v) -> {
                int i = 0;
                try {
                    a.append(k).append("=\"").append(v).append('"');
                    if (i != attribut.size() - 1) {
                        a.append(' ');
                    }
                    i++;
                } catch (IOException e) {

                }
            });
        }

        if (!child.isEmpty()) {
            a.append(">\n");
            for (XmlElement c : child) {
                if (c.isIndented()) {
                    c.setIndent(indent + 1);
                    c.write(a);
                } else {
                    c.write(a);
                }

            }
            if (isIndented()) {
                writeIndent(a);
            }
            a.append("</").append(getName()).append(">\n");
        } else {
            if(isClosable()){
                a.append("/>");
            }else{
                a.append("></").append(getName()).append(">\n");
            }
            
        }

    }

    private void writeIndent(Appendable a) throws IOException {
        for (int i = 0; i < indent; i++) {
            a.append(' ');
        }
    }

    public Map<String, String> getAttribut() {
        return attribut;
    }

    public void setAttribut(Map<String, String> attribut) {
        this.attribut = attribut;
    }


    public int getIndent() {
        return indent;
    }

    public void setIndent(int indent) {
        this.indent = indent;
    }

    public List<XmlElement> getChild() {
        return child;
    }

    public void setChild(List<XmlElement> child) {
        this.child = child;
    }
    public boolean haveChild(){
        return !child.isEmpty();
    }
    @Override
    public boolean isIndented() {
        return true;
    }
    
    public String toString(){
        StringWriter sw = new StringWriter();
        try {
            this.write(sw);
        } catch (IOException ex) {
            Logger.getLogger(Xml.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw.toString();
        
    }

}
