package util;//permet d'inclure la classe Element dans la package util
/**
 * Classe permettant la gestion d'élément de type Object dans une pile
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
class Element {
    Object data;
    Element next;

    /**
     * Constructeur spécifique de la classe Element
     * @param data object répresentant la valeur de l'élément
     */
    Element(Object data) {
        this.data = data;
    }

    /**
     * Deuxième constructeur spécifique de la classe object
     * permettant de donner l'adresse de l'élément suivant dans la pile
     * @param data object représentant la valeur de l'élément
     * @param next objet représentant la valeur de l'élément suivant dans la pile
     */
    Element(Object data, Element next) {
        this(data);
        this.next = next;
    }
}
