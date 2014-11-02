/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import java.net.Socket;

/**
 *
 * @author jonathan
 */
public abstract class Client {
    private String ipSource;

    Client(Socket accept) {
        ipSource = accept.getRemoteSocketAddress().toString();
    }
    public abstract void process(Socket accept);
    
    
}
