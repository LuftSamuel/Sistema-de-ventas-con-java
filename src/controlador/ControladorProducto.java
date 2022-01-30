package controlador;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.DB;
import modelo.Producto;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import vista.VistaCliente;
import vista.VistaMaestroDetalle;
import vista.VistaNuevoDetalle;
import vista.VistaProducto;

public class ControladorProducto {    

    public static void AgregarModificar(JTable t, String descripcion, int cantidad, int precio) {
        //este if else es para ver si inserta o modifica
        if (t.getSelectedRow() == -1) {
            Producto p = new Producto();
            p.setDescripcion(descripcion.strip());
            p.setCantidad(cantidad);
            p.setPrecio(precio);
            DB db = new DB();
            db.agregar(p);
        } else {
            int fila = t.getSelectedRow();
            int valor = Integer.parseInt(t.getValueAt(fila, 0).toString());
            Producto p = new Producto();
            p.setCodigo(valor);
            p.setCantidad(cantidad);
            p.setPrecio(precio);
            DB db = new DB();
            db.modificar(p);
        }
    }

    public static void Eliminar(JTable t) {
        int fila = t.getSelectedRow();
        int valor = Integer.parseInt(t.getValueAt(fila, 0).toString());
        Producto p = new Producto();
        p.setCodigo(valor);
        DB db = new DB();
        db.eliminar(p);
    }

    public static void VerOcultarTodos(JTable t, JButton b) {
        /*
        Funcion para alternar entre dos estados, uno donde se muestran todos los productos y 
        el otro donde no se muestran los uqe estan marcados como ocultos
         */
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();
       // modelo.setColumnCount(0);
        modelo.setNumRows(0);
        /*modelo.addColumn("Codigo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("activo");*/
        DB db = new DB();
        ArrayList<Producto> pl = db.obtenerProductos();
        for (Producto p : pl) {
            if (b.getText() == "Ocultar Inactivos") {
                if (p.isActivo()) {
                    Object[] fila = new Object[5];
                    fila[0] = p.getCodigo();
                    fila[1] = p.getDescripcion();
                    fila[2] = p.getCantidad();
                    fila[3] = p.getPrecio();
                    fila[4] = p.isActivo();
                    modelo.addRow(fila);
                }
            } else {
                Object[] fila = new Object[5];
                fila[0] = p.getCodigo();
                fila[1] = p.getDescripcion();
                fila[2] = p.getCantidad();
                fila[3] = p.getPrecio();
                fila[4] = p.isActivo();
                modelo.addRow(fila);
            }

        }
        if (b.getText() == "Ocultar Inactivos") {
            b.setText("Mostrar Inactivos");
        } else {
            b.setText("Ocultar Inactivos");
        }
    }

    public static void ActualizarGrilla(JTable t, JButton b) {
        //este metodo o muestra todo o muestra solo los activos pero no altera nada
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();
        //modelo.setColumnCount(0);
        modelo.setNumRows(0);
        /*modelo.addColumn("Codigo");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("activo");*/
        DB db = new DB();
        ArrayList<Producto> pl = db.obtenerProductos();
        if (b.getText() == "Mostrar Inactivos") {
            for (Producto p : pl) {
                if (p.isActivo()) {
                    Object[] fila = new Object[5];
                    fila[0] = p.getCodigo();
                    fila[1] = p.getDescripcion();
                    fila[2] = p.getCantidad();
                    fila[3] = p.getPrecio();
                    fila[4] = p.isActivo();
                    modelo.addRow(fila);
                }
            }
        } else {
            for (Producto p : pl) {
                Object[] fila = new Object[5];
                fila[0] = p.getCodigo();
                fila[1] = p.getDescripcion();
                fila[2] = p.getCantidad();
                fila[3] = p.getPrecio();
                fila[4] = p.isActivo();
                modelo.addRow(fila);
            }
        }
    }
    
    public static void BuscarProducto(JTable t, JTextField txtbuscar) {
        if (t.getSelectedRow() == -1) {
            DefaultTableModel modelo = (DefaultTableModel) t.getModel();
            modelo.setNumRows(0);
            DB db = new DB();
            ArrayList<Producto> pl = db.obtenerProductos();
            for (Producto p : pl) {
                String descripcion = p.getDescripcion().toLowerCase();
                String busqueda = txtbuscar.getText().toLowerCase();
                if (descripcion.contains(busqueda)) {
                    Object[] fila = new Object[5];
                    fila[0] = p.getCodigo();
                    fila[1] = p.getDescripcion();
                    fila[2] = p.getCantidad();
                    fila[3] = p.getPrecio();
                    fila[4] = p.isActivo();
                    modelo.addRow(fila);
                }

            }
        }
    }
    
    public static void DesactivarTabla(JTable t) {
        t.setEnabled(false);
    }
    
    public static void ActivarTabla(JTable t) {
        t.setEnabled(true);
    }
    
    public static void DesseleccionarFila(JTable t) {
        t.clearSelection();
    }
    
    public static void VaciarBusqueda(JTextField txtbuscar) {
        txtbuscar.setText("");
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

    public static void ActivarDesactivarProducto(JTable t) {
        int fila = t.getSelectedRow();
        int valor = Integer.parseInt(t.getValueAt(fila, 0).toString());
        Producto p = new Producto();
        p.setCodigo(valor);
        if (t.getValueAt(fila, 4).toString() == "true") {
            p.setActivo(false);
        } else {
            p.setActivo(true);
        }
        DB db = new DB();
        db.activarDesactivar(p);
    }

    public static void LimpiarCampos(JTextField txtdescripcion, JTextField txtcantidad, JTextField txtprecio) {
        try {
            txtdescripcion.getDocument().remove(0, txtdescripcion.getDocument().getLength());
            txtcantidad.getDocument().remove(0, txtcantidad.getDocument().getLength());
            txtprecio.getDocument().remove(0, txtprecio.getDocument().getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void LlenarCampos(JTable t, JTextField txtdescripcion, JTextField txtcantidad, JTextField txtprecio) {
        int fila = t.getSelectedRow();
        String descripcion = t.getValueAt(fila, 1).toString();
        String cantidad = t.getValueAt(fila, 2).toString();
        String precio = t.getValueAt(fila, 3).toString();
        txtdescripcion.setText(descripcion);
        txtcantidad.setText(cantidad);
        txtprecio.setText(precio);
    }

    public static void ActivarDesactivarBotonGuardar(JButton btnGuardar, JTextField txtDescripcion, JTextField txtCantidad, JTextField txtPrecio) {
        //Metodo que activa el boton guardar solo si todos los textfield contienen informacion
        if (!"".equals(txtDescripcion.getText()) && !"".equals(txtCantidad.getText()) && !"".equals(txtPrecio.getText())) {
            btnGuardar.setEnabled(true);
        } else {
            btnGuardar.setEnabled(false);
        }

    }

    public static void ActivarDesactivarBotonCancelar(JButton btnCancelar, JTextField txtDescripcion, JTextField txtCantidad, JTextField txtPrecio, JTable t, JTextField txtBuscarProducto) {
        //metodo que activa el boton cancelar siempre al menos un campo no este vacio
        if (!"".equals(txtDescripcion.getText()) || !"".equals(txtCantidad.getText()) || !"".equals(txtPrecio.getText()) || t.getSelectedRow() != -1 || !"".equals(txtBuscarProducto.getText())) {
            btnCancelar.setEnabled(true);
        } else {
            btnCancelar.setEnabled(false);
        }

    }

    public static void ActivarDesactivarBotonActivarDesactivar(JTable t, JButton b) {
        if (t.getSelectedRow() != -1) {
            b.setEnabled(true);
        } else {
            b.setEnabled(false);
        }
    }

    public static void ActivarBoton(JButton boton) {
        boton.setEnabled(true);
    }

    public static void DesactivarBoton(JButton boton) {
        boton.setEnabled(false);
    }

    public static void ActivarDesactivarTxtDescripcion(JTextField txtDescripcion, JTable t) {
        if (t.getSelectedRow() != -1) {
            txtDescripcion.setEnabled(false);
        } else {
            txtDescripcion.setEnabled(true);
        }
    }

    //parte del menu
    public static void NuevaVista() {
        VistaProducto vp = new VistaProducto();
        vp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vp.setLocationRelativeTo(null);
        vp.setVisible(true);

    }

    public static void CerrarVista(VistaProducto vp) {
        vp.dispose();
        vp.setVisible(false);
    }

    public static void NuevaVistaNuevoDetalle() {
        VistaNuevoDetalle vnd = new VistaNuevoDetalle();
        vnd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vnd.setLocationRelativeTo(null);
        vnd.setVisible(true);

    }

    public static void NuevaVistaCliente() {
        VistaCliente vc = new VistaCliente();
        vc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vc.setLocationRelativeTo(null);
        vc.setVisible(true);
    }

    public static void NuevaVistaMaestroDetalle() {
        VistaMaestroDetalle vmd = new VistaMaestroDetalle();
        vmd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vmd.setLocationRelativeTo(null);
        vmd.setVisible(true);
    }

    public static void ValidarDescripcion(JTextField txtdescripcion) {
        DocumentFilter MyFilter = new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                try {
                    if (txtdescripcion.getText().length() < 100) {
                        String ingreso = text;
                        ingreso = ingreso.replaceAll("[^\\w\\s]", "");
                        ingreso = ingreso.replaceAll("_", "");

                        if (ingreso.length() < 2) {
                            if (txtdescripcion.getText().isEmpty() || Character.isWhitespace(txtdescripcion.getText().charAt(txtdescripcion.getText().length() - 1))) {
                                ingreso = ingreso.replaceAll("\\s", "");
                            }
                        } else {
                            ingreso = ingreso.strip();
                            ingreso = ingreso.replaceAll("\\s+", " ");
                        }

                        fb.insertString(offset, ingreso, attrs);

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

        AbstractDocument name = (AbstractDocument) txtdescripcion.getDocument();
        name.setDocumentFilter(MyFilter);
    }

    public static void ValidarEntero(JTextField txtcantidad) {
        //este lo uso para cantidad y precio
        DocumentFilter MyFilter = new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                try {
                    if (txtcantidad.getText().length() < 6) {
                        String ingreso = text;
                        ingreso = ingreso.replaceAll("\\s", "");
                        ingreso = ingreso.replaceAll("[a-zA-Z]", "");
                        ingreso = ingreso.replaceAll("[^\\w\\s]", "");
                        ingreso = ingreso.replaceAll("_", "");

                        fb.insertString(offset, ingreso, attrs);

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
