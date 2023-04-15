package Inventario_ListaDoble;

import Articulos_Venta.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Inventario extends Nodo_prueba{

    private Nodo_prueba inicio;
    private Nodo_prueba fin;

    public Inventario() {
        this.inicio = null;
        this.fin = null;
    }

    //verifica la lista en null
    public boolean vacio() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    //extrae los datos de MySql  los guarda en una lista
    public void extraeBaseDatos() {
        try (Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario?autoReconnect=true&useSSL=false", "root", "")) {
            String consulta = "SELECT Codigo, Nombre, Descripcion, Unidades, Precio FROM articulo ORDER BY Codigo"; //
            try (Statement statement = conexion.createStatement();
                    ResultSet resultSet = statement.executeQuery(consulta)) {

                while (resultSet.next()) {
                    Articulo producto = new Articulo();
                    producto.setCodigo(resultSet.getString("codigo"));
                    producto.setNombre(resultSet.getString("nombre"));
                    producto.setDescripcion(resultSet.getString("descripcion"));
                    producto.setUnidades(resultSet.getInt("unidades"));
                    producto.setPrecio(resultSet.getDouble("precio"));

                    Nodo_prueba nuevo = new Nodo_prueba();
                    nuevo.setDato(producto);
                    agregarLista(producto);
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    //crea un lista doble circular
    public void agregarLista(Articulo a) {
        Nodo_prueba nuevo = new Nodo_prueba();
        nuevo.setDato(a);
        // Si la lista está vacía, agregar al siguiente
        if (inicio == null) {
            System.out.println("Agregando Principio de la lista (vacia) o si (lista==[!=0]) ");
            inicio = nuevo;
            fin = nuevo;
            System.out.println(a.getNombre());
        } else if (inicio != null) {
            if (a.getNombre().compareTo(inicio.getDato().getNombre()) < 0
                    || a.getNombre().compareTo(fin.getDato().getNombre()) == 0) {
                System.out.println("Agregando al Principio de la lista");
                inicio.setAnterior(nuevo);
                nuevo.setSiguiente(inicio);
                inicio = nuevo;
                System.out.println(a.getCodigo());
            } // Si el nuevo nodo es mayor que el último nodo, agregar al anterioral
            else if (a.getNombre().compareTo(fin.getDato().getNombre()) > 0) {
                System.out.println("Agregando al Final de la lista");
                fin.setSiguiente(nuevo);
                nuevo.setAnterior(fin);
                fin = nuevo;
                System.out.println(a.getNombre());
            } // Si el nuevo nodo está en algún lugar intermedio de la lista
            else {
                Nodo_prueba temp = inicio.getSiguiente();
                System.out.println("Agregando en algun lugar intermedio de la lista");
                while (temp != null) {
                    if (a.getNombre().compareTo(temp.getDato().getNombre()) < 0) {
                        nuevo.setSiguiente(temp);
                        nuevo.setAnterior(temp.getAnterior());
                        temp.getAnterior().setSiguiente(nuevo);
                        temp.setAnterior(nuevo);
                        break;
                    }
                    temp = temp.getSiguiente();
                }
                System.out.println(a.getNombre());
            }
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
    //crea una tabla 
    public DefaultTableModel cargarTabla() {
        extraeBaseDatos();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Unidades");
        modelo.addColumn("Precio");
        
        Nodo_prueba aux = inicio;
        while (aux!=null) {
            Object[] fila = new Object[5];
            fila[0]=aux.getDato().getCodigo();
            fila[1]=aux.getDato().getNombre();
            fila[2]=aux.getDato().getDescripcion();
            fila[3]=aux.getDato().getUnidades();
            fila[4]=aux.getDato().getPrecio();
            modelo.addRow(fila);
            aux= aux.getSiguiente();             
        }
       
        return modelo;
        
    }

    public void mostrarDatos() {
        String s = "";
        Nodo_prueba aux = inicio;

        while (aux != null) {
            s += ">" + aux.getDato().getNombre();
            aux = aux.getSiguiente();
        }
        System.out.println(s);
    }

}
