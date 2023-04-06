/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compra;

import Proyecto_EstructuraDeDatos.Articulos;
import javax.swing.JOptionPane;


/**
 *
 * @author Ignac
 */
public class Compra_LSC {

    private Nodo_LSC inicio;
    private Nodo_LSC fin;
    private Articulos Datos;

    public Compra_LSC() {
        this.inicio = null;
        this.fin = null;
    }

    public boolean vacio() {
        if (inicio == null) {
            return true;

        } else {
            return false;
        }
    }
    public void agregarArticulo(Articulos n){
        try{
            Nodo_LSC nuevo = new Nodo_LSC();
            nuevo.setDatos(n);
            if (vacio()) {
                inicio = nuevo;
                fin = nuevo;
                fin.setSiguiente(inicio);
            } else if (n.getPrecio() < inicio.getDatos().getPrecio()) {
                nuevo.setSiguiente(inicio);
                inicio = nuevo;
                fin.setSiguiente(inicio);
            } else if (n.getPrecio() > fin.getDatos().getPrecio()) {
                fin.setSiguiente(nuevo);
                fin = nuevo;
                fin.setSiguiente(inicio);
            } else {
                Nodo_LSC aux = inicio;
                while (aux.getSiguiente().getDatos().getPrecio() < n.getPrecio()) {
                    aux = aux.getSiguiente();
                }
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);
            }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }
        public void extraerEspecifico(String nombre) {
        try {
            if (!vacio()) {
                
                if (inicio.getDatos().equals(nombre)) {
                    inicio = inicio.getSiguiente();
                    fin.setSiguiente(inicio);
                    JOptionPane.showMessageDialog(null, "Elemento extraído");
                } else {
                    Nodo_LSC anterior;
                    Nodo_LSC aux;
                    anterior = inicio;
                    aux = inicio.getSiguiente();
                    while ((aux != inicio) && (!aux.getDatos().getNombre().equals(nombre))) {
                        anterior = anterior.getSiguiente();
                        aux = aux.getSiguiente();
                    }
                    if (aux != inicio) {
                        anterior.setSiguiente(aux.getSiguiente());
                        fin.setSiguiente(inicio);
                        JOptionPane.showMessageDialog(null, "Elemento extraído");
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Articulo no existe");
        }
    }
    
}
