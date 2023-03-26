package AgregarInventario;

import java.io.*;
import Proyecto_EstructuraDeDatos.*;
import javax.swing.JOptionPane;

public class Archivo_Cola extends Articulos {

    public Archivo_Cola(String nombre, String descripcion, double precio, int cantidad) {
        super(nombre, descripcion, precio, cantidad);
    }

    public Archivo_Cola() {
    }
    
    
    public void agregar(Articulos datos) {
        try {
            DataOutputStream d = new DataOutputStream(new FileOutputStream("factura.dat"));
            d.writeUTF("Nombre"+datos.getNombre());
            d.writeUTF(datos.getDescripcion());
            d.writeUTF(Double.toString(datos.getPrecio()));
            d.write(datos.getCantidad());
            System.out.println("Datos guardados");
            d.close();
        } catch (Exception e) {
            System.out.println("error al agregar datos");
        }
    }
    public static void intercambiar() {
        String arti, descri;
        int cantidad;
        double precio;
        try {
            DataInputStream i = new DataInputStream(new FileInputStream("auxiliar.dat"));
            DataOutputStream o = new DataOutputStream(new FileOutputStream("clientes.dat"));

            try {
                while (true) {
                    arti = i.readUTF();
                    descri = i.readUTF();
                    cantidad = i.readInt();
                    precio = i.readDouble();
                    
                    o.writeUTF(arti);
                    o.writeUTF(descri);
                    o.writeInt(cantidad);
                    o.writeDouble(precio);
                    
                }
            } catch (EOFException e) {
                o.close();
                i.close();
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo...");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error con el dispositivo...");
        }
    }
    

     public void editar(String articulo,String descripcion,double precio,int cantidad) {
        
        String arti;
        //int canti;
        //double prei;
        //boolean proximo = true;
        
        arti = articulo;
        try {
            DataInputStream i = new DataInputStream(new FileInputStream("clientes.dat"));
            DataOutputStream o = new DataOutputStream(new FileOutputStream("auxiliar.dat"));

            try {
                while (true) {
                    articulo = i.readUTF();
                    descripcion = i.readUTF();
                    cantidad = i.readInt();
                    precio = i.readDouble();
                    if (arti.equals(articulo)) {

                        articulo=articulo;
                        descripcion=descripcion;
                        precio=precio;
                        cantidad= cantidad;
                    }
                    o.writeUTF(articulo);
                    o.writeUTF(descripcion);
                    o.writeInt(cantidad);
                    o.writeDouble(precio);
                }
            } catch (EOFException e) {
                o.close();
                i.close();
                intercambiar();
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error con el dispositivo!");
        }
    }
     public static void eliminar() {
        String cedu, ced, prov, nom, dir, tel, ema;
        boolean proximo = true;
        int ed;
        cedu = JOptionPane.showInputDialog("Digite la cédula:");
        try {
            DataInputStream i = new DataInputStream(new FileInputStream("clientes.dat"));
            DataOutputStream o = new DataOutputStream(new FileOutputStream("auxiliar.dat"));

            try {
                while (true) {
                    ced = i.readUTF();
                    nom = i.readUTF();
                    ed = i.readInt();
                    dir = i.readUTF();
                    tel = i.readUTF();
                    ema = i.readUTF();
                    prov = i.readUTF();
                    if (!cedu.equals(ced)) {

                        o.writeUTF(ced);
                        o.writeUTF(nom);
                        o.writeInt(ed);
                        o.writeUTF(dir);
                        o.writeUTF(tel);
                        o.writeUTF(ema);
                        o.writeUTF(prov);
                        proximo = false;
                    }

                }
            } catch (EOFException e) {
                o.close();
                i.close();
                intercambiar();
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro el archivo!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error con el dispositivo!");
        }
    }
}
