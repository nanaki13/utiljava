/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import com.jonathan.json.ArrayJson;
import com.jonathan.json.JsonObject;
import com.jonathan.json.NumberJson;
import com.jonathan.json.TypeJson;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.Acteur;
import metier.Film;
import metier.Pays;
import metier.Realisateur;
import parser.ParserJson;

/**
 *
 * @author jonathan
 */
public class Serialiseur {

    private static final String get = "get";
    private static final String set = "set";

    public static void main(String[] a) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        System.out.println(lastSuperBeforeObj(ArrayList.class));
        test();
    }

    public static void test() {
        Film film = new Film(4);
        film.setSynopsis("un petit film sympas");
        film.setTitre("l'arnaque");
        film.setChemin("/jsdjd/");
        Acteur acteur = new Acteur();
        acteur.setId(3);
        acteur.setNom("Newman");
        acteur.setPrenom("Paul");
        Acteur acteur2 = new Acteur();
        acteur2.setId(4);
        acteur2.setNom("Po");
        acteur2.setPrenom("bill");
        Pays p = new Pays();
        p.setNom("France");
        p.setId(2);
        ArrayList<Pays> pays = new ArrayList<>();
        pays.add(p);
        Realisateur realisateur = new Realisateur();
        realisateur.setId(5);
        ArrayList<Acteur> acteurs = new ArrayList<>();
        ArrayList<Acteur> acteurs2 = new ArrayList<>();
        ArrayList<Realisateur> realisateurs = new ArrayList<>();
        realisateurs.add(realisateur);
        acteurs.add(acteur);
        acteurs2.add(acteur);
        acteurs2.add(acteur2);
        acteurs2.add(realisateur);
        film.setActeurs(acteurs);
        ArrayList<Film> f = new ArrayList<>();
        LinkedList<Film> lf = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            film = new Film(i);
            if (i % 2 == 0) {
                film.setActeurs(acteurs);
            } else {
                film.setActeurs(acteurs2);
            }
            f.add(film);
            film.setRealisateurs(realisateurs);
            film.setDuree(123);
            film.setSynopsis("un petit film sympas" + i);
            film.setTitre("l'arnaque");
            film.setOrigine(p);
            //    film.setChemin("/jsdjd/");
            lf.add(film);
        }
//        serialise(lf, "filmsLourd");
        acteur.setFilmsJoue(f);

        serialiseLazy(lf, "films");
        serialiseLazy(pays, "pays");
        serialiseLazy(realisateurs, "realisateurs");
        serialiseLazy(acteurs2, "acteurs");
        
        List<Pays> ppays = deserialise(Pays.class, "pays");
        List<Acteur> act = deserialise(Acteur.class, "acteurs");
        List<Realisateur> rea = deserialise(Realisateur.class, "realisateurs");
        List<Film> deserialise = deserialise(Film.class, "films");

       
        for (Film fff : deserialise) {
             
            for (Acteur aacteur : fff.getActeurs()) {
//                System.out.println(aacteur.getFilmsJoue());
                if (aacteur.getFilmsJoue() == null) {
                    aacteur.setFilmsJoue(new ArrayList<Film>());
                }
                aacteur.getFilmsJoue().add(fff);
            }
        }
        System.out.println(act.get(1).getFilmsJoue());
        System.out.println(rea.get(0).getFilmsJoue());
//        for (Film fff : act.get(0).getFilmsJoue()) {
//            System.out.println(fff.getTitre());
//        }

//        for (Film f : deserialise) {
        //    System.out.println(f.getId());
        //    System.out.println(f.getOrigine().getNom());
        //   System.out.println(f.getDistribution().get(0).getNom());
//        }
    }

    public static Class lastSuperBeforeObj(Class clazz) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
//
        Class<?> superTmp =null;
        if (clazz.equals(Object.class)) {
            superTmp = Object.class;
        } else {
            Class<?> superC = clazz.getSuperclass();
//        System.out.println(clazz.getName());
//        System.out.println(superC.getName());
            if (superC.equals(Object.class)) {
                superTmp = clazz;
            } else {
                boolean continu = true;
                while (continu) {          
                    superTmp = superC;
                    superC = superC.getSuperclass();
                    if (superC.equals(Object.class)) {
                        continu = false;
                    }
                }
            }
        }
        return superTmp;
    }

    public static Map<Class, Map<Integer, Object>> cacheObject = new HashMap<>();
    public static Set<Object> ObjectsDejaSerealise = new HashSet<Object>();

    public static <T> List<T> deserialise(Class<T> c, String file) {
        //ParserJson pj = new ParserJson(new File(file));
        ArrayList<T> ar;
        ar = new ArrayList<>();
        Map<Integer, Object> mapDeC=null;
        Class superC;
        try {
            superC = lastSuperBeforeObj(c);
            if (!cacheObject.containsKey(superC)) {
            mapDeC = new HashMap<Integer, Object>();
            cacheObject.put(superC, mapDeC);
        } else {
            mapDeC = cacheObject.get(superC);
        }
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Serialiseur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Serialiseur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Serialiseur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Serialiseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            // Map<Integer, JsonObject> mj = Storage.fileToMapJson(new File(file));
            //  System.out.println("size"+mj.size());
            Collection<JsonObject> values = Storage.fileToSetJson(new File(file));
            Method[] methods = c.getMethods();
            for (JsonObject jo : values) {
                Method method = null;

                Object[] param = new Object[1];
                Object newInstance = c.newInstance();
                Set<String> keys = jo.getKeys();
                int id;
                for (String key : keys) {
                    TypeJson type = jo.getType(key);
                    String ucFirst = ((char) ((key.charAt(0) - 'a') + 'A')) + key.substring(1);
                    switch (type) {
                        case TEXT:
                            method = c.getMethod(set + ucFirst, String.class);
                            param[0] = jo.getString(key);
                            break;

                        case NUMBER:
                            BigDecimal bg = (BigDecimal) jo.get(key);
                            if (bg.scale() >= 0) {
                                param[0] = jo.getInt(key);
                                if (key.equals("id")) {
                                    id = (int) param[0];
                                    mapDeC.put(id, newInstance);
                                    try {
                                        method = c.getMethod(set + ucFirst, Integer.TYPE);
                                    } catch (NoSuchMethodException e) {
                                        method = c.getMethod(set + ucFirst, Integer.class);
                                    }

                                } else if (key.startsWith("id_")) {
                                    String fieldName = key.substring(3);
                                    String methodFor = set + ((char) ((fieldName.charAt(0) - 'a') + 'A')) + fieldName.substring(1);
                                    Class cFor = getClassPara(methodFor, methods);
                                    
                                    Object objForFieldObj = cacheObject.get(lastSuperBeforeObj( cFor)).get((int) param[0]);
                                    param[0] = objForFieldObj;
                                    method = c.getMethod(methodFor, cFor);
                                } else {
                                    method = c.getMethod(set + ucFirst, Integer.TYPE);
                                }

                            }
                            break;
                        case ARRAY:
                            if (key.startsWith("ids_")) {
                                String fieldName = key.substring(4);
                                String methodNameFor = set + ucFirst(fieldName);
                                Class cFor = getClassPara(methodNameFor, methods);
                                String name = cFor.getName();
                                Type genericType = getGenericTpe(methodNameFor, methods);
                                String substring = genericType.toString().substring(name.length());
                                String genericClassName = substring.substring(1, substring.length() - 1);
                                try {
                                    Class<?> forName = Class.forName(genericClassName);
                                    ArrayJson array = jo.getArray(key);
                                    int size = array.size();
                                    Collection col = (Collection) cFor.newInstance();

                                    for (int i = 0; i < size; i++) {
                                        Class lastSuperBeforeObj = lastSuperBeforeObj( forName);
                                        System.out.println(lastSuperBeforeObj);
                                         System.out.println(cacheObject.containsKey(lastSuperBeforeObj));
                                        if (cacheObject.containsKey(lastSuperBeforeObj)) {
                                            Map<Integer, Object> map = cacheObject.get(lastSuperBeforeObj);
                                            Object get1 = map.get(array.getInt(i));
                                            col.add(get1);
                                        }

                                    }
                                    param[0] = col;
                                    method = c.getMethod(methodNameFor, cFor);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(Serialiseur.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                            break;
                    }
                    if(type!=TypeJson.NULL){
                        method.invoke(newInstance, param);
                    }
                    

                }

                ar.add(c.cast(newInstance));

            }

        } catch (ObjectReaderException | InstantiationException | IllegalAccessException | SecurityException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(Serialiseur.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ar;
    }

    public static void serialiseLazy(Collection<?> c, String file) {
        FileWriter fw = null;

        try {
            Iterator<?> iterator = c.iterator();
            fw = new FileWriter(file);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                while (iterator.hasNext()) {
                    Object next = iterator.next();
                    if(!ObjectsDejaSerealise.contains(next)){
                        bw.append(ObjectToJsonObjectLazy(next).toStringJson());
                        if (iterator.hasNext()) {
                            bw.append(',');
                        }
                    bw.append('\n');    
                    }
                    
                }
                bw.flush();
            }
        } catch (IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
            Logger.getLogger(Serialiseur.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Serialiseur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void serialise(Collection<?> c, String file) {
        FileWriter fw = null;

        try {
            Iterator<?> iterator = c.iterator();
            fw = new FileWriter(file);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                while (iterator.hasNext()) {
                    bw.append(ObjectToJsonObject(iterator.next()).toStringJson());
                    if (iterator.hasNext()) {
                        bw.append(',');
                    }
                    bw.append('\n');
                }
                bw.flush();
            }
        } catch (IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
            Logger.getLogger(Serialiseur.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Serialiseur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // static F jo = new JsonObject();
    public static JsonObject ObjectToJsonObjectLazy(Object objetASerealis) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        //  File f = new File(o.getClass().getSimpleName());

        JsonObject jo = new JsonObject();
        Map<String, Method> mapMethod = new HashMap<>();
        Class<? extends Object> aClass = objetASerealis.getClass();
        Method[] methods = aClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method;
            method = methods[i];
            String name = method.getName();
            boolean matches;
            matches = (name.charAt(0) == 'g') && (name.charAt(1) == 'e') && (name.charAt(2) == 't');
            if (matches) {
                Class<?> returnType = method.getReturnType();
                String ReturnTypeName = method.getReturnType().getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                String nomApresGet = method.getName().substring(3);
                nomApresGet = lcFirst(nomApresGet);
                if (returnType.isPrimitive() && parameterTypes.length == 0) {
                    if (method.getParameterTypes().length == 0) {
                        switch (ReturnTypeName) {
                            case "int": {

                                int x = (int) method.invoke(objetASerealis, (Object[]) null);
                                jo.put(nomApresGet, x);
                                break;

                            }
                            case "double": {
                                double x = (double) method.invoke(objetASerealis, (Object[]) null);

                                jo.put(nomApresGet, x);
                                break;
                            }
                            case "float": {
                                float x = (float) method.invoke(objetASerealis, (Object[]) null);
                                jo.put(nomApresGet, x);
                                break;
                            }
                            case "char": {
                                char x = (char) method.invoke(objetASerealis, (Object[]) null);
                                jo.put(nomApresGet, x);
                                break;
                            }
                        }
                    }
                } else if (returnType.equals(String.class)) {
                    String x = (String) method.invoke(objetASerealis, (Object[]) null);
                    jo.put(nomApresGet, x);

                } else if (returnType.equals(Integer.class)) {
                    Integer x = (Integer) method.invoke(objetASerealis, (Object[]) null);
                    jo.put(nomApresGet, x);

                } else {
                    try {
                        Method getMethodOfReturnType = returnType.getMethod("getId");
                        Object invoke = method.invoke(objetASerealis, (Object[]) null);
                        if (!ObjectsDejaSerealise.contains(invoke)) {
                            if (invoke != null) {
                                Object idObj = getMethodOfReturnType.invoke(invoke, (Object[]) null);
                                jo.put("id_" + nomApresGet, new NumberJson((int) idObj));
                            }
                        }
                    } catch (NoSuchMethodException | SecurityException ex) {
                        Class avtDerniereClass = lastSuperBeforeObj(returnType);
                        if (avtDerniereClass.equals(AbstractCollection.class)) {
                            Collection<?> x = (Collection<?>) method.invoke(objetASerealis, (Object[]) null);
                            if (x != null) {
                                boolean isSerialisable = false, first = true, breakk = false;
                                ArrayJson aj = null;

                                Method methodObjectCol = null;
                                for (Object object : x) {
                                    //          System.out.println(object.getClass());
                                    if (first) {
                                        try {
                                            methodObjectCol = object.getClass().getMethod("getId", new Class[0]);
                                            isSerialisable = true;
                                            if (!ObjectsDejaSerealise.contains(object)) {
                                                Object invoke = methodObjectCol.invoke(object, (Object[]) null);
                                                aj = new ArrayJson();
                                                aj.add(new NumberJson((int) invoke));

                                                nomApresGet = "ids_" + nomApresGet;
                                            } else {
                                                breakk = true;
                                                break;
                                            }

                                        } catch (NoSuchMethodException | SecurityException ex1) {
                                            aj = new ArrayJson();
                                            aj.add(ObjectToJsonObjectLazy(object));
                                        }
                                        first = false;
                                    } else {
                                        if (isSerialisable) {
                                            Object invoke = methodObjectCol.invoke(object, (Object[]) null);
                                            aj.add(new NumberJson((int) invoke));
                                        } else {
                                            aj.add(ObjectToJsonObjectLazy(object));
                                        }
                                    }
                                }
                                if (aj != null) {
                                    jo.put(nomApresGet, aj);
                                }
                            }
                        }
                    }

                }
            }
        }
        ObjectsDejaSerealise.add(objetASerealis);
        return jo;
    }

    public static JsonObject ObjectToJsonObject(Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        //  File f = new File(o.getClass().getSimpleName());

        JsonObject jo = new JsonObject();
        Map<String, Method> mapMethod = new HashMap<>();
        Class<? extends Object> aClass = o.getClass();
        Method[] methods = aClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method;
            method = methods[i];
            String name = method.getName();
            boolean matches;
            matches = (name.charAt(0) == 'g') && (name.charAt(1) == 'e') && (name.charAt(2) == 't');
            if (matches) {
                Class<?> returnType = method.getReturnType();
                String ReturnTypeName = method.getReturnType().getName();
                Class<?>[] parameterTypes = method.getParameterTypes();
                String nomApresGet = method.getName().substring(3);
                nomApresGet = lcFirst(nomApresGet);
                if (returnType.isPrimitive() && parameterTypes.length == 0) {
                    if (method.getParameterTypes().length == 0) {
                        switch (ReturnTypeName) {
                            case "int": {

                                int x = (int) method.invoke(o, (Object[]) null);
                                jo.put(nomApresGet, x);
                                break;

                            }
                            case "double": {
                                double x = (double) method.invoke(o, (Object[]) null);

                                jo.put(nomApresGet, x);
                                break;
                            }
                            case "float": {
                                float x = (float) method.invoke(o, (Object[]) null);
                                jo.put(nomApresGet, x);
                                break;
                            }
                            case "char": {
                                char x = (char) method.invoke(o, (Object[]) null);
                                jo.put(nomApresGet, x);
                                break;
                            }
                        }
                    }
                } else if (returnType.equals(String.class)) {
                    String x = (String) method.invoke(o, (Object[]) null);
                    jo.put(nomApresGet, x);

                } else if (returnType.equals(Integer.class)) {
                    Integer x = (Integer) method.invoke(o, (Object[]) null);
                    jo.put(nomApresGet, x);

                } else {
                    System.out.println("nom après get : " + nomApresGet);
                    if (!nomApresGet.equals("class")) {
                        Class avtDerniereClass = lastSuperBeforeObj(returnType);
                        if (avtDerniereClass != null) {
                            if (AbstractCollection.class.equals(avtDerniereClass)) {
                                Collection<?> x = (Collection<?>) method.invoke(o, (Object[]) null);
                                if (x != null) {
                                    ArrayJson aj = new ArrayJson();
                                    for (Object object : x) {
                                        aj.add(ObjectToJsonObject(object));
                                    }
                                    jo.put(nomApresGet, aj);
                                }
                            } else {
                                Object x = method.invoke(o, (Object[]) null);
                                if (x != null) {
                                    jo.put(nomApresGet, ObjectToJsonObject(x));
                                }

                            }
                        }
                    } else {
                        jo.put("class", o.getClass().getName());
                    }
                }
            }
        }
        return jo;
    }

    public static String ucFirst(String s) {
        return ((char) ((s.charAt(0) - 'a') + 'A')) + s.substring(1);
    }

    public static Class getClassPara(String name, Method[] methods) {
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return method.getParameterTypes()[0];
            }
        }
        return null;

    }

    private static Type getGenericTpe(String methodNameFor, Method[] methods) {
        for (Method method : methods) {
            if (method.getName().equals(methodNameFor)) {
                return method.getGenericParameterTypes()[0];
            }
        }
        return null;
    }

    private static String lcFirst(String s) {
        return ((char) ((s.charAt(0) - 'A') + 'a')) + s.substring(1);
    }
}
