
package Compra_Cola;
import AgregarArticulos_ListaSimple.*;
import Articulos_Venta.Articulo;

public class Nodo {
    private Articulo datos;
    private Nodo siguiente;
   
    
    public Nodo(){
        this.siguiente=null;
       
    }

    public Articulo getDatos() {
        return datos;
    }

    public void setDatos(Articulo datos) {
        this.datos = datos;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    
    
}
