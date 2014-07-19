/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 *
 * @author jonathan
 */
public class NumberJson extends BigDecimal implements JsonObjectInterface,FeuilleJson<BigDecimal>{
   
    
  
    public NumberJson(char[] in, int offset, int len) {
        super(in, offset, len);
    }

    public NumberJson(char[] in, int offset, int len, MathContext mc) {
        super(in, offset, len, mc);
    }

    public NumberJson(char[] in) {
        super(in);
    }

    public NumberJson(char[] in, MathContext mc) {
        super(in, mc);
    }

    public NumberJson(String val) {
        super(val);
    }

    public NumberJson(String val, MathContext mc) {
        super(val, mc);
    }

    public NumberJson(double val) {
        super(val);
    }

    public NumberJson(double val, MathContext mc) {
        super(val, mc);
    }

    public NumberJson(BigInteger val) {
        super(val);
    }

    public NumberJson(BigInteger val, MathContext mc) {
        super(val, mc);
    }

    public NumberJson(BigInteger unscaledVal, int scale) {
        super(unscaledVal, scale);
    }

    public NumberJson(BigInteger unscaledVal, int scale, MathContext mc) {
        super(unscaledVal, scale, mc);
    }

    public NumberJson(int val) {
        super(val);
    }

    public NumberJson(int val, MathContext mc) {
        super(val, mc);
    }

    public NumberJson(long val) {
        super(val);
    }

    public NumberJson(long val, MathContext mc) {
        super(val, mc);
    }

    

    @Override
    public TypeJson getType() {
        return TypeJson.NUMBER;
    }

    @Override
    public BigDecimal getValue() {
        return this;
    }
  
    @Override
    public Appendable toStringJson(Appendable out) throws IOException {
        out.append(toPlainString());
        return out;
    }

   @Override
    public int toStringJsonPretty(Appendable b ,int indent) throws IOException {
        toStringJson(b);
        return indent;
    }


    
}
