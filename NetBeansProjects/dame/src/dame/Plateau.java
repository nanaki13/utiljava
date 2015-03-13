/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jonathan
 */
public class Plateau {

    private List<Pion> blancs;
    private List<Pion> noirs;
    private Case[][] cases = new Case[8][8];

    public Plateau() {
        init();
    }
    public Pion getPion(int i , int j){
        return cases[i][j].pion;
    }
    public Case getCase(int i , int j){
        if(isIn(i,j))
            return cases[i][j];
        else
            return null;
    }

    /**
     *
     * @param p
     * @return les déplacement possible du pion p
     */
    public List<Case> deplPoss(Pion p){
        int x = p.getX();
        int y = p.getY();
        List<Case> l = new ArrayList<Case>(4);
        Case aCase = getCase(x+1, y+1);
        if(aCase!=null){
            if(aCase.pion ==null){
                l.add(aCase);
            }
        }
        aCase = getCase(x-1, y+1);
        if(aCase!=null){
            if(aCase.pion ==null){
                l.add(aCase);
            }
        }
        aCase = getCase(x+1, y-1);
        if(aCase!=null){
            if(aCase.pion ==null){
                l.add(aCase);
            }
        }
        Case aCase = getCase(x+1, y+1);
        if(aCase!=null){
            if(aCase.pion ==null){
                l.add(aCase);
            }
        }
        return null;
    }
    private void init() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; i++) {
                Case caze  = new Case();
                cases[i][j] = caze;
                if(j < 2){
                    if(j == 0){
                        if(i%2==0){
                            Pion p;
                            p= new Pion(Pion.Couleur.BLANC);
                            caze.pion = p;
                            p.setX(i);
                            p.setY(j);
                        }
                    }else{
                        if(i%2!=0){
                            Pion p;
                            p= new Pion(Pion.Couleur.BLANC);
                            caze.pion = p;
                             p.setX(i);
                            p.setY(j);
                            
                        }
                    }
                }else if (j >5){
                    if(j == 6){
                        if(i%2==0){
                            Pion p;
                            p= new Pion(Pion.Couleur.NOIR);
                            caze.pion = p;
                             p.setX(i);
                            p.setY(j);
                        }
                    }else{
                        if(i%2!=0){
                            Pion p;
                            p= new Pion(Pion.Couleur.NOIR);
                            caze.pion = p;
                             p.setX(i);
                            p.setY(j);
                            
                        }
                    }
                }
            }
        }
    }

    private static boolean isIn(int i, int j) {
        return ( i < 8 && i > -1 && j < 8 && j > -1);
    }

    public static class Case {

        private Pion pion = null;

        public Case() {
        }

        public Pion getPion() {
            return pion;
        }

        public void setPion(Pion pion) {
            this.pion = pion;
        }

    }
}
