import java.util.*;

class Element{
    Object data;
    Element next;

    protected Element(Object o, Element next){
        this.data = o;
        this.next = next;
    }
}

public class Pile {
    private LinkedList<Object> elements;

    public Pile() {
        this.elements = new LinkedList<>();
    }

    public Pile(LinkedList<Object> elements) {
        //Implement deep copy
        this.elements = new LinkedList<>(elements);
    }

    public void push(Object element) {
        elements.push(element);
    }

    public Object pop() {
        return elements.pop();
    }

    public LinkedList<Object> getElements() {
        return elements;
    }

    public Iterator<Object> iterator() {
        return elements.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        for (Object el : elements) {
            sb.append(" <").append(el).append(">");
        }
        sb.append(" ]");

        return sb.toString();
    }
}
