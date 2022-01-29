package modelo;

public class DetalleCompra {
    private int num_compra;
    private int cod_producto;
    private int cantidad;
    private int precio_unitario;

    public DetalleCompra() {
    }    

    public DetalleCompra(int num_compra, int cod_producto, int cantidad, int precio_unitario) {
        this.num_compra = num_compra;
        this.cod_producto = cod_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public int getNum_compra() {
        return num_compra;
    }

    public void setNum_compra(int num_compra) {
        this.num_compra = num_compra;
    }

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(int precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" + "num_compra=" + num_compra + ", cod_producto=" + cod_producto + ", cantidad=" + cantidad + ", precio_unitario=" + precio_unitario + '}';
    }
    
    
    
    
}