
package Articulos_Venta;
public class Nodo_prueba {
    private Articulo dato;
    private Nodo_prueba siguiente;
    private Nodo_prueba anterior;
    
    public Nodo_prueba(){
        this.siguiente=null;
        this.anterior=null;
    }

    public Articulo getDato() {
        return dato;
    }

    public void setDato(Articulo dato) {
        this.dato = dato;
    }

    public Nodo_prueba getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_prueba siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo_prueba getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo_prueba anterior) {
        this.anterior = anterior;
    }

 
    
    
}
