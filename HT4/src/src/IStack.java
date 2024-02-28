/**
 * 
 */
package src;

/**
 * Interfaz para el Stack
 */
public interface IStack<T> {
    
    /**
     * Devuelve la cantidad de elementos que posee el stack
     * @return
     */
    int count();
    
    /**
     * Devuelve si el stack esta vacío
     * @return
     */
    boolean isEmpty();
    
    /**
     * Agrega un elemento
     * @param value
     */
    void push(T value);

    T pop();

    T peek();

}
