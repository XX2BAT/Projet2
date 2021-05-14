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
import recup.Lire;


public class Noeud extends FigureSimple {
    
    private double nx;
    private double ny;
    
    public Noeud(double nx,double ny,Color couleur){
        super(couleur);
        this.nx = nx;
        this.ny = ny;
    }

    public Noeud(double nx, double ny) {
        this(nx, ny, Color.BLACK);
    }

    public double getNx() {
        return nx;
    }

    public void setNx(double Nx) {
        this.nx = nx;
    }

    public double getNy() {
        return ny;
    }

    public void setNy(double ny) {
        this.ny = ny;
    }
    
    @Override    
    public String toString() {
        return "(" + nx + "," + ny + ')';
    }

    public static Noeud demandeNoeud() {
        System.out.println("abscisse : ");
        double nx = Lire.d();
        System.out.println("ordonnée : ");
        double ny = Lire.d();
        return new Noeud(nx, ny);
    }

    @Override
    public double maxX() {
        return this.nx;
    }

    @Override
    public double minX() {
        return this.nx;
    }

    @Override
    public double maxY() {
        return this.ny;
    }

    @Override
    public double minY() {
        return this.ny;
    }

    public double distanceNoeud(Noeud n) {
        double dx = this.nx - n.nx;
        double dy = this.ny - n.ny;
        return Math.sqrt(dx*dx+dy*dy);
    }
    
    @Override
    public void dessineSelection(GraphicsContext gc){
        gc.setStroke(Figure.COULEUR_SELECTION);
        gc.strokeRect(nx-5.5, ny-5.5,8,8);
    }
    @Override
    public void dessinDupplique(GraphicsContext gc, Interface vue){
        gc.setFill(this.getCouleur());
        gc.fillOval(this.nx, this.ny, 7,7);
     //   vue.getModel().add(new Noeud(this.nx, this.ny));
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.getCouleur());
        gc.fillOval(this.nx-5, this.ny-5, 7,7);
    }
} 

