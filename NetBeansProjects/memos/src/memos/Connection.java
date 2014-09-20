/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author jonathan
 */
public class Connection {
    private Socket socket;
    private final OutputStream os;
    private final InputStream is;
    private  ObjectInputStream ois ;
    private  ObjectOutputStream oos;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        os = socket.getOutputStream();
        is = socket.getInputStream();
//        oos= new ObjectOutputStream(os);
//        ois = new ObjectInputStream(is);
        
    }
    
    public void send(Object o) throws IOException, ClassNotFoundException{
//        System.out.println("o.getClass()"+o.getClass());
        oos= new ObjectOutputStream(os);
        oos.writeObject(o);
        oos.flush();
//        oos.close();
//        ois = new ObjectInputStream(is);
//        Object readObject = ois.readObject();
//        return readObject;
        
    }
    public Object readFile(String savep) throws IOException, ClassNotFoundException{
//         FileOutputStream fos =new FileOutputStream(savep);
//         
//         fos.write(b);
//         fos.flush();
//         fos.close();
        return null;
        
    }
    
    public Object recoit() throws IOException, ClassNotFoundException{
//        System.out.println(is.read());
        ois = new ObjectInputStream(is);
        Object readObject = ois.readObject();
      
        return readObject;
        
    }

    Socket getSocket() {
        return socket;
    }
    
}
