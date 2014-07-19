/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.json;

import java.io.IOException;

/**
 *
 * @author jonathan
 */
public class BooleanJson implements JsonObjectInterface{
    private final boolean booleanValue;
    private static final String TRUE_STRING="true";
    private static final String FALSE_STRING = "false";
    public static final BooleanJson TRUE = new BooleanJson(true);
    public static final BooleanJson FALSE = new BooleanJson(false);

    private BooleanJson(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }
    
    public boolean getValue(){
        return booleanValue;
    }

    @Override
    public TypeJson getType() {
        return TypeJson.BOOLEAN;
    }

    @Override
    public Appendable toStringJson(Appendable out) throws IOException {
        if(booleanValue == true){
            out.append(TRUE_STRING);
        }else{
            out.append(FALSE_STRING);
        }
        return out;
    }

    @Override
    public int toStringJsonPretty(Appendable out, int indent) throws IOException {
        toStringJson(out);
        return indent;
    }
    
}
