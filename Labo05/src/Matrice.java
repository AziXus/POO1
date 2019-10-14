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
        int[colonne][ligne] matrice2;
        this.matrice = matrice2;
        //TODO: Check que le modulo la colonne ou la ligne soit correcte
        generate();
    }

    private void generate(){
        int nombre;
        for(int i = 0; i < ligne; i++) {
            for (int j = 0; i < colonne; j++) {
                nombre = (int)Math.round(Math.random());
                nombre = nombre % modulo;
                matrice[i][j] = nombre;
            }
        }
    }

    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < ligne; i++) {
            for (int j = 0; i < colonne; j++) {
                str += matrice[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
}
