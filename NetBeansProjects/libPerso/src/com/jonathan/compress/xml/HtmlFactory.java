/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.compress.xml;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jonathan
 */
public class HtmlFactory {
   
    public XmlElement get(final String name,final boolean closable){
        Xml x = new Xml() {

            @Override
            public String getName() {
                return name;
            }

            @Override
            public boolean isClosable() {
                return closable;
            }
        };
        return x;
    }
    public XmlElement get(final String name){
       return get(name, false);
    }
    public XmlElement getCloasble(final String name){
       return get(name,true);
    }
    
}
