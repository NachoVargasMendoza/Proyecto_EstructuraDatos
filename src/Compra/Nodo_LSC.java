/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compra;
import Proyecto_EstructuraDeDatos.*;

/**
 *
 * @author Ignac
 */
public class Nodo_LSC { private Articulos datos;
    private Nodo_LSC siguiente;
    
    public Nodo_LSC(){
        this.siguiente=null;
    }

   
    public Articulos getDatos() {
        return datos;
    }

    
    public void setDatos(Articulos datos) {
        this.datos = datos;
    }

    
    public Nodo_LSC getSiguiente() {
        return siguiente;
    }

    
    public void setSiguiente(Nodo_LSC siguiente) {
        this.siguiente = siguiente;
    }
    
}
