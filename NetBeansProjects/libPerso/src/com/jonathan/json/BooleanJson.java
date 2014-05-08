/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jonathan.json;

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
    public String toStringJson() {
        if(booleanValue == true){
            return TRUE_STRING;
        }else{
            return FALSE_STRING;
        }
    }

    @Override
    public int toStringJsonPretty(StringBuilder out, int indent) {
        out.append(toStringJson());
        return indent;
    }
    
}
