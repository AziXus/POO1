/**
 * Classe abstraite effectuant un calcul sur des entiers
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
abstract class Operation {
    /**
     * Effectue un calcul entre deux éléments
     * @param element1 entier étant le premier élément
     * @param element2 entier étant le deuxième élément
     * @return un entier le résultat du calcul
     */
    abstract int calcul(int element1, int element2);
}
