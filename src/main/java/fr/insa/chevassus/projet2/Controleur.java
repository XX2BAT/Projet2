/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.chevassus.projet2;

/**
 *
 * @author guilh
 */
import fr.insa.chevassus.projet2.figures.Barre;
import fr.insa.chevassus.projet2.figures.Figure;
import fr.insa.chevassus.projet2.figures.Groupe;
import fr.insa.chevassus.projet2.figures.Noeud;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class Controleur {
      
    private int etat;
    private Interface vue;
    private double[] n1 = new double [2];
    public int a;
    
    private List<Figure> selection;
    
    public Controleur(Interface vue) {
        this.vue = vue;
        this.selection = new ArrayList<>();
    }
    
    public void changeEtat (int nouvelEtat){
        //selection
        if (nouvelEtat==20){
            this.selection.clear();
            this.vue.redraw();
        }
        // Supprimer
        if (nouvelEtat==60){
            etat=60;
            this.selection=this.getSelection();
            this.vue.redraw();
            this.vue.getbSupprimer().setDisable(true);
        }
        //Duppliquer
        if (nouvelEtat==70){
            etat=70;
            this.selection=this.getSelection();
            List<Figure> select = this.selection;
            for (Figure f:select){
                this.vue.getModel().add(f);
                }
            this.vue.redraw();
            this.vue.getbGrouper().setDisable(true);
        }
        //creation noeud simple
        if (nouvelEtat==30){
            this.selection.clear();
            this.vue.getbSupprimer().setDisable(true);
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbDuppliquer().setDisable(true);        }
        //creation noeud double
        if (nouvelEtat==31){
            this.selection.clear();
            this.vue.getbSupprimer().setDisable(true);
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbDuppliquer().setDisable(true);        }
        //creation noeud appui
        if (nouvelEtat==32){
            this.selection.clear();
            this.vue.getbSupprimer().setDisable(true);
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbDuppliquer().setDisable(true);        }
        //creation 1er point de barre
        if (nouvelEtat==40){
            this.selection.clear();
            this.vue.getbSupprimer().setDisable(true);
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbDuppliquer().setDisable(true);        }
        if (nouvelEtat==41){
            //créer 2eme point et barre
        }
        this.etat=nouvelEtat;
    }

    void clicZoneDessin(MouseEvent t) {
        double nx = t.getX();
        double ny = t.getY();
        if (a==1){
            this.etat=41;
        }
        if(this.etat == 20){
            Noeud posClic= new Noeud(t.getX(), t.getY(),Color.BEIGE);
            Figure plusProche = this.vue.getModel().plusProche(posClic,250);
            if (plusProche != null){
                if (t.isShiftDown()){
                    this.selection.add(plusProche);
                }else if (t.isControlDown()){
                    if (this.selection.contains(plusProche)){
                        this.selection.remove(plusProche);
                    }else{
                        this.selection.add(plusProche);
                    }
                }else{
                    this.selection.clear();
                    this.selection.add(plusProche);
                }      
                this.activeBoutonsSuivantSelection();
                this.vue.redraw();
            }
        }else if (this.etat == 30) {
            Groupe model = this.vue.getModel();
            model.add(new Noeud(nx,ny,Color.RED));
            this.vue.redraw();
        }else if (this.etat == 31) {
            Groupe model = this.vue.getModel();
            model.add(new Noeud(nx,ny,Color.BLUE));
            this.vue.redraw();
        }else if (this.etat == 32) {
            Groupe model = this.vue.getModel();
            model.add(new Noeud(nx,ny,Color.BLACK));
            this.vue.redraw();
        } else if (this.etat == 40) {
            this.n1[0] = t.getX();
            this.n1[1] = t.getY();
            a=1;
        } else if (this.etat == 41) {
            this.vue.getModel().add(new Barre(new Noeud(this.n1[0],this.n1[1],Color.RED),new Noeud(nx,ny,Color.RED),Color.ORCHID));
            a=0;
            this.vue.redraw();
            this.changeEtat(40);
       // } else if (this.etat == 60) {
            
        }else{
            System.out.print("aucune action séléctionnée\n");
        }
}
    void bNoeudSimple(ActionEvent t) {
        this.changeEtat(30);
    }
    void bNoeudDouble(ActionEvent t) {
        this.changeEtat(31);
    }
    void bNoeudAppui(ActionEvent t) {
        this.changeEtat(32);
    }
    void bBarre(ActionEvent t) {
        this.changeEtat(40);
    }
    void bEnregistrer(ActionEvent t) {
        this.changeEtat(9);
    }

    void bSelectionner(ActionEvent t) {
        this.changeEtat(20);
    }

    void bCouleur(ActionEvent t) {
        this.changeEtat(11);
    }

    void bRelierNoeud(ActionEvent t) {
        this.changeEtat(12);    
    }

    void bGrouper(ActionEvent t) {
        this.changeEtat(13);
    }

    void bDuppliquer(ActionEvent t) {
        this.changeEtat(70);
    }

    void bSupprimer(ActionEvent t) {
        this.changeEtat(60);
    }

    private void activeBoutonsSuivantSelection() {
        if(this.selection.isEmpty()){
            this.vue.getbSupprimer().setDisable(true);
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbDuppliquer().setDisable(true);
        }
        if(this.selection.size()==1){
            this.vue.getbSupprimer().setDisable(false);
            this.vue.getbGrouper().setDisable(true);
            this.vue.getbDuppliquer().setDisable(false);
        }else{
            this.vue.getbGrouper().setDisable(false);
            this.vue.getbSupprimer().setDisable(false);
            this.vue.getbDuppliquer().setDisable(false);
        }
    }

    public List<Figure> getSelection() {
        return selection;
    }

    public int getEtat() {
        return etat;
    }
    public Interface getInterface() {
        return vue;
    }
}


