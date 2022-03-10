package controlador;

import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import modelo.DB;
import org.apache.commons.validator.routines.EmailValidator;
import vista.VistaCliente;
import vista.VistaMaestroDetalle;
import vista.VistaNuevoDetalle;
import vista.VistaProducto;

public class ControladorCliente {

    public static void AgregarModificar(JTable t, String nombre, long cuil, String telefono, String direccion, String correo) {
        //las validaciones para guardar estan hechas en los document filter de cada txt y en ActivarDeasctivarBotonGuardar
        //este if else es para ver si inserta o modifica
        if (t.getSelectedRow() == -1) {
            Cliente c = new Cliente();
            c.setNombre(nombre.strip());
            c.setCuil(cuil);

            if (!"".equals(telefono)) {
                c.setTelefono(Long.parseLong(telefono));
            }
            if (!"".equals(direccion)) {
                c.setDireccion(direccion.strip());
            }
            if (!"".equals(correo)) {
                c.setCorreo(correo);
            }

            DB db = new DB();
            db.agregar(c);
        } else {
            int fila = t.getSelectedRow();
            int valor = Integer.parseInt(t.getValueAt(fila, 0).toString());
            Cliente c = new Cliente(valor, nombre, cuil);

            if (!"".equals(telefono)) {
                c.setTelefono(Long.parseLong(telefono));
            }
            if (!"".equals(direccion)) {
                c.setDireccion(direccion.strip());
            }
            if (!"".equals(correo)) {
                c.setCorreo(correo);
            }

            DB db = new DB();
            db.modificar(c);
        }
    }

    public static void BuscarCliente(JTable t, JTextField txtbuscar) {
        if (t.getSelectedRow() == -1) {
            DefaultTableModel modelo = (DefaultTableModel) t.getModel();
            modelo.setNumRows(0);
            DB db = new DB();
            ArrayList<Cliente> cl = db.obtenerClientes();
            for (Cliente c : cl) {
                String descripcion = c.getNombre().toLowerCase();
                String busqueda = txtbuscar.getText().toLowerCase();
                if (descripcion.contains(busqueda)) {
                    Object[] fila = new Object[6];
                    fila[0] = c.getId();
                    fila[1] = c.getNombre();
                    fila[2] = c.getCuil();
                    if (c.getTelefono() != 0) { //hago esto por uqe los telefonos vacios se recuperan como un 0
                        fila[3] = c.getTelefono();
                    } else {
                        fila[3] = "";
                    }
                    fila[4] = c.getDireccion();
                    fila[5] = c.getCorreo();
                    modelo.addRow(fila);
                }

            }
        }
    }

    public static void VaciarBusqueda(JTextField txtbuscar) {
        txtbuscar.setText("");
    }

    public static void Eliminar(JTable t) {
        int fila = t.getSelectedRow();
        int valor = Integer.parseInt(t.getValueAt(fila, 0).toString());
        Cliente c = new Cliente();
        c.setId(valor);
        DB db = new DB();
        db.eliminar(c);
    }

    public static void ActualizarGrilla(JTable t) {
        DefaultTableModel modelo = (DefaultTableModel) t.getModel();
        modelo.setNumRows(0);
        /*
        modelo.setColumnCount(0);       
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cuil");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Correo");*/
        DB db = new DB();
        ArrayList<Cliente> cl = db.obtenerClientes();
        for (Cliente c : cl) {
            Object[] fila = new Object[6];
            fila[0] = c.getId();
            fila[1] = c.getNombre();
            fila[2] = c.getCuil();
            if (c.getTelefono() != 0) { //hago esto por uqe los telefonos vacios se recuperan como un 0
                fila[3] = c.getTelefono();
            } else {
                fila[3] = "";
            }
            fila[4] = c.getDireccion();
            fila[5] = c.getCorreo();
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

    public static void LimpiarCampos(JTextField txtnombre, JTextField txtcuil, JTextField txttelefono, JTextField txtdireccion, JTextField txtcorreo) {
        try {
            txtnombre.getDocument().remove(0, txtnombre.getDocument().getLength());
            txtcuil.getDocument().remove(0, txtcuil.getDocument().getLength());
            txttelefono.getDocument().remove(0, txttelefono.getDocument().getLength());
            txtdireccion.getDocument().remove(0, txtdireccion.getDocument().getLength());
            txtcorreo.getDocument().remove(0, txtcorreo.getDocument().getLength());
        } catch (BadLocationException ex) {
            Logger.getLogger(ControladorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void DesseleccionarFila(JTable t) {
        t.clearSelection();
    }

    public static void LlenarCampos(JTable t, JTextField txtnombre, JTextField txtcuil, JTextField txttelefono, JTextField txtdireccion, JTextField txtcorreo) {
        int fila = t.getSelectedRow();
        String nombre = t.getValueAt(fila, 1).toString();
        String cuil = t.getValueAt(fila, 2).toString();
        String telefono = "";
        String direccion = "";
        String correo = "";

        if (t.getValueAt(fila, 3) != null) {
            telefono = t.getValueAt(fila, 3).toString();
        }
        if (t.getValueAt(fila, 4) != null) {
            direccion = t.getValueAt(fila, 4).toString();
        }
        if (t.getValueAt(fila, 5) != null) {
            correo = t.getValueAt(fila, 5).toString();
        }

        txtnombre.setText(nombre);
        txtcuil.setText(cuil);
        txttelefono.setText(telefono);
        txtdireccion.setText(direccion);
        txtcorreo.setText(correo);
    }

    public static void DesactivarTabla(JTable t) {
        t.setEnabled(false);
    }

    public static void ActivarTabla(JTable t) {
        t.setEnabled(true);
    }

    public static void ActivarDesactivarBotonGuardar(JButton btnGuardar, JTextField txtNombre, JTextField txtcuil, JTextField txtteleofno, JTextField txtcorreo, JTable t) {
        /*
        Metodo que activa el boton guardar siempre que el nombre y el cuil no esten vacios y 
        el cuil sea valido, ademas verifica que el numero de telefono y el correo esten
        vacios o sean validos.
         */
        EmailValidator validator = EmailValidator.getInstance();
        boolean valido = validator.isValid(txtcorreo.getText());
        //////////////
        int flag = 0;
        if (!"".equals(txtcuil.getText())) {
            flag = CuilUnico(Long.parseLong(txtcuil.getText()));
        }
        //que nombre no este vacio, cuil sea valaido, telefono este vacio o sea valido, correo este vacio o sea valido
        if (!"".equals(txtNombre.getText()) && ValidarCuit(txtcuil.getText()) && (txtteleofno.getText().length() == 0 || txtteleofno.getText().length() == 10) && ("".equals(txtcorreo.getText()) || valido)) {
            //una vez que se cumple eso hay dos escenarios, el primero donde estoy modificando
            //un registro ya existente, y el segundo donde estoy introduciendo uno nuevo
            if(t.getSelectedRow() == -1 && flag == 1){
                //flag valdra uno por ser un registro ya cargado en la bd
                btnGuardar.setEnabled(false);
            }else{
                btnGuardar.setEnabled(true);
            }
        } else {
            btnGuardar.setEnabled(false);
        }
    }

    public static void ActivarDesactivarBotonCancelar(JButton btnCancelar, JTextField txtNombre, JTextField txtCuil, JTable t, JTextField txttelefono, JTextField txtdireccion, JTextField txtcorreo, JTextField txtBuscarCliente) {
        //Metodo que activa el boton cancelar siempre que al menos un text field contenga algun dato.
        if (!"".equals(txtNombre.getText()) || !"".equals(txtCuil.getText()) || t.getSelectedRow() != -1 || !"".equals(txttelefono.getText()) || !"".equals(txtdireccion.getText()) || !"".equals(txtcorreo.getText()) || !"".equals(txtBuscarCliente.getText())) {
            btnCancelar.setEnabled(true);
        } else {
            btnCancelar.setEnabled(false);
        }
    }

    public static void ActivarDesactivarTxt(JTextField txt, JTable t) {
        //metodo que previene que se modifique el nombre y cuil de un cliente ya cargado
        if (t.getSelectedRow() != -1) {
            txt.setEnabled(false);
        } else {
            txt.setEnabled(true);
        }
    }

    public static void ActivarBoton(JButton boton) {
        boton.setEnabled(true);
    }

    public static void DesactivarBoton(JButton boton) {
        boton.setEnabled(false);
    }

    public static void NuevaVista() {
        VistaCliente vc = new VistaCliente();
        vc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vc.setLocationRelativeTo(null);
        vc.setVisible(true);
    }

    public static void CerrarVista(VistaCliente vc) {
        vc.dispose();
        vc.setVisible(false);
    }

    public static void NuevaVistaNuevoDetalle() {
        VistaNuevoDetalle vnd = new VistaNuevoDetalle();
        vnd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vnd.setLocationRelativeTo(null);
        vnd.setVisible(true);
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

    public static int CuilUnico(long cuil) {
        //retorna 0 si el cuil no existe, 1 si ya existe
        DB db = new DB();
        int flag = 0;
        ArrayList<Cliente> cli = db.obtenerClientes();
        for (Cliente cl : cli) {
            long busqueda = cl.getCuil();
            if (busqueda == cuil) {
                flag = 1;
            }
        }
        return flag;
    }

    public static void ValidarNombre(JTextField txtnombre) {
        //  123  -*/ Juan  Gomez 123 -*/
        DocumentFilter MyFilter1 = new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                try {
                    if (txtnombre.getText().length() < 30) {
                        String ingreso = text;
                        ingreso = ingreso.replaceAll("([0-9])", "");
                        ingreso = ingreso.replaceAll("[^\\w\\s]", "");
                        ingreso = ingreso.replaceAll("_", "");

                        //longitud == 1 significa que se tipeo un caracter y no se copio y pego una cadena
                        if (ingreso.length() < 2) {
                            /*
                                Si nombre esta vacio el primer caracter no puede ser un espacio en blanco
                                Si el ultimo caracter escrito es un espacio en blanco, el proximo no puede serlo
                             */
                            if (txtnombre.getText().isEmpty() || Character.isWhitespace(txtnombre.getText().charAt(txtnombre.getText().length() - 1))) {
                                ingreso = ingreso.replaceAll("\\s", "");
                            }
                        } else {
                            //se eliminan espacios en blanco al comienzo y al final 
                            //y espacios en blanco consecutivos se reemplazan por uno solo
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

        AbstractDocument name = (AbstractDocument) txtnombre.getDocument();
        name.setDocumentFilter(MyFilter1);
    }

    public static void ValidarCuil(JTextField txtcuil, JLabel lbladvertenciacuil) {
        DocumentFilter MyFilter2 = new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (txtcuil.getText().length() < 11) {
                    String ingreso = text;

                    ingreso = ingreso.replaceAll("([a-zA-Z])", "");
                    ingreso = ingreso.replaceAll("[^\\w\\s]", "");
                    ingreso = ingreso.replaceAll("\\s", "");
                    ingreso = ingreso.replaceAll("_", "");
                    fb.insertString(offset, ingreso, attrs);
                }

                //0 cuil inexistente
                //1 cuil ya existe
                //2 esta vacio
                int flag;
                if (!"".equals(txtcuil.getText())) {
                    flag = CuilUnico(Long.parseLong(txtcuil.getText()));
                } else {
                    flag = 2;
                }
                
                //flag == 1 && txtcuil.isEnabled()
                /*
                La segunda condicion es para evitar que se muestre la advertencia de cuil existente
                cuando selecciono un registro ya cargado en la tabla
                */
                if (txtcuil.getText().length() < 11 && txtcuil.getText().length() != 0 || !ValidarCuit(txtcuil.getText()) || flag == 1 && txtcuil.isEnabled()) {
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\\\Users\\\\Desktop\\\\Desktop\\\\iaes\\\\IAES 2020\\\\Programacion II\\\\AplicacionVentasEscritorio-Github\\\\src\\\\img\\\\warning.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
                    lbladvertenciacuil.setIcon(imageIcon);
                    if (flag == 0) {
                        lbladvertenciacuil.setToolTipText("Verifica que el cuil sea valido.");
                    } else {
                        lbladvertenciacuil.setToolTipText("Ese cuil ya existe en la base de datos.");
                    }

                }
                if (txtcuil.getText().length() == 0 || ValidarCuit(txtcuil.getText()) && flag == 0) {
                    lbladvertenciacuil.setIcon(null);
                    lbladvertenciacuil.setToolTipText(null);
                }

            }

            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {

            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
                if (txtcuil.getText().length() < 11 && txtcuil.getText().length() != 0 || !ValidarCuit(txtcuil.getText())) {
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\\\Users\\\\Desktop\\\\Desktop\\\\iaes\\\\IAES 2020\\\\Programacion II\\\\AplicacionVentasEscritorio-Github\\\\src\\\\img\\\\warning.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
                    lbladvertenciacuil.setIcon(imageIcon);
                    lbladvertenciacuil.setToolTipText("Verifica que el cuil sea valido.");
                }
                if (txtcuil.getText().length() == 0 || ValidarCuit(txtcuil.getText())) {
                    lbladvertenciacuil.setIcon(null);
                    lbladvertenciacuil.setToolTipText(null);
                }
            }
        };

        AbstractDocument name = (AbstractDocument) txtcuil.getDocument();
        name.setDocumentFilter(MyFilter2);
    }

    public static void ValidarTelefono(JTextField txttelefono, JLabel lbladvertenciatelefono) {
        //-*/ asd   -*/ 3743asd481635-*/-* asd -*/
        DocumentFilter MyFilter3 = new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (txttelefono.getText().length() < 10) {
                    String ingreso = text;
                    ingreso = ingreso.replaceAll("([a-zA-Z])", "");
                    ingreso = ingreso.replaceAll("[^\\w\\s]", "");
                    ingreso = ingreso.replaceAll("\\s", "");
                    ingreso = ingreso.replaceAll("_", "");

                    fb.insertString(offset, ingreso, attrs);
                }

                if (txttelefono.getText().length() < 10 && txttelefono.getText().length() != 0) {
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\\\Users\\\\Desktop\\\\Desktop\\\\iaes\\\\IAES 2020\\\\Programacion II\\\\AplicacionVentasEscritorio-Github\\\\src\\\\img\\\\warning.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
                    lbladvertenciatelefono.setIcon(imageIcon);
                    lbladvertenciatelefono.setToolTipText("Verifica que el telefono sea valido.");
                } else {
                    lbladvertenciatelefono.setIcon(null);
                    lbladvertenciatelefono.setToolTipText(null);
                }

            }

            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {

            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
                if (txttelefono.getText().length() < 10 && txttelefono.getText().length() != 0) {
                    ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\\\Users\\\\Desktop\\\\Desktop\\\\iaes\\\\IAES 2020\\\\Programacion II\\\\AplicacionVentasEscritorio-Github\\\\src\\\\img\\\\warning.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
                    lbladvertenciatelefono.setIcon(imageIcon);
                    lbladvertenciatelefono.setToolTipText("Verifica que el telefono sea valido.");
                } else {
                    lbladvertenciatelefono.setIcon(null);
                    lbladvertenciatelefono.setToolTipText(null);
                }
            }
        };

        AbstractDocument name = (AbstractDocument) txttelefono.getDocument();
        name.setDocumentFilter(MyFilter3);
    }

    public static void ValidarDireccion(JTextField txtdireccion) {

        DocumentFilter MyFilter4 = new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (txtdireccion.getText().length() < 50) {
                    String ingreso = text;
                    ingreso = ingreso.replaceAll("[^\\w\\s]", "");
                    ingreso = ingreso.replaceAll("_", "");

                    if (ingreso.length() < 2) {
                        if (txtdireccion.getText().isEmpty() || Character.isWhitespace(txtdireccion.getText().charAt(txtdireccion.getText().length() - 1))) {
                            ingreso = ingreso.replaceAll("\\s", "");
                        }
                    } else {
                        ingreso = ingreso.strip();
                        ingreso = ingreso.replaceAll("\\s+", " ");
                    }

                    fb.insertString(offset, ingreso, attrs);
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

        AbstractDocument name = (AbstractDocument) txtdireccion.getDocument();
        name.setDocumentFilter(MyFilter4);
    }

    public static void ValidarCorreo(JTextField txtcorreo, JLabel lbladvertenciacorreo) {
        // https://en.wikipedia.org/wiki/Email_address#Local-part
        DocumentFilter MyFilter5 = new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (txtcorreo.getText().length() < 50) {
                    String ingreso = text;
                    ingreso = ingreso.replaceAll("\\s", "");

                    fb.insertString(offset, ingreso, attrs);
                }
                //  juan  @  lopez  .com   
                EmailValidator validator = EmailValidator.getInstance();
                boolean valido = validator.isValid(txtcorreo.getText());
                if ("".equals(txtcorreo.getText()) || valido) {
                    lbladvertenciacorreo.setIcon(null);
                    lbladvertenciacorreo.setToolTipText(null);
                } else {
                    if (!valido) {
                        ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\\\Users\\\\Desktop\\\\Desktop\\\\iaes\\\\IAES 2020\\\\Programacion II\\\\AplicacionVentasEscritorio-Github\\\\src\\\\img\\\\warning.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
                        lbladvertenciacorreo.setIcon(imageIcon);
                        lbladvertenciacorreo.setToolTipText("Verifica que el correo sea valido.");
                    } else if ("".equals(txtcorreo.getText())) {
                        lbladvertenciacorreo.setIcon(null);
                        lbladvertenciacorreo.setToolTipText(null);
                    }
                }

            }

            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            }

            @Override
            public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
                super.remove(fb, offset, length);
                if (txtcorreo.getText().length() == 0) {
                    lbladvertenciacorreo.setIcon(null);
                    lbladvertenciacorreo.setToolTipText(null);
                }
                EmailValidator validator = EmailValidator.getInstance();
                boolean valido = validator.isValid(txtcorreo.getText());
                if ("".equals(txtcorreo.getText()) || valido) {
                    lbladvertenciacorreo.setIcon(null);
                    lbladvertenciacorreo.setToolTipText(null);
                } else {
                    if (!valido) {
                        ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\\\Users\\\\Desktop\\\\Desktop\\\\iaes\\\\IAES 2020\\\\Programacion II\\\\AplicacionVentasEscritorio-Github\\\\src\\\\img\\\\warning.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
                        lbladvertenciacorreo.setIcon(imageIcon);
                        lbladvertenciacorreo.setToolTipText("Verifica que el correo sea valido.");
                    } else if ("".equals(txtcorreo.getText())) {
                        lbladvertenciacorreo.setIcon(null);
                        lbladvertenciacorreo.setToolTipText(null);
                    }
                }
            }
        };

        AbstractDocument name = (AbstractDocument) txtcorreo.getDocument();
        name.setDocumentFilter(MyFilter5);
    }

    public static boolean ValidarCuit(String cuit) {

        if (cuit.length() != 11) {
            return false;
        }

        boolean rv = false;
        int resultado = 0;
        String cuit_nro = cuit;
        String codes = "6789456789";
        int verificador = Character.getNumericValue(cuit_nro.charAt(cuit_nro.length() - 1));
        int x = 0;

        while (x < 10) {
            int digitoValidador = Integer.parseInt(codes.substring(x, x + 1));
            int digito = Integer.parseInt(cuit_nro.substring(x, x + 1));
            int digitoValidacion = digitoValidador * digito;
            resultado += digitoValidacion;
            x++;
        }
        resultado = resultado % 11;
        rv = (resultado == verificador);
        return rv;
    }

}
