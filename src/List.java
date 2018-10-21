public class List<T> implements IList<T> {
    private Element<T> head;
    private Element<T> tail;
    private int size;

    public List() {
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void add(T element) {
        Element<T> item = new Element<>(element);
        if (isEmpty()) {
            head = item;
            tail = item;
        } else {
            Element<T> helper = tail;
            tail = item;
            helper.setNext(item);
            item.setPrevious(helper);
        }
        size++;
    }

    @Override
    public Element<T> get(int index) {
        if(isEmpty())
            throw new IllegalArgumentException("IList is empty, you cannot get an element");
        else if ((index < 0) || (index >= size))
            throw new IndexOutOfBoundsException("Incorrect index");
        else {
            Element<T> item = head;
            for (int i = 0; i < index; i++)
                item = item.getNext();
            return item;
        }
    }

    @Override
    public Element<T> search(T value) {
        if (isEmpty()) {
            return null;
        }
        Element<T> item = head;
        for (int i = 0; i < getSize(); i++) {
            if (item.getValue().equals(value))
                return item;
            item = item.getNext();
        }
        return null;
    }

    @Override
    public void delete(int index) {
        if(isEmpty())
            throw new IllegalArgumentException("IList is empty, you cannot delete the element");
        else if((index < 0) || (index >= size))
            throw new IndexOutOfBoundsException("Incorrect index");
        else {
            if (index == 0)
                deleteHead();
            else if (index == size - 1)
                deleteTail();
            else {
                Element<T> item = head;
                for (int i = 0; i < index; i++)
                    item = item.getNext();
                deleteInnerElement(item);
            }
            size--;
        }
    }

    @Override
    public void delete(Object value) {
        if(isEmpty())
            throw new IllegalArgumentException("IList is empty, you cannot delete the element");
        Element<T> item = head;
        for(int i=0; i < getSize(); i++){
            if(item.getValue().equals(value)){
                if(i == 0)
                    deleteHead();
                else if(i == size-1)
                    deleteTail();
                else
                    deleteInnerElement(item);
                size--;
                break;
            }
            item = item.getNext();
        }
    }

    private void deleteHead(){
        Element<T> item = head;
        Element<T> afterDeletedElement = item.getNext();
        head = afterDeletedElement;
        afterDeletedElement.setPrevious(null);
    }

    private void deleteTail() {
        Element<T> item = tail;
        Element<T> behindDeletedElement = item.getPrevious();
        tail = behindDeletedElement;
        behindDeletedElement.setNext(null);
    }

    private void deleteInnerElement(Element<T> removedItem) {
        Element<T> afterDeletedElement = removedItem.getNext();
        Element<T> behindDeletedElement = removedItem.getPrevious();
        behindDeletedElement.setNext(afterDeletedElement);
        afterDeletedElement.setPrevious(behindDeletedElement);
    }

    @Override
    public void insert(T value, int index) {
        if ((index < 0) || ((index >= size) && (!isEmpty())))
            throw new IndexOutOfBoundsException();
        else if (index == 0 && !isEmpty())
            insertOnTheTop(value);
        else if (index == size-1)
            insertInTheEnd(value);
        else if(index == 0 && isEmpty()){
            Element<T> item = new Element<>(value);
            head = item;
            tail = item;
        }
        else {
            Element<T> item = head;
            for (int i = 1; i < index; i++)
                item = item.getNext();
            insertInTheMiddle(item, value);
        }
        size++;
    }

    private void insertOnTheTop(T value)
    {
        Element<T> newElement = new Element<>(value);
        Element<T> helper = head;
        newElement.setNext(helper);
        head = newElement;
        helper.setPrevious(newElement);
    }

    private void insertInTheEnd(T value)
    {
        Element<T> newElement = new Element<>(value);
        Element<T> helper = tail;
        newElement.setPrevious(helper);
        tail = newElement;
        helper.setNext(newElement);
    }

    private void insertInTheMiddle(Element<T> item, T value)
    {
        Element<T> newElement = new Element<>(value);
        Element<T> helper = item.getNext();
        item.setNext(newElement);
        newElement.setNext(helper);
        newElement.setPrevious(item);
        helper.setPrevious(newElement);
    }
    @Override
    public void printFromBeginning() {
        Element item = head;
        for (int i = 0; i < getSize(); i++) {
            System.out.println("(" + i + ") -> " + item.getValue());
            item = item.getNext();
        }
    }

    @Override
    public void printFromEnd() {
        Element item = tail;
        for (int i = getSize() - 1; i >= 0; i--) {
            System.out.println("(" + i + ") -> " + item.getValue());
            item = item.getPrevious();
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    public Element<T> getIndexRecursive(int index){
        if(isEmpty())
            return null;
        if((index < 0) || (index >= size))
            throw new IndexOutOfBoundsException("Incorrect index");
        Element<T> item = head;
        return recursiveSearching(index, item);
    }

    private Element<T> recursiveSearching(int index, Element<T> item){
        if (index == 0)
            return item;
        else{
            index--;
            item = item.getNext();
            item = recursiveSearching(index, item);
            return item;
        }
    }
}
