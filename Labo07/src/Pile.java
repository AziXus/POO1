import java.util.*;

class Element {
    Object data;
    Element next;

    Element(Object data) {
        this.data = data;
    }

    Element(Object data, Element next) {
        this(data);
        this.next = next;
    }
}

public class Pile {
    private Element head;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void push(Object o) {
        head = new Element(o, head);
        ++size;
    }

    public Object pop() {
        if (isEmpty())
            return null;

        Object top = head.data;
        head = head.next;
        --size;

        return top;
    }

    public Object[] getElements() {
        Object[] elements = new Object[size];

        int i = 0;
        for (Element e = head; e.next != null; e = e.next, ++i) {
            elements[i] = e.data;
        }

        return elements;
    }

    public Iterateur iterateur() {
        return new Iterateur(head);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Iterateur it = this.iterateur();
        sb.append("[ ");
        while (it.possedeSuivant()) {
            sb.append("<").append(it.suivant()).append("> ");
        }
        sb.append("]");

        return sb.toString();
    }
}
