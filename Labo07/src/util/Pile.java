package util;
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
/**
 * Classe permettant de représenter une pile générique (capable de stocker un nombre quelconque
 * d’objets)
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class Pile {
    private Element head;
    private int size;

    /**
     * Permet de savoir si la pile est vide
     * @return vrai si la pile est vide, faux sinon
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Retourne la taille de la pile
     * @return un entier indiquant la taille de la pile
     */
    public int size() {
        return size;
    }

    /**
     * Permet d'ajouter un élément en début de pile
     * @param o Object à ajouter à la pile
     */
    public void push(Object o) {
        head = new Element(o, head);
        ++size;
    }

    /**
     * Permet de supprimer l'élément en début de pile
     * @return l'objet ayant été supprimé
     */
    public Object pop() {
        if (isEmpty())
            return null;
        //Pour renvoyer l'objet supprimer affectation à une variable avant d'enlever le lien de l'élément avec la pile
        Object top = head.data;
        head = head.next;
        --size;

        return top;
    }

    /**
     * Retourne tout les éléments contenus dans la pile
     * @return un tableau d'objet contenant les objets de la pile
     */
    public Object[] getElements() {
        Object[] elements = new Object[size];

        //Entier permettant de parcourir le tableau contenant les objets
        int index = 0;
        //Parcours la pile tant que le prochain élément n'est pas null
        for (Element e = head; e != null; e = e.next, ++index) {
            elements[index] = e.data;
        }
        /*
        for (Iterateur it = new Iterateur(head); it.possedeSuivant(); it.suivant(), ++index) {
            elements[index] = it.data;
        }
        * */

        return elements;
    }

    /**
     * Permet de renvoyer l'élément de début de pile sous forme d'objet iterateur
     * @return un objet de type iterateur pour l'élément de début de pile
     */
    public Iterateur iterateur() {
        return new Iterateur(head);
    }

    /**
     * Réecriture de la fonction toString pour afficher une pile
     * @return string contenant l'affichage de la pile
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Iterateur it = this.iterateur();
        sb.append("[ ");
        while (it.possedeSuivant()) {
            sb.append("<").append(it.suivant()).append("> ");
        }
        sb.append("]\n");

        return sb.toString();
    }
}
