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
import fr.insa.chevassus.projet2.figures.Groupe;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Dessin de treillis 2D");
        Scene scene = new Scene(new Interface(Groupe.terrainDebut()),1100,600);
        stage.setScene(scene);
        stage.show();
    }

    public static void Main(String[] args) {
        launch(args);
    }

}