/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class SimpleMethodCaller {

    private Object o;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Map<String, Method> mapMethod;
//    private Set<String> confirmsDialogue;

    public SimpleMethodCaller(Object o, ObjectOutputStream oos, ObjectInputStream ois) {
        this.o = o;
        this.oos = oos;
        this.ois = ois;
        mapMethod = new HashMap<String, Method>();
//        confirmsDialogue = new HashSet<String>();
    }

    public void start() throws IOException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        while (true) {
            String methodS = (String) ois.readObject();
            Method method = getMethod(methodS);
            if (method != null) {
//                System.out.println("method : " + methodS);
                Class<?> returnType = method.getReturnType();
                if ("void".equals(returnType.getName())) {
                    int nbRead = method.getParameterTypes().length;
                    Object[] param = new Object[nbRead];
                    boolean haveNull = false;
                    for (int i = 0; i < nbRead; i++) {
                        param[i] = ois.readObject();
                        if (param[i] == null) {
                            haveNull = true;
                        }

//                        System.out.println("param[i] : " + param[i]);

                    }
//                    if(confirmsDialogue.contains(methodS)){
//                        if(haveNull){
//                            oos.writeBoolean(false);
//                            System.out.println(" oos.writeBoolean(false)");
//                        }else{
//                            oos.writeBoolean(true);
//                            System.out.println(" oos.writeBoolean(true)");
//                        }
//                    }
                    method.invoke(o, param);
                } else {
                    Object invoke = method.invoke(o);
                    oos.writeObject(invoke);

                }
                
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(SimpleMethodCaller.class.getName()).log(Level.SEVERE, null, ex);
//                }
            }

        }
    }

    private Method getMethod(String m) {
        Method get = mapMethod.get(m);
        if (get != null) {
            return get;
        }
        Method[] methods = o.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(m)) {
                mapMethod.put(m, method);
                return method;
            }
        }
        return null;
    }

//    void addConfirm(String carteJouer) {
//        confirmsDialogue.add(carteJouer);
//    }

}
