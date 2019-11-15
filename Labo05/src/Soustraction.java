/**
 * Classe héritant d'opération effectuant une soustraction sur des entiers
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
class Soustraction extends Operation{
    /**
     * Réecriture de la fonction calcul. Heritée de Opération
     * Effectue une soustraction entre deux éléments.
     * @param element1 entier étant le premier élément
     * @param element2 entier étant le deuxième élément
     * @return un entier étant le résultat de la soustraction
     * @throws ArithmeticException en cas d'overflow par Math.subtractExact
     */
    @Override
    public int calcul(int element1, int element2) {
        return Math.subtractExact(element1, element2);
    }
}
