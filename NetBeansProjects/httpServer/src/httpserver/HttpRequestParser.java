/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonathan
 */
class HttpRequestParser {
    public static void main(String[] args) throws UnsupportedEncodingException, IOException{
        HttpRequestParser  hrp = new HttpRequestParser();
        String a = "GET /fichier.ext HTTP/1.1\n" +
"Host: www.site.com\n" +
"Connection: Close";
        byte[] bytes = a.getBytes("utf-8");
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        hrp.parse(bais);
    }
    private final StringBuilder buff = new StringBuilder();
    private boolean start = false;
    private boolean inCommand;
    private String encoding = "utf-8";
    private InputStreamReader isr;
    private HttpRequest httpRequest;

    void append(char c) {
        if (c != '\n' && c != '\r') {
            buff.append(c);
        } else {

        }
    }

    void parse(InputStream is) throws IOException {
        isr = new InputStreamReader(is, encoding);
        httpRequest = new HttpRequest();
        int c = isr.read();
        boolean continu = true;
        inCommand = true;
        while (continu) {
            if (c != '\n' && c != '\r') {
                buff.append((char) c);
            } else {
                String ligne = buff.toString();
                System.out.println("ligne : "+ligne);
                if (inCommand) {
                    List<String> readWord = readWord(0,ligne);
                    System.out.println("readWord = "+readWord);
                    continu = false;
                } 
            }
            c = isr.read();
        }
    }

    HttpRequest getHttpRequest() {
        return null;
    }

    private void readCommand(String ligne) {

    }

    private static List<String> readWord(int start, String in) {
        StringBuilder bl = new StringBuilder();
        char c;
        int i = start ;
        ArrayList<String> l = new ArrayList<String>();
        while (i < in.length()) {
            c = in.charAt(i); 
            if (isNotWhite(c)) {
                bl.append(c);
            }else{
                l.add(bl.toString());
                bl.setLength(0);
                while(isWhite(c)&& i <in.length()){
                    c = in.charAt(i);
                    i++;
                }
                if(i<in.length()){
                    bl.append(c);
                }
                i--;
            }   
            i++;
        }
        if(bl.length()!=0){
            l.add(bl.toString());
        }
        return l;
    }
    public static boolean isNotWhite(char c){
        return (c != ' ' && c != '\t' && c != '\n' && c != '\r') ;
    }
    public static boolean isWhite(char c){
        return (c == ' ' || c == '\t' || c == '\n' || c == '\r') ;
    }
}
