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

public class Barre extends FigureSimple {
    
    private Noeud debut;
    private Noeud fin;
        
    public Barre(Noeud debut, Noeud fin, Color couleur) {
        super(couleur);
        this.debut = debut;
        this.fin = fin;
    }

    public Barre(Noeud debut, Noeud fin) {
        this(debut, fin, Color.ORCHID);
    }

    public Noeud getDebut() {
        return debut;
    }

    public Noeud getFin() {
        return fin;
    }

    @Override
    public String toString() {
        return "Barre allant du noeud " + this.debut + " au noeud " + this.fin + ".";
    }

    public static Barre demandeBarre() {
        System.out.println("point début : ");
        Noeud deb = Noeud.demandeNoeud();
        System.out.println("point fin : ");
        Noeud fin = Noeud.demandeNoeud();
        return new Barre(deb, fin);
    }

    @Override
    public double maxX() {
        return Math.max(this.debut.maxX(), this.fin.maxX());
    }

    @Override
    public double minX() {
        return Math.min(this.debut.minX(), this.fin.minX());
    }

    @Override
    public double maxY() {
        return Math.max(this.debut.maxY(), this.fin.maxY());
    }

    @Override
    public double minY() {
        return Math.min(this.debut.minY(), this.fin.minY());
    }

    @Override
    public double distanceNoeud(Noeud n) {
        double x1 = this.debut.getNx();
        double y1 = this.debut.getNy();
        double x2 = this.fin.getNx();
        double y2 = this.fin.getNy();
        double x3 = n.getNx();
        double y3 = n.getNy();
        double up = ((x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1))
                / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (up < 0) {
            return this.debut.distanceNoeud(n);
        } else if (up > 1) {
            return this.fin.distanceNoeud(n);
        } else {
            Noeud n4 = new Noeud(x1 + up * (x2 - x1),
                    y1 + up * (y2 - y1));
            return n4.distanceNoeud(n);
        }
    }
    
    @Override
    public void dessineSelection(GraphicsContext gc){
        gc.setStroke(Figure.COULEUR_SELECTION);
        gc.strokeLine(this.debut.getNx()-1,this.debut.getNy()-1,this.fin.getNx()-1,this.fin.getNy()-1);
    }
    @Override
    public void dessinDupplique(GraphicsContext gc, Interface vue){
        gc.setStroke(this.getCouleur());
        gc.strokeLine(this.debut.getNx()+5,this.debut.getNy()+5,this.fin.getNx()+5,this.fin.getNy()+5);
      //  vue.getModel().add(new Barre(new Noeud(this.debut.getNx()+5,this.debut.getNy()+5),new Noeud(this.fin.getNx()+5,this.fin.getNy()+5),Color.ORCHID));
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(this.getCouleur());
        gc.strokeLine(this.debut.getNx(),this.debut.getNy(),this.fin.getNx(),this.fin.getNy());
    }    
}
