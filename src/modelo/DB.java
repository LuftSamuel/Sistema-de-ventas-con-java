package modelo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

//faltan por hacer los metodos modificar y eliminar
public class DB {

    String user = "root";
    String pass = "";

    public String obtenerDirectorio() {
        //funcion que obtiene la ruta para guardar los pdf almacenada en la base de datos       
        String directorio = "";
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("SELECT directorio FROM ruta");
            ResultSet rs = sql.executeQuery();
            //con eso pregunto si esta vacio
            if (!rs.isBeforeFirst()) {
                return ".";  //ese directorio hace referencia a la carpeta del proyecto              
            }
            while (rs.next()) { //cambiar por if
                directorio = rs.getString("directorio");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Modelo-DB-obtenerDirectorio)");

        }
        return directorio;
    }

    public void cambiarDirectorio(String directorio) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);

            if (obtenerDirectorio() == ".") { //"." era el resultado si no habia ningun registro
                PreparedStatement sql = con.prepareStatement("INSERT INTO ruta (directorio) VALUES (?)");
                sql.setString(1, directorio);
                sql.execute();
            } else {
                PreparedStatement sql = con.prepareStatement("UPDATE ruta SET directorio = ?");
                sql.setString(1, directorio);
                sql.execute();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Modelo-DB-cambiarDirectorio");
        }
    }

    public void agregar(Cliente c) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("INSERT INTO cliente (nombre, cuil, telefono, direccion, correo) VALUES (?, ?, ?, ?, ?)");
            //por lo visto estos parametros de sql comienzan de 1 no de 0 y corresponden a los signos
            // de pregunta que puse arriba

            if (c.getNombre() != null) { //que habre echo aca?
                sql.setString(1, c.getNombre());
            } else {
                sql.setString(1, c.getNombre());
            }
            sql.setLong(2, c.getCuil());
            sql.setLong(3, c.getTelefono());
            if (c.getDireccion() != null) { //que habre echo aca?
                sql.setString(4, c.getDireccion());
            } else {
                sql.setString(4, c.getDireccion());
            }
            sql.setString(5, c.getCorreo());
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Modelo-DB-agregar(Cliente c)");
        }
    }

    public ArrayList<Cliente> obtenerClientes() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("SELECT * FROM cliente");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente(rs.getInt("codigo"), rs.getString("nombre"), rs.getLong("cuil"), rs.getLong("telefono"), rs.getString("direccion"), rs.getString("correo"));
                listaClientes.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Modelo-DB-obtener(Cliente c)");

        }
        return listaClientes;
    }

    public void eliminar(Cliente c) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("DELETE FROM cliente WHERE codigo = ?");
            sql.setInt(1, c.getId());
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Imposible borrar un cliente con compras.\nPrimero se deben borrar las compras");
        }
    }

    public void modificar(Cliente c) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("UPDATE cliente SET telefono=?, direccion=?, correo=? WHERE codigo = ?");
            sql.setLong(1, c.getTelefono());
            if (c.getDireccion() != null) { //que habre echo aca?
                sql.setString(2, c.getDireccion());
            } else {
                sql.setString(2, c.getDireccion());
            }
            sql.setString(3, c.getCorreo());
            sql.setInt(4, c.getId());
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void agregar(Producto p) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("INSERT INTO producto (descripcion, cantidad, precio) VALUES (?, ?, ?)");
            sql.setString(1, p.getDescripcion());
            sql.setInt(2, p.getCantidad());
            sql.setInt(3, p.getPrecio());
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("SELECT * FROM producto");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Producto p = new Producto(rs.getInt("codigo"), rs.getString("descripcion"), rs.getInt("cantidad"), rs.getInt("precio"), rs.getBoolean("activo"));
                listaProductos.add(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaProductos;
    }

    public void eliminar(Producto p) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("DELETE FROM producto WHERE codigo = ?");
            sql.setInt(1, p.getCodigo());;
            sql.execute();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "No se puede borrar el articulo por que hay ventas que lo contienen");
        }
    }

    public void modificar(Producto p) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("UPDATE producto SET cantidad=?, precio=? WHERE codigo = ? ");
            sql.setInt(1, p.getCantidad());
            sql.setInt(2, p.getPrecio());
            sql.setInt(3, p.getCodigo());
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void activarDesactivar(Producto p) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("UPDATE producto SET activo = ? WHERE codigo = ? ");
            sql.setBoolean(1, p.isActivo());
            sql.setInt(2, p.getCodigo());            
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void restar(Producto p) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("UPDATE producto SET cantidad = cantidad - ? WHERE codigo = ? ");
            sql.setInt(1, p.getCantidad());
            sql.setInt(2, p.getCodigo());
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Funcion restar");
        }
    }

    public void agregar(Compra co) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("INSERT INTO compra (cod_cliente) VALUES (?)");
            sql.setInt(1, co.getCod_cliente());
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Compra> obtenerCompras() {
        ArrayList<Compra> listaCompras = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("SELECT * FROM compra");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                Compra c = new Compra(rs.getInt("num_compra"), rs.getTimestamp(2).toLocalDateTime(), rs.getInt("cod_cliente"));
                listaCompras.add(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaCompras;
    }

    public int obtenerUltimaCompra() {
        int ultimaCompra = 0; //no me gusta iniciar en 0
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("SELECT num_compra FROM compra ORDER BY num_compra DESC LIMIT 1");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                ultimaCompra = rs.getInt("num_compra");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ultimaCompra;
    }

    public void eliminar(Compra co) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("DELETE FROM compra WHERE num_compora = ?");
            sql.setInt(1, co.getNum_compra());
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //no creo que modificar compra sirva ni se pueda hacer
    public void agregar(DetalleCompra d) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("INSERT INTO detalle_compra (num_compra, cod_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)");
            sql.setInt(1, d.getNum_compra());
            sql.setInt(2, d.getCod_producto());
            sql.setInt(3, d.getCantidad());
            sql.setInt(4, d.getPrecio_unitario());
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<DetalleCompra> obtenerDetalles(int num_compra) {
        ArrayList<DetalleCompra> listaDetalles = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("SELECT * FROM detalle_compra WHERE num_compra = ?");
            sql.setInt(1, num_compra);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                DetalleCompra dc = new DetalleCompra(rs.getInt("num_compra"), rs.getInt("cod_producto"), rs.getInt("cantidad"), rs.getInt("precio_unitario"));
                listaDetalles.add(dc);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaDetalles;
    }

    public void eliminar(DetalleCompra dc) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("DELETE FROM detalle_compra WHERE num_compra = ? AND cod_producto = ?");
            sql.setInt(1, dc.getNum_compra());
            sql.setInt(2, dc.getCod_producto());
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificar(DetalleCompra dc) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ventasonline", user, pass);
            PreparedStatement sql = con.prepareStatement("UPDATE detalle_compra SET cantidad = ?, precio_unitario = ? WHERE num_compra = ? AND cod_producto = ?");
            sql.setInt(1, dc.getCantidad());
            sql.setInt(2, dc.getPrecio_unitario());
            sql.setInt(3, dc.getNum_compra());
            sql.setInt(4, dc.getCod_producto());
            sql.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
