import java.lang.*;

public class Matrice {
    private int colonne;
    private int ligne;
    private int modulo;
    private int[][] matrice;
    private Operation add = new Addition();
    private Operation sub = new Soustraction();
    private Operation mult = new Multiplication();

    Matrice(int colonne, int ligne, int modulo){
        this.colonne = colonne;
        this.ligne = ligne;
        this.modulo = modulo;
        this.matrice = new int[ligne][colonne];
        //TODO: Check que le modulo la colonne ou la ligne soit correcte
        generate();
    }

    Matrice(int modulo, int[][] matrice){
        this.colonne = matrice[0].length;
        this.ligne = matrice.length;
        this.modulo = modulo;
        //TODO: vérifie les dimensions de la matrice
        this.matrice = matrice;
        //TODO: Check que le modulo la colonne ou la ligne soit correcte
        //generate();
    }

    private void generate(){
        for(int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                //Math.random() génère un double entre 0.0 et 1.0
                //On multiplie par le modulo pour obtenir une valeur entre 0 à modulo - 1
                matrice[i][j] = (int)(Math.random() * modulo);
            }
        }
    }

    private Matrice operation(Matrice m2, Operation op){
        //TODO: vérfier que les 2 modulo soit identique
        int[][] matrice = new int[Math.max(this.ligne, m2.ligne)][Math.max(this.colonne, m2.colonne)];
        for(int i = 0; i < Math.min(this.ligne, m2.ligne); i++) {
            for (int j = 0; j < Math.min(this.colonne, m2.colonne); j++) {
                //Un modulo peut être négatif en java
                matrice[i][j] = (op.calcul(this.matrice[i][j], m2.matrice[i][j]) % modulo + modulo) % modulo;
            }
        }
        return new Matrice(this.modulo, matrice);
    }

    Matrice addition(Matrice m2){
        return operation(m2,add);
    }

    Matrice soustraction(Matrice m2){
        return operation(m2,sub);
    }

    Matrice multiplication(Matrice m2){
        return operation(m2,mult);
    }

    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                str += matrice[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
}
