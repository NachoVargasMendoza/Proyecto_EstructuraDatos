/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventario;

import Proyecto_EstructuraDeDatos.*;

/**
 *
 * @author Ignacio
 */
public class Nodo_LDC {
     private Articulos datos;
    private Nodo_LDC siguiente;
    private Nodo_LDC anterior;
    
    public Nodo_LDC(){
        this.siguiente=null;
        this.anterior=null;
    }

    public Articulos getDatos() {
        return datos;
    }

    public void setDatos(Articulos datos) {
        this.datos = datos;
    }

    public Nodo_LDC getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_LDC siguiente) {
        this.siguiente = siguiente;
    }

    public Nodo_LDC getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo_LDC anterior) {
        this.anterior = anterior;
    }
    
}
