/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class ArrayJson implements JsonObjectInterface {

    public List<JsonObjectInterface> jsonObjects;

    @Override
    public TypeJson getType() {
        return TypeJson.ARRAY;
    }

    public List<JsonObjectInterface> getJsonObjects() {
        return jsonObjects;
    }
    
    public JsonObjectInterface get(int i) {
        return jsonObjects.get(i);
    }

    public JsonObject getObject(int i) {
        return (JsonObject)jsonObjects.get(i);
    }
    public ArrayJson getArray(int i) {
        return (ArrayJson) jsonObjects.get(i);
    }
    public ArrayJson add(JsonObjectInterface i) {
        jsonObjects.add(i);

        return this;
    }

    public void set(int j, JsonObjectInterface i) {
        jsonObjects.set(j, i);
    }

    public ArrayJson() {
        jsonObjects = new ArrayList<>();
    }

    @Override
    public Appendable toStringJson(Appendable out) throws IOException{
//        StringBuilder out = new StringBuilder(100);
        out.append('[');
        // Iterator<JsonObjectInterface> iterator = jsonObjects.iterator();
        // System.out.println(jsonObjects.());
        for (Iterator<JsonObjectInterface> it = jsonObjects.iterator(); it.hasNext();) {
            JsonObjectInterface jsonObjectInterface = it.next();
            jsonObjectInterface.toStringJson(out);
            if (it.hasNext()) {
                out.append(',');
            }

        }
        out.append(']');
        return out;
    }

    @Override
    public int toStringJsonPretty(Appendable builder, int indent) throws IOException {
//        builder.append('\n');
        for (int i = 0; i < indent; i++) {
            builder.append(INDENTSPACE);
        }
        builder.append("[\n");
        indent++;
//        for (int i = 0; i < indent; i++) {
//            builder.append(INDENTSPACE);
//        }
        for (Iterator<JsonObjectInterface> it = jsonObjects.iterator(); it.hasNext();) {
            JsonObjectInterface jsonObjectInterface = it.next();
            if(jsonObjectInterface.getType() != TypeJson.OBJET && jsonObjectInterface.getType() != TypeJson.ARRAY){
                for (int i = 0; i < indent; i++) {
                    builder.append(INDENTSPACE);
                }
            }
            jsonObjectInterface.toStringJsonPretty(builder, indent);
            if (it.hasNext()) {
                builder.append(",\n");
//                for (int i = 0; i < indent; i++) {
//                    builder.append(INDENTSPACE);
//                }
            }

        }
        indent--;
        builder.append('\n');
        for (int i = 0; i < indent; i++) {
            builder.append(INDENTSPACE);
        }
        builder.append("]");
        return indent;
    }

    public int getInt(int i) {
        return ((BigDecimal) jsonObjects.get(i)).intValue();
    }

    public void add(int nb) {
        add(new NumberJson(nb));
    }

    public int size() {
        return jsonObjects.size();
    }

    public boolean isEmpty() {
        return jsonObjects.isEmpty();
    }
}
