package lib;

import java.util.Date;

public class Utilitaires {
       public static boolean verbal = true;

        
    /**
     *Converti un nombre décimal en heure au format String
     * ex : 12,5 => 12H30
     * @param heureDec
     * @return l'heure en String
     */
    public static String convDecToStrHeure(float heureDec)
    {
            String str ;
            int minute ;
            String strMinute ;
            str = (int) heureDec+"H";
            minute = ((int)((heureDec - (int) heureDec)*60));
           
            if(minute==0)
            {
                strMinute = "00";
            }
            else
            {
                strMinute = minute+"";
            }
             str = str+strMinute;
            return str;
        }
    /**
     *Converti un String  en heure nombre décimal
     * ex : 12H30 => 12,5
     * @param heureStr
     * @return l'heure en String
     */
    public static float convStrHeureToDec(String heureStr)
    {
        float heureDec;
        String[] heureMin = heureStr.split("H");
        heureDec = Integer.parseInt(heureMin[0]) + Integer.parseInt(heureMin[1])/60.0f;

        return heureDec;
    }

    /**
     *retourne nb fois la chaine
     * @param c
     * @param nb
     * @return
     */
    public static String duplicate(String c, int nb) {
        String iniC = c;
        for (int i = 0; i < nb - 1; i++) {
            c += iniC;
        }
        return c;
    }

    /**
     *Met le première lettre en majuscule
     * @param str
     * @return str(ref)
     */
    public static String ucFirst(String str) {
        if(!str.isEmpty()){
            StringBuilder strBf = new StringBuilder(str);
            char deb = strBf.charAt(0);
            deb = Character.toUpperCase(deb);
            strBf.setCharAt(0, deb);
            str=new String(strBf);
        }
       
            return str;
        
    }
    
    /**
     *complete en écrasant à gauche
     * @param o l'entrée
     * @param taille nombre de fois qu'on veux mettre le caractère dans la 
     * chaine
     * @param c charctère a insérer
     * @return un nouvelle chaine issu de o.toString()
     */
    public static String completeAGauche(Object o , int taille , char c)
    {
        String strO = o.toString();
        while(strO.length() < taille)
        {
            strO = c+strO;
        }
        return strO;
        
    }

    public static String formatStringSql(String nom) {
        if (nom!=null)
        {
            String ret ="'";
            char charRegarder; 
            for(int i=0 ; i<nom.length();i++){
                charRegarder = nom.charAt(i);
                if(charRegarder!='\''){
                    ret+=String.valueOf(charRegarder);
                }
                else
                {
                    ret+="''";
                }
            }
            return ret+"'";
        }else{
            return "NULL";
        }
        
    }

    public static String formatDateSql(Date date) {
        DateFr d;
        d = new DateFr(date);
           String[] split;
           split = d.toString().split("/");
           String ret=split[2]+"-"+split[1]+"-"+split[0];
      
        d.setFormat("");
        return "'"+ret+"'";
    }

   
    
    /**
     *si verbal est actif 
     * affiche à la console l'objet. 
     * @param str
     */
    public  void mdVerb(Object str)
    {
         if(verbal) System.out.println(str);
     }
    public static String premierMot(String text){
         String mot;
         int indexDeb=0,indexFin;
         if(text.charAt(0)==' '){
              while(text.charAt(indexDeb)==' '){
             indexDeb++;
              }
         }        
         indexFin=indexDeb;
         while(indexFin<text.length()&&text.charAt(indexFin)!=' '&&text.charAt(indexFin)!=';'){
             indexFin++;
         }
         mot=text.substring(indexDeb, indexFin);
       
      
         return mot;
     }
}

