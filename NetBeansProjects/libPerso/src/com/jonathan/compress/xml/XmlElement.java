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
public interface XmlElement {
    public void write(Appendable a)throws IOException;
    public boolean isIndented();
    public int getIndent();
    public void setIndent(int i);
    public String getName();
    public List<XmlElement> getChild() ;
    public boolean haveChild();
     public Map<String, String> getAttribut() ;
     public boolean isClosable();
    public void setAttribut(Map<String, String> attribut);
    public void setChild(List<XmlElement> child) ;
}
