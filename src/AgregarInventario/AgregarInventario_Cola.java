
package AgregarInventario;
import Proyecto_EstructuraDeDatos.*;

import javax.swing.JOptionPane;

/**
 *
 * @author Ignac
 */
public class AgregarInventario_Cola extends Articulos{

    private Nodo_Cola inicio;
    private Nodo_Cola fin;

    public AgregarInventario_Cola (String nombre, String descripcion, double precio, int cantidad){
        super(nombre, descripcion, precio, cantidad);
        this.inicio = null;
        this.fin = null;
    }

    public AgregarInventario_Cola() {
    }

    public boolean vacio() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    public void agregar(String nombre, String descripcion) {

        Articulos articuloNuevo = new Articulos(nombre, descripcion, 0, 0);

        articuloNuevo.setNombre(nombre);
        articuloNuevo.setDescripcion(descripcion);

        Nodo_Cola nuevo = new Nodo_Cola();
        nuevo.setDato(articuloNuevo);

        if (vacio()) {
            inicio = nuevo;
            System.out.println("Inicio " + inicio.getDato().getNombre());
            fin = nuevo;
            System.out.println("Fin " + fin.getDato().getNombre());
        } else {
            fin.setSiguiente(nuevo);
            System.out.println("Inicio" + inicio.getDato().getNombre());
            fin = nuevo;
            System.out.println("Fin " + fin.getDato().getNombre());
        }
    }

    public boolean encuentra(String s) {
        Nodo_Cola aux = inicio;
        boolean existe = false;
        while (existe != true && aux != null) {
            if (s.equals(aux.getDato().getNombre())) {
                existe = true;
            } else {
                aux = aux.getSiguiente();
            }
        }
        return existe;
    }

    public void extraeEspesifico(String nombre) {
        Nodo_Cola inicioAux = null;
        Nodo_Cola finAux = null;
        if (encuentra(nombre)) {
            while (!nombre.equals(inicio.getDato().getNombre())) {
                Nodo_Cola nuevo = new Nodo_Cola();
                nuevo.setDato(inicio.getDato());
                if (inicioAux == null) {
                    inicioAux = nuevo;
                    finAux = nuevo;
                } else {
                    finAux.setSiguiente(nuevo);
                    finAux = nuevo;
                }
                inicio = inicio.getSiguiente();
            }
            if (!vacio()) {
                inicio = inicio.getSiguiente();
            }
            while (inicio != null) {
                Nodo_Cola nuevo = new Nodo_Cola();
                nuevo.setDato(inicio.getDato());
                finAux.setSiguiente(nuevo);
                finAux = nuevo;
                inicio = inicio.getSiguiente();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Datos no encontrados");
        }

    }

    @Override
    public String toString() {
        String s = "";
        if (!vacio()) {
            Nodo_Cola aux = inicio;
            while (aux != null) {
                s += "Articulo:" + aux.getDato().getNombre() + "\n";
                aux = aux.getSiguiente();
            }
            JOptionPane.showMessageDialog(null, s);
        } else {
            JOptionPane.showMessageDialog(null, "No hay articulos");
        }
        return s;

    }

}
