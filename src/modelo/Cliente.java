package modelo;

public class Cliente {
    private int id;
    private String nombre;
    private long cuil;
    private long telefono;
    private String direccion;
    private String correo;

    public Cliente() {
    }   

    public Cliente(int id, String nombre, long cuil) {
        this.id = id;
        this.nombre = nombre;
        this.cuil = cuil;
    }    

    public Cliente(int id, String nombre, long cuil, long telefono, String direccion, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.cuil = cuil;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }         

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCuil() {
        return cuil;
    }

    public void setCuil(long cuil) {
        this.cuil = cuil;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }     

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", cuil=" + cuil + ", telefono=" + telefono + ", direccion=" + direccion + ", correo=" + correo + '}';
    }
    
    
    
}