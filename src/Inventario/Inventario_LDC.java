/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventario;

import Proyecto_EstructuraDeDatos.Articulos;
import javax.swing.JOptionPane;

/**
 *
 * @author Ignac
 */
public class Inventario_LDC extends Articulos {

    private Nodo_LDC inicio;
    private Nodo_LDC fin;

    public Inventario_LDC(String nombre, String descripcion, double precio, int cantidad) {
        super(nombre, descripcion, precio, cantidad);
        this.inicio = null;
        this.fin = null;
    }
    
    public Inventario_LDC(){}

    public boolean vacio() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    public void agregar(Articulos nu) {

        //Articulos articuloNuevo = new Articulos(nombre, descripcion, 0, 0);

        //articuloNuevo.setNombre(nombre);
        //articuloNuevo.setDescripcion(descripcion);
        //articuloNuevo.setPrecio(precio);
        //articuloNuevo.setCantidad(cantidad);

        Nodo_LDC nuevo = new Nodo_LDC();
        nuevo.setDatos(nu);

        if (vacio()) {
            inicio = nuevo;
            
            fin = nuevo;
            fin.setSiguiente(inicio);
            inicio.setAnterior(fin);
        } else if(nu.getPrecio()<inicio.getDatos().getPrecio()){
            nuevo.setSiguiente(inicio);
            inicio=nuevo;
            fin.setSiguiente(inicio);
            inicio.setAnterior(fin);
        }else if(nu.getPrecio()>fin.getDatos().getPrecio()){
            fin.setSiguiente(inicio);
            fin=fin.getSiguiente();
            fin.setSiguiente(inicio);
            inicio.setAnterior(fin);
        }else{
            Nodo_LDC aux = inicio;
            while(aux.getSiguiente().getDatos().getPrecio()<nu.getPrecio()){
                aux=aux.getSiguiente();
            }
            nuevo.setSiguiente(aux.getSiguiente());
            nuevo.setAnterior(aux);
            aux.setSiguiente(nuevo);
            nuevo.getSiguiente().setAnterior(nuevo);
        }
    }
    
    public String toString(){
        String s ="";
        if(!vacio()){
            Nodo_LDC aux = inicio;
            s+="\t"+aux.getDatos().getNombre()+"-"+aux.getDatos().getDescripcion()+"-"+aux.getDatos().getPrecio()+"-"+aux.getDatos().getCantidad();
            aux=aux.getSiguiente();
            while (aux!=inicio) {                
                s+=aux.getDatos().getNombre()+aux.getDatos().getDescripcion()+aux.getDatos().getPrecio()+aux.getDatos().getCantidad();
                aux= aux.getSiguiente();
            }
            JOptionPane.showMessageDialog(null, s);
        }
        
        return s;
    }

}
