package modelo;

import java.time.LocalDateTime;

public class Compra {
    private int num_compra;
    private LocalDateTime fecha; //la idea con fecha es usarla solo al recibir datos para mostrarlos no para cargarlos
    private int cod_cliente;

    public Compra() {
    }

    public Compra(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }      
    
    public Compra(int num_compra, int cod_cliente) {
        this.num_compra = num_compra;
        this.cod_cliente = cod_cliente;
    }

    public Compra(int num_compra, LocalDateTime fecha, int cod_cliente) {
        this.num_compra = num_compra;
        this.fecha = fecha;
        this.cod_cliente = cod_cliente;
    }

    public int getNum_compra() {
        return num_compra;
    }

    public void setNum_compra(int num_compra) {
        this.num_compra = num_compra;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    @Override
    public String toString() {
        return "Compra{" + "num_compra=" + num_compra + ", fecha=" + fecha + ", cod_cliente=" + cod_cliente + '}';
    }

    
    
}