/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jonathan.test;

import com.jonathan.compress.xml.HtmlFactory;
import com.jonathan.compress.xml.Xml;
import com.jonathan.compress.xml.XmlElement;
import com.jonathan.compress.xml.XmlText;
import com.jonathan.lib.HtmlGenerateur;
import com.jonathan.lib.graphique.JFrameGiver;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

/**
 *
 * @author jonathan
 */
public class Test {

    public static void main(String[] args) throws IOException {
        HtmlFactory htmlFactory = new HtmlFactory();
        XmlElement html = htmlFactory.get("html");
        XmlElement body = htmlFactory.get("body");
        XmlElement head = htmlFactory.get("head");
        //<meta http-equiv="content-type" content="text/html; charset=iso-8859-1">
        XmlElement meta = htmlFactory.getCloasble("meta");
        meta.getAttribut().put("http-equiv", "content-type");
        meta.getAttribut().put("content", "text/html");
        meta.getAttribut().put("charset", "utf-8");
        head.getChild().add(meta);
        html.getChild().add(head);
        html.getChild().add(body);
        XmlElement table = htmlFactory.get("table");
        XmlElement js = htmlFactory.get("script");
        js.getAttribut().put("type", "text/javascript");
//        js.getAttribut().put("src", "monamour.js");
 
        BufferedReader br = new BufferedReader(new FileReader("monamour.js"));
        String s;
        StringBuilder b = new StringBuilder();
        while ((s = br.readLine()) != null) {
            b.append(s+"\n");
       }
        XmlText text = new XmlText(b.toString());
        js.getChild().add(text);
        body.getChild().add(table);
        body.getChild().add(js);
        XmlText blanc = new XmlText(" ");
        for (int j = 0; j < 50; j++) {
            XmlElement tr = htmlFactory.get("tr");
            table.getChild().add(tr);
            for (int i = 0; i < 75; i++) {
                XmlElement td = htmlFactory.get("td");
                td.getAttribut().put("id", i + "+" + j);
                tr.getChild().add(td);
                td.getChild().add(blanc);
            }

        }
        FileWriter fw = new FileWriter("pour_mon_amour.html");
//        System.out.println(html.toString());
        String DOCTYPE = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n";
        fw.append(DOCTYPE);
        html.write(fw);
        fw.close();

    }

}
