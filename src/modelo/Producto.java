package modelo;

public class Producto {
    private int codigo;
    private String descripcion;
    private int cantidad;
    private int precio;
    private boolean activo;

    public Producto() {
    }    
    
    public Producto(int codigo, String descripcion, int cantidad, int precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        //el constructor no incluye la variable "activo" por que en la base de datos se le da el valor por defecto 1
    }

    public Producto(int codigo, String descripcion, int cantidad, int precio, boolean activo) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.activo = activo;
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", cantidad=" + cantidad + ", precio=" + precio + '}';
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    

    
      
    
    
    
}