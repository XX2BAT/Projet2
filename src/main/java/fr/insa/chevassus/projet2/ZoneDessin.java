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
import fr.insa.chevassus.projet2.figures.Figure;
import fr.insa.chevassus.projet2.figures.Groupe;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ZoneDessin extends StackPane {

    private Groupe model;
    private Canvas realCanvas;
    private Interface main;

    public ZoneDessin(Interface main,Groupe model, double initWidth, double initHeight) {
        this.model = model;
        this.main = main;
        this.setPrefSize(initWidth, initHeight);
        this.realCanvas = new Canvas();
        this.realCanvas.setManaged(false);
        this.realCanvas.widthProperty().bind(this.widthProperty());
        this.realCanvas.heightProperty().bind(this.heightProperty());
        this.getChildren().add(this.realCanvas);
        this.realCanvas.widthProperty().addListener((o) -> {
            redraw();
        });
        this.realCanvas.heightProperty().addListener((o) -> {
            redraw();
        });
        this.realCanvas.setOnMouseClicked((t) -> {
            Controleur controleur= this.main.getControleur();
            controleur.clicZoneDessin(t);
        });
    }

    public void redraw() {
  
        GraphicsContext gc = this.realCanvas.getGraphicsContext2D();
        gc.setFill(Color.BEIGE);
        gc.fillRect(0,0,this.realCanvas.getWidth(),this.realCanvas.getHeight());
        Groupe model= this.main.getModel();
        List<Figure> select = this.main.getControleur().getSelection();
        if (! select.isEmpty()){
            if (this.main.getEtatInterface()==60){            
                this.model.removeAll(select);
            }else if (this.main.getEtatInterface()==70){            
                for (Figure f:select){
                f.dessinDupplique(gc,main);
                }
            }else{
                for (Figure f:select){
                f.dessineSelection(gc);
                }
            }
        }
        model.draw(gc);
    }
}

