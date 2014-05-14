/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import com.jonathan.json.ArrayJson;
import com.jonathan.json.BooleanJson;
import com.jonathan.json.JsonObject;
import com.jonathan.json.JsonObjectInterface;
import com.jonathan.json.NumberJson;
import com.jonathan.json.TextJson;
import com.jonathan.json.TypeJson;
import com.jonathan.json.validator.ValidatorException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.jonathan.json.parser.ParserJson;
//import com.jonathan.metier.Acteur;
//import com.jonathan.metier.Film;
//import com.jonathan.metier.Pays;
//import com.jonathan.metier.Realisateur;

/**
 *
 * @author jonathan
 */
public class DataJson {

    private static final String get = "get";
    private static final String set = "set";

    public static void main(String[] a) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ObjectReaderException {
        test2();

//        test();
    }

    public static void objectToFile(Object o, String file) {
        try {
            JsonObjectInterface objectToJsonObject = DataJson.objectToJsonObject(o);

            File f = new File(file);
            try (FileWriter fw = new FileWriter(f)) {
                fw.write(objectToJsonObject.toStringJson());
            }
        } catch (ObjectReaderException | IOException ex) {
            Logger.getLogger(DataJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static <T> T FileToObject(Class<T> aClass, String file) {
        try {
            JsonObject fileToOneJson = Storage.fileToOneJson(new File(file));
            return deserialiseOneObject(aClass, fileToOneJson);
        } catch (ObjectReaderException ex) {
            Logger.getLogger(DataJson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void clearCacheDeserealisation() {
        cacheObject.clear();
    }

    public void clearCacheSerealisation() {
        objectsDejaSerealise.clear();
    }

    public static class Test {

        private String string;
        private int nb;
        private int id;
        private boolean boo;
        private List<Integer> listInt;
        private List<Test> listTest;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Test> getListTest() {
            return listTest;
        }

        public void setListTest(List<Test> listTest) {
            this.listTest = listTest;
        }

        public String getString() {
            return string;
        }

        public int getNb() {
            return nb;
        }

        public List<Integer> getListInt() {
            return listInt;
        }

        public void setString(String string) {
            this.string = string;
        }

        public void setNb(int nb) {
            this.nb = nb;
        }

        public void setBoo(boolean boo) {
            this.boo = boo;
        }

        public boolean isBoo() {
            return boo;
        }

        public void setListInt(List<Integer> listInt) {
            this.listInt = listInt;
        }

        public Test() {

        }

    }

    public static void test2() throws ObjectReaderException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        JsonObject jo = new JsonObject();
        jo.put("string", "balzla");
        jo.put("nb", 123);
        jo.put("boo", true);
        jo.put("id", 1);
        ArrayJson aj = new ArrayJson();
        aj.add(0);
        aj.add(2);
        jo.put("listInt", aj);
        Test test = deserialiseOneObject(Test.class, jo);
        Test test1 = deserialiseOneObject(Test.class, jo);
        Test test2 = deserialiseOneObject(Test.class, jo);
        List<Test> l = new ArrayList<>();
        l.add(test1);
        l.add(test);
        l.add(test2);
        test.setId(2);
        test2.setId(3);
//        test.setListTest(l);
        // DataJson dt = new DataJson();
        //  dt.ser
        jo = (JsonObject) objectToJsonObject(test);
        System.out.println(jo.toStringJsonPretty());
        test = deserialiseOneObject(Test.class, jo);
        System.out.println(test.boo);
        DataJson dt = new DataJson();

        Test test3 = new Test();
        test3.setListTest(l);
        List<Test> l2 = new ArrayList<>();
        l2.add(test3);
        dt.serialiseLazy(l2, "test2.json");
        dt.serialiseLazy(l, "test.json");
        dt.clearCacheDeserealisation();
        List<Test> deserialise = dt.deserialise(Test.class, "test.json");
        List<Test> deserialise2 = dt.deserialise(Test.class, "test2.json");
        System.out.println(deserialise2.get(0).getListTest().get(0));
        System.out.println(deserialise2.get(0).getString());

    }

//    public static void test() {
//        Film film = new Film(4);
//        film.setSynopsis("un petit film sympas");
//        film.setTitre("l'arnaque");
//        film.addChemin("/jsdjd/");
//        Acteur acteur = new Acteur();
//        acteur.setId(3);
//        acteur.setNom("Newman");
//        acteur.setPrenom("Paul");
//        Acteur acteur2 = new Acteur();
//        acteur2.setId(4);
//        acteur2.setNom("Po");
//        acteur2.setPrenom("bill");
//        Pays p = new Pays();
//        p.setNom("France");
//        p.setId(2);
//        ArrayList<Pays> pays = new ArrayList<>();
//        pays.add(p);
//        Realisateur realisateur = new Realisateur();
//        realisateur.setId(5);
//        ArrayList<Acteur> acteurs = new ArrayList<>();
//        ArrayList<Acteur> acteurs2 = new ArrayList<>();
//        ArrayList<Realisateur> realisateurs = new ArrayList<>();
//        realisateurs.add(realisateur);
//        acteurs.add(acteur);
//        acteurs2.add(acteur);
//        acteurs2.add(acteur2);
//        acteurs2.add(realisateur);
//        film.setActeurs(acteurs);
//        ArrayList<Film> f = new ArrayList<>();
//        LinkedList<Film> lf = new LinkedList<>();
//        for (int i = 0; i < 500; i++) {
//            film = new Film(i);
//            if (i % 2 == 0) {
//                film.setActeurs(acteurs);
//            } else {
//                film.setActeurs(acteurs2);
//            }
//            f.add(film);
//            film.setRealisateurs(realisateurs);
//            film.setDuree(123);
//            film.setSynopsis("un petit film sympas" + i);
//            film.setTitre("l'arnaque");
//            film.setOrigine(p);
//            //    film.setChemin("/jsdjd/");
//            lf.add(film);
//        }
////        serialise(lf, "filmsLourd");
//        acteur.setFilmsJoue(f);
//        DataJson dataJson = new DataJson();
//        dataJson.serialiseLazy(lf, "films");
//        dataJson.serialiseLazy(pays, "pays");
//        dataJson.serialiseLazy(realisateurs, "realisateurs");
//        dataJson.serialiseLazy(acteurs2, "acteurs");
//
//        List<Pays> ppays = dataJson.deserialise(Pays.class, "pays");
//        List<Acteur> act = dataJson.deserialise(Acteur.class, "acteurs");
//        List<Realisateur> rea = dataJson.deserialise(Realisateur.class, "realisateurs");
//        List<Film> deserialise = dataJson.deserialise(Film.class, "films");
//
//        for (Film fff : deserialise) {
//
//            for (Acteur aacteur : fff.getActeurs()) {
////                System.out.println(aacteur.getFilmsJoue());
//                if (aacteur.getFilmsJoue() == null) {
//                    aacteur.setFilmsJoue(new ArrayList<Film>());
//                }
//                aacteur.getFilmsJoue().add(fff);
//            }
//        }
//        System.out.println(act.get(1).getFilmsJoue());
//        System.out.println(rea.get(0).getFilmsJoue());
////        for (Film fff : act.get(0).getFilmsJoue()) {
////            System.out.println(fff.getTitre());
////        }
//
////        for (Film f : deserialise) {
//        //    System.out.println(f.getId());
//        //    System.out.println(f.getOrigine().getNom());
//        //   System.out.println(f.getDistribution().get(0).getNom());
////        }
//    }

    /**
     *
     *
     * @param clazz
     * @return la derniere classe mere avant objet
     * @throws IllegalArgumentException
     */
    public static Class lastSuperBeforeObj(Class clazz) {
//
        Class<?> superTmp = null;
        if (clazz.equals(Object.class)) {
            superTmp = Object.class;
        } else {
            Class<?> superC = clazz.getSuperclass();
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

    public Map<Class, Map<Integer, Object>> cacheObject = new HashMap<>();
    public Set<Object> objectsDejaSerealise = new HashSet<>();

    public <T> List<T> deserialise(Class<T> c, String file) {
        ArrayList<T> ar;
        ar = new ArrayList<>();
        Map<Integer, Object> mapDeC ;
        Class superC;

        superC = lastSuperBeforeObj(c);
        if (!cacheObject.containsKey(superC)) {
            mapDeC = new HashMap<>();
            cacheObject.put(superC, mapDeC);
        } else {
            mapDeC = cacheObject.get(superC);
        }

        try {
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

                                    Object objForFieldObj = cacheObject.get(lastSuperBeforeObj(cFor)).get((int) param[0]);
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
                                Type genericType = getGenericType(methodNameFor, methods);
                                String substring = genericType.toString().substring(name.length());
                                String genericClassName = substring.substring(1, substring.length() - 1);
                                try {
                                    Class<?> forName = Class.forName(genericClassName);
                                    ArrayJson array = jo.getArray(key);
                                    int size = array.size();
                                    Collection col = new ArrayList();

                                    for (int i = 0; i < size; i++) {
                                        Class lastSuperBeforeObj = lastSuperBeforeObj(forName);
                                        if (cacheObject.containsKey(lastSuperBeforeObj)) {
                                            Map<Integer, Object> map = cacheObject.get(lastSuperBeforeObj);
                                            Object get1 = map.get(array.getInt(i));
                                            col.add(get1);
                                        }

                                    }
                                    param[0] = col;
                                    method = c.getMethod(methodNameFor, cFor);
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(DataJson.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                            break;

                    }
                    if (type != TypeJson.NULL) {
                        if(method != null)
                            method.invoke(newInstance, param);
                        else{
                            
                        }
                            
                    }

                }

                ar.add(c.cast(newInstance));

            }

        } catch (ObjectReaderException | InstantiationException | IllegalAccessException | SecurityException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(DataJson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ar;
    }

    public static <T> T deserialiseOneObject(Class<T> clazz, JsonObjectInterface jo) throws ObjectReaderException {
        Method[] methods = clazz.getMethods();
        Method method;
        Object[] param = new Object[1];
        Object newInstance = null;
        if (jo.getType() == TypeJson.OBJET) {
            try {
                newInstance = clazz.newInstance();
                JsonObject jsonObject = (JsonObject) jo;
                Set<String> attributs = jsonObject.getKeys();
                for (String attribut : attributs) {
                    TypeJson type = jsonObject.getType(attribut);
                    String ucFirstAttribut = ucFirst(attribut);
                    String methodNameFor = set + ucFirstAttribut;
                    Class cFor = getClassPara(methodNameFor, methods);
                    if (cFor.equals(Integer.TYPE)) {
                        param[0] = jsonObject.getInt(attribut);
                    } else if (cFor.equals(Float.TYPE)) {
                        param[0] = jsonObject.getFloat(attribut);
                    } else if (cFor.equals(Double.TYPE)) {
                        param[0] = jsonObject.getDouble(attribut);
                    } else if (cFor.equals(Long.TYPE)) {
                        param[0] = jsonObject.getLong(attribut);
                    } else if (cFor.equals(Boolean.TYPE)) {
                        param[0] = jsonObject.getBoolean(attribut);
                    } else if (type.equals(TypeJson.ARRAY)) {

                        String name = cFor.getName();
                        Type genericType = getGenericType(methodNameFor, methods);
                        String substring = genericType.toString().substring(name.length());
                        String genericClassName = substring.substring(1, substring.length() - 1);

                        Class<?> forName = Class.forName(genericClassName);
                        ArrayJson array = jsonObject.getArray(attribut);
                        int size = array.size();
                        Collection col;
                        if (cFor.getConstructors().length == 0) {
                            if (cFor.isAssignableFrom(List.class)) {
                                col = new ArrayList();
                            } else {
                                col = new HashSet();
                            }
                        } else {
                            col = (Collection) cFor.newInstance();
                        }

                        for (int i = 0; i < size; i++) {

                            col.add(deserialiseOneObject(forName, array.get(i)));

                        }
                        param[0] = col;
                    } else {
                        param[0] = deserialiseOneObject(cFor, jsonObject.get(attribut));
                    }

                    method = clazz.getMethod(methodNameFor, cFor);

                    method.invoke(newInstance, param);
                }
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
                throw new ObjectReaderException(ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DataJson.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (jo.getType() == TypeJson.TEXT) {
            newInstance = ((TextJson) jo).getValue();
        } else if (jo.getType() == TypeJson.NUMBER) {
            BigDecimal bg = (BigDecimal) jo;
            if (clazz.equals(Integer.class)) {
                newInstance = bg.intValue();
            } else if (clazz.equals(Float.class)) {
                newInstance = bg.floatValue();
            } else if (clazz.equals(Double.class)) {
                newInstance = bg.doubleValue();
            } else if (clazz.equals(Long.class)) {
                newInstance = bg.longValue();
            }
        } else if (jo.getType() == TypeJson.BOOLEAN) {
            BooleanJson booleanJson = (BooleanJson) jo;
            newInstance = booleanJson.getValue();
        }
        return clazz.cast(newInstance);
    }

    public <T> T deserialiseOneObject(Class<T> c, String file) throws ObjectReaderException, ValidatorException {

        JsonObject jo = new ParserJson(new File(file)).parse();
        return deserialiseOneObject(c, jo);

    }

    /**
     * Sérealise un collection d'object en Json dans le fichier file. garde en
     * mémoire les référence des objects déja séréalisé. si un object de la
     * collection a déja été séréalisé même par un appelle précédant, il ne sera
     * pas séréalisé une autre fois. si les objets propriété d'un objet à
     * séréalisé possédent une id ()int ou Integer, juste celle ci sera
     * séréalisé.
     *
     * @param objects
     * @param file
     */
    public void serialiseLazy(Collection<?> objects, String file) {
        FileWriter fw = null;

        try {
            Iterator<?> iterator = objects.iterator();
            fw = new FileWriter(file);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write('[');
                while (iterator.hasNext()) {
                    Object next = iterator.next();
                    if (!objectsDejaSerealise.contains(next)) {
                        bw.append(ObjectToJsonObjectLazy(next).toStringJson());
                        if (iterator.hasNext()) {
                            bw.append(',');
                            bw.append('\n');
                        }
                        
                    }

                }
                 bw.write(']');
                bw.flush();
                bw.close();
                fw.close();
            }
        } catch (IOException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
            Logger.getLogger(DataJson.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(DataJson.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Sérealise un collection d'object en Json dans le fichier file.
     *
     * @param objects
     * @param file
     */
    public void serialise(Collection<?> objects, String file) {
        FileWriter fw = null;
        try {

            Iterator<?> iterator = objects.iterator();
            fw = new FileWriter(file);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                while (iterator.hasNext()) {
                    bw.append(objectToJsonObject(iterator.next()).toStringJson());
                    if (iterator.hasNext()) {
                        bw.append(',');
                    }
                    bw.append('\n');
                }
                bw.flush();
            } catch (ObjectReaderException ex) {
                Logger.getLogger(DataJson.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(DataJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // static F jo = new JsonObject();
    public JsonObjectInterface ObjectToJsonObjectLazy(Object objetASerealis) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        //  File f = new File(o.getClass().getSimpleName());

        Class<? extends Object> aClass = objetASerealis.getClass();
        if (aClass.equals(Integer.class)) {
            Integer i = (Integer) objetASerealis;
            return new NumberJson(i);
        } else if (aClass.equals(Float.class)) {
            Float i = (Float) objetASerealis;
            return new NumberJson(i);
        } else if (aClass.equals(Long.class)) {
            Long i = (Long) objetASerealis;
            return new NumberJson(i);
        } else if (aClass.equals(Double.class)) {
            Double i = (Double) objetASerealis;
            return new NumberJson(i);
        } else if (aClass.equals(String.class)) {
            String i = (String) objetASerealis;
            return new TextJson(i);
        } else if (aClass.equals(Boolean.class)) {
            Boolean i = (Boolean) objetASerealis;
            return (i == true) ? BooleanJson.TRUE : BooleanJson.FALSE;
        }
        JsonObject jo = new JsonObject();
        Method[] methods = aClass.getMethods();

        for (int i = 0; i < methods.length; i++) {
            Method method;
            method = methods[i];
            String name = method.getName();
            int modifiers = method.getModifiers();
            if (Modifier.isStatic(modifiers)
                    || Modifier.isPrivate(modifiers) || Modifier.isTransient(modifiers)) {
                continue;
            }
            boolean matches;
            matches = (name.startsWith(get));
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
                            case "boolean": {
                                boolean x = (boolean) method.invoke(objetASerealis, (Object[]) null);
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
                        if (!objectsDejaSerealise.contains(invoke)) {
                            if (invoke != null) {
                                Object idObj = getMethodOfReturnType.invoke(invoke, (Object[]) null);
                                jo.put("id_" + nomApresGet, new NumberJson((int) idObj));
                            }
                        }
                    } catch (NoSuchMethodException | SecurityException ex) {

                        if (Collection.class.isAssignableFrom(returnType)) {
                            Collection<?> x = (Collection<?>) method.invoke(objetASerealis, (Object[]) null);
                            if (x != null) {
                                boolean isSerialisable = false, first = true, breakk = false;
                                ArrayJson aj = null;

                                Method methodObjectCol = null;
                                for (Object object : x) {
                                    if (first) {
                                        try {
                                            methodObjectCol = object.getClass().getMethod("getId", new Class[0]);
                                            isSerialisable = true;
                                            if (!objectsDejaSerealise.contains(object)) {
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
            } else if (name.startsWith("is")) {
                String nomApresIs = name.substring(2);
                nomApresIs = lcFirst(nomApresIs);
                Class<?> returnType = method.getReturnType();
                Class<?>[] parameterTypes = method.getParameterTypes();
                if ((returnType.equals(Boolean.class) || returnType.equals(Boolean.TYPE)) && parameterTypes.length == 0) {
                    Boolean b = (Boolean) method.invoke(objetASerealis, (Object[]) null);
                    jo.put(nomApresIs, b);
                }
            }
        }
        objectsDejaSerealise.add(objetASerealis);
        return jo;
    }

    public static JsonObjectInterface objectToJsonObject(Object o) throws ObjectReaderException {

        Class<? extends Object> aClass = o.getClass();
        if (aClass.equals(Integer.class)) {
            Integer i = (Integer) o;
            return new NumberJson(i);
        } else if (aClass.equals(Float.class)) {
            Float i = (Float) o;
            return new NumberJson(i);
        } else if (aClass.equals(Long.class)) {
            Long i = (Long) o;
            return new NumberJson(i);
        } else if (aClass.equals(Double.class)) {
            Double i = (Double) o;
            return new NumberJson(i);
        } else if (aClass.equals(String.class)) {
            String i = (String) o;
            return new TextJson(i);
        } else if (aClass.equals(Boolean.class)) {
            Boolean i = (Boolean) o;
            return (i == true) ? BooleanJson.TRUE : BooleanJson.FALSE;
        }
        JsonObject jo = new JsonObject();
        Method[] methods = aClass.getMethods();
        try {
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
                                case "boolean": {
                                    boolean x = (boolean) method.invoke(o, (Object[]) null);
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
                        if (!nomApresGet.equals("class")) {
//                        Class avtDerniereClass = lastSuperBeforeObj(returnType);
//                        if (avtDerniereClass != null) {
                            if (Collection.class.isAssignableFrom(returnType)) {
                                Collection<?> x = (Collection<?>) method.invoke(o, (Object[]) null);
                                if (x != null) {
                                    ArrayJson aj = new ArrayJson();
                                    for (Object object : x) {
                                        aj.add(objectToJsonObject(object));
                                    }
                                    jo.put(nomApresGet, aj);
                                }
                            } else {
                                Object x = method.invoke(o, (Object[]) null);
                                if (x != null) {
                                    jo.put(nomApresGet, objectToJsonObject(x));
                                }

                            }
//                        }
                        } else {
//                        jo.put("class", o.getClass().getName());
                        }
                    }
                } else if (name.startsWith("is")) {
                    String nomApresIs = name.substring(2);
                    nomApresIs = lcFirst(nomApresIs);
                    Class<?> returnType = method.getReturnType();
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if ((returnType.equals(Boolean.class) || returnType.equals(Boolean.TYPE)) && parameterTypes.length == 0) {
                        Boolean b = (Boolean) method.invoke(o, (Object[]) null);
                        jo.put(nomApresIs, b);
                    }
                }
            }
        } catch (Exception e) {
            throw new ObjectReaderException(e);
        }

        return jo;
    }

    public static String ucFirst(String s) {
        return (Character.toUpperCase(s.charAt(0))) + s.substring(1);
    }

    public static Class getClassPara(String name, Method[] methods) {
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return method.getParameterTypes()[0];
            }
        }
        return null;

    }

    private static Type getGenericType(String methodNameFor, Method[] methods) {
        for (Method method : methods) {
            if (method.getName().equals(methodNameFor)) {
                return method.getGenericParameterTypes()[0];
            }
        }
        return null;
    }

    private static String lcFirst(String s) {
        return (Character.toLowerCase(s.charAt(0))) + s.substring(1);
    }
}
