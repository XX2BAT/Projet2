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
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import recup.Lire;

public class Groupe extends Figure {

    private List<Figure> contient;
    private Interface vue;
    
    public Groupe() {
        this.contient = new ArrayList<Figure>();
    }

    public void add(Figure f) {
        if (f.getGroupe() != this) {
            if (f.getGroupe() != null) {
                throw new Error("la figure est déja dans un autre groupe");
            }
            this.contient.add(f);
            f.setGroupe(this);
        }
    }
    
    public void remove(Figure f) {
        if (f.getGroupe() != this) {
            throw new Error("la figure n'est pas dans ce groupe");
        }
        this.contient.remove(f);
        f.setGroupe(null);
    }
    
    public void removeAll(List<Figure> lf) {
        for(Figure f : lf) {
            this.remove(f);
        }
    }
    
    public void clear() {
        List<Figure> toRemove = new ArrayList<>(this.contient);
        this.removeAll(toRemove);
    }

    public int size() {
        return this.contient.size();
    }
    
    /**
     * retourne la figure contenue dans le groupe la plus proche du point et 
     * au maximum à distMax du point;
     * retourne null si aucune figure n'est à une distance plus faible que
     * distMax;
     */
    public Figure plusProche(Noeud n,double distMax) {
        if (this.contient.isEmpty()) {
            return null;
        } else {
            Figure fmin = this.contient.get(0); 
            double min = fmin.distanceNoeud(n);
            for(int i = 1 ; i < this.contient.size() ; i ++) {
                Figure fcur = this.contient.get(i);
                double cur = fcur.distanceNoeud(n);
                if (cur < min) {
                    min = cur;
                    fmin = fcur;
                }
            }
            if (min <= distMax) {
                return fmin;
            } else {
                return null;
            }
        }
    }
    /**
     * crée un sous groupe avec les figures contenues dans lf. Ces figures
     * doivent appartenir au groupe this.
     *
     * @param lf
     */
    public void sousGroupe(List<Figure> lf) {
        // verifie que les figures font partie du groupe et les enleve du groupe
        for (int i = 0; i < lf.size(); i++) {
            Figure f = lf.get(i);
            if (f.getGroupe() != this) {
                throw new Error(f + " n'appartient pas au groupe " + this);
            }
            this.contient.remove(f);
            f.setGroupe(null);
        }
        Groupe sg = new Groupe();
        for (int i = 0; i < lf.size(); i++) {
            sg.add(lf.get(i));
        }
        this.add(sg);
    }

    public static String indente(String toIndente, String prefix) {
        return prefix + toIndente.replaceAll("\n", "\n" + prefix);
    }

    @Override
    public String toString() {
        String res = "Groupe :\n";
        for (int i = 0; i < this.contient.size(); i++) {
            res = res + indente(this.contient.get(i).toString(), "  ") + "\n";
        }
        return res + "\n";
    }

    public static Groupe terrainDebut() {
    //terrain à droite
        Noeud ng1 = new Noeud(0,450,Color.GREEN);
        Noeud ng2 = new Noeud(200,450,Color.GREEN);
        Noeud ng3 = new Noeud(120,700,Color.GREEN);
        Noeud ng4 = new Noeud(0,700,Color.GREEN);
        Barre bg1 = new Barre(ng1, ng2,Color.GREEN);
        Barre bg2 = new Barre(ng2, ng3,Color.GREEN);
        Barre bg3 = new Barre(ng3, ng4,Color.GREEN);
        Barre bg4 = new Barre(ng1, ng4,Color.GREEN);        
    //terrain à droite
        Noeud nd1 = new Noeud(1000,450,Color.GREEN);
        Noeud nd2 = new Noeud(750,450,Color.GREEN);
        Noeud nd3 = new Noeud(800,700,Color.GREEN);
        Noeud nd4 = new Noeud(1000,700,Color.GREEN);
        Barre bd1 = new Barre(nd1,nd2,Color.GREEN);
        Barre bd2 = new Barre(nd2,nd3,Color.GREEN);
        Barre bd3 = new Barre(nd3, nd4,Color.GREEN);
        Barre bd4 = new Barre(nd1, nd4,Color.GREEN);
        
        Groupe terrain = new Groupe();
        terrain.add(bg1);
        terrain.add(bg2);
        terrain.add(bg3);
        terrain.add(bg4);
        terrain.add(bd1);
        terrain.add(bd2);
        terrain.add(bd3);
        terrain.add(bd4);
        return terrain;
    }

    public Noeud choisiNoeud() {
        List<Noeud> ln = new ArrayList<>();
        System.out.println("liste des noeuds disponibles : ");
        int nbr = 0;
        for (int i = 0; i < this.contient.size(); i++) {
            Figure f = this.contient.get(i);
            if (f instanceof Noeud) {
                nbr++;
                ln.add((Noeud) f);
                System.out.println(nbr + ") " + f);
            }
        }
        if (nbr == 0) {
            System.out.println("Aucun noeud disponible");
            return null;
        } else {
            int rep = -1;
            while (rep < 0 || rep > nbr) {
                System.out.println("votre choix (0 pour annuler) : ");
                rep = Lire.i();
            }
            if (rep == 0) {
                return null;
            } else {
                return ln.get(rep - 1);
            }
        }
    }

    public List<Figure> choisiFigures() {
        List<Figure> res = new ArrayList<>();
        int rep = -1;
        while (rep != 0) {
            System.out.println("liste des figures disponibles : ");
            for (int i = 0; i < this.contient.size(); i++) {
                System.out.println((i + 1) + ") " + this.contient.get(i));
            }
            System.out.println("votre choix (0 pour finir) : ");
            rep = Lire.i();
            if (rep > 0 && rep <= this.contient.size()) {
                Figure f = this.contient.get(rep - 1);
                if (res.contains(f)) {
                    System.out.println("déja selectionnée !!");
                } else {
                    res.add(f);
                }
                System.out.println(res.size() + " figure(s) séléctionnée(s)");
            }
        }
        return res;
    }

    public void menuTexte() {
        int rep = -1;
        while (rep != 0) {
            System.out.println("1) afficher le groupe");
            System.out.println("2) ajouter un noeud");
            System.out.println("3) ajouter une barre avec deux nouveaux noeuds");
            System.out.println("4) ajouter une barre sur deux noeuds existants");
            System.out.println("5) créer un sous-groupe");
            System.out.println("6) afficher le rectangle englobant");
            System.out.println("7) calculer la distance à un noeud");
            System.out.println("8) retirer des figures du groupe");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1) {
                System.out.println(this);
            } else if (rep == 2) {
                Noeud np = Noeud.demandeNoeud();
                this.add(np);
            } else if (rep == 3) {
                Barre ns = Barre.demandeBarre();
                this.add(ns);
            } else if (rep == 4) {
                System.out.println("choisissez le début de la barre");
                Noeud deb = this.choisiNoeud();
                if (deb != null) {
                    System.out.println("choisissez la fin de la barre");
                    Noeud fin = this.choisiNoeud();
                    Barre ns = new Barre(deb, fin);
                    this.add(ns);
                }
            } else if (rep == 5) {
                List<Figure> select = this.choisiFigures();
                this.sousGroupe(select);
            } else if (rep == 6) {
                System.out.println("maxX = " + this.maxX() + " ; " +
                        "minX = " + this.minX() + "\n" +
                        "maxY = " + this.maxY() + " ; " +
                        "minY = " + this.minY() + "\n");
            } else if (rep == 7) {
                System.out.println("entrez un noeud :");
                Noeud p = Noeud.demandeNoeud();
                System.out.println("distance : "+this.distanceNoeud(p));
            } else if (rep == 8) {
                List<Figure> select = this.choisiFigures();
                this.removeAll(select);
            }
        }
    }

    public static void test1() {
        System.out.println("groupe test : \n" + Groupe.terrainDebut());
    }

    public static void testMenu() {
        Groupe g = terrainDebut();
        g.menuTexte();
    }

    public static void main(String[] args) {
//        test1();
        testMenu();
    }

    /**
     * abscice maximale d'un groupe de figures. 0 si le groupe est vide.
     */
    @Override
    public double maxX() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double max = this.contient.get(0).maxX();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).maxX();
                if (cur > max) {
                    max = cur;
                }
            }
            return max;
        }
    }

    /**
     * abscice minimale d'un groupe de figures. 0 si le groupe est vide.
     */
    @Override
    public double minX() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double min = this.contient.get(0).minX();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).minX();
                if (cur < min) {
                    min = cur;
                }
            }
            return min;
        }
    }

    /**
     * ordonnee maximale d'un groupe de figures. 0 si le groupe est vide.
     */
    @Override
    public double maxY() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double max = this.contient.get(0).maxY();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).maxY();
                if (cur > max) {
                    max = cur;
                }
            }
            return max;
        }
    }

    /**
     * ordonnee minimale d'un groupe de figures. 0 si le groupe est vide.
     */
    @Override
    public double minY() {
        if (this.contient.isEmpty()) {
            return 0;
        } else {
            double min = this.contient.get(0).minY();
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).minY();
                if (cur < min) {
                    min = cur;
                }
            }
            return min;
        }
    }

    @Override
    public double distanceNoeud(Noeud n) {
        if (this.contient.isEmpty()) {
            return new Noeud(0,0).distanceNoeud(n);
        } else {
            double dist = this.contient.get(0).distanceNoeud(n);
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).distanceNoeud(n);
                if (cur < dist) {
                    dist = cur;
                }
            }
            return dist;
        }

    }
    
    @Override
    public void dessineSelection(GraphicsContext gc){
        for(Figure f : this.contient) {
            f.dessineSelection(gc);
        }
    }
    @Override
    public void dessinDupplique(GraphicsContext gc,Interface vue){
        for(Figure f : this.contient) {
            f.dessinDupplique(gc,vue);
     //       vue.getModel().add(f);
        }
    }
    
    @Override
    public void draw(GraphicsContext gc) {
        for(Figure f : this.contient) {
            f.draw(gc);
        }
    }
}
