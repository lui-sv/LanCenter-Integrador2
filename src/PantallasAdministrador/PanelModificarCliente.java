/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PantallasAdministrador;

import DAO.DAOClienteImplements;
import Informacion.Cliente;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jhon SM
 */
public class PanelModificarCliente extends javax.swing.JPanel {
    
    DAOClienteImplements MC = new DAOClienteImplements();
    Cliente cliente = new Cliente();
    
    public PanelModificarCliente() {
        initComponents();
        cargarTabla(MC.obtenerClientes());
        
        // Agregar el ListSelectionListener a la tabla
        jTableCLientes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    llenarCamposConDatosSeleccionados();
                }
            }
        });
    }

    private void cargarTabla(List<Cliente> clientes) {
        DefaultTableModel model = (DefaultTableModel) jTableCLientes.getModel();
        model.setRowCount(0);
        for (Cliente c : clientes) {
            model.addRow(new Object[]{c.getDNICliente(), c.getNombreCliente(), c.getApePaterno(), c.getApeMaterno(), c.getCorreoCliente()});
        }
    }

    private void llenarCamposConDatosSeleccionados() {
        int selectedRow = jTableCLientes.getSelectedRow();
        if (selectedRow >= 0) {
            cliente.setDNICliente(jTableCLientes.getValueAt(selectedRow, 0).toString());
            cliente.setNombreCliente(jTableCLientes.getValueAt(selectedRow, 1).toString());
            cliente.setApePaterno(jTableCLientes.getValueAt(selectedRow, 2).toString());
            cliente.setApeMaterno(jTableCLientes.getValueAt(selectedRow, 3).toString());
            cliente.setCorreoCliente(jTableCLientes.getValueAt(selectedRow, 4).toString());

            jTextFieldNumeroDNI.setText(cliente.getDNICliente());
            jTextFieldNombreCliente.setText(cliente.getNombreCliente());
            jTextFieldApellidoPCliente.setText(cliente.getApePaterno());
            jTextFieldApellidoMCliente.setText(cliente.getApeMaterno());
            jTextFieldCorreoElectronico.setText(cliente.getCorreoCliente());
        }
    }

    private boolean validarCamposLlenos() {
        return !jTextFieldNumeroDNI.getText().isEmpty() &&
               !jTextFieldNombreCliente.getText().isEmpty() &&
               !jTextFieldApellidoPCliente.getText().isEmpty() &&
               !jTextFieldApellidoMCliente.getText().isEmpty() &&
               !jTextFieldCorreoElectronico.getText().isEmpty();
    }

    private boolean hayCambiosRealizados() {
        return !cliente.getDNICliente().equals(jTextFieldNumeroDNI.getText()) ||
               !cliente.getNombreCliente().equals(jTextFieldNombreCliente.getText()) ||
               !cliente.getApePaterno().equals(jTextFieldApellidoPCliente.getText()) ||
               !cliente.getApeMaterno().equals(jTextFieldApellidoMCliente.getText()) ||
               !cliente.getCorreoCliente().equals(jTextFieldCorreoElectronico.getText());
    }

    private void limpiarCampos() {
        jTextFieldNumeroDNI.setText("");
        jTextFieldNombreCliente.setText("");
        jTextFieldApellidoPCliente.setText("");
        jTextFieldApellidoMCliente.setText("");
        jTextFieldCorreoElectronico.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btmodificar = new javax.swing.JButton();
        ApellidoPaterno = new javax.swing.JLabel();
        CorreoElectronico = new javax.swing.JLabel();
        NumeroDNI = new javax.swing.JLabel();
        nombres = new javax.swing.JLabel();
        jTextFieldApellidoPCliente = new javax.swing.JTextField();
        jTextFieldNumeroDNI = new javax.swing.JTextField();
        jTextFieldCorreoElectronico = new javax.swing.JTextField();
        jTextFieldNombreCliente = new javax.swing.JTextField();
        ApellidoMaterno = new javax.swing.JLabel();
        jTextFieldApellidoMCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCLientes = new javax.swing.JTable();
        jLabelfondo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(10, 31, 137));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(19, 56, 235));
        jTextField2.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 21)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("  Modificar Clientes");
        jTextField2.setAutoscrolls(false);
        jTextField2.setBorder(null);
        jTextField2.setFocusable(false);
        jTextField2.setRequestFocusEnabled(false);
        jTextField2.setVerifyInputWhenFocusTarget(false);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 1080, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoAUR.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, -1, 110));

        btmodificar.setBackground(new java.awt.Color(102, 0, 0));
        btmodificar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btmodificar.setForeground(new java.awt.Color(255, 255, 255));
        btmodificar.setText("Modificar");
        btmodificar.setBorder(null);
        btmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmodificarActionPerformed(evt);
            }
        });
        add(btmodificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 610, 170, 43));

        ApellidoPaterno.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        ApellidoPaterno.setForeground(new java.awt.Color(255, 255, 255));
        ApellidoPaterno.setText("Apellido Paterno:");
        add(ApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, -1, 30));

        CorreoElectronico.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        CorreoElectronico.setForeground(new java.awt.Color(255, 255, 255));
        CorreoElectronico.setText("Correo Electronico:");
        add(CorreoElectronico, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, -1, 30));

        NumeroDNI.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        NumeroDNI.setForeground(new java.awt.Color(255, 255, 255));
        NumeroDNI.setText("Numero de DNI:");
        add(NumeroDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 400, -1, 30));

        nombres.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        nombres.setForeground(new java.awt.Color(255, 255, 255));
        nombres.setText("Nombres:");
        add(nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, -1, 30));
        add(jTextFieldApellidoPCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 260, 370, 40));
        add(jTextFieldNumeroDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 440, 370, 40));
        add(jTextFieldCorreoElectronico, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 530, 370, 40));
        add(jTextFieldNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, 370, 40));

        ApellidoMaterno.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        ApellidoMaterno.setForeground(new java.awt.Color(255, 255, 255));
        ApellidoMaterno.setText("Apellido Materno:");
        add(ApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, -1, 30));
        add(jTextFieldApellidoMCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, 370, 40));

        jTableCLientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "DNI", "Nombres", "Apellido Paterno", "Apellido Materno", "Correo Electronico"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableCLientes);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 560, 430));

        jLabelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPaneles.jpg"))); // NOI18N
        add(jLabelfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void btmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmodificarActionPerformed
        if (validarCamposLlenos()) {
            if (hayCambiosRealizados()) {
                Cliente clienteModificado = new Cliente();
                clienteModificado.setDNICliente(jTextFieldNumeroDNI.getText());
                clienteModificado.setNombreCliente(jTextFieldNombreCliente.getText());
                clienteModificado.setApePaterno(jTextFieldApellidoPCliente.getText());
                clienteModificado.setApeMaterno(jTextFieldApellidoMCliente.getText());
                clienteModificado.setCorreoCliente(jTextFieldCorreoElectronico.getText());

                boolean exito = MC.modificarCliente(clienteModificado);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Cliente modificado exitosamente.");
                    cargarTabla(MC.obtenerClientes());
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar cliente.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se han realizado cambios.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.");
        }        
    }//GEN-LAST:event_btmodificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ApellidoMaterno;
    private javax.swing.JLabel ApellidoPaterno;
    private javax.swing.JLabel CorreoElectronico;
    private javax.swing.JLabel NumeroDNI;
    private javax.swing.JButton btmodificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelfondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCLientes;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextFieldApellidoMCliente;
    private javax.swing.JTextField jTextFieldApellidoPCliente;
    private javax.swing.JTextField jTextFieldCorreoElectronico;
    private javax.swing.JTextField jTextFieldNombreCliente;
    private javax.swing.JTextField jTextFieldNumeroDNI;
    private javax.swing.JLabel nombres;
    // End of variables declaration//GEN-END:variables
}
