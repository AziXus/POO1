package util;//permet d'inclure la classe Iterateur dans la package util
/**
 * Classe de création d'iterateur
 * @author Müller Robin, Teixeira Carvalho Stéphane
 */
public class Iterateur {
    private Element current;

    /**
     * Constructeur spécifique de la classe Iterateur
     * @param el objet de type Element étant l'élément allant être l'iterateur desiré
     */
    public Iterateur(Element el) {
        current = el;
    }

    /**
     * Permet de savoir si l'itérateur possède un élément suivant
     * Si l'élément courant n'est pas nul cela veut dire que nous ne sommes pas en fin de pile
     * et donc qu'il existe encore un élément suivant
     * @return vrai si un élément courant est différent de null, sinon faux
     */
    public boolean possedeSuivant() {
        return current != null;
    }

    /**
     * Permet de connaître la valeur de l'élément suivant
     * @return un object étant la valeur de l'élément suivant et
     * s'il ne possède pas d'élément suivant il retourne null
     */
    public Object suivant() {
        if(possedeSuivant()) {
            Object o = current.data;
            current = current.next;
            return o;
        }
        return null;
    }
}