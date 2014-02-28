/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json;

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
    public String toStringJson() {
        StringBuilder builder = new StringBuilder(100);
        builder.append('[');
        // Iterator<JsonObjectInterface> iterator = jsonObjects.iterator();
        // System.out.println(jsonObjects.());
        for (Iterator<JsonObjectInterface> it = jsonObjects.iterator(); it.hasNext();) {
            JsonObjectInterface jsonObjectInterface = it.next();
            builder.append(jsonObjectInterface.toStringJson());
            if (it.hasNext()) {
                builder.append(',');
            }

        }
        builder.append(']');
        return builder.toString();
    }

    @Override
    public int toStringJsonPretty(StringBuilder builder, int indent) {
        builder.append('\n');
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
            if(jsonObjectInterface.getType() != TypeJson.OBJET){
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

    public void add(int longuerReel) {
        add(new NumberJson(longuerReel));
    }

    public int size() {
        return jsonObjects.size();
    }
}
