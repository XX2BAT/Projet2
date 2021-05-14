/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.chevassus.projet2.figures;

/**
 *
 * @author guilh
 */
import javafx.scene.paint.Color;

public abstract class FigureSimple extends Figure {

    private Color couleur;

    public FigureSimple(Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
}

