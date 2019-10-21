class Addition extends Operation {
    /**
     * Réecriture de la fonction calcul. Heritée de Opération
     * Effetcue une addition entre deux éléments.
     * @param element1 entier étant le premier élément
     * @param element2 entier étant le deuxième élément
     * @return un entier étant le résultat de l'addition
     */
    @Override
    int calcul(int element1, int element2) {
        return element1 + element2;
    }
}
