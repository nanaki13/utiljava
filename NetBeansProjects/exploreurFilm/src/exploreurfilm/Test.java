/*
 * Copyright (C) 2014 jonathan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package exploreurfilm;

import com.jonathan.lib.collections.ChooserText;
import com.jonathan.metier.Pays;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author jonathan
 */
public class Test {
    
    public static void main(String[] args){
        JFrame j = new JFrame("test");
        Comparator<Pays> comparator = new Comparator<Pays>() {

            @Override
            public int compare(Pays o1, Pays o2) {
                return o1.getNom().compareToIgnoreCase(o2.getNom());
            }
        };
        ChooserText<Pays> chooser;
        chooser = new ChooserText<Pays>() {
            
            @Override
            public boolean choose(Pays o) {
                String trim = text.trim();
                String toLowerCase = trim.toLowerCase();
                if(!toLowerCase.isEmpty())
                    return o.getNom().toLowerCase().contains(toLowerCase);
                else
                    return false;
            }
        };
        
        List<Pays> l = new ArrayList<Pays>();
        Pays pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");
        pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");pays = new Pays();
        pays.setNom("Russie");
        l.add(pays);
        pays = new Pays();
        pays.setNom("France");
        l.add(pays);
        pays = new Pays();
        pays.setNom("Brésil");
        l.add(pays);
        
        JTextFieldPP<Pays> jTextFieldPP = new JTextFieldPP(l, chooser,10);
        jTextFieldPP.setStringMaker(new StringMaker<Pays>() {

            @Override
            public String buildString(Pays t) {
                return t.getNom();
            }
        });
        jTextFieldPP.setComparator(comparator);
        j.add(jTextFieldPP);
        j.pack();
        j.setVisible(true);
    }
    
}
