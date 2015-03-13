/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json2;

import com.jonathan.lib.string.StringTool;
import com.jonathan.test.PojoTest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class JsonWriter {

    private Appendable out;
    private final Map<Class<?>, Map<String, Method>> cacheMethod;

    public JsonWriter() {
        cacheMethod = new HashMap<>(20);
    }
    public JsonWriter(Appendable out) {
        this();
        this.out = out;
    }

    public void clearCache(){
        cacheMethod.clear();
    }
    
    public Appendable getOut() {
        return out;
    }

    public void setOut(Appendable out) {
        this.out = out;
    }
    
    public void writeVarDeclaration(String var) throws IOException{
        out.append("var "+var+" = ");
    }
    public void writeEndLine() throws IOException{
        out.append(";\n");
    }
    public void write(Object o) throws IOException {
        if(out == null){
            out = new StringBuilder();
        }
        if (o == null) {
            out.append("null");
        } else {
            Class<? extends Object> oClass = o.getClass();

            if (oClass == Boolean.class) {
                Boolean b = (Boolean) o;
                if (b) {
                    out.append("true");
                } else {
                    out.append("false");
                }
            } else if (oClass == String.class) {
                writeString((String) o);
            } else if (o instanceof Number) {
                out.append(o.toString());
            } else {
                if (o instanceof Collection) {
                    writeCollection((Collection<?>) o);
                } else {
                    writePojo(o, oClass);
                }
            }

        }
    }

    private void writeString(String string) throws IOException {
        out.append('"').append(StringTool.echapeInvisibleChar(string, '"')).append('"');
    }

    private void writeCollection(Collection<?> collection) throws IOException {
        out.append('[');
        boolean first = true;
        for (Object o : collection) {
            if (!first) {
                out.append(',');
            } else {
                first = false;
            }
            write(o);
        }
        out.append(']');
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        JsonWriter jw = new JsonWriter(sb);
        jw.writeVarDeclaration("anime");
        jw.write(new PojoTest());
        jw.writeEndLine();
        System.out.println(sb.toString());

//        System.out.println(PBN.matcher("\n sdvsdvsd \t dsdvsdvsdv\t \r dsdfsdf").replaceAll("PPPP"));
    }

    private void writePojo(Object o, Class<? extends Object> oClass) throws IOException {
        Map<String, Method> OMethod = cacheMethod.get(oClass);
        if (OMethod == null) {
            OMethod = initCacheMethode(oClass);
        }
        boolean first = true;
        out.append('{');
        for (Map.Entry<String, Method> e : OMethod.entrySet()) {
            String key = e.getKey();
            Method value = e.getValue();
            if (first) {
                first = false;
            } else {
                out.append(',');
            }
            out.append('"').append(key).append('"').append(':');
            try {
                write(value.invoke(o));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(JsonWriter.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
         out.append('}');

    }

    private Map<String, Method> initCacheMethode(Class<? extends Object> oClass) {
        Map<String, Method> ret = new HashMap<>();
        cacheMethod.put(oClass, ret);
        for (Method m : oClass.getMethods()) {
            int mod = m.getModifiers();
            boolean isStatic = Modifier.isStatic(mod);
           boolean noParam = ( m.getParameterTypes().length == 0);
            if (!isStatic && noParam) {
                boolean startsWithGetClass = m.getName().startsWith("getClass");
                if (!startsWithGetClass) {
                    boolean startsWithGet = m.getName().startsWith("get");
                    if (startsWithGet) {
                        ret.put(StringTool.lcFirst(m.getName().substring(3)), m);
                    } else if (m.getName().startsWith("is")) {
                        ret.put(StringTool.lcFirst(m.getName().substring(2)), m);
                    }
                }

            }

        }
        return ret;
    }

}
