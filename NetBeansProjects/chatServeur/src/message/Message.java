/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package message;

//import chatserveur.ChatServeur.ClientSocketProcessor;
import chatserveur.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class Message implements Serializable{
    private String content;
    private String source;
//    private ClientSocketProcessor source;
//    private List<ClientSocketProcessor> dest;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
  
    public Message(String content) {
        this.content = content;
       
    }

    public Message() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    

//    public ClientSocketProcessor getSource() {
//        return source;
//    }
//
//    public void setSource(ClientSocketProcessor source) {
//        this.source = source;
//    }
//
//    public List<ClientSocketProcessor> getDest() {
//        return dest;
//    }
//
//    public void setDest(List<ClientSocketProcessor> dest) {
//        this.dest = dest;
//    }
//    

    @Override
    public String toString() {
        return source+" : "+content;
    }
}
