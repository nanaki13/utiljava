/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memos;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author jonathan
 */
public class MemoDao implements Serializable, Id{
    
    
    private String text;
    private int id;
    private LocalDate date;
    private List <String> motClef;

    private static final long serialVersionUID = 1;
    public MemoDao(String text, LocalDate date) {
        this.text = text;
        this.date = date;
        motClef = new LinkedList<>();
    }

    MemoDao(String text, LocalDate now, int i) {
        this(text , now);
        this.id = i;
    }

    public List<String> getMotClef() {
        return motClef;
    }

    public void setMotClef(List<String> motClef) {
        this.motClef = motClef;
    }
    
    

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return date+"\n\nid : "+id+"\n\n"+StringMotClef()+"\n\n"+text;
    }

    private String StringMotClef() {
        StringBuilder b = new StringBuilder();
        b.append("mot clefs : ");
        motClef.forEach((s)-> b.append(s).append(", "));
        return b.toString();
    }

    @Override
    public int getId() {
        return id;
    }
    
    public void setId(int i ) {
        this.id =i;
    }
    
    
    
    
}
