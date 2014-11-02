/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class HttpServer extends Thread {

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = new HttpServer(8080);
        httpServer.clientProvider = (Socket accept) -> {
            return new Client(accept) {

                @Override
                public void process(Socket accept) {
                    InputStreamReader isr = null;
                    try {
                        isr = new InputStreamReader(accept.getInputStream(), "utf-8");
                        int c = '\n';
                        boolean continu = true;
                        int cptRet =0;
                        HttpRequestParser parser = new HttpRequestParser();
                        while ((c = isr.read())!=-1 && cptRet!=2) {
                                parser.append((char) c);
                                 if(c == '\n'||c == '\r'){
                                     cptRet++;
                                 }
                        }
                        HttpRequest httpRequest;
                        httpRequest = parser.getHttpRequest();

                        byte[] bytes = "<h1>Livre posté</h1>".getBytes("utf-8");
                        String s = "HTTP/1.1 200 Ok\n"
                                + "Date: Wed, 29 Apr 2009 19:05:03 GMT\n"
                                + "Server: Nun v0.0\n"
                                + "Content-Length: "+bytes.length+"\n"
                                + "Content-Type: text/html; charset=utf-8\n"
                                + "Content-Language: fr\n"
                                + "\n";
                        accept.getOutputStream().write(s.getBytes("utf-8"));
                        accept.getOutputStream().write(bytes);
                        accept.getOutputStream().flush();
                        accept.getOutputStream().close();
                        accept.close();

                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            isr.close();
                        } catch (IOException ex) {
                            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };
        };
        httpServer.start();
//        Socket s = new Socket("localhost", 8080);
//        
//        s.getOutputStream().write("coucou".getBytes("utf-8"));
//        s.close();
    }
    private int port;
    private List<Socket> sockets;
    private ClientProvider clientProvider;
    private HashMap<String, Client> map;

    public HttpServer(int port) {
        sockets = new ArrayList<>();
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(port);
            while (!isInterrupted()) {
                Socket accept = ss.accept();
                Client c = findClient(accept);
                c.process(accept);
//                sockets.add(accept);
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Client findClient(Socket accept) {
        map = new HashMap<>();
        Client cl = map.get(accept.getRemoteSocketAddress().toString());
        if (cl == null) {
            cl = clientProvider.createClient(accept);
        }
        return cl;
    }

}
