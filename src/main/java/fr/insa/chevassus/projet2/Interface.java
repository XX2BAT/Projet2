/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.chevassus.projet2;

/**
 *
 * @author guilhem
 */
import fr.insa.chevassus.projet2.figures.Groupe;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Interface extends BorderPane {
    
    private Groupe model;
    private Controleur controleur;

    //  HBOX    
    private Button bOuvrir;
    private Button bEnregistrer;
    private Button bSupprimer;
    private Button bDuppliquer;
    private Button bGrouper;
    private Button bRelierNoeud;
    private Button bSelectionner;
   
    //  VBOX
    private RadioButton bNoeudSimple;
    private RadioButton bNoeudDouble;
    private RadioButton bNoeudAppui;
    private RadioButton bBarre;
    
    
    //ETAT (quel bouton cliqué)
    private int etat;

    private ZoneDessin zoneDessin;

    public Interface() {
        this(new Groupe());
    }

    public Interface(Groupe model) {
        this.model = model;
        this.controleur=new Controleur(this);
        this.etat=this.controleur.getEtat();
        
        this.bOuvrir = new Button("Ouvrir");
        this.bEnregistrer = new Button("Enregistrer");
        this.bEnregistrer.setOnAction((t) -> {
          this.controleur.bEnregistrer(t);
        });
        this.bSupprimer = new Button("Supprimer");
        this.bSupprimer.setDisable(true);
        this.bSupprimer.setOnAction((t) -> {
          this.controleur.bSupprimer(t);
        });
        this.bDuppliquer = new Button("Duppliquer");
        this.bDuppliquer.setDisable(true);
        this.bDuppliquer.setOnAction((t) -> {
          this.controleur.bDuppliquer(t);
        });
        this.bGrouper = new Button("Grouper");
        this.bGrouper.setDisable(true);
        this.bGrouper.setOnAction((t) -> {
          this.controleur.bGrouper(t);
        });
        this.bRelierNoeud= new Button("Relier noeuds");
        this.bRelierNoeud.setDisable(true);
        this.bRelierNoeud.setOnAction((t) -> {
          this.controleur.bRelierNoeud(t);
        });
        this.bSelectionner = new Button("Selectionner");
        this.bSelectionner.setDisable(false);
        this.bSelectionner.setOnAction((t) -> {
          this.controleur.bSelectionner(t);
        });
        HBox entete = new HBox(this.bOuvrir,this.bEnregistrer,this.bSupprimer,this.bDuppliquer,this.bGrouper,this.bRelierNoeud,this.bSelectionner);
        this.setTop(entete);
   
        ToggleGroup tgState = new ToggleGroup() ;
        this.bNoeudSimple= new RadioButton("Noeud simple (rouge)");
        this.bNoeudSimple.setToggleGroup(tgState);
        this.bNoeudSimple.setOnAction((t) -> {
           this.controleur.bNoeudSimple(t);
        });
        this.bNoeudDouble = new RadioButton("Noeud double (bleu)");
        this.bNoeudDouble.setToggleGroup(tgState);
        this.bNoeudDouble.setOnAction((t) -> {
           this.controleur.bNoeudDouble(t);
        });
        this.bNoeudAppui= new RadioButton("Noeud appui (noir)");
        this.bNoeudAppui.setToggleGroup(tgState);
        this.bNoeudAppui.setOnAction((t) -> {
            this.controleur.bNoeudAppui(t);
        });
        this.bBarre = new RadioButton("Barre");
        this.bBarre.setToggleGroup(tgState);
        this.bBarre.setOnAction((t) -> {
            this.controleur.bBarre(t);
        });
        
        VBox v = new VBox(this.bNoeudSimple,this.bNoeudDouble,this.bNoeudAppui,this.bBarre);
        this.setLeft(v);
        
        this.zoneDessin = new ZoneDessin(this,this.model,300,200);
        this.setCenter(this.zoneDessin);
    }

    public Groupe getModel() {
        return model;
    }
       
    public Controleur getControleur() {
        return controleur;
    }

    public Button getbGrouper() {
        return bGrouper;
    }
    public Button getbSupprimer() {
        return bSupprimer;
    }
    public Button getbDuppliquer() {
        return bDuppliquer;
    }
    public int getEtatInterface() {
        return this.controleur.getEtat();
    }
    public Color curColor() {
        // TODO
        return Color.color(Math.random(), Math.random(), Math.random());
    }

    void redraw() {
        this.zoneDessin.redraw();
    }    
}


