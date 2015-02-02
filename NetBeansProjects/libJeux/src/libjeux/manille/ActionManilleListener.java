/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libjeux.manille;

import libjeux.manille.object.JoueurInterface;
import libjeux.manille.object.Equipe;

/**
 *
 * @author jonathan
 */
public interface ActionManilleListener {
    public void settingEquipe(JoueurInterface j11,JoueurInterface j21,JoueurInterface j12,JoueurInterface j22, Equipe e1, Equipe e2);
}
