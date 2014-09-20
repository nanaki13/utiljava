/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.interpretor;

import com.jonathan.reader.ReaderCompteur;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class ReaderCommand implements ObjectCommand
{

    private final ReaderCompteur r;

    public ReaderCommand(Reader r)  {
        this.r = new ReaderCompteur(r);
    }
public static void main(String[] args) throws IOException {
        StringReader sr = new StringReader(" eval  \"exec echo coucou\"\n");
        ReaderCommand rc = new ReaderCommand(sr);
        Command read = rc.read();
        System.out.println(read);
        System.out.println( rc.exec(read));
        
    }
    public void coucou(Command c){
        System.out.println("coucou");
    }
    public Float mult(Command c){
        
        return Float.parseFloat(c.getStartArgs().get(0)) * Float.parseFloat(c.getStartArgs().get(1));
    }
    public Command read() throws IOException {
        String readLine = r.readLine();
        if (readLine != null) {
            Command co = new Command();
            ReaderCompteur rc = new ReaderCompteur(new StringReader(readLine));
            String readNextWord = rc.readNextWord();
            co.setCommandName(readNextWord);
            readNextWord = rc.readNextWord();
            boolean matk =true;
            while (readNextWord != null) {
                if (readNextWord.charAt(0) == '-') {
                     matk = false;
                    CommandEntry<String, String> entry = new CommandEntry<>();
                    entry.setArg(readNextWord);
                    readNextWord = rc.readNextWord();
                    entry.setVal(readNextWord);
                    co.getEntry().add(entry);
                } else{
                    if(matk == true){
                        co.getStartArgs().add(readNextWord);
                    }else if(!co.getEntry().isEmpty()){
                        co.getFinalArgs().add(readNextWord);
                    }
                }
               
                readNextWord = rc.readNextWord();
            }

            return co;

        }
        return null;
        
        
    }
    public int eval(Command linux){
        ProcessBuilder builder = new ProcessBuilder(linux.getStartArgs().get(0).split(null));
        try {
            Process start = builder.start();
            return start.exitValue();
        } catch (IOException ex) {
            Logger.getLogger(ReaderCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 999;
    }

}
