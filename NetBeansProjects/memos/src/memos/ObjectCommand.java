/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class ObjectCommand {

    private Method[] methods;

    public ObjectCommand() {
        methods = this.getClass().getMethods();
    }

    public Object exec(String method, Object... param) throws ObjectCommandExeption {
        Method m = searchMethod(method, param);
        if (m != null) {
            try {
                return m.invoke(this, param);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ObjectCommand.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ObjectCommand.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ObjectCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            throw new ObjectCommandExeption("command "+method +" inconnu");
        }

        return null;

    }

    public void coucou() {
        System.out.println("coucou");
    }

    public void echo(int i) {
        System.out.println(i);
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ObjectCommandExeption {
        ObjectCommand o = new ObjectCommand();
        o.exec("coucou");
        o.exec("echo", 4);
    }

    private Method searchMethod(String method, Object... param) {
        for (Method m : methods) {
            if (m.getName().equals(method)) {
                Class<?>[] parameterTypes = m.getParameterTypes();
//                System.out.println(m.getName()+" "+Arrays.toString(parameterTypes));
//                System.out.println(param.length);
//                System.out.println(parameterTypes.length);
                if (param.length == parameterTypes.length) {
                    boolean same = true;
                    for (int i = 0; i < param.length; i++) {
                        if (param[i].getClass() != parameterTypes[i]) {
                            same = false;
                        }
                    }
                    if (same) {
                        return m;
                    }
                }
            }
        }
        return null;
    }
}
