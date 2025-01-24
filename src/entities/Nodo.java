/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Luis Soriano
 * @param <T>
 */

    
public class Nodo<T> {
    private T dato; // Dato almacenado en el nodo
    private Nodo<T> siguiente; // Referencia al siguiente nodo

    // Constructor
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    /**
     * Devuelve el dato almacenado en el nodo.
     * @return El dato almacenado.
     */
    public T getDato() {
        return dato;
    }

    /**
     * Establece el dato del nodo.
     * @param dato El dato a almacenar.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Devuelve el nodo siguiente.
     * @return El siguiente nodo.
     */
    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    /**
     * Establece la referencia al siguiente nodo.
     * @param siguiente El nodo siguiente.
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}
    
    
    

