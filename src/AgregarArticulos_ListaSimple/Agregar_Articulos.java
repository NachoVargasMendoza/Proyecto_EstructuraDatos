package AgregarArticulos_ListaSimple;

import Articulos_Venta.*;

import java.sql.*;
import java.util.Random;
import javax.swing.JOptionPane;

public class Agregar_Articulos extends Nodo_prueba {

    private Nodo_prueba inicio;

    public Agregar_Articulos() {
        super();
        this.inicio = null;

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
        Nodo_prueba actual = inicio;
        while (actual != null) {
            if (actual.getDato().getCodigo().equals(s)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    //Agrega un codigo Alfa
    public void codigoProducto(Articulo nu) {
        String codigo = generarCodigoUnico();
        nu.setCodigo(codigo);
    }

    private String generarCodigoUnico() {
        Random rand = new Random();
        String codigo = "";
        for (int i = 0; i < 2; i++) {
            int n = rand.nextInt(26) + 65;
            codigo += (char) n;
        }
        if (existeCod(codigo) || codigoYaUsado(codigo)) {
            codigo = generarCodigoUnico();
        }
        return codigo;
    }

    private boolean codigoYaUsado(String codigo) {
        Nodo_prueba actual = inicio;
        while (actual != null) {
            if (actual.getDato().getCodigo().equals(codigo)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
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
            Nodo_prueba actual = inicio;
            try {
                while (actual != null) {
                    String instructionSQL = "INSERT INTO Articulo (Codigo, Nombre, Descripcion, Unidades, Precio) values ('" + actual.getDato().getCodigo() + "', '"
                            + actual.getDato().getNombre() + "', '"
                            + actual.getDato().getDescripcion() + "',"
                            + actual.getDato().getUnidades() + ", "
                            + actual.getDato().getPrecio() + ")";
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
        Nodo_prueba nuevo = new Nodo_prueba();
        nuevo.setDato(n);
        if (!consultaNombreBd(n.getNombre()) || (encuentra(n.getNombre()))) {
            if (vacio()) {
                inicio = nuevo;
                //System.out.println("inicio>"+inicio.getDato().getNombre());

            } else if (n.getCodigo().compareTo(inicio.getDato().getCodigo()) < 0) {
                nuevo.setSiguiente(inicio);
                inicio = nuevo;

            } else if (inicio.getSiguiente() == null) {
                inicio.setSiguiente(nuevo);

            } else {
                Nodo_prueba aux = inicio;
                while (aux.getSiguiente() != null && aux.getSiguiente().getDato().getCodigo().compareTo(n.getCodigo()) < 0) // System.out.println("inicio>"+inicio.getDato().getNombre());
                {
                    aux = aux.getSiguiente();
                }
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);
                // System.out.println("fin>"+fin.getDato().getNombre());

            }
        } else {
            System.out.println("Datos existentes");
        }

    }

    //Metodo para extraer inicio
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
        Nodo_prueba aux = inicio;
        while (aux != null) {
            elementos += "Und: " + aux.getDato().getUnidades()
                    + "| Cod: " + aux.getDato().getCodigo()
                    + "| Articulo:" + aux.getDato().getNombre()
                    + "| Precio: $" + aux.getDato().getPrecio()
                    + "| Descripcion: " + aux.getDato().getDescripcion() + "\n";
            aux = aux.getSiguiente();
        }
        System.out.println(elementos);
        return elementos;

    }

    //Metodo para extraer es espesifico
    public void extraeEspesifico(String nombre) {

        if (encuentra(nombre)) {
            if (inicio.getDato().getNombre().equals(nombre)) {
                inicio = inicio.getSiguiente();

                JOptionPane.showMessageDialog(null, "Elemento extraído");
            } else {
                Nodo_prueba anterior;
                Nodo_prueba aux;
                anterior = inicio;
                aux = inicio.getSiguiente();
                while ((aux != inicio) && (!aux.getDato().getNombre().equals(nombre))) {
                    anterior = anterior.getSiguiente();
                    aux = aux.getSiguiente();
                }
                if (aux != inicio) {
                    anterior.setSiguiente(aux.getSiguiente());

                    JOptionPane.showMessageDialog(null, "Elemento extraído");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Datos no encontrados");
        }

    }

    //metodo para encontrar articiculo por string
    public boolean encuentra(String s) {
        Nodo_prueba aux = inicio;
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

    //Modifica dato buscado
    public void modificar(String nombre) {

        if (encuentra(nombre)) {

            String nuevoNombre = JOptionPane.showInputDialog("Ingrese nombre");
            Double nuevoPRecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese precio"));
            String nuevaDEs = JOptionPane.showInputDialog("Ingrese nueva descipcion");
            if (inicio.getDato().getNombre().equals(nombre)) {
                inicio.getDato().setNombre(nuevoNombre);
                inicio.getDato().setDescripcion(nuevaDEs);
                inicio.getDato().setPrecio(nuevoPRecio);
            } else {
                Nodo_prueba aux = inicio;
                while ((aux != null)) {

                    if (aux.getDato().getNombre().equals(nombre)) {

                        aux.getDato().setNombre(nuevoNombre);
                        aux.getDato().setDescripcion(nuevaDEs);
                        aux.getDato().setPrecio(nuevoPRecio);
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
