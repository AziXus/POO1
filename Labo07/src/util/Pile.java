package util;//permet d'inclure les classes Element et Pile dans la package util
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
        //Pour renvoyer l'objet supprimer, affectation à une variable avant d'enlever le lien de l'élément avec la pile
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
        //Parcours la pile tant qu'un element possède un suivant
        for (Iterateur it = iterateur(); it.possedeSuivant(); ++index) {
            elements[index] = it.suivant();
        }

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

        Iterateur it = iterateur();
        sb.append("[ ");
        while (it.possedeSuivant()) {
            sb.append("<").append(it.suivant()).append("> ");
        }
        sb.append("]\n");

        return sb.toString();
    }
}
