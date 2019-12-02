package util;//permet d'inclure la classe Element dans la package util
/**
 * Classe permettant la gestion d'élément de type Object dans une pile
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
class Element {
    Object data;
    Element next;

    /**
     * Constructeur de la classe Element
     * @param data object répresentant la valeur de l'élément
     */
    Element(Object data) {
        this.data = data;
    }

    /**
     * Deuxième constructeur de la classe object permettant d'ajouter un élément en depuis de pile
     * @param data object représentant la valeur de l'élément
     * @param next objet représentant la valeur de l'élément suivant dans la pile
     */
    Element(Object data, Element next) {
        this(data);
        this.next = next;
    }
}
