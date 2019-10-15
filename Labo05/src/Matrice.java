import java.lang.*;

public class Matrice {
    private int modulo;
    private int[][] matrice;
    private Operation add = new Addition();
    private Operation sub = new Soustraction();
    private Operation mult = new Multiplication();

    private int getColonne(){
        return matrice[0].length;
    }

    private int getLigne(){
        return matrice.length;
    }

    Matrice(int colonne, int ligne, int modulo){
        //TODO: Check que le modulo ne soit pas négatif
        this.modulo = modulo;
        this.matrice = new int[ligne][colonne];
        //TODO: Check que le modulo la colonne ou la ligne soit correcte
        generate();
    }

    Matrice(int modulo, int[][] matrice){
        //TODO: Check que le modulo ne soit pas négatif
        this.modulo = modulo;
        //TODO: vérifie les dimensions de la matrice
        this.matrice = matrice;
        //TODO: Check que le modulo la colonne ou la ligne soit correcte
        //generate();
    }

    private void generate(){
        for(int i = 0; i < getLigne(); i++) {
            for (int j = 0; j < getColonne(); j++) {
                //Math.random() génère un double entre 0.0 et 1.0
                //On multiplie par le modulo pour obtenir une valeur entre 0 à modulo - 1
                matrice[i][j] = (int)(Math.random() * modulo);
            }
        }
    }

    private Matrice operation(Matrice m2, Operation op){
        //TODO: vérfier que les 2 modulo soit identique
        int[][] matrice = new int[Math.max(this.getLigne(), m2.getLigne())][Math.max(this.getColonne(), m2.getColonne())];
        for(int i = 0; i < Math.max(this.getLigne(), m2.getLigne()); i++) {
            for (int j = 0; j < Math.max(this.getColonne(), m2.getColonne()); j++) {
                //Un modulo peut être négatif en java
                int result = op.calcul(this.getElement(i,j), m2.getElement(i,j)) % modulo;
                if(result < 0){
                    result += modulo;
                }
                matrice[i][j] = result;
            }
        }
        return new Matrice(this.modulo, matrice);
    }

    int getElement(int posLigne, int posColonne){
        if(posLigne >= this.getLigne() || posColonne >= this.getColonne()){
            return 0;
        }
        return matrice[posLigne][posColonne];
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
        for(int i = 0; i < getLigne(); i++) {
            for (int j = 0; j < getColonne(); j++) {
                str += matrice[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
}
