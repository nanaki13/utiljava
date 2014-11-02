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
interface ClientProvider {

    public Client createClient(Socket accept);
    
}
