package hanoi;

import util.*;
/**
 * Classe représentant les tours d'Hanoi et leurs disques
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class Hanoi {
    private int nbDisks;
    private HanoiDisplayer displayer;
    private Pile[] tours = new Pile[3];
    private int nbDeplacement;

    /**
     * Constructeur spécifique de la classe Hanoi
     * Initialise les tours de Hanoi et place les disques dans un ordre
     * croissant sur la première tour.
     * @param disks le nombre de disques à placer sur la première tour.
     * @throws RuntimeException si le nombre de disque est négatif
     */
    public Hanoi(int disks){
        this.nbDisks = disks;
        this.tours[0] = new Pile();
        this.tours[1] = new Pile();
        this.tours[2] = new Pile();

        //place les disques dans un ordre croissant sur la première tour.
        for(int i = disks; i > 0; i--){
            this.tours[0].push(i);
        }

        // public Hanoi(int disks) : Constructeur pour l’affichage à la console.
        //this.displayer = new HanoiDisplayer();
    }

    /**
     * Constructeur spécifique de la classe Hanoi.
     * Initialise les tours de Hanoi et place les disques dans un ordre
     * croissant sur la première tour.
     * @param disks le nombre de disques à placer sur la première tour.
     * @param displayer instance HanoiDisplayer à utiliser pour l'affichage
     */
    public Hanoi(int disks, HanoiDisplayer displayer){
        this(disks);
        this.displayer = displayer;
    }

    /**
     * Méthode privée recursive permettant de déplacer les disques d'une tour
     * vers une autre en passant par une troisième tour intermédiaire.
     * @param O Pile (tour) d'origine
     * @param I Pile (tour) intermédiaire
     * @param D Pile (tour) de destination
     * @param n Nombres de disques à déplacer
     */
    private void solveRecursif(Pile O, Pile I, Pile D, int n){
        if(n > 0) {
            solveRecursif(O, D, I, n - 1);
            displayer.display(this);
            nbDeplacement++;
            // Déplace le disque du sommet de la tour d'origine vers la destination
            D.push(O.pop());
            solveRecursif(I, O, D, n - 1);
        }
    }

    /**
     * Méthode privée convertissant un tableau d'Object en tableau de int
     * @param elements tableau d'Objects à convertir
     * @return tableau de int
     */
    private int[] objectArrayToInt(Object[] elements){
        int[] hanoiPile = new int[elements.length];
        for(int i = 0; i < elements.length; i++){
            hanoiPile[i] = (int)elements[i];
        }
        return hanoiPile;
    }

    /**
     * Déplace tous les disques de la première aiguille à la troisième
     */
    public void solve(){
        solveRecursif(tours[0], tours[1], tours[2], nbDisks);
        displayer.display(this);
    }

    /**
     * Retourne un tableau de tableaux représentant l’état des tours d'Hanoi.
     * L’élément t[i][j] correspond à la taille du j-ème disque de la i-ème tour.
     * @return tableau de tableaux représentant l’état des tours d'Hanoi.
     */
    public int[][] status(){
        int[][] hanoi = new int[3][];
        hanoi[0] = objectArrayToInt(tours[0].getElements());
        hanoi[1] = objectArrayToInt(tours[1].getElements());
        hanoi[2] = objectArrayToInt(tours[2].getElements());
        return hanoi;
    }

    /**
     * Retourne si la partie est finie ou non.
     * @return true si la dernière tour contient tous les disques, false sinon
     */
    public boolean finished(){
        return tours[2].getElements().length == nbDisks;
    }

    /**
     * Retourne le nombre de déplacements effectués
     * @return nombre de déplacements effectués
     */
    public int turn(){
        return nbDeplacement;
    }

    /**
     * Surcharge de la fonction toString retournant l'état courant des tours.
     * @return état courant des tours
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("One:    ").append(tours[0]);
        str.append("Two:    ").append(tours[1]);
        str.append("Three:  ").append(tours[2]);
        return str.toString();
    }

}
