package util;

public class Iterateur {
    private Element current;

    public Iterateur(Element el) {
        current = el;
    }

    boolean possedeSuivant() {
        return current != null;
    }

    Object suivant() {
        Object o = current.data;
        current = current.next;

        return o;
    }
}