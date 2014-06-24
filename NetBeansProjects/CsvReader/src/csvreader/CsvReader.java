/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvreader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import reader.ReaderCompteur;

/**
 *
 * @author jonathan
 */
public class CsvReader {

    private char fieldseparator;
    private char stringEncloser;
    private char endLine;
    private Reader reader;
    private ReaderCompteur rc;

    public CsvReader(Reader reader, char fieldseparator, char stringEncloser, char endLine) {
        this.fieldseparator = fieldseparator;
        this.stringEncloser = stringEncloser;
        this.endLine = endLine;
        rc = new ReaderCompteur(reader);
    }

    public CsvReader(Reader reader) {
        this(reader, ';', '"', '\n');
    }

    public List<String> read() throws IOException {
        int read = rc.read();
        if (read == -1) {
            return null;
        }
        List<String> ret = new ArrayList<String>();
        StringBuilder buff = new StringBuilder();
        boolean continu = true;
        while (continu) {
            if (read == stringEncloser) {
                read = rc.read();
//                System.out.println("entré");
                while (read != stringEncloser) {
//                   System.out.println((char) read);
                    buff.append((char) read);
                    read = rc.read();
                    if (read == '\\') {
                        read = rc.read();
                    } else if (read == -1) {
                        throw new IOException("string open but not close : " + rc.getNbLigne() + "," + rc.getNbCharDeLigne());
                    }
                }
                read = rc.read();
//                System.out.println("sortie");
            } else if (read == fieldseparator) {
                ret.add(buff.toString());
                read = rc.read();
                buff = new StringBuilder();
            } else if (read == endLine) {
                ret.add(buff.toString());
                continu = false;
            } else if (read == -1 && buff.length()!=0) {
                ret.add(buff.toString());
                continu = false;
            } else {
                buff.append((char) read);
                read = rc.read();
            }
            
        }
        return ret;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String csv = "\"bla\nbla\";\"12345\"\n"
                + "\"bla\nbla\";12345;\"salut\"\n"
                + "\"bla\nbla\";;12345\n"
                + "\"bla\nbla\";12345\n"
                + "\"bla\nbla\";12345";
        CsvReader r = new CsvReader(new StringReader(csv));
        List<String> read;
        while((read = r.read())!=null ){
            System.out.println(read.size());
            for(String s : read){
                System.out.print(s+",");
            }
            System.out.println();
        }
    }
}
