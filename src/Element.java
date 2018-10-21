public class Element<T> {
    private Element<T> next;
    private Element<T> previous;
    private T value;

    public Element(T value) {
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    public void setNext(Element<T> next) {
        this.next = next;
    }

    public Element<T> getNext() {
        return next;
    }

    public void setPrevious(Element<T> previous) {
        this.previous = previous;
    }

    public Element<T> getPrevious() {
        return previous;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
