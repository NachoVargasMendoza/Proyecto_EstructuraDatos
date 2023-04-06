/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventario;

import AgregarInventario.Nodo_Cola;
import Proyecto_EstructuraDeDatos.Articulos;
import javax.swing.JOptionPane;

/**
 *
 * @author Ignac
 */
public class Inventario_LDC extends Articulos {

    private Nodo_LDC inicio;
    private Nodo_LDC fin;

    public Inventario_LDC(String nombre, String descripcion, String coPro, double precio, int cantidad) {
        super(nombre, descripcion, coPro, precio, cantidad);
        this.inicio = null;
        this.fin = null;
    }

    public Inventario_LDC() {
    }

    public boolean vacio() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }



    public int codRandom() {
        int i = (int) (Math.random() * 9000) + 1000;
        return i;
    }

  

    public void codigoProducto(Articulos nu) {
        String codigo = "";
        int longitud = 2;
        for (int i = 0; i < longitud; i++) {
            codigo = codigo + (char) (Math.random() * (122 - 97 + 1) + 97);
        }
        System.out.println(codigo);
        Nodo_LDC aux = inicio;

        while (existeCod(codigo) && aux == null) {
            System.out.println("Dato actual " + aux.getDatos().getCoPro());
            if (codigo.equals(aux.getDatos().getCoPro())) {
                codigoProducto(nu);
            } else {
                aux = aux.getSiguiente();
            }

        }
        nu.setCoPro(codigo);

    }

    public void agregarPueba(Articulos nu) {
        codigoProducto(nu);

        Nodo_LDC nuevo = new Nodo_LDC();
        nuevo.setDatos(nu);

        if (vacio()) {
            inicio = nuevo;
            System.out.println("paso 1");

            fin = nuevo;
            fin.setSiguiente(inicio);
            inicio.setAnterior(fin);
        } else if (nu.getCoPro().compareToIgnoreCase(inicio.getDatos().getCoPro()) < 0) {
            System.out.println("paso 2");
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
            fin.setSiguiente(inicio);
            inicio.setAnterior(fin);
        } else if (nu.getCoPro().compareToIgnoreCase(fin.getDatos().getCoPro()) > 0) {
            System.out.println("paso 3");
            fin.setSiguiente(inicio);
            fin = fin.getSiguiente();
            fin.setSiguiente(inicio);
            inicio.setAnterior(fin);
        } else {
            Nodo_LDC aux = inicio;
            while (aux.getSiguiente().getDatos().getCoPro().compareToIgnoreCase(nu.getCoPro()) < 0) {
                aux = aux.getSiguiente();
            }
            nuevo.setSiguiente(aux.getSiguiente());
            nuevo.setAnterior(aux);
            aux.setSiguiente(nuevo);
            nuevo.getSiguiente().setAnterior(nuevo);
        }
        System.out.println("sale");
    }

    public boolean existeCod(String s) {
        Nodo_LDC aux = inicio;
        boolean existe = false;
        while (aux == null && existe == true) {
            System.out.println("cod " + aux.getDatos().getCoPro());
            if (s.equals(aux.getDatos().getCoPro())) {
                existe = true;
            } else {
                aux = aux.getSiguiente();
            }
        }
        return existe;
    }

    public String mostrarElementos() {
        String s = "";
        Nodo_LDC aux = inicio;
        s += aux.getDatos().getCoPro() + " <=> ";
        aux = aux.getSiguiente();

        while (aux != inicio) {
            s += aux.getDatos().getCoPro()+  " <=> ";
            aux = aux.getSiguiente();
        }
        //JOptionPane.showMessageDialog(null,"La lista contiene\n"+s);
        System.out.println(s);
        return s;
    }
}
