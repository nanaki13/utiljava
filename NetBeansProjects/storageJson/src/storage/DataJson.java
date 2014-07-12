/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import com.jonathan.json.ArrayJson;
import com.jonathan.json.BooleanJson;
import com.jonathan.json.JsonNull;
import com.jonathan.json.JsonObject;
import com.jonathan.json.JsonObjectInterface;
import com.jonathan.json.NumberJson;
import com.jonathan.json.TextJson;
import com.jonathan.json.TypeJson;
import com.jonathan.json.parser.ValidatorException;
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
import com.jonathan.utils.Reflexivite;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Objects;
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

    public static void main(String[] a) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, DataJsonException, ClassNotFoundException, IOException {

        Test t = new Test();

        Test t2 = new Test();
        t2.setId(1);
        List<String> s = new LinkedList<>();
        List<Test> tl = new LinkedList<>();
        tl.add(t2);
        t2.setBoo(true);
        s.add("cou\ncou");
        s.add("pipi");
        t.setListS(s);
        t.setString("salut");
        t.setListTest(tl);
        JsonObjectInterface objectToJsonObject = objectToJsonObject(t);
        StringBuilder bl = new StringBuilder();
        objectToJsonObject.toStringJsonPretty(bl,0);
        System.out.println(bl);
        Test deserialiseOneObject = deserialiseOneObject(Test.class , (JsonObject) objectToJsonObject);
        System.out.println(deserialiseOneObject);
        objectToFile(deserialiseOneObject, "teset.json");
//        DataJson d = new DataJson();
//        JsonObjectInterface objectToJsonObjectLazy = d.objectToJsonObjectLazy(t);
//        System.out.println(objectToJsonObjectLazy.toStringJson());
//        System.out.println(d.objectToJsonObjectLazy(t2).toStringJson());
//        Test deserialiseOne = d.deserialiseOne(Test.class, d.objectToJsonObjectLazy(t2));
//        deserialiseOne = d.deserialiseOne(Test.class, objectToJsonObjectLazy);
//        System.out.println(deserialiseOne.getListS());
//        System.out.println(deserialiseOne.getString());
//        System.out.println(deserialiseOne.getListTest().get(0).getId());
//        System.out.println(deserialiseOne.getListTest().get(0).isBoo());
//        DataJson d = new DataJson();
//        System.out.println(d.objectToJsonObject(8l).toStringJson());
//        t = d.
//        test();

    }

    public static String getInsideRaw(String type) {
        int indexOf = type.indexOf('<');
        int lastIndexOf = type.lastIndexOf('>');
        if (indexOf != -1 && lastIndexOf != -1) {
            String substring = type.substring(indexOf + 1, lastIndexOf);
            return substring;
        }
        return null;
    }

    public static void objectToFile(Object o, String file) {
        try {
            JsonObjectInterface objectToJsonObject = DataJson.objectToJsonObject(o);

            File f = new File(file);
            try (FileWriter fw = new FileWriter(f)) {
                objectToJsonObject.toStringJson(fw);
            }
        } catch (DataJsonException | IOException ex) {
            Logger.getLogger(DataJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static <T> T fileToObject(Class<T> aClass, String file) throws DataJsonException {
        try {
            ParserJson pj;
            pj = new ParserJson(new File(file));
            JsonObjectInterface parse;
            parse = pj.parse();
            if (parse.getType() != TypeJson.OBJET) {
                throw new DataJsonException("le type d'objet dans le fichier n'est pas Object : " + parse.getType());
            }
//            JsonObject fileToOneJson = Storage.fileToOneJson(new File(file));
            return deserialiseOneObject(aClass, (JsonObject) parse);
        } catch (ValidatorException | DataJsonException | IOException ex) {
            throw new DataJsonException(ex);
        }

    }
    private static final String getClass = "getClass";

    private static String findFieldName(Method method) {
        if(method.getName().startsWith(get) || method.getName().startsWith(set))
            return lcFirst(method.getName().substring(3));
        else{
            return lcFirst(method.getName().substring(2));
        }
    }

    public void clearCacheDeserealisation() {
        cacheObject.clear();
    }

    public void clearCacheSerealisation() {
        objectsDejaSerealise.clear();
    }

    public static Collection getCompatibleCol(Class cFor) throws InstantiationException, IllegalAccessException {
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
        return col;
    }

    private <T> T deserialiseOne(Class<T> forName, JsonObjectInterface get) throws DataJsonException {
        return deserialiseOne(forName, get, forName.getMethods());
    }

    private static Object deserialiseSimpleType(Class c, JsonObjectInterface joi) {
        if (joi.getType() == TypeJson.NUMBER) {
            return processNumber((NumberJson) joi, c);
        } else if (joi.getType() == TypeJson.TEXT) {
            if (c.equals(String.class) || c.equals(Character.class) || c.equals(Character.TYPE)) {
                return ((TextJson) joi).getValue();
            }

        } else if (joi.getType() == TypeJson.BOOLEAN) {
            return ((BooleanJson) joi).getValue();
        }
        return null;
    }

    public static class Test {

        private String string;
        private int nb;
        private int id;
        private boolean boo;
        private List<Integer> listInt;
        private List<Test> listTest;
        private List<String> listS;

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

        public void setListS(List<String> listS) {
            this.listS = listS;
        }

        public List<String> getListS() {
            return listS;
        }

        public Test() {

        }

    }

    public static void test2() throws DataJsonException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException {
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
        jo.toStringJsonPretty(System.out, 0);
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

    private final Map<Class, Map<Integer, Object>> cacheObject = new HashMap<>();
    private final Set<Object> objectsDejaSerealise = new HashSet<>();

    public <T> List<T> deserialise(Class<T> c, String file) throws DataJsonException {
        ArrayList<T> ar;
        ar = new ArrayList<>();
        Map<Integer, Object> mapDeC;
        Class superC;

        superC = lastSuperBeforeObj(c);
        if (!cacheObject.containsKey(superC)) {
            mapDeC = new HashMap<>();
            cacheObject.put(superC, mapDeC);
        } else {
            mapDeC = cacheObject.get(superC);
        }

        try {
            File file1 = new File(file);
            if (!file1.exists()) {
                FileWriter fw = new FileWriter(file1);
                fw.write("[]");
                fw.close();
            }
            ParserJson pj = new ParserJson(file1);
            ArrayJson aj = (ArrayJson) pj.parse();
            List<JsonObjectInterface> jsonObjects = aj.getJsonObjects();
//            Collection<JsonObject> values = Storage.fileToSetJson(new File(file));
            Method[] methods = c.getMethods();

            for (JsonObjectInterface joi : jsonObjects) {

                ar.add(c.cast(deserialiseOne(c, joi, methods)));

            }

        } catch (Exception ex) {
            throw new DataJsonException(ex);
        }
        return ar;
    }

    public <T> T deserialiseOne(Class<T> c, JsonObjectInterface joi, Method[] methods) throws DataJsonException {

        try {

//            List<JsonObjectInterface> jsonObjects = aj.getJsonObjects();
//            Collection<JsonObject> values = Storage.fileToSetJson(new File(file));
//            Method[] methods = c.getMethods();
            Method method = null;
            if (joi.getType() != TypeJson.OBJET && joi.getType() != TypeJson.ARRAY) {
                return c.cast(deserialiseSimpleType(c, joi));
            }
            Map<Integer, Object> mapDeC;
            Class superC;
            Object newInstance = null;
            if (joi.getType() == TypeJson.OBJET) {
                superC = lastSuperBeforeObj(c);
                if (!cacheObject.containsKey(superC)) {
                    mapDeC = new HashMap<>();
                    cacheObject.put(superC, mapDeC);
                } else {
                    mapDeC = cacheObject.get(superC);
                }
                JsonObject jo = (JsonObject) joi;

                Object[] param = new Object[1];
                newInstance = c.newInstance();
                Set<String> keys = jo.getKeys();
                int id;
                for (String key : keys) {
                    TypeJson type = jo.getType(key);
                    String ucFirst = ucFirst(key);
                    String methodName = set + ucFirst;
                    switch (type) {
                        case TEXT:
                            method = getMethodByName(methodName, methods);
                            param[0] = jo.getString(key);
                            break;
                        case BOOLEAN:
                            String nMet = methodName;
                            method = getMethodByName(nMet, methods);
                            param[0] = jo.getBoolean(key);
                            break;

                        case NUMBER:
                            NumberJson bg = (NumberJson) jo.get(key);
                            String fieldName;

                            Class cFor;

                            if ((bg.scale() >= 0) && (key.startsWith("id_") || key.startsWith("_date_"))) {

                                if (key.startsWith("id_")) {
                                    methodName = key.substring(3);
                                    method = getMethodByName(methodName, methods);
                                    cFor = method.getParameterTypes()[0];
                                    Object objForFieldObj = cacheObject.get(lastSuperBeforeObj(cFor)).get((int) param[0]);
                                    param[0] = objForFieldObj;

                                } else if (key.startsWith("_date_")) {
                                    fieldName = key.substring(5);
                                    methodName = set + ucFirst(fieldName);
                                    method = getMethodByName(methodName, methods);
                                    cFor = method.getParameterTypes()[0];
                                    Date d = new Date();
                                    param[0] = d;

                                }
                            } else {
                                methodName = set + ucFirst(key);
                                method = getMethodByName(methodName, methods);
                                cFor = method.getParameterTypes()[0];
                                param[0] = processNumber(bg, cFor);
                            }
                            break;
                        case ARRAY:
                            ArrayJson array = jo.getArray(key);
                            int size = array.size();
                            if (key.startsWith("ids_")) {
                                fieldName = key.substring(4);
                                methodName = set + ucFirst(fieldName);
                                method = getMethodByName(methodName, methods);
                                cFor = method.getParameterTypes()[0];
                                String name = cFor.getName();
                                Type genericType = getGenericType(methodName, methods);
                                String substring = genericType.toString().substring(name.length());
                                String genericClassName = substring.substring(1, substring.length() - 1);

                                Class<?> forName = Class.forName(genericClassName);
                                Collection col = getCompatibleCol(cFor);

                                for (int i = 0; i < size; i++) {
                                    Class lastSuperBeforeObj = lastSuperBeforeObj(forName);
                                    if (cacheObject.containsKey(lastSuperBeforeObj)) {
                                        Map<Integer, Object> map = cacheObject.get(lastSuperBeforeObj);
                                        Object get1 = map.get(array.getInt(i));
                                        col.add(get1);
                                    }

                                }
                                param[0] = col;

                            } else {
                                methodName = set + ucFirst(key);
                                method = getMethodByName(methodName, methods);
                                cFor = method.getParameterTypes()[0];
                                String name = cFor.getName();
                                Type genericType = getGenericType(methodName, methods);
                                String substring = genericType.toString().substring(name.length());
                                String genericClassName = substring.substring(1, substring.length() - 1);
                                Class<?> forName = Class.forName(genericClassName);
                                Method[] ms = forName.getMethods();
                                Collection col = getCompatibleCol(cFor);
                                for (int i = 0; i < size; i++) {
                                    col.add(deserialiseOne(forName, array.get(i), ms));
                                }
                                param[0] = col;
//                                method = c.getMethod(methodNameFor, cFor);
                            }
                            break;

                    }
                    if (type != TypeJson.NULL) {
                        if (method != null) {
                            method.invoke(newInstance, param);
                        } else {

                        }
                    }
                }
                if (jo.getKeys().contains("id")) {
                    id = jo.getInt("id");
                    mapDeC.put(id, newInstance);
                }
            } else if (joi.getType() == TypeJson.ARRAY) {
//                ArrayJson array = (ArrayJson) joi;
//                int size = array.size();
//                String name = c.getName();
////                Type genericType = getGenericType(methodNameFor, methods);
////                String substring = genericType.toString().substring(name.length());
////                String genericClassName = substring.substring(1, substring.length() - 1);
//                Class<?> forName = Class.forName(genericClassName);
//                Method[] ms = forName.getMethods();
//                Collection col = getCompatibleCol(c);
//                for (int i = 0; i < size; i++) {
//                    col.add(deserialiseOne(forName, array.get(i), ms));
//                }
//                 newInstance = col;
                newInstance = null;

            }

            return c.cast(newInstance);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DataJsonException(ex);
        }

    }

    public static <T> T deserialiseOneObject(Class<T> clazz, JsonObject jo) throws DataJsonException {
        return deserialiseObject(clazz, jo, Reflexivite.getObjectSetters(clazz));
    }

//    public static Object extractObject(Method method, JsonObjectInterface jo) throws DataJsonException {
//        if (jo.getType() == TypeJson.BOOLEAN
//                || jo.getType() == TypeJson.TEXT
//                || jo.getType() == TypeJson.NUMBER) {
//            return deserialiseSimpleType(method.getParameterTypes()[0], jo);
//        } else {
//            if(jo.getType() == TypeJson.OBJET){
//                return deserialiseOneObject(method.getParameterTypes()[0], jo);
//            } else if(jo.getType() == TypeJson.ARRAY){
//                return deserialiseArray(method.getParameterTypes()[0], method.getGenericParameterTypes()[0], jo);
//            }
//        }
//        return null;
//    }
    public static Object deserialiseArray(Class<?> clazz, String insideGeneric, ArrayJson jo) throws DataJsonException {
        try {
            Collection compatibleCol = getCompatibleCol(clazz);
            if (jo.isEmpty()) {
                return compatibleCol;
            }
            String insideRaw = getInsideRaw(insideGeneric);
            TypeJson type = jo.get(0).getType();
            if (insideRaw != null) {
                String supposedCol = insideGeneric.substring(0, insideGeneric.indexOf('<'));
                clazz = Class.forName(supposedCol);
                if (type != TypeJson.ARRAY) {
                    throw new DataJsonException("le type n'est pas une array");
                }
            } else {
                clazz = Class.forName(insideGeneric);
            }
            for (int i = 0; i < jo.size(); i++) {
                Object toAdd = null;
                switch (type) {
                    case ARRAY:
                        toAdd = deserialiseArray(clazz, insideRaw, jo.getArray(i));
                        break;
                    case OBJET:
                        toAdd = deserialiseOneObject(clazz, jo.getObject(i));
                        break;
                    case NULL:
                        break;
                    default:
                        toAdd = deserialiseSimpleType(clazz, jo.get(i));
                }
                compatibleCol.add(toAdd);
            }
            return compatibleCol;
        } catch (InstantiationException ex) {
            throw new DataJsonException(ex);
        } catch (IllegalAccessException ex) {
            throw new DataJsonException(ex);
        } catch (ClassNotFoundException ex) {
            throw new DataJsonException(ex);
        }
    }

    public static <T> T deserialiseObject(Class<T> clazz, JsonObject jo, Method[] methods) throws DataJsonException {
        try {
//            Method[] setters = Reflexivite.getSetters(clazz);
            T newInstance = clazz.newInstance();
            Object[] param = new Object[1];
            for (Method method : methods) {
                String fileld = lcFirst(method.getName().substring(3));
                JsonObjectInterface joi = jo.get(fileld);
                Class<?> classParam = method.getParameterTypes()[0];
                if (joi != null) {
                    if (joi.getType() == TypeJson.OBJET) {
                        param[0] = deserialiseOneObject(classParam, (JsonObject) joi);
                    } else if (joi.getType() == TypeJson.ARRAY) {
                        method.getGenericParameterTypes()[0].toString();
                        String fullClassString = method.getGenericParameterTypes()[0].toString();
                        String insideRaw = getInsideRaw(fullClassString);
                        param[0] = deserialiseArray(classParam, insideRaw, (ArrayJson) joi);
                    } else {
                        param[0] = deserialiseSimpleType(classParam, joi);
                    }
                }
                method.invoke(newInstance, param[0]);
            }
            return newInstance;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new DataJsonException(ex);
        } catch (IllegalArgumentException ex) {
            throw new DataJsonException(ex);
        } catch (InvocationTargetException ex) {
            throw new DataJsonException(ex);
        }
    }

    private static Object processNumber(NumberJson num, Class clazz) {
        Object newInstance = null;
        if (clazz.equals(Integer.class) || clazz.equals(Integer.TYPE)) {
            newInstance = num.intValue();
        } else if (clazz.equals(Float.class) || clazz.equals(Float.TYPE)) {
            newInstance = num.floatValue();
        } else if (clazz.equals(Double.class) || clazz.equals(Double.TYPE)) {
            newInstance = num.doubleValue();
        } else if (clazz.equals(Long.class) || clazz.equals(Long.TYPE)) {
            newInstance = num.longValue();
        }
        return newInstance;
    }

    public <T> T deserialiseOneObject(Class<T> c, String file) throws DataJsonException, FileNotFoundException, IOException, ValidatorException {

        JsonObjectInterface jo = new ParserJson(new File(file)).parse();
        if (jo.getType() != TypeJson.OBJET) {
            throw new DataJsonException("le type n'est pas objet : type de l'objet : " + jo.getType());
        }
        return deserialiseOneObject(c, (JsonObject) jo);

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
                        objectToJsonObjectLazy(next).toStringJson(bw);
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
     * @throws storage.DataJsonException
     */
    public static void serialise(Collection<?> objects, String file) throws DataJsonException {
        FileWriter fw;
        try {

            Iterator<?> iterator = objects.iterator();
            fw = new FileWriter(file);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.append('[');
                while (iterator.hasNext()) {
                    objectToJsonObject(iterator.next()).toStringJson(bw);
                    if (iterator.hasNext()) {
                        bw.append(',');
                    }
                    bw.append('\n');
                }
                bw.append(']');
                bw.flush();
            }
        } catch (IOException ex) {
            throw new DataJsonException(ex);
        }
    }

    // static F jo = new JsonObject();
    public JsonObjectInterface objectToJsonObjectLazy(Object objetASerealis) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
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
        } else if (aClass.equals(Character.class)) {
            Character i = (Character) objetASerealis;
            return new TextJson(String.valueOf(i));
        }
        JsonObject jo = new JsonObject();
        Method[] methods = aClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method;
            method = methods[i];
            String name = method.getName();
            int modifiers = method.getModifiers();
            if (Modifier.isStatic(modifiers)
                    || Modifier.isPrivate(modifiers)
                    || Modifier.isTransient(modifiers)
                    || method.getParameterTypes().length != 0) {
                continue;
            }
            boolean matches;
            matches = (name.startsWith(get) && !name.equals(getClass));
            if (matches) {
                Class<?> returnType = method.getReturnType();
                String nomApresGet = method.getName().substring(3);
                if (nomApresGet.equals("Id")) {
                    objectsDejaSerealise.add(objetASerealis);
                }
                nomApresGet = lcFirst(nomApresGet);
                if (returnType.isPrimitive()) {
                    if (returnType.equals(Integer.TYPE)) {
                        int x = (int) method.invoke(objetASerealis, (Object[]) null);
                        jo.put(nomApresGet, x);
                    } else if (returnType.equals(Long.TYPE)) {
                        long x = (long) method.invoke(objetASerealis, (Object[]) null);
                        jo.put(nomApresGet, x);
                    } else if (returnType.equals(Character.TYPE)) {
                        char x = (char) method.invoke(objetASerealis, (Object[]) null);
                        jo.put(nomApresGet, x);
                    } else if (returnType.equals(Float.TYPE)) {
                        float x = (float) method.invoke(objetASerealis, (Object[]) null);
                        jo.put(nomApresGet, x);
                    } else if (returnType.equals(Double.TYPE)) {
                        double x = (double) method.invoke(objetASerealis, (Object[]) null);
                        jo.put(nomApresGet, x);
                    } else if (returnType.equals(Boolean.TYPE)) {
                        boolean x = (boolean) method.invoke(objetASerealis, (Object[]) null);
                        jo.put(nomApresGet, x);
                    }
                } else if (Collection.class.isAssignableFrom(returnType)) {
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
                                    aj.add(objectToJsonObjectLazy(object));
                                }
                                first = false;
                            } else {
                                if (isSerialisable) {
                                    Object invoke = methodObjectCol.invoke(object, (Object[]) null);
                                    aj.add(new NumberJson((int) invoke));
                                } else {
                                    aj.add(objectToJsonObjectLazy(object));
                                }
                            }
                        }
                        if (aj != null) {
                            jo.put(nomApresGet, aj);
                        }
                    }
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
                    } catch (NoSuchMethodException ex) {
                        Object invoke = method.invoke(objetASerealis, (Object[]) null);
                        if (invoke != null) {
                            jo.put(nomApresGet, this.objectToJsonObjectLazy(invoke));
                        }
                    }
                }
            } else if (name.startsWith("is")) {
                String nomApresIs = name.substring(2);
                nomApresIs = lcFirst(nomApresIs);
                Class<?> returnType = method.getReturnType();
                if ((returnType.equals(Boolean.class) || returnType.equals(Boolean.TYPE))) {
                    Boolean b = (Boolean) method.invoke(objetASerealis, (Object[]) null);
                    jo.put(nomApresIs, b);
                }
            }
        }
        objectsDejaSerealise.add(objetASerealis);
        return jo;
    }

    public static JsonObjectInterface objectToJsonObject(Object o) throws DataJsonException {
        if(o == null){
            return JsonNull.NULL;
        }
        Class<? extends Object> aClass = o.getClass();
//        System.out.println(aClass);
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
        }else if (aClass.equals(Character.class)) {
            Character i = (Character) o;
            return new TextJson(String.valueOf(i));
        } else if (Collection.class.isAssignableFrom(aClass)){
            Collection col = (Collection) o;
            ArrayJson aj = new ArrayJson();
            Iterator iterator = col.iterator();
            while (iterator.hasNext()) {
                Object object = iterator.next();
                aj.add(objectToJsonObject(object));              
            }
            return aj;
        }
        JsonObject jo = new JsonObject();
        Method[] methods = Reflexivite.getObjectGetters(aClass);

        for (Method method : methods) {
            try {
                Object invoke = method.invoke(o, (Object[]) null);
                jo.put(findFieldName(method), objectToJsonObject(invoke));
                
            } catch (IllegalAccessException ex) {
                throw new DataJsonException(ex);
            } catch (IllegalArgumentException ex) {
                throw new DataJsonException(ex);
            } catch (InvocationTargetException ex) {
                throw new DataJsonException(ex);
            }
        }

        return jo;
    }

    public static String ucFirst(String s) {
        return (Character.toUpperCase(s.charAt(0))) + s.substring(1);
    }

    public static Method getMethodByName(String name, Method[] methods) {
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    return method;
                }
            }
        }
        return null;

    }
//    private static class MethodAndClassPara{
//        Method method;
//        Method method;
//    }

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
