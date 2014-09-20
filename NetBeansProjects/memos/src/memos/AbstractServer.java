/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memos;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public abstract class AbstractServer extends Thread {
    public static int packetSize = 4000;
    private static MessageClient extractMessageString(String cmd) {
        String[] extractCommand = extractCommand(cmd);
        String commande = extractCommand[0];
        Object[] param;
        if (extractCommand.length > 1) {
            param = new Object[1];
            param[0] = "";
            for (int i = 0; i < extractCommand.length - 1; i++) {
                param[0] += extractCommand[i + 1];
                if (i != extractCommand.length) {
                    param[0] += " ";
                }
            }
        } else {
            param = new Object[0];
        }

        return new MessageClient(commande, param, true);
    }

    private ServerSocket ss;

    public AbstractServer() {
        try {
            ss = new ServerSocket(8080);

        } catch (IOException ex) {
            Logger.getLogger(AbstractServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while (!isInterrupted()) {
            try {
                Socket accept = ss.accept();
//                accept.set
                (new ThreadClient(accept, this)).start();
            } catch (IOException ex) {
                Logger.getLogger(AbstractServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException, IOException, IOException, ClassNotFoundException {
        AbstractServer s = new AbstractServer() {
        };
        s.start();
//            Thread.sleep(1000);
        Connection c = new Connection(new Socket("127.0.0.1", 8080));
        String cmd;
        while (!(cmd = getStringUtil()).equals("quit prg")) {
            MessageClient mess ;
            mess = extractMessageString(cmd);
            if (mess.getCommand().equals("cp")) {
                
                String[] split = mess.getArgs()[0].toString().split(" ");
                FileOutputStream fos = new FileOutputStream(split[1]);
                Socket socket = c.getSocket();
//                Object[] o = new Object[1]{split[1]};
                mess.setArgs(new Object[]{split[0]});
                c.send(mess);
                boolean continu = true;
                Long sizeOfFile ;
                Object r = c.recoit();
//                System.out.println("r" + r);
                int cpt = 0;
                sizeOfFile = (Long) r;
                System.out.println("sizeOfFile" + sizeOfFile);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                while (cpt<sizeOfFile) {
                    byte[] b ;
                    if(cpt +  AbstractServer.packetSize <= sizeOfFile){
                        b = new byte[AbstractServer.packetSize];
                    }
                    else{
                        int still = (int) (sizeOfFile - cpt);
                        b = new byte[still];
                    }
                    dis.read(b);
//                    socket.getInputStream().read(b);
                    fos.write(b);
                    cpt+=AbstractServer.packetSize;
                    
//                    c.send("recu");
//                    System.out.println(read1);
//                    System.out.println(1024*1024);
//                    if(read1 < packetSize){
//                        System.out.println("doit skeep");
//                        socket.getInputStream().skip(packetSize - read1);
//                    }
                    

                }
                
                System.out.println(c.recoit());
                fos.close();

            } else {
                c.send(mess);
                mess.setNeedReponse(true);
            }

            if (mess.isNeedReponse()) {
                System.out.println(c.recoit());
            }
        }
        System.exit(0);

    }

    private static String getStringUtil() {
        StringBuilder b = new StringBuilder();
        try {
            InputStream is = System.in;
            char c = (char) is.read();
            while (c != '\n') {
                b.append(c);
                c = (char) is.read();
            }
        } catch (IOException ex) {
            Logger.getLogger(Memos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b.toString();
    }

    public static String[] extractCommand(String stringUtil) {
        ArrayList<String> ret = new ArrayList<>();
        StringBuilder b = new StringBuilder();
        boolean inString = false;
        boolean inWhite = false;
        boolean inWord = false;
        boolean write = false;
        boolean stringed = false;
        boolean flush = false;
        int prev = -1;
        int charAt = -1;
        for (int i = 0; i < stringUtil.length(); i++) {
            prev = charAt;
            charAt = stringUtil.charAt(i);
            if (inWord) {
                if (charAt == ' ') {
                    inWord = false;
                    inWhite = true;
                    flush = true;
                } else {
                    write = true;
                }
            } else if (inWhite) {
                if (charAt != ' ') {
                    if (charAt == '"') {
                        inString = true;
                        stringed = true;
                    } else {
                        inWord = true;
                        write = true;
                    }
                    inWhite = false;
                }
            } else if (inString) {
                if (charAt == '"' && prev != '\\') {
                    inString = false;
                    flush = true;
                } else {
                    write = true;
                }
            } else {
                inWord = true;
                write = true;
            }
            if (write) {
                b.append((char) charAt);
                write = false;
            } else if (flush) {
                if (stringed) {
                    ret.add('"' + b.toString() + '"');

                } else {
                    ret.add(b.toString());
                }

                b.setLength(0);
                flush = false;
                stringed = false;
            }
        }
        if (inWord || inString) {
            ret.add(b.toString());
            b.setLength(0);
            flush = false;
        }
        return ret.toArray(new String[0]);
    }

}
