package metier;

import java.util.ArrayList;

public class MembreFilm extends Personne{

      private ArrayList<Film> filmsJoue;

    public ArrayList<Film> getFilmsJoue() {
        return filmsJoue;
    }

    public void setFilmsJoue(ArrayList<Film> filmJoue) {
        this.filmsJoue = filmJoue;
    }
    private ArrayList<Recompense> recompense;

    public ArrayList<Recompense> getRecompense() {
        return recompense;
    }

    public void setRecompense(ArrayList<Recompense> recompense) {
        this.recompense = recompense;
    }
        
}