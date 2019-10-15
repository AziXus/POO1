import java.lang.*;

public class Matrice {
    private int colonne;
    private int ligne;
    private int modulo;
    private int[][] matrice;

    Matrice(int colonne, int ligne, int modulo){
        this.colonne = colonne;
        this.ligne = ligne;
        this.modulo = modulo;
        this.matrice = new int[ligne][colonne];
        //TODO: Check que le modulo la colonne ou la ligne soit correcte
        generate();
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
