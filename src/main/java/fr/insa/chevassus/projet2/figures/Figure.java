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
import fr.insa.chevassus.projet2.Interface;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Figure {


    public static Color COULEUR_SELECTION = Color.CHARTREUSE;

    // null si aucun groupe
    private Groupe groupe;

    public Groupe getGroupe() {
        return groupe;
    }

    void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public abstract double maxX();
    public abstract double minX();
    public abstract double maxY();
    public abstract double minY();

    public abstract double distanceNoeud(Noeud n);

    public abstract void draw(GraphicsContext gc);

    public abstract void dessineSelection(GraphicsContext gc);

    public abstract void dessinDupplique(GraphicsContext gc, Interface vue);
}
