package hanoi;

import java.util.Arrays;
import util.*;

public class Hanoi {
    private int nbDisks;
    private HanoiDisplayer displayer;
    private Pile[] pile = new Pile[3];
    private int nbDeplacement;

    public Hanoi(int disks){
        this.nbDisks = disks;
        pile[0] = new Pile();
        pile[1] = new Pile();
        pile[2] = new Pile();
        for(int i = disks; i > 0; i--){
            pile[0].push(i);
        }
    }

    public Hanoi(int disks, HanoiDisplayer displayer){
        this(disks);
        this.displayer = displayer;
    }

    private void solveRecursif(Pile O, Pile D, Pile I, int n){
        if(n > 0) {
            solveRecursif(O, I, D, n - 1);
            displayer.display(this);
            nbDeplacement++;
            D.push(O.pop());
            solveRecursif(I, D, O, n - 1);
        }
    }

    private int[] objectArrayToInt(Object[] elements){
        int[] hanoiPile = new int[elements.length];
        for(int i = 0; i < elements.length; i++){
            hanoiPile[i] = (int)elements[i];
        }
        return hanoiPile;
    }

    public void solve(){
        solveRecursif(pile[0], pile[2], pile[1], nbDisks);
        displayer.display(this);
    }

    public int[][] status(){
        int[][] hanoi = new int[3][];
        hanoi[0] = objectArrayToInt(pile[0].getElements());
        hanoi[1] = objectArrayToInt(pile[1].getElements());
        hanoi[2] = objectArrayToInt(pile[2].getElements());
        return hanoi;
    }

    public boolean finished(){
        return pile[2].getElements().length == nbDisks;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("One:    ").append(pile[0]);
        str.append("Two:    ").append(pile[1]);
        str.append("Three:  ").append(pile[2]);
        return str.toString();
    }

    public int turn(){
        return nbDeplacement;
    }

}
