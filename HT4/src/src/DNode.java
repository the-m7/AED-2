package src;

public class DNode<T> {
    public T value;

    public DNode<T> next; 
    public DNode<T> prev;

    public DNode(T _value){
        value = _value;
        next = null;
        prev = null;
    }
}
