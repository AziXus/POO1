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
     * Constructeur spécifique de la classe matrice
     * Les éléments à l'intérieure de la matrice sont générés en fonction du modulo
     * Aucun nombre générés ne dépassera le modulo
     * @param ligne entier représentant le nombre de ligne de la matrice
     * @param colonne entier représentant le nombre de colonne de la matrice
     * @param modulo entier réprésentant le modulo de la matrice
     * @throws RuntimeException si le modulo est négatif
     * Si la ligne ou la colonne de la matrice est négative ou égale à 0 une RuntimeException est
     * également retournée
     */
    public Matrice(int ligne, int colonne, int modulo){
        //Check que le modulo ne soit pas négatif sinon erreur
        if(modulo <= 0)
            throw new RuntimeException("Modulo négatif ou égal à 0 impossible de créer la matrice");
        //Check que la ligne ou la colonne ne soit pas négative ou égale à 0 sinon dimension incorrecte
        if(ligne <= 0 || colonne <= 0)
            throw new RuntimeException("Dimension de matrice incorrecte(ligne et colonne > 0)");
        this.modulo = modulo;
        this.matrice = new int[ligne][colonne];
        //Génération des éléments de la matrice
        generate();
    }

    /**
     * Constructeur spécifique de la classe Matrice.
     * Les éléments de la matrice auront la valeur de la matrice passée en paramètre
     * @param modulo entier étant le module de la matrice
     * @param matrice tableau en 2 dimensions étant la matrice de laquelle reprendre les éléments
     * @throws RuntimeException si le modulo est négatif ou égal à 0
     * Si la ligne ou la colonne de la matrice a une valeur négative ou égale à 0
     * une RuntimeException est également retournée
     * Si un élément dans la matrice ne respecte pas la condition
     * avec le modulo(entre 0 et le modulo - 1) une RuntimeException est levée
     */
    public Matrice(int modulo, int[][] matrice){
        //Check que le modulo ne soit pas négatif
        if(modulo <= 0)
            throw new RuntimeException("modulo négatif ou égal à 0 impossible de créer la matrice");
        //Vérifie les dimensions de la matrice. Ligne et colonne inférieure ou égale 0
        if(matrice.length <= 0 || matrice[0].length <= 0)
            throw new RuntimeException("Dimension de matrice incorrecte(ligne et colonne > 0)");
        this.modulo = modulo;
        this.matrice = matrice;
        //Check que le modulo des éléments des colonnes ou des lignes soit corrects
        for(int i = 0; i < getLigne(); i++) {
            for (int j = 0; j < getColonne(); j++) {
                if(this.matrice[i][j] < 0 || this.matrice[i][j] % modulo != this.matrice[i][j])
                    //Retourne un message d'erreur avec l'élément qui ne respecte pas la condition
                    // du modulo
                    throw new RuntimeException("l'élément (" + i + ", " + j + ") de la matrice ne" +
                                               " respecte pas la condition du modulo");
            }
        }
    }

    /**
     * Retourne le modulo d'une matrice
     * @return un entier étant le modulo d'une matrice
     */
    public int getModulo(){
        return modulo;
    }

    /**
     * Retourne le nombre de colonnes de la matrice
     * @return un entier indiquant le nombre de colonne de la matrice
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
     * @return 0 si la position donnée est erronée, sinon il retourne l'élément
     */
    private int getElement(int posLigne, int posColonne){
        if(posLigne   >= this.getLigne()   || posLigne < 0 ||
           posColonne >= this.getColonne() || posColonne < 0 ){
            return 0;
        }
        return matrice[posLigne][posColonne];
    }

    /**
     * Génère les éléments d'une matrice
     */
    private void generate(){
        for(int i = 0; i < getLigne(); i++) {
            for (int j = 0; j < getColonne(); j++) {
                //Math.random() génère un double entre 0.0 (inclus) et 1.0 (non-inclus)
                //On multiplie par le modulo pour obtenir une valeur entre 0 à (modulo - 1)
                matrice[i][j] = (int)(Math.random() * modulo);
            }
        }
    }

    /**
     * Effectue une opération sur les éléments de deux matrices
     * @param m2 objet Matrice étant la deuxième matrice avec laquelle effectuer une opération
     * @param op objet Opération étant l'opération à effectuer entre les 2 matrices
     * @return un objet matrice étant contenant les éléments resultants de l'opérations
     * @throws RuntimeException si le modulo de la première Matrice n'est pas égal à celui de la
     * seconde(passée en paramètre)
     */
    private Matrice operation(Matrice m2, Operation op){
        //Vérfie que les 2 modulo soit identiques
        if(modulo != m2.modulo)
            throw new RuntimeException("Modulo non équivalent");
        //La matrice va prendre la ligne et la colonne la plus grande entre les deux matrices
        int ligne = Math.max(this.getLigne(), m2.getLigne());
        int colonne = Math.max(getColonne(), m2.getColonne());
        int[][] matrice = new int[ligne][colonne];
        //Parcours de la matrice contenant les résultats et ajout des résultats des opérations entre
        //les éléments des 2 matrices
        for(int i = 0; i < ligne; i++) {
            for (int j = 0; j < colonne; j++) {
                //Le résultat d'un modulo peut être négatif en java
                int result = op.calcul(this.getElement(i,j), m2.getElement(i,j)) % modulo;
                if(result < 0){ //Si le résultat du modulo est négatif, on ajoute une fois le modulo
                    result += modulo;
                }
                matrice[i][j] = result;
            }
        }
        return new Matrice(this.modulo, matrice);
    }

    /**
     * Effectue une addition entre deux matrice
     * @param m2 Matrice passée en paramètre étant la deuxième matrice pour l'addition
     * @return un objet Matrice étant la matrice contenant les résultats
     */
    public Matrice addition(Matrice m2){
        return operation(m2,add);
    }

    /**
     * Effectue une soustraction entre deux matrice
     * @param m2 Matrice passée en paramètre étant la deuxième matrice pour la soustraction
     * @return un objet Matrice étant la matrice contenant les résultats
     */
    public Matrice soustraction(Matrice m2){
        return operation(m2,sub);
    }

    /**
     * Effectue une multiplication entre deux matrice
     * @param m2 Matrice passée en paramètre étant la deuxième matrice pour la multiplication
     * @return un objet Matrice étant la matrice contenant les résultats
     */
    public Matrice multiplication(Matrice m2){
        return operation(m2,mult);
    }

    /**
     * Réecriture de la fonction toString pour afficher une Matrice
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
