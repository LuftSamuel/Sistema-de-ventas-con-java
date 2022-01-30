package controlador;

import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import modelo.Cliente;
import modelo.Compra;
import modelo.DB;
import modelo.DetalleCompra;
import modelo.Producto;
import vista.VistaCliente;
import vista.VistaMaestroDetalle;
import vista.VistaNuevoDetalle;
import vista.VistaProducto;

public class ControladorNuevoDetalle {

    public static void IniciarTablaDetalles(JTable t) {
        //usado en el init del formulario para crear los campos y para reiniciar la tabla despues de una venta
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();
        modelo.setColumnCount(0);
        modelo.setNumRows(0);
        modelo.addColumn("Codigo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio unit");
        modelo.addColumn("Precio total");
    }

    public static void ActualizarTablaProductos(JTable t) {
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();
        //modelo.setColumnCount(0);
        modelo.setNumRows(0);
        /*modelo.addColumn("Codigo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio unit");*/
        DB db = new DB();
        ArrayList<Producto> pl = db.obtenerProductos();
        for (Producto p : pl) {
            //Condicion para que no se carguen los productos inactivos
            if (p.isActivo()) {
                Object[] fila = new Object[4];
                fila[0] = p.getCodigo();
                fila[1] = p.getDescripcion();
                fila[2] = p.getCantidad();
                fila[3] = p.getPrecio();
                modelo.addRow(fila);
            }
        }
    }
    
    public static void ActualizarTablaClientes(JTable t) {
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();
        //modelo.setColumnCount(0);
        modelo.setNumRows(0);
        /*modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cuil");*/
        DB db = new DB();
        ArrayList<Cliente> cl = db.obtenerClientes();
        for (Cliente c : cl) {
            Object[] fila = new Object[3];
            fila[0] = c.getId();
            fila[1] = c.getNombre();
            fila[2] = c.getCuil();
            modelo.addRow(fila);
        }
    }
    
    public static void AjustarTabla(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public static void BuscarProducto(JTable t, JTextField txtbuscar) {
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();
        //modelo.setColumnCount(0);
        modelo.setNumRows(0);
        /*modelo.addColumn("Codigo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio unit");*/
        DB db = new DB();
        ArrayList<Producto> pl = db.obtenerProductos();
        for (Producto p : pl) {
            //Condicion para que no se carguen los productos inactivos
            if (p.isActivo()) {
                String descripcion = p.getDescripcion().toLowerCase();
                String busqueda = txtbuscar.getText().toLowerCase();
                if (descripcion.contains(busqueda)) {
                    Object[] fila = new Object[4];
                    fila[0] = p.getCodigo();
                    fila[1] = p.getDescripcion();
                    fila[2] = p.getCantidad();
                    fila[3] = p.getPrecio();
                    modelo.addRow(fila);
                }
            }
        }
    }

    public static void BuscarCliente(JTable t, JTextField txtbuscar) {
        if (t.getSelectedRow() == -1) {
            DefaultTableModel modelo = (DefaultTableModel) t.getModel();
            //modelo.setColumnCount(0);
            modelo.setNumRows(0);
            /*modelo.addColumn("ID");
            modelo.addColumn("Nombre");
            modelo.addColumn("Cuil");*/
            DB db = new DB();
            ArrayList<Cliente> cl = db.obtenerClientes();
            for (Cliente c : cl) {
                String descripcion = c.getNombre().toLowerCase();
                String busqueda = txtbuscar.getText().toLowerCase();
                if (descripcion.contains(busqueda)) {
                    Object[] fila = new Object[3];
                    fila[0] = c.getId();
                    fila[1] = c.getNombre();
                    fila[2] = c.getCuil();
                    modelo.addRow(fila);
                }

            }
        }
    }

    public static void VaciarBusqueda(JTextField txtbuscar) {
        txtbuscar.setText("");
    }

    public static void facturar(JTable t, int id) {
        DB db = new DB();
        int idCliente = id;
        Compra co = new Compra(idCliente);
        db.agregar(co);
        //obtengo el ultimo numero de compra ingresado en la base de datos
        int ultimaCompra = db.obtenerUltimaCompra();
        //ese numero de compra lo uso con el codigo (del articulo) de cada registro de la tabla, junto con el valor cantidad
        //y precio unitario (por que puede cambiar) para ingresar cada registro en la base de datos de DetalleCompra
        for (int i = 0; i < t.getRowCount(); i++) {
            int cod_producto = Integer.parseInt(t.getValueAt(i, 0).toString());
            int cantidad = Integer.parseInt(t.getValueAt(i, 2).toString());
            int precio_u = Integer.parseInt(t.getValueAt(i, 3).toString());
            DetalleCompra dc = new DetalleCompra();
            dc.setNum_compra(ultimaCompra);
            dc.setCod_producto(cod_producto);
            dc.setCantidad(cantidad);
            dc.setPrecio_unitario(precio_u);
            db.agregar(dc);

            //por ultimo resto la cantidad comprada al stock de producto
            Producto p = new Producto();
            p.setCodigo(cod_producto);
            p.setCantidad(cantidad);
            db.restar(p);
        }

    }

    public static void GetTotalAPagar(JTable t, JLabel lblTotalAPagar) {
        int total = 0;
        for (int i = 0; i < t.getRowCount(); i++) {
            total = total + Integer.parseInt(t.getValueAt(i, 4).toString());
        }
        lblTotalAPagar.setText(String.valueOf(total));

    }

    public static void AgregarEnTabla(DefaultTableModel t, int codigo, String descripcion, int cantidad, int preciou, JTable tablaProductos) {
        //agrega los datos a la tabladetalle que despues recupero con la funcion facturar
        boolean existe = false;
        Object[] fila = new Object[5];
        fila[0] = codigo;
        fila[1] = descripcion;
        fila[2] = cantidad;
        fila[3] = preciou;
        fila[4] = (cantidad * preciou);
        for (int i = 0; i < t.getRowCount(); i++) {
            if (fila[0] == t.getValueAt(i, 0)) {
                existe = true;
            }
        }
        if (existe == true) {
            JOptionPane.showMessageDialog(null, "Ese articulo ya esta agregado. \nPara modificar la cantidad eliminelo\ne inserte de nuevo, cambiando\nelcampo cantidad.");
        } else {
            int filaProductos = tablaProductos.getSelectedRow();

            if (filaProductos != -1) {
                if (Integer.parseInt(tablaProductos.getValueAt(filaProductos, 2).toString()) >= cantidad) {
                    t.addRow(fila);
                } else {
                    JOptionPane.showMessageDialog(null, "No se dispone de esa cantidad en stock");
                }
            }
        }
    }

    public static void EliminarDeTabla(JTable t) {
        int fila = t.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();
        modelo.removeRow(fila);
    }

    public static void LimpiarCampos(JTextField txtCodigo, JTextField txtDescripcion, JTextField txtCantidad, JTextField txtPreciou) {
        txtCodigo.setText("");
        txtDescripcion.setText("");
        try {
            txtCantidad.getDocument().remove(0, txtCantidad.getDocument().getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(ControladorNuevoDetalle.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtPreciou.setText("");
    }

    public static void LimpiarCamposCliente(JTextField txtIdCliente, JTextField txtNombreCliente) {
        txtIdCliente.setText("");
        txtNombreCliente.setText("");
    }

    public static void LlenarCamposProducto(JTable t, JTextField txtCodigo, JTextField txtDescripcion, JTextField txtCantidad, JTextField txtPreciou) {
        //metodo que recibe la tabla de productos y todos los txt del formulario y pone los valores de la fila seleccionada en esos txt
        int fila = t.getSelectedRow();
        String codigo = t.getValueAt(fila, 0).toString();
        String descripcion = t.getValueAt(fila, 1).toString();
        String cantidad = "1";
        String preciou = t.getValueAt(fila, 3).toString();
        String preciot = String.valueOf(Integer.parseInt(preciou) * Integer.parseInt(cantidad));
        txtCodigo.setText(codigo);
        txtDescripcion.setText(descripcion);
        txtCantidad.setText(cantidad);
        txtPreciou.setText(preciou);
    }

    public static void LlenarCamposCliente(JTable t, JTextField txtIdCliente, JTextField txtNombreCliente) {
        //metodo que recibe la tabla de clientes y los txt id y nombre y pone los valores de la fila seleccionada en esos txt
        try {
            int fila = t.getSelectedRow();
            String id = t.getValueAt(fila, 0).toString();
            String nombre = t.getValueAt(fila, 1).toString();
            txtIdCliente.setText(id);
            txtNombreCliente.setText(nombre);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void ActualizarPrecioTotal(JTable t) {
        int fila = t.getSelectedRow();
        int cantidad = Integer.parseInt(t.getValueAt(fila, 2).toString());
        int preciou = Integer.parseInt(t.getValueAt(fila, 3).toString());
        int preciot = (cantidad * preciou);
        t.setValueAt(preciot, fila, 4);
    }

    public static void DesseleccionarFila(JTable t) {
        t.clearSelection();
    }

    public static void DesactivarTabla(JTable t) {
        t.setEnabled(false);
    }

    public static void ActivarTabla(JTable t) {
        t.setEnabled(true);
    }

    public static void ActivarDesactivarBoton(JButton btnAñadir, JTable t) {
        /*
        usado para activar y desactivar los botones añadir y quitar (añadir recibe la tabla 
        productos, quitar la tabla detalles)
        tambien lo uso para activar y desactivar el boton cancelar
         */
        if (t.getSelectedRow() != -1) {
            btnAñadir.setEnabled(true);
        } else {
            btnAñadir.setEnabled(false);
        }

    }

    public static void ActivarDesactivarBotonFacturar(JButton btnFacturar, JTextField txtIdCliente, JTable t) {
        //recibe txtIdCliente y tabla de detalles, para que se habilite tiene que estar seleccionado un cliente y tiene
        //que haber al menos un detalle en la tabla detalles
        if (t.getRowCount() > 0 && !"".equals(txtIdCliente.getText())) {
            btnFacturar.setEnabled(true);
        } else {
            btnFacturar.setEnabled(false);
        }
    }

    //parte del menu
    public static void NuevaVista() {
        VistaNuevoDetalle vnd = new VistaNuevoDetalle();
        vnd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vnd.setLocationRelativeTo(null);
        vnd.setVisible(true);
    }

    public static void CerrarVista(VistaNuevoDetalle v) {
        v.dispose();
        v.setVisible(false);
    }

    public static void NuevaVistaCliente() {
        VistaCliente vc = new VistaCliente();
        vc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vc.setLocationRelativeTo(null);
        vc.setVisible(true);
    }

    public static void NuevaVistaProducto() {
        VistaProducto vp = new VistaProducto();
        vp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vp.setLocationRelativeTo(null);
        vp.setVisible(true);
    }

    public static void NuevaVistaMaestroDetalle() {
        VistaMaestroDetalle vmd = new VistaMaestroDetalle();
        vmd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vmd.setLocationRelativeTo(null);
        vmd.setVisible(true);
    }

    public static void ValidarCantidad(JTextField txtcantidad) {
        //lo uso para validar cantidad
        DocumentFilter MyFilter = new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                try {
                    if (txtcantidad.getText().length() < 11 && text.matches("[0-9]")) {
                        fb.insertString(offset, text, attrs);
                    }

                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }

            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);

            }
        };

        AbstractDocument name = (AbstractDocument) txtcantidad.getDocument();
        name.setDocumentFilter(MyFilter);
    }

}
