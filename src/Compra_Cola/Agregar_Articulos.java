package Compra_Cola;

import AgregarArticulos_ListaSimple.*;
import Articulos_Venta.Articulo;

import java.sql.*;
import java.util.Random;
import javax.swing.JOptionPane;

public class Agregar_Articulos {

    private Nodo inicio;
    private Nodo fin;

    public Agregar_Articulos() {
        this.inicio = null;
        this.fin = null;
    }

    //Valida si la lista esta vacia paso1
    public boolean vacio() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    //valida si el dato tiene un codigo existente en la lista
    public boolean existeCod(String s) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.getDatos().getCodigo().equals(s)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    //Agrega un codigo Alfa
    public void codigoProducto(Articulo nu) {
        Random rand = new Random();
        String codigo = "";
        for (int i = 0; i < 2; i++) {
            int n = rand.nextInt(26) + 65;
            codigo += (char) n;
        }
        boolean codigoUnico = false;
        while (!codigoUnico) {
            codigoUnico = true;
            Nodo actual = inicio;
            while (actual != null) {
                if (existeCod(codigo) || (actual.getDatos().getCodigo().equals(codigo))) {
                    codigoUnico = false;
                    codigo = "";
                    for (int i = 0; i < 2; i++) {
                        int n = rand.nextInt(26) + 65;
                        codigo += (char) n;
                    }
                    break;
                }
                actual = actual.getSiguiente();
            }
        }
        nu.setCodigo(codigo);
    }

    //Metodos que verifican codigo y nombre en la base de datos
    public boolean consultaNombreBd(String nombre) {
        boolean existe = false;
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario?autoReconnect=true&useSSL=false", "root", "");
            PreparedStatement mist = conn.prepareStatement("Select * from articulo where nombre=?");
            mist.setString(1, nombre);
            ResultSet mirs = mist.executeQuery();
            while (mirs.next()) {
                existe = true;
                System.out.println("Producto existente");
                System.out.println(mirs.getString("Codigo") + " " + mirs.getString("Nombre") + " " + mirs.getString("Unidades") + " " + mirs.getString("Precio"));

            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("No se conecta con la base de datos " + ex.getMessage());
        }
        return existe;

    }

    public boolean consultaCodigoBd(String codigo) {
        boolean existe = false;
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario?autoReconnect=true&useSSL=false", "root", "");
            PreparedStatement mist = conn.prepareStatement("Select * from articulo where codigo=?");
            mist.setString(1, codigo);
            ResultSet mirs = mist.executeQuery();
            while (mirs.next()) {
                existe = true;
                System.out.println("Producto existente");
                System.out.println(mirs.getString("Codigo") + " " + mirs.getString("Nombre") + " " + mirs.getString("Unidades") + " " + mirs.getString("Precio"));

            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("No se conecta con la base de datos " + ex.getMessage());
        }
        return existe;

    }

    //Metodo para agregar a BD
    public void AgregarBaseD() {

        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario?autoReconnect=true&useSSL=false", "root", "");
            Statement mist = conn.createStatement();
            Nodo actual = inicio;
            try {
                while (actual != null) {
                    String instructionSQL = "INSERT INTO Articulo (Codigo, Nombre, Descripcion, Unidades, Precio) values ('" + actual.getDatos().getCodigo() + "', '"
                            + actual.getDatos().getNombre() + "', '"
                            + actual.getDatos().getDescripcion() + "',"
                            + actual.getDatos().getUnidades() + ", "
                            + actual.getDatos().getPrecio() + ")";
                    mist.executeUpdate(instructionSQL);
                    actual = actual.getSiguiente();
                }
            } catch (NullPointerException e) {
                System.out.println("Datos cargados");
            }

            conn.close();

        } catch (SQLException ex) {
            System.out.println("No inserta" + ex.getMessage());
        }
    }

    //metodo para agregar datos a la lista
    public void agregarDatos(Articulo n) {
        codigoProducto(n);
        Nodo nuevo = new Nodo();
        nuevo.setDatos(n);
        if (!consultaNombreBd(n.getNombre()) || (encuentra(n.getNombre()))) {
            if (vacio()) {
                inicio = nuevo;
                //System.out.println("inicio>"+inicio.getDato().getNombre());
                fin = nuevo;
                // System.out.println("fin>"+fin.getDato().getNombre());
            } else {
                // System.out.println("inicio>"+inicio.getDato().getNombre());
                fin.setSiguiente(nuevo);
                fin = nuevo;
                // System.out.println("fin>"+fin.getDato().getNombre());

            }
        } else {
            System.out.println("Datos existentes");
        }

    }

    //Metodo para agregar inicio
    public void extraeInicio() {
        if (!vacio()) {
            inicio = inicio.getSiguiente();
        } else {
            System.out.println("Lista vacia");
        }

    }

    //metodo para mostar elementos
    public String mostrarElementos() {
        String elementos = "";
        Nodo aux = inicio;
        while (aux != null) {
            elementos += "Und: " + aux.getDatos().getUnidades()
                    + "| Cod: " + aux.getDatos().getCodigo()
                    + "| Articulo:" + aux.getDatos().getNombre()
                    + "| Precio: $" + aux.getDatos().getPrecio()
                    + "| Descripcion: " + aux.getDatos().getDescripcion() + "\n";
            aux = aux.getSiguiente();
        }
        System.out.println(elementos);
        return elementos;

    }

    //Metodo para extraer es espesifico
    public void extraeEspesifico(String nombre) {
        Nodo inicioAux = null;
        Nodo finAux = null;
        if (encuentra(nombre)) {
            while (!nombre.equals(inicio.getDatos().getNombre())) {
                Nodo nuevo = new Nodo();
                nuevo.setDatos(inicio.getDatos());
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
                Nodo nuevo = new Nodo();
                nuevo.setDatos(inicio.getDatos());
                finAux.setSiguiente(nuevo);
                finAux = nuevo;
                inicio = inicio.getSiguiente();
            }
            while (inicioAux != null) {

                agregarDatos(inicioAux.getDatos());
                inicioAux = inicioAux.getSiguiente();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Datos no encontrados");
        }

    }

    //metodo para encontrar articiculo por string
    public boolean encuentra(String s) {
        Nodo aux = inicio;
        boolean existe = false;
        while (existe != true && aux != null) {
            if (s.equals(aux.getDatos().getNombre())) {
                existe = true;
            } else {
                aux = aux.getSiguiente();
            }
        }
        return existe;
    }

    //Modifica dato buscado
    public void modificar(String nombre) {

        if (encuentra(nombre)) {

            String nuevoNombre = JOptionPane.showInputDialog("Ingrese nombre");
            Double nuevoPRecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio"));
            String nuevaDEs = JOptionPane.showInputDialog("Ingrese nueva descipcion");
            if (inicio.getDatos().getNombre().equals(nombre)) {
                inicio.getDatos().setNombre(nuevoNombre);
                inicio.getDatos().setDescripcion(nuevaDEs);
                inicio.getDatos().setPrecio(nuevoPRecio);
            } else {
                Nodo aux = inicio;
                while ((aux != null)) {

                    if (aux.getDatos().getNombre().equals(nombre)) {

                        aux.getDatos().setNombre(nuevoNombre);
                        aux.getDatos().setDescripcion(nuevaDEs);
                        aux.getDatos().setPrecio(nuevoPRecio);
                    }
                    aux = aux.getSiguiente();

                }
                System.out.println("Elemento modificado");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Datos no encontrados");
        }

    }
}
