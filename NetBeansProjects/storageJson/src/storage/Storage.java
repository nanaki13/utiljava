/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import com.jonathan.json.ArrayJson;
import com.jonathan.json.JsonObject;
import com.jonathan.json.JsonObjectInterface;
import com.jonathan.json.parser.ParserJson;
import com.jonathan.json.validator.ValidatorException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class Storage implements StorageInterface {

    private static final Logger logger = Logger.getLogger(Storage.class.getCanonicalName());
    private int nbCharLu = 0;
    private int longuerReel = 0;
    private static final String ORDRE = "ordre";
    private static final String TAILLES = "tailles";

    public static void main(String[] args) throws IOException, ValidatorException, ObjectReaderException {
        Storage s = new Storage();
        File f;
        f = new File("films.data");
        Map<Integer, JsonObject> mapJson = fileToMapJson(f);
        System.out.println(mapJson.get(5).toStringJson());

    }

    private JsonObject buildAllIndex(String file) throws IOException, ValidatorException {
        nbCharLu = 0;
        JsonObject jsonObjectIndex = new JsonObject();
        ArrayJson ordreJson = new ArrayJson();
        ArrayJson tailles = new ArrayJson();
        jsonObjectIndex.put(ORDRE, ordreJson);
        jsonObjectIndex.put(TAILLES, tailles);
        JsonObject ordreDeUn = new JsonObject();
        //JsonObject caracPosition = new JsonObject();
        int cptObj = 0;
        long currentTimeMillis = 0;
        try {
            File f = new File(file);
            currentTimeMillis = System.currentTimeMillis();
            String readStringObject = readStringObject(0, f);
            ParserJson parserJson = new ParserJson(readStringObject);
            JsonObject jsonObject = parserJson.parse();

            cptObj = 1;
            ordreDeUn.put("id", jsonObject.getInt("id"));
            ordreDeUn.put("position", cptObj);
            //   caracPosition.put("longuerObj", readStringObject.length());
            //         caracPosition.put("taille", longuerReel);
            //     caracPosition.put("position", cptObj);
            ordreJson.add(ordreDeUn);
            tailles.add(longuerReel);

            while (true) {

                readStringObject = readStringObject(nbCharLu, f);
                cptObj++;
                parserJson = new ParserJson(readStringObject);
                jsonObject = parserJson.parse();
                ordreDeUn = new JsonObject();
                //      caracPosition = new JsonObject();
                ordreDeUn.put("id", jsonObject.getInt("id"));
                ordreDeUn.put("position", cptObj);
                //              caracPosition.put("longuerObj", readStringObject.length());
                //        caracPosition.put("taille", longuerReel);
                //       caracPosition.put("position", cptObj);
                ordreJson.add(ordreDeUn);
                tailles.add(longuerReel);
            }

        } catch (ObjectReaderException ex) {
            System.out.println("fin");

        }
        jsonObjectIndex.put("nbObjet", cptObj);
        Collections.sort(ordreJson.getJsonObjects(), new Comparator<JsonObjectInterface>() {
            @Override
            public int compare(JsonObjectInterface o1, JsonObjectInterface o2) {
                int id1, id2;
                id1 = ((JsonObject) o1).getInt("id");
                id2 = ((JsonObject) o2).getInt("id");
                if (id1 > id2) {
                    return 1;
                } else if (id1 < id2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        String indexFileName = file.substring(0, file.indexOf('.')) + ".index";
        OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(indexFileName), "UTF-8");

        ow.write(jsonObjectIndex.toStringJsonPretty());

        logger.log(Level.INFO, "temps : {0}", (System.currentTimeMillis() - currentTimeMillis));
        return jsonObjectIndex;

    }

    public String readStringObject(long offset, File f) throws IOException, ObjectReaderException {
        int i;
        try (FileReader fileReader = new FileReader(f)) {
            long realSkip = fileReader.skip(offset);
            if (realSkip != offset) {
                throw new ObjectReaderException("dépassement de la longueur du fichier par skeep");
            }
            boolean open = false, close = false;
            StringBuilder builder = new StringBuilder();

            int cptOpen = 0;
            longuerReel = 0;
            while (((i = fileReader.read()) != -1) && !close) {
                nbCharLu++;
                longuerReel++;
                char c = (char) i;
                //    System.out.println(c);
                if (c == '{') {
                    if (!open) {
                        open = true;

                        //  firstOpen = true;
                    }
                    cptOpen++;
                } else if (c == '}') {
                    cptOpen--;
                    if (cptOpen == 0) {
                        open = false;
                        close = true;
                        builder.append(c);
                    }
                }

                if (open) {
                    builder.append(c);
                }
            }
            if (i == -1) {
                if (!close && open) {
                    throw new ObjectReaderException("nombre de paranthèse nom conforme : fin du fichier atteind");
                } else {
                    if (close) {
                        return builder.toString();
                    } else {
                        throw new ObjectReaderException("pas d'ouverture ({)");
                    }
                }
            }
            return builder.toString();
        }

//        Storage s = new Storage();
//        s.connect("films.data");
//        s.writer.append("qsdqsdzqc");
//        
//        System.out.println(s.reader.read());
//        s.close();
    }

    private static int findPosition(int id, ArrayJson ordre) {
        if (ordre.size() == 0) {
            return -1;
        }
        List<JsonObjectInterface> jsonObjects = ordre.getJsonObjects();
        int size = jsonObjects.size();
        int demiSize = size / 2;
        int initDico;
        int max, min;
        int idCourant = ordre.getObject(demiSize).getInt("id");
        if (idCourant != id) {
            if (idCourant < id) {
                min = demiSize;
                initDico = demiSize + demiSize / 2;
                max = size;
            } else {
                min = 0;
                max = demiSize;
            }
            return findPosition(id, ordre, min, max);
        }
        return ordre.getObject(demiSize).getInt("position");
    }

    private static int findPosition(int id, ArrayJson ordre, int min, int max) {
        List<JsonObjectInterface> jsonObjects = ordre.getJsonObjects();
        int size = jsonObjects.size();
        int initDico = (min + max) / 2;

        int idCourant = ordre.getObject(initDico).getInt("id");
        int ret = -1;
        boolean continu;
        continu = true;
        while (continu) {
            if (idCourant != id) {
                int nvMin = min, nvMax = max;
                if (idCourant < id) {
                    nvMin = initDico;
                } else {
                    nvMax = initDico;
                }
                if (nvMin == min && nvMax == max) {
                    return -1;
                }
                min = nvMin;
                max = nvMax;
                initDico = (min + max) / 2;
                idCourant = ordre.getObject(initDico).getInt("id");
                //return findPosition(id, ordre, nvMin,  nvMax);
            } else {
                continu = false;
            }

        }
        return initDico;
    }

    private static int calculCharToSkeep(int position, ArrayJson array) {
        int nbSk = 0;
        for (int i = 0; i < position; i++) {
            nbSk += array.getInt(i);
        }
        return nbSk;
    }
    private File fileData;
    private String filePath;
    private JsonObject index;
    private boolean connect = false;

    @Override
    @Deprecated
    public void connect(String filePath) throws StorageException {
        this.filePath = filePath;
        fileData = new File(filePath + ".data");
        if (!fileData.exists()) {

            OutputStreamWriter fw = null;
            try {
                fw = new OutputStreamWriter(new FileOutputStream(fileData), "UTF-8");
                //   fw = new FileWriter(fileData);
                fileData.createNewFile();
            } catch (IOException ex) {
                logger.log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fw.close();
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        }
        File indexFile = new File(filePath + ".index");
        if (!indexFile.exists()) {

            try {
                readStringObject(0, fileData);
                try {
                    index = buildAllIndex(filePath + ".data");
                } catch (ValidatorException ex) {
                    Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ObjectReaderException ex) {
                index = new JsonObject();
                index.put(ORDRE, new ArrayJson()).put(TAILLES, new ArrayJson());
            }
        } else {
            logger.log(Level.INFO, "index de {0} trouv\u00e9", filePath);
            try {
                index = (new ParserJson(indexFile)).parse();
            } catch (ValidatorException ex) {
                throw new StorageException(ex);
            }
        }
        connect = true;

    }

    @Override
    public JsonObject get(int id) {
        int findPosition;
        int calculCharToSkeep;
        findPosition = findPosition(id, index.getArray(ORDRE));
        System.out.println(findPosition);
        if (findPosition == -1) {
            return null;
        }
        calculCharToSkeep = calculCharToSkeep(findPosition, index.getArray(TAILLES));
        try {
            return (new ParserJson(readStringObject(calculCharToSkeep, fileData))).parse();
        } catch (IOException | ObjectReaderException | ValidatorException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void save(JsonObject jsonObject) throws StorageException {
        if (jsonObject.get("id") == null) {
            throw new StorageException("l'objet n'a pas d'id");
        } else {
            try {
                int id = jsonObject.getInt("id");
                int findPosition = findPosition(id, index.getArray(ORDRE));
                if (findPosition != -1) {
                    throw new StorageException("l'objet existe déja");
                }
                String jsonString;
                if (index.getArray(ORDRE).size() == 0) {
                    jsonString = jsonObject.toStringJson();
                } else {
                    jsonString = ",\n" + jsonObject.toStringJson();
                }

                int taille = jsonString.length();
                try (OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(fileData, true), "UTF-8")) {

                    fw.append(jsonString);

                    ArrayJson array = index.getArray(TAILLES);
                    array.add(taille);
                    index.getArray(ORDRE).add((new JsonObject()).put("position", array.size()).put("id", id));
                }
            } catch (IOException ex) {
                Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void close() {
        try (OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(filePath + ".index"), "UTF-8")) {
            fw.write(index.toStringJsonPretty());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public static Map<Integer, JsonObject> fileToMapJson(File f) throws ObjectReaderException  {
        int i;
        ParserJson parserJson;
        JsonObject jsonObject;
        parserJson = new ParserJson();
        int id;
        Map<Integer, JsonObject> ret = new HashMap<>();
        try (FileReader fileReader = new FileReader(f)) {

            boolean open,close;
            i = fileReader.read();
            while (i != -1) {
                StringBuilder builder = new StringBuilder();
                int cptOpen = 0;
                open=false;
                close=false;
                while (i != -1 && !close) {
                    char c = (char) i;
//                    System.out.println(c);
                    if (c == '{') {
                        if (!open) {
                            open = true;
                        }
                        cptOpen++;
                    } else if (c == '}') {
                        cptOpen--;
                        if (cptOpen == 0) {
                            open = false;
                            close = true;
                            builder.append(c);
                        }
                    }

                    if (open) {
                        builder.append(c);
                    }
                    i=fileReader.read();
                }
                if (i == -1) {
                    if (!close && open) {
                        throw new ObjectReaderException("nombre de paranthèse nom conforme : fin du fichier atteind");
//                    } else {
//                        if (close) {
//                            jsonObject = parserJson.parse();
//                            id = jsonObject.getInt("id");
//                            ret.put(id, jsonObject);
//                        } else if(ret.isEmpty()) {
//                            throw new ObjectReaderException("aucune d'ouverture ({)");
//                        }
                    }
                }
                if(close){
                    parserJson.setInput(builder.toString());
                    jsonObject = parserJson.parse();
                    id = jsonObject.getInt("id");
                    ret.put(id, jsonObject);
                }
                
            }

        }  catch (IOException |ObjectReaderException | ValidatorException ex) {
            throw new ObjectReaderException("erreur !", ex);
        }
        return ret;
    }
    public static Set<JsonObject> fileToSetJson(File f) throws ObjectReaderException  {
        int i;
        ParserJson parserJson;
        JsonObject jsonObject;
        parserJson = new ParserJson();
        //int id;
        Set< JsonObject> ret = new HashSet<>();
        try (FileReader fileReader = new FileReader(f)) {

            boolean open,close;
            i = fileReader.read();
            while (i != -1) {
                StringBuilder builder = new StringBuilder();
                int cptOpen = 0;
                open=false;
                close=false;
                while (i != -1 && !close) {
                    char c = (char) i;
//                    System.out.println(c);
                    if (c == '{') {
                        if (!open) {
                            open = true;
                        }
                        cptOpen++;
                    } else if (c == '}') {
                        cptOpen--;
                        if (cptOpen == 0) {
                            open = false;
                            close = true;
                            builder.append(c);
                        }
                    }

                    if (open) {
                        builder.append(c);
                    }
                    i=fileReader.read();
                }
                if (i == -1) {
                    if (!close && open) {
                        throw new ObjectReaderException("nombre de paranthèse nom conforme : fin du fichier atteind");

                    }
                }
                if(close){
                    parserJson.setInput(builder.toString());
                    jsonObject = parserJson.parse();
             //       id = jsonObject.getInt("id");
                    ret.add( jsonObject);
                }
                
            }

        }  catch (IOException |ObjectReaderException | ValidatorException ex) {
            throw new ObjectReaderException("erreur !", ex);
        }
        return ret;
    }
    public static JsonObject fileToOneJson(File f) throws ObjectReaderException  {
        int i;
        ParserJson parserJson;
        JsonObject jsonObject = null;
        parserJson = new ParserJson();
        //int id;

        try (FileReader fileReader = new FileReader(f)) {

            boolean open,close;
            i = fileReader.read();
            while (i != -1) {
                StringBuilder builder = new StringBuilder();
                int cptOpen = 0;
                open=false;
                close=false;
                while (i != -1 && !close) {
                    char c = (char) i;
//                    System.out.println(c);
                    if (c == '{') {
                        if (!open) {
                            open = true;
                        }
                        cptOpen++;
                    } else if (c == '}') {
                        cptOpen--;
                        if (cptOpen == 0) {
                            open = false;
                            close = true;
                            builder.append(c);
                        }
                    }

                    if (open) {
                        builder.append(c);
                    }
                    i=fileReader.read();
                }
                if (i == -1) {
                    if (!close && open) {
                        throw new ObjectReaderException("nombre de paranthèse nom conforme : fin du fichier atteind");

                    }
                }
                if(close){
                    parserJson.setInput(builder.toString());
                    jsonObject = parserJson.parse();
                }
            }
        }  catch (IOException |ObjectReaderException | ValidatorException ex) {
            throw new ObjectReaderException("erreur !", ex);
        }
        return jsonObject;
    }
}
