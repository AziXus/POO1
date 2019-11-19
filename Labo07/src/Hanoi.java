import java.util.Iterator;

public class Hanoi {
    private int nbDisks;
    private HanoiDisplayer displayer;
    private Pile[] pile = new Pile[3];
    private int nbDeplacement;

    public Hanoi(int disks){
        this.nbDisks = disks;
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
            //Affichage
            nbDeplacement++;
            D.push(O.pop());
            solveRecursif(I, D, O, n - 1);
        }
    }

    public void solve(){
        solveRecursif(pile[0], pile[1], pile[2], nbDisks);
    }

    public int[][] status(){
        int[][] hanoi = new int[3][];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < nbDisks; j++){

            }
        }
    }

    public boolean finished(){
        return pile[2].getElements().size() == nbDisks;
    }

    public int turn(){
        return nbDeplacement;
    }

}
