import java.lang.*;

/**
 * Classe de gestion des matrices
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class Matrice {
    private int modulo;
    private int[][] matrice;
    //Déclaration des différents opérations disponibles sur une matrice
    private Operation add = new Addition();
    private Operation sub = new Soustraction();
    private Operation mult = new Multiplication();

    /**
     * Constructeur spécifique de la classe matrice. Les éléments à l'intérieure de la matrice sont générés en fonction du modulo.
     * Aucun nombres générés ne dépassera le modulo
     * @param colonne entier représentant le nombre de colonne de la matrice
     * @param ligne entier représentant le nombre de ligne de la matrice
     * @param modulo entier réprésentant le modulo de la matrice
     * @throws RuntimeException si le modulo est négatif et
     * si la ligne ou la colonne de la matrice est négative ou égale à 0 une RuntimeException est également retournée
     */
    Matrice(int colonne, int ligne, int modulo){
        //Check que le modulo ne soit pas négatif sinon erreur
        if(modulo <= 0)
            throw new RuntimeException("Modulo négatif ou égal à 0 impossible de créer la matrice");
        //Check que la ligne ou la colonne ne soit pas négative ou égale à 0 sinon dimension incorrecte
        if(ligne <= 0 || colonne <= 0)
            throw new RuntimeException("Dimension de matrice incorrecte( > 0)");
        this.modulo = modulo;
        this.matrice = new int[ligne][colonne];
        //Génération des éléments de la matrice
        generate();
    }

    /**
     * Constructeur spécifique de la classe Matrice. Les éléments de la matrice auront la valeur de la matrice passée en paramètre
     * @param modulo entier étant le module de la matrice
     * @param matrice tableau en 2 dimensions étant la matrcie de laquelle reprendre les éléments
     * @throws RuntimeException si le modulo est négatif ou égal à 0
     * Si la ligne ou la colonne de la matrice a une valeur négative ou égale à 0 une RuntimeException est également retournée
     * Si un élément dans la matrice ne respecte pas la condtion avec le modulo(entre 0 et le modulo - 1) on retourne une RuntimeException
     */
    Matrice(int modulo, int[][] matrice){
        //Check que le modulo ne soit pas négatif
        if(modulo <= 0)
            throw new RuntimeException("modulo négatif ou égal à 0 impossible de créer la matrice");
        //Vérifie les dimensions de la matrice. Ligne et colonne inférieure ou égale 0
        if(matrice.length <= 0 || matrice[0].length <= 0)
            throw new RuntimeException("Dimension de matrice incorrecte( > 0)");
        this.modulo = modulo;
        this.matrice = matrice;
        //Check que le modulo des éléments des colonnes ou des lignes soit corrects
        for(int i = 0; i < getLigne(); i++) {
            for (int j = 0; j < getColonne(); j++) {
                if(this.matrice[i][j] % modulo != this.matrice[i][j])
                    //Retourne un message d'erreur avec l'élément qui ne respecte pas la condition du modulo
                    throw new RuntimeException("l'élément en position ligne = " + i + " colonne = "+ j +" de la matrice ne respecte pas la condition du modulo");
            }
        }
    }

    /**
     * Retourne le modulo d'une matrice
     * @return un entier étant le modulo d'une matrice
     */
    int getModulo(){
        return modulo;
    }

    /**
     * Retourne le nombre de colonnes de la mctrice
     * @return un entier indiuant le nombre de colonne
     */
    private int getColonne(){
        return matrice[0].length;
    }

    /**
     * Retourne le nombre de lignes de la matrice
     * @return un entier indiquant le nombre de ligne de la matrice
     */
    private int getLigne(){
        return matrice.length;
    }

    /**
     * Retourne l'élément donné d'une Matrice
     * @param posLigne entier étant la position de l'éléments sur la ligne
     * @param posColonne entier étant la position de l'élément sur la colonne
     * @return -1 si la position donnée est erronée, sinon il retourne l'élément
     */
    private int getElement(int posLigne, int posColonne){
        if(posLigne >= this.getLigne() || posLigne <= 0 || posColonne >= this.getColonne() || posColonne <= 0 ){
            return -1;
        }
        return matrice[posLigne][posColonne];
    }

    /**
     * Génère les éléments d'une matrice
     */
    private void generate(){
        for(int i = 0; i < getLigne(); i++) {
            for (int j = 0; j < getColonne(); j++) {
                //Math.random() génère un double entre 0.0 et 1.0
                //On multiplie par le modulo pour obtenir une valeur entre 0 à modulo - 1
                matrice[i][j] = (int)(Math.random() * modulo);
            }
        }
    }

    /**
     * Effectue une opération sur les éléments de deux matrices
     * @param m2 objet Matrice étant la deuxième matrice avec laquelle effectuer une opération
     * @param op objet Opération étant l'opération à effectuer entre les 2 matrices
     * @return un objet matrice étant contenant les éléments resultants de l'opérations
     * @throws RuntimeException si le modulo de la première Matrice n'est pas égal à celui de la seconde(passée en paramètre)
     */
    private Matrice operation(Matrice m2, Operation op){
        //Vérfie que les 2 modulo soit identiques
        if(modulo != m2.modulo)
            throw new RuntimeException("Modulo non équivalent");
        //La matrice contenant le résultat va prendre la ligne et la colonne la plus grande entre les deux matrices
        int[][] matrice = new int[Math.max(this.getLigne(), m2.getLigne())][Math.max(this.getColonne(), m2.getColonne())];
        //Parcours de la matrice conteant les résulats et ajout des résultats des opérations entre les éléments des 2 matrices
        for(int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[0].length; j++) {
                //Le résultat d'un modulo peut être négatif en java
                int result = op.calcul(this.getElement(i,j), m2.getElement(i,j)) % modulo;
                if(result < 0){
                    result += modulo;
                }
                matrice[i][j] = result;
            }
        }
        return new Matrice(this.modulo, matrice);
    }

    /**
     * Effetcue une addition entre deux matrice
     * @param m2 Matrice passée en paramètre étant la deuxième matrice pour l'addition
     * @return un objet Matrice étant la matrice contenant les résultats
     */
    Matrice addition(Matrice m2){
        return operation(m2,add);
    }

    /**
     * Effetcue une soustraction entre deux matrice
     * @param m2 Matrice passée en paramètre étant la deuxième matrice pour la soustraction
     * @return un objet Matrice étant la matrice contenant les résultats
     */
    Matrice soustraction(Matrice m2){
        return operation(m2,sub);
    }

    /**
     * Effetcue une multiplication entre deux matrice
     * @param m2 Matrice passée en paramètre étant la deuxième matrice pour la multiplication
     * @return un objet Matrice étant la matrice contenant les résultats
     */
    Matrice multiplication(Matrice m2){
        return operation(m2,mult);
    }

    /**
     * Réecriture de éa fonction toString pour afficher une Matrice
     * @return string contenant l'affichage de la Matrice
     */
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
