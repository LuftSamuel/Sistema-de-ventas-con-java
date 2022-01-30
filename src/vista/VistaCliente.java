package vista;

import controlador.ControladorCliente;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VistaCliente extends javax.swing.JFrame {

    
    
    public VistaCliente() {
        initComponents();
        ControladorCliente.ActualizarGrilla(tablaClientes);
        ControladorCliente.AjustarTabla(tablaClientes);
        ControladorCliente.ValidarNombre(txtNombre);
        ControladorCliente.ValidarCuil(txtCuil, lblAdvertenciaCuil);        
        ControladorCliente.ValidarTelefono(txtTelefono, lblAdvertenciaTelefono);
        ControladorCliente.ValidarDireccion(txtDireccion);
        ControladorCliente.ValidarCorreo(txtCorreo, lblAdvertenciaCorreo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtTelefono = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        lblAdvertenciaCuil = new javax.swing.JLabel();
        lblAdvertenciaCorreo = new javax.swing.JLabel();
        lblAdvertenciaTelefono = new javax.swing.JLabel();
        txtCuil = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txtBuscarCliente = new javax.swing.JTextField();
        lblLimpiarBusquedaCliente = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuNuevoDetalle = new javax.swing.JMenuItem();
        menuProductos = new javax.swing.JMenuItem();
        menuMaestroDetalle = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(741, 670));
        setResizable(false);
        setSize(new java.awt.Dimension(741, 670));

        jPanel1.setBackground(new java.awt.Color(41, 41, 41));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Clientes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 0, 0));

        jPanel2.setBackground(new java.awt.Color(84, 84, 84));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nuevo Cliente");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 258, -1));

        jLabel3.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nombre*");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 90, -1));

        txtNombre.setBackground(new java.awt.Color(84, 84, 84));
        txtNombre.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(153, 153, 153));
        txtNombre.setBorder(null);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        jPanel2.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 220, 30));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 220, 10));

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 110, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 110, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 110, -1));

        txtTelefono.setBackground(new java.awt.Color(84, 84, 84));
        txtTelefono.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(153, 153, 153));
        txtTelefono.setBorder(null);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });
        jPanel2.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 200, 30));

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 200, 10));

        jLabel5.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Telefono");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 90, -1));

        txtDireccion.setBackground(new java.awt.Color(84, 84, 84));
        txtDireccion.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(153, 153, 153));
        txtDireccion.setBorder(null);
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });
        jPanel2.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 220, 30));

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 220, 10));

        jLabel6.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dirección");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 90, -1));

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 200, 10));

        jLabel7.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cuil*");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 60, -1));

        txtCorreo.setBackground(new java.awt.Color(84, 84, 84));
        txtCorreo.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(153, 153, 153));
        txtCorreo.setBorder(null);
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
        });
        jPanel2.add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 200, 30));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 210, 10));

        jLabel8.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Correo");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 90, -1));

        lblAdvertenciaCuil.setBackground(new java.awt.Color(84, 84, 84));
        jPanel2.add(lblAdvertenciaCuil, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 30, 30));
        jPanel2.add(lblAdvertenciaCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 30, 30));
        jPanel2.add(lblAdvertenciaTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 30, 30));

        txtCuil.setBackground(new java.awt.Color(84, 84, 84));
        txtCuil.setFont(new java.awt.Font("Source Sans Pro Semibold", 0, 14)); // NOI18N
        txtCuil.setForeground(new java.awt.Color(153, 153, 153));
        txtCuil.setBorder(null);
        txtCuil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuilKeyReleased(evt);
            }
        });
        jPanel2.add(txtCuil, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 170, 30));

        tablaClientes.setFont(new java.awt.Font("Source Serif Pro", 0, 12)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Cuil", "Telefono", "Direccion", "Correo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaClientes.setRowHeight(25);
        tablaClientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaClientes.setShowVerticalLines(false);
        tablaClientes.getTableHeader().setReorderingAllowed(false);
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaClientesMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tablaClientes);
        if (tablaClientes.getColumnModel().getColumnCount() > 0) {
            tablaClientes.getColumnModel().getColumn(2).setMinWidth(80);
            tablaClientes.getColumnModel().getColumn(2).setMaxWidth(80);
            tablaClientes.getColumnModel().getColumn(3).setMinWidth(75);
            tablaClientes.getColumnModel().getColumn(3).setMaxWidth(75);
        }

        jLabel9.setFont(new java.awt.Font("Source Serif Pro Semibold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Buscar cliente");

        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });

        lblLimpiarBusquedaCliente.setFont(new java.awt.Font("Source Code Pro Semibold", 1, 18)); // NOI18N
        lblLimpiarBusquedaCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblLimpiarBusquedaCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLimpiarBusquedaCliente.setText("X");
        lblLimpiarBusquedaCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        lblLimpiarBusquedaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLimpiarBusquedaClienteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLimpiarBusquedaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(lblLimpiarBusquedaCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jMenu1.setText("Menu");

        menuNuevoDetalle.setText("NuevoDetalle");
        menuNuevoDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNuevoDetalleActionPerformed(evt);
            }
        });
        jMenu1.add(menuNuevoDetalle);

        menuProductos.setText("Productos");
        menuProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProductosActionPerformed(evt);
            }
        });
        jMenu1.add(menuProductos);

        menuMaestroDetalle.setText("MaestroDetalle");
        menuMaestroDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMaestroDetalleActionPerformed(evt);
            }
        });
        jMenu1.add(menuMaestroDetalle);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        ControladorCliente.AgregarModificar(tablaClientes, txtNombre.getText(), Long.parseLong(txtCuil.getText()), txtTelefono.getText(), txtDireccion.getText(), txtCorreo.getText());
        ControladorCliente.ActualizarGrilla(tablaClientes);
        ControladorCliente.AjustarTabla(tablaClientes);
        ControladorCliente.LimpiarCampos(txtNombre, txtCuil, txtTelefono, txtDireccion, txtCorreo);
        ControladorCliente.DesactivarBoton(btnGuardar);
        ControladorCliente.DesactivarBoton(btnEliminar);
        ControladorCliente.DesactivarBoton(btnCancelar);
        ControladorCliente.ActivarDesactivarTxt(txtNombre, tablaClientes);
        ControladorCliente.ActivarDesactivarTxt(txtCuil, tablaClientes);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        ControladorCliente.Eliminar(tablaClientes);
        ControladorCliente.ActualizarGrilla(tablaClientes);
        ControladorCliente.AjustarTabla(tablaClientes);
        ControladorCliente.LimpiarCampos(txtNombre, txtCuil, txtTelefono, txtDireccion, txtCorreo);
        ControladorCliente.DesactivarBoton(btnGuardar);
        ControladorCliente.DesactivarBoton(btnEliminar);
        ControladorCliente.DesactivarBoton(btnCancelar);
        ControladorCliente.ActivarDesactivarTxt(txtNombre, tablaClientes);
        ControladorCliente.ActivarDesactivarTxt(txtCuil, tablaClientes);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        ControladorCliente.ActivarDesactivarBotonGuardar(btnGuardar, txtNombre, txtCuil, txtTelefono, txtCorreo);
        ControladorCliente.ActivarDesactivarBotonCancelar(btnCancelar, txtNombre, txtCuil, tablaClientes, txtTelefono, txtDireccion, txtCorreo);
    }//GEN-LAST:event_txtNombreKeyReleased

    private void tablaClientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMousePressed
        ControladorCliente.LimpiarCampos(txtNombre, txtCuil, txtTelefono, txtDireccion, txtCorreo);
        ControladorCliente.LlenarCampos(tablaClientes, txtNombre, txtCuil, txtTelefono, txtDireccion, txtCorreo);
        ControladorCliente.ActivarBoton(btnEliminar);
        ControladorCliente.ActivarBoton(btnCancelar);
        ControladorCliente.ActivarDesactivarTxt(txtNombre, tablaClientes);
        ControladorCliente.ActivarDesactivarTxt(txtCuil, tablaClientes);
    }//GEN-LAST:event_tablaClientesMousePressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        ControladorCliente.DesseleccionarFila(tablaClientes);
        ControladorCliente.LimpiarCampos(txtNombre, txtCuil, txtTelefono, txtDireccion, txtCorreo);
        ControladorCliente.DesactivarBoton(btnGuardar);
        ControladorCliente.DesactivarBoton(btnEliminar);
        ControladorCliente.DesactivarBoton(btnCancelar);
        ControladorCliente.ActivarDesactivarTxt(txtNombre, tablaClientes);
        ControladorCliente.ActivarDesactivarTxt(txtCuil, tablaClientes);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void menuNuevoDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNuevoDetalleActionPerformed
        ControladorCliente.NuevaVistaNuevoDetalle();
        ControladorCliente.CerrarVista(this);
    }//GEN-LAST:event_menuNuevoDetalleActionPerformed

    private void menuProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProductosActionPerformed
        ControladorCliente.NuevaVistaProducto();
        ControladorCliente.CerrarVista(this);
    }//GEN-LAST:event_menuProductosActionPerformed

    private void menuMaestroDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMaestroDetalleActionPerformed
        ControladorCliente.NuevaVistaMaestroDetalle();
        ControladorCliente.CerrarVista(this);
    }//GEN-LAST:event_menuMaestroDetalleActionPerformed

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        ControladorCliente.ActivarDesactivarBotonGuardar(btnGuardar, txtNombre, txtCuil, txtTelefono, txtCorreo);
        ControladorCliente.ActivarDesactivarBotonCancelar(btnCancelar, txtNombre, txtCuil, tablaClientes, txtTelefono, txtDireccion, txtCorreo);
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        ControladorCliente.ActivarDesactivarBotonGuardar(btnGuardar, txtNombre, txtCuil, txtTelefono, txtCorreo);
        ControladorCliente.ActivarDesactivarBotonCancelar(btnCancelar, txtNombre, txtCuil, tablaClientes, txtTelefono, txtDireccion, txtCorreo);
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased
        ControladorCliente.ActivarDesactivarBotonGuardar(btnGuardar, txtNombre, txtCuil, txtTelefono, txtCorreo);
        ControladorCliente.ActivarDesactivarBotonCancelar(btnCancelar, txtNombre, txtCuil, tablaClientes, txtTelefono, txtDireccion, txtCorreo);
    }//GEN-LAST:event_txtCorreoKeyReleased

    private void txtCuilKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuilKeyReleased
        ControladorCliente.ActivarDesactivarBotonGuardar(btnGuardar, txtNombre, txtCuil, txtTelefono, txtCorreo);
        ControladorCliente.ActivarDesactivarBotonCancelar(btnCancelar, txtNombre, txtCuil, tablaClientes, txtTelefono, txtDireccion, txtCorreo);
    }//GEN-LAST:event_txtCuilKeyReleased

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        //ControladorNuevoDetalle.BuscarCliente(tablaClientes, txtBuscarCliente);
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

    private void lblLimpiarBusquedaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLimpiarBusquedaClienteMouseClicked
        //ControladorNuevoDetalle.VaciarBusqueda(txtBuscarCliente);
        //ControladorNuevoDetalle.BuscarCliente(tablaClientes, txtBuscarCliente);
    }//GEN-LAST:event_lblLimpiarBusquedaClienteMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblAdvertenciaCorreo;
    private javax.swing.JLabel lblAdvertenciaCuil;
    private javax.swing.JLabel lblAdvertenciaTelefono;
    private javax.swing.JLabel lblLimpiarBusquedaCliente;
    private javax.swing.JMenuItem menuMaestroDetalle;
    private javax.swing.JMenuItem menuNuevoDetalle;
    private javax.swing.JMenuItem menuProductos;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtCuil;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
