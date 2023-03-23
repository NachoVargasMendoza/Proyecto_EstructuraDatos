/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AgregarInventario;
import Proyecto_EstructuraDeDatos.*;

/**
 *
 * @author Ignac
 */
public class Nodo_Cola {

    private Articulos dato;
    private Nodo_Cola siguiente;

    public Nodo_Cola() {
        this.siguiente = null;
    }

    public Articulos getDato() {
        return dato;
    }

    public void setDato(Articulos dato) {
        this.dato = dato;
    }

    public Nodo_Cola getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo_Cola siguiente) {
        this.siguiente = siguiente;
    }
}
