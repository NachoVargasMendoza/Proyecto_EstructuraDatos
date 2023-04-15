package Articulos_Venta;


public class Articulo {
    private String codigo;
    private String nombre;
    private String descripcion;
    private int unidades;
    private double precio;
    
    public Articulo (String codigo, String nombre, String descripcion, int unidades,double precio){
        this.codigo=codigo;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.unidades=unidades;
        this.precio=precio;
    }
    public Articulo(){}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
            
    
}
