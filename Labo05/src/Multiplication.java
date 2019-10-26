/**
 * Classe héritant d'opération effectuant une multiplication sur des entiers
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
class Multiplication extends Operation {
    /**
     * Réecriture de la fonction calcul. Heritée de Opération
     * Effectue une multiplication entre deux éléments.
     * @param element1 entier étant le premier élément
     * @param element2 entier étant le deuxième élément
     * @return un entier étant le résultat de la multiplication
     * @throws ArithmeticException en cas d'overflow par Math.multiplyExact
     */
    @Override
    int calcul(int element1, int element2) {
        return Math.multiplyExact(element1, element2);
    }
}
