public interface IList<T> {
    void add(T element);
    Object get(int index);
    Object search(T value);
    void delete(int index);
    void delete(T value);
    void insert(T value, int index);
    void printFromBeginning();
    void printFromEnd();
    boolean isEmpty();
}
