/**
 * Classe héritant d'opération effectuant une addition sur des entiers
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
class Addition extends Operation {
    /**
     * Réecriture de la fonction calcul. Heritée de Opération
     * Effectue une addition entre deux éléments.
     * @param element1 entier étant le premier élément
     * @param element2 entier étant le deuxième élément
     * @return un entier étant le résultat de l'addition
     * @throws ArithmeticException en cas d'overflow par Math.addExact
     */
    @Override
    public int calcul(int element1, int element2) {
        return Math.addExact(element1, element2);
    }
}
