package controlador;

import com.itextpdf.kernel.geom.PageSize;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Compra;
import modelo.DB;
import modelo.DetalleCompra;
import modelo.Producto;
import vista.VistaCliente;
import vista.VistaMaestroDetalle;
import vista.VistaNuevoDetalle;
import vista.VistaProducto;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ControladorMaestroDetalle {

    public static void ActualizarTablaCompra(DefaultTableModel t) {
        t.setColumnCount(0);
        t.setNumRows(0);
        t.addColumn("Num Compra");
        t.addColumn("Fecha");
        t.addColumn("Cod Cliente");
        t.addColumn("Nombre Cliente");
        t.addColumn("Cuil");
        DB db = new DB();
        ArrayList<Compra> cl = db.obtenerCompras();
        ArrayList<Cliente> clie = db.obtenerClientes();
        for (Compra c : cl) {
            Object[] fila = new Object[5];
            fila[0] = c.getNum_compra();
            fila[1] = c.getFecha();
            fila[2] = c.getCod_cliente();
            for(Cliente cli : clie){
                if(cli.getId() == c.getCod_cliente()){
                    fila[3] = cli.getNombre();
                    fila[4] = cli.getCuil();
                }
            }
            t.addRow(fila);
        }
    }

    public static void ActualizarTablaDetalle(DefaultTableModel t, JTable s) {
        //t = tabla detalles, s = tabla compras

        //obtengo la fila seleccionada de la tabla compras y con eso el numero de compra
        int filaSeleccionada = s.getSelectedRow();
        int num_compra = Integer.parseInt(s.getValueAt(filaSeleccionada, 0).toString());

        //inicio la tabla detalles
        t.setColumnCount(0);
        t.setNumRows(0);
        t.addColumn("Cod Producto");
        t.addColumn("Descripcion");
        t.addColumn("Cantidad");
        t.addColumn("Precio u");
        t.addColumn("Precio t");

        DB db = new DB();
        ArrayList<DetalleCompra> dl = db.obtenerDetalles(num_compra);
        ArrayList<Producto> p1 = db.obtenerProductos();
        for (DetalleCompra d : dl) {
            Object[] fila = new Object[5];
            fila[0] = d.getCod_producto();
            //descripcion esta en la tabla de producto
            for (Producto p : p1) {
                if (p.getCodigo() == d.getCod_producto()) {
                    fila[1] = p.getDescripcion();
                }
            }
            fila[2] = d.getCantidad();
            fila[3] = d.getPrecio_unitario();
            fila[4] = d.getCantidad() * d.getPrecio_unitario();
            t.addRow(fila);
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
    
    public static void CrearPDF(JTable t, JTable s){
        int fila = t.getSelectedRow();
        String nFac = t.getValueAt(fila, 0).toString();
        String fecha = t.getValueAt(fila, 1).toString();
        fecha = fecha.replace('-', '.');
        fecha = fecha.replace(':', '.');
        String comprador = t.getValueAt(fila, 3).toString();
        
        try {
            //antes de convertir a la fecha en parte del titulo la tengo que formatear, creo que por los guiones
            File file = new File(nFac+comprador+fecha+".pdf");
            //File file = new File("asd.pdf");
            PdfWriter pdfWriter = new PdfWriter(file); 
            PdfDocument pdfDocument = new PdfDocument(pdfWriter); 
            Document document = new Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4); //21.5 x 28 cm?, sino a1 a2 a3
 
            float col = 280f;
            float columnWidth[] = {col, col};
            Table datosEmpresa = new Table(columnWidth);            
            
            datosEmpresa.addCell(new Cell().add(new Paragraph("FACTURA"))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER)
            );
            
            datosEmpresa.addCell(new Cell().add(new Paragraph("C.U.I.T.:xx-xxxxxxxx-x\nIngresos Brutos C.M.:xxx-xxxxxx-x\nINICIO DE ACTIVIDADES: MES 20XX"))
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setMarginRight(10f)
                    .setBorder(Border.NO_BORDER)
            ); 
            
            document.add(datosEmpresa);
            
            //separador
            document.add(new Paragraph("\n"));
            
            float columnWidth2[] = {80, 300, 100, 80};
            Table datosCliente = new Table(columnWidth2);
            
            datosCliente.addCell(new Cell(0, 4).add(new Paragraph("Datos del cliente"))
                    .setBold()
                    .setBorder(Border.NO_BORDER)
            );
            
            //primer parametro filas de ancho, segundo parametro columnas de ancho
            datosCliente.addCell(new Cell(1 ,1).add(new Paragraph("Nombre")).setBorder(Border.NO_BORDER));
            datosCliente.addCell(new Cell(1 ,1).add(new Paragraph(t.getValueAt(fila, 3).toString())).setBorder(Border.NO_BORDER));           
            datosCliente.addCell(new Cell(1 ,1).add(new Paragraph("Numero Factura")).setBorder(Border.NO_BORDER));
            datosCliente.addCell(new Cell(1 ,1).add(new Paragraph(t.getValueAt(fila, 0).toString())).setBorder(Border.NO_BORDER));
            
            String cuil = t.getValueAt(fila, 4).toString();
            cuil = cuil.substring(0, 2) + "-" + cuil.substring(2, 10) + "-" + cuil.substring(10, 11);
            
            datosCliente.addCell(new Cell(0 ,1).add(new Paragraph("Cuil num")).setBorder(Border.NO_BORDER));
            datosCliente.addCell(new Cell(0 ,1).add(new Paragraph(cuil)).setBorder(Border.NO_BORDER));
            datosCliente.addCell(new Cell(0 ,1).add(new Paragraph("Fecha")).setBorder(Border.NO_BORDER));            
            //aca le quito a la fecha las horas minutos y segundos y despues recien la agrego
            String sFecha = t.getValueAt(fila, 1).toString();
            sFecha = sFecha.substring(0, sFecha.length() - 9);            
            datosCliente.addCell(new Cell(0 ,1).add(new Paragraph(sFecha)).setBorder(Border.NO_BORDER));
            
            document.add(datosCliente);         
            
            //separador
            document.add(new Paragraph("\n"));
            
            float columnWidth3[] = {320, 80, 80, 80};
            Table datosProductos = new Table(columnWidth3);
            
            datosProductos.addCell(new Cell(1, 1).add(new Paragraph("Descripcion")).setBorder(Border.NO_BORDER));
            datosProductos.addCell(new Cell(1, 1).add(new Paragraph("Cantidad")).setBorder(Border.NO_BORDER));
            datosProductos.addCell(new Cell(1, 1).add(new Paragraph("Precio u")).setBorder(Border.NO_BORDER));
            datosProductos.addCell(new Cell(1, 1).add(new Paragraph("Precio t")).setBorder(Border.NO_BORDER));
            
            document.add(datosProductos);
            
            //separador
            document.add(new Paragraph("\n"));
            
            Table detalleProductos = new Table(columnWidth3);
            
            int total = 0;
            
            for(int i=0; i < s.getRowCount(); i++){
                detalleProductos.addCell(new Cell(1, 1).add(new Paragraph(s.getValueAt(i, 1).toString())).setBorder(Border.NO_BORDER));
                detalleProductos.addCell(new Cell(1, 1).add(new Paragraph(s.getValueAt(i, 2).toString())).setBorder(Border.NO_BORDER));
                detalleProductos.addCell(new Cell(1, 1).add(new Paragraph(s.getValueAt(i, 3).toString())).setBorder(Border.NO_BORDER));
                detalleProductos.addCell(new Cell(1, 1).add(new Paragraph(s.getValueAt(i, 4).toString())).setBorder(Border.NO_BORDER));    
                total = total + Integer.parseInt(s.getValueAt(i, 4).toString());
            }
            
            detalleProductos.addCell(new Cell(1, 1).add(new Paragraph()).setBorder(Border.NO_BORDER));
            detalleProductos.addCell(new Cell(1, 1).add(new Paragraph()).setBorder(Border.NO_BORDER));    
            detalleProductos.addCell(new Cell(1, 1).add(new Paragraph()).setBorder(Border.NO_BORDER));    
            detalleProductos.addCell(new Cell(1, 4).add(new Paragraph(String.valueOf(total))).setBorder(Border.NO_BORDER).setBold());
            document.add(detalleProductos);
            
            
            
            
            
            
            
            
            
            
            
            
            
            document.close(); 
            pdfWriter.close();
 
            
            
            
            
            
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void ActivarDesactivarBotonCrearFactura(JTable t, JButton b){
        if(t.getSelectedRow() != -1){
            b.setEnabled(true);
        }else{
            b.setEnabled(false);
        }
    }

    public static void DesseleccionarFila(JTable t){
        t.clearSelection();
    }
    
    public static void NuevaVista() {
        VistaMaestroDetalle vmd = new VistaMaestroDetalle();
        vmd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vmd.setLocationRelativeTo(null);
        vmd.setVisible(true);

    }

    public static void CerrarVista(VistaMaestroDetalle vmd) {
        vmd.dispose();
        vmd.setVisible(false);
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

    public static void NuevaVistaProducto() {
        VistaProducto vp = new VistaProducto();
        vp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vp.setLocationRelativeTo(null);
        vp.setVisible(true);
    }

}
