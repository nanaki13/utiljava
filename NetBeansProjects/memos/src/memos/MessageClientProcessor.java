/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memos;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class MessageClientProcessor extends ObjectCommand{
    private final ThreadClient client;
    private final AbstractServer server;
    private MessageClient mc;
    
    public MessageClientProcessor(ThreadClient client, AbstractServer server){
        this.client = client;
        this.server = server;
        
    }
    
    public Object exec(MessageClient mc){
        this.mc = mc;
        try {
//            System.out.println(mc.getCommand()+ " " + Arrays.toString(mc.getArgs()));
//            System.out.println(mc.getArgs());
            return exec(mc.getCommand(), mc.getArgs());
        } catch (ObjectCommandExeption ex) {
            if(mc.isNeedReponse()){
                try {
                    client.getC().send(ex.toString());
                } catch (IOException ex1) {
                    Logger.getLogger(MessageClientProcessor.class.getName()).log(Level.SEVERE, null, ex1);
                } catch (ClassNotFoundException ex1) {
                    Logger.getLogger(MessageClientProcessor.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return null;
    }
    
    public String echo(String[]  toEcho){
        try {
            StringBuilder bl = new StringBuilder();
            for(String s : toEcho){
                bl.append(toEcho).append(" ");
            }
            client.getC().send(toEcho);
        } catch (IOException ex) {
            Logger.getLogger(MessageClientProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageClientProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String echo(String  toEcho){
        try {
//            StringBuilder bl = new StringBuilder();
//            for(String s : toEcho){
//                bl.append(toEcho).append(" ");
//            }
            client.getC().send(toEcho);
        } catch (IOException ex) {
            Logger.getLogger(MessageClientProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageClientProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void exit() throws ClassNotFoundException{
        try {
            client.getC().send("vous être déconnecté");
            client.getSocket().close();
        } catch (IOException ex) {
            Logger.getLogger(MessageClientProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cp(String source) throws ClassNotFoundException{
        try {
            File f = new File(source);
            long length = f.length();
            
            FileInputStream fis = new FileInputStream(source);
            byte[] b = new byte[AbstractServer.packetSize];
            int readingByte =0;
            client.getC().send(length);
            DataOutputStream dos = new DataOutputStream(client.getSocket().getOutputStream());
            while((readingByte = fis.read(b))!=-1){
//                client.getSocket().getOutputStream().write(1);
                
                
                if(readingByte != AbstractServer.packetSize){
                    b = Arrays.copyOf(b, readingByte);
                }
                dos.write(b);
//                System.out.println(client.getC().recoit());
//                b = new byte[AbstractServer.packetSize];
            }
//            client.getC().send(-1);
//            client.getSocket().getOutputStream().write(0);
            client.getC().send("fin du transfere");
        } catch (IOException ex) {
            Logger.getLogger(MessageClientProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
