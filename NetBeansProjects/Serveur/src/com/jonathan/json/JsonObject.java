/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.json;

import com.jonathan.json.validator.ValidatorException;
import parser.ParserJson;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jonathan
 */
public class JsonObject implements JsonObjectInterface {
    private boolean guillemetOnKey;
    

    public static void main(String[] args) throws ValidatorException {
        JsonObject jo = new JsonObject();
        JsonObject jo2 = new JsonObject();
        jo2.put("bli", "deseded");
        NumberJson numberJson = new NumberJson(3);
        ArrayJson aj = new ArrayJson();
        aj.add(numberJson);
        aj.add(new TextJson("bababab"));
        jo.put("id", new NumberJson("3.2"));
        jo.put("nom", "paul");
        jo.put("obj", jo2);
        jo.put("array", aj);
        jo.guillemetOnKey = false;
        System.out.println(jo.toStringJsonPretty());
        ParserJson parserJson = new ParserJson(jo.toStringJsonPretty());
        JsonObject parse = parserJson.parse();
        parse.setGuillemetOnKey(true);
        System.out.println(parse.toStringJsonPretty());



    }
    private Map<String, JsonObjectInterface> data;

    public Map<String, JsonObjectInterface> getData() {
        return data;
    }

    public void setData(Map<String, JsonObjectInterface> data) {
        this.data = data;
    }

    public JsonObject(Map<String, JsonObjectInterface> data) {
        this.data = data;
        guillemetOnKey = false;
    }

    public JsonObject() {
        this.data = new HashMap<>();
        guillemetOnKey = false;
    }

    @Override
    public TypeJson getType() {
        return TypeJson.OBJET;
    }
   
    public JsonObjectInterface get(String key) {
        return this.data.get(key);
    }

    public JsonObject put(String key, JsonObjectInterface jsonObjectInterface) {
        data.put(key, jsonObjectInterface);
        return this;
    }

    @Override
    public String toStringJson() {
        StringBuilder builder = new StringBuilder(100);
        builder.append('{');
        int size = data.size();
        int i = 0;
        for (Map.Entry<String, JsonObjectInterface> entry : data.entrySet()) {
            String key = entry.getKey();
            
            JsonObjectInterface jsonObjectInterface = entry.getValue();
            if(jsonObjectInterface instanceof JsonObject){
                ((JsonObject) jsonObjectInterface).guillemetOnKey = this.guillemetOnKey;
            }
            if(guillemetOnKey == true){
                builder.append('"').append(key).append('"').append(':').append(jsonObjectInterface.toStringJson());
            }
            else{
                builder.append(key).append(':').append(jsonObjectInterface.toStringJson());
            }
            
            i++;
            if (i < size) {
                builder.append(',');
            }

        }
        builder.append('}');
        return builder.toString();
    }
    

    public String toStringJsonPretty() {
        StringBuilder builder = new StringBuilder(100);
        builder.append("{\n");
        int indent = 0;
        int size = data.size();
        int i = 0;
        indent++;
        for (Map.Entry<String, JsonObjectInterface> entry : data.entrySet()) {
            
            for (int j = 0; j < indent; j++) {
                builder.append(INDENTSPACE);
            }
            String key = entry.getKey();
            System.out.println("key : "+key);
            JsonObjectInterface jsonObjectInterface = entry.getValue();
            if(jsonObjectInterface instanceof JsonObject){
                ((JsonObject) jsonObjectInterface).guillemetOnKey = this.guillemetOnKey;
            }
            System.out.println("guillemetOnKey: "+guillemetOnKey);
            if(guillemetOnKey == true){
                builder.append('"').append(key).append('"').append(':');
            }
            else{
                builder.append(key).append(':');
            }
            jsonObjectInterface.toStringJsonPretty(builder , indent);
            i++;
            if (i < size) {
                builder.append(",\n");
            }

        }
        builder.append("\n}");
        indent--;
        return builder.toString();
    }

    @Override
    public int toStringJsonPretty(StringBuilder builder, int indent) {
//        builder.append('\n');
        for (int j = 0; j < indent; j++) {
            builder.append(INDENTSPACE);
        }
        builder.append("{\n");
        indent++;

        int size = data.size();
        int i = 0;
        for (Map.Entry<String, JsonObjectInterface> entry : data.entrySet()) {
            for (int j = 0; j < indent; j++) {
                builder.append(INDENTSPACE);
            }
            String key = entry.getKey();
            JsonObjectInterface jsonObjectInterface = entry.getValue();
            if(jsonObjectInterface instanceof JsonObject){
                ((JsonObject) jsonObjectInterface).guillemetOnKey = this.guillemetOnKey;
            }
            if(guillemetOnKey == true){
                builder.append('"').append(key).append('"').append(':');
            }
            else{
                builder.append(key).append(':');
            }
            jsonObjectInterface.toStringJsonPretty(builder , indent);
            i++;
                if (i < size) {
                    
              
                builder.append(",\n");
            }

        }
        builder.append('\n');
        indent--;
        for (int j = 0; j < indent; j++) {
            builder.append(INDENTSPACE);
        }
        
        builder.append('}');

        return indent;
    }

    public JsonObject put(String key, String value) {
        return put(key, new TextJson(value));
    }

    public JsonObject put(String key, int value) {
        return put(key, new NumberJson(value));
    }

    public JsonObject put(String key, double value) {
        return put(key, new NumberJson(value));
    }

    public JsonObject put(String key, float value) {
        return put(key, new NumberJson(value));
    }
    public JsonObject put(String key, boolean value) {
        if(value == true)
            return put(key, BooleanJson.TRUE);
        else{
            return put(key, BooleanJson.FALSE);
        }
    }
    
    public JsonObject putNull(String key) {
       return put(key, JsonNull.NULL);
    }

    public TypeJson getType(String key) {
        return get(key).getType();
    }

    public String getString(String key) {
        return get(key).toString();
    }

    public int getInt(String key) {
        return ((BigDecimal) get(key)).intValue();
    }

    public double getDouble(String key) {
        return ((BigDecimal) get(key)).doubleValue();
    }
    public float getFloat(String key) {
        return ((BigDecimal) get(key)).floatValue();
    }
    public double getLong(String key) {
        return ((BigDecimal) get(key)).longValue();
    }

    public JsonObject getObject(String key) {
        return (JsonObject) get(key);
    }
    
    public ArrayJson getArray(String key) {
        return (ArrayJson) get(key);
    }
    
    public Set<String> getKeys(){
        return data.keySet();
    }

    public boolean getBoolean(String attribut) {
        return ((BooleanJson) this.get(attribut)).getValue();
    }

    public void setGuillemetOnKey(boolean guillemetOnKey) {
        this.guillemetOnKey = guillemetOnKey;
    }
    
    
}
