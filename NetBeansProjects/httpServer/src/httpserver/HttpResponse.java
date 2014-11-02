/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import com.jonathan.utils.ArrayMap;
import java.util.Map;

/**
 *
 * @author jonathan
 */
public class HttpResponse {
    private String version;
    private String statut;
    private byte[] response;
    private Map<String, String> params;
    private String encoding;

    public String getEncoding() {
        return encoding;
    }

    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public byte[] getResponse() {
        return response;
    }

    public void setResponse(byte[] response) {
        this.response = response;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
    
      public void addParams(String param, String value) {
        this.params.put(param, value);
    }

    public HttpResponse() {
        params  = new ArrayMap<>();
    }

    void setStatusCode(String string) {
    }

    void addParams(String readLine) {
        int indexOf = readLine.indexOf(':');
            if (indexOf != -1) {
                String param = readLine.substring(0,indexOf);
                String value = readLine.substring(indexOf+2,readLine.length());
//                String.out.println(param);
                if(param.equals("Content-Type")){
                    int indexOf1 = value.indexOf("charset=");
                    encoding = value.substring(indexOf1+8,value.length());
                    System.out.println(encoding);
//                    System.exit(0);
                }
                params.put(param, value);
            }
    }

    String getParam(String param) {
        return params.get(param);
    }
    
    
}
