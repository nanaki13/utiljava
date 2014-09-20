/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memos;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public interface Command {
    
    default public void exit(String[] args) {
        System.out.println("by by!");
        System.exit(0);
    }

    default public void runConsolMethod() {
        try {
            String stringUtil = getStringUtil();
            if(stringUtil.isEmpty()){
                return;
            }
            String[] sp = extractCommand(stringUtil);
            Method method = this.getClass().getMethod(sp[0], String[].class);
            Object[] pa = new Object[]{sp};
            method.invoke(this, pa);
        } catch (NoSuchMethodException ex) {

        } catch (SecurityException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    default public Object execMethod(String methodString) {
        try {
           
            if(methodString.isEmpty()){
                return null;
            }
            String[] sp = extractCommand(methodString);
            Method method = this.getClass().getMethod(sp[0], String[].class);
            Object[] pa = new Object[]{sp};
            return method.invoke(this, pa);
        } catch (NoSuchMethodException ex) {

        } catch (SecurityException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    

    public static String getStringUtil() {
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

    default public void mult(String[] command) {
        System.out.println(Double.valueOf(command[1]) * Double.valueOf(command[2]));
    }

    default public void echo(String[] command) {
        System.out.println(Arrays.asList(command));
    }

    public static void main(String[] args) {
         Command b = new Command() {};
         while(true){
             b.runConsolMethod();
         }
//        System.out.println(Arrays.asList(extractCommand("aa \"b  \\\" b\" cc")));
    }

    public static void run(Command c) {
        while (true) {
            c.runConsolMethod();
        }
    }

    public static String[] extractCommand(String stringUtil) {
        ArrayList<String> ret = new ArrayList<>();
        StringBuilder b = new StringBuilder();
        boolean inString = false;
        boolean inWhite = false;
        boolean inWord = false;
        boolean write = false;
        boolean read = true;
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
                ret.add(b.toString());
                b.setLength(0);
                flush = false;
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
