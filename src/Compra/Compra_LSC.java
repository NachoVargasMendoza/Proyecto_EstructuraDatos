/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Compra;

import Proyecto_EstructuraDeDatos.Articulos;


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
}
