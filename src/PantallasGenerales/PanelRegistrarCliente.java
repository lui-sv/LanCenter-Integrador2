package PantallasGenerales;

import DAO.DAOClienteImplements;
import DAOInterfaces.DAOCliente;
import Informacion.Cliente;
import javax.swing.JOptionPane;


public class PanelRegistrarCliente extends javax.swing.JPanel {

    public PanelRegistrarCliente() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ApellidoPaterno = new javax.swing.JLabel();
        CorreoElectronico = new javax.swing.JLabel();
        NumeroDNI = new javax.swing.JLabel();
        nombres = new javax.swing.JLabel();
        BotonSubirImagen = new javax.swing.JButton();
        jTextFieldApellidoPCliente = new javax.swing.JTextField();
        jTextFieldNumeroDNI = new javax.swing.JTextField();
        jTextFieldCorreoElectronico = new javax.swing.JTextField();
        jTextFieldNombreCliente = new javax.swing.JTextField();
        BotonRegistrarCliente = new javax.swing.JButton();
        ApellidoMaterno = new javax.swing.JLabel();
        jTextFieldApellidoMCliente = new javax.swing.JTextField();
        jLabelfondo = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1060, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(19, 56, 235));
        jTextField2.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 21)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("  Registrar Clientes");
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/REGISTRARCLIENTE.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 400, 400));

        ApellidoPaterno.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        ApellidoPaterno.setForeground(new java.awt.Color(255, 255, 255));
        ApellidoPaterno.setText("Apellido Paterno:");
        add(ApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, -1, 30));

        CorreoElectronico.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        CorreoElectronico.setForeground(new java.awt.Color(255, 255, 255));
        CorreoElectronico.setText("Correo Electronico:");
        add(CorreoElectronico, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 510, -1, 30));

        NumeroDNI.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        NumeroDNI.setForeground(new java.awt.Color(255, 255, 255));
        NumeroDNI.setText("Numero de DNI:");
        add(NumeroDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 420, -1, 30));

        nombres.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        nombres.setForeground(new java.awt.Color(255, 255, 255));
        nombres.setText("Nombres:");
        add(nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, -1, 30));

        BotonSubirImagen.setBackground(new java.awt.Color(102, 102, 102));
        BotonSubirImagen.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        BotonSubirImagen.setForeground(new java.awt.Color(255, 255, 255));
        BotonSubirImagen.setText("Subir imagen...");
        BotonSubirImagen.setBorder(null);
        BotonSubirImagen.setBorderPainted(false);
        BotonSubirImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(BotonSubirImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 560, 210, 30));
        add(jTextFieldApellidoPCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 280, 370, 40));
        add(jTextFieldNumeroDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 460, 370, 40));
        add(jTextFieldCorreoElectronico, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 550, 370, 40));
        add(jTextFieldNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 370, 40));

        BotonRegistrarCliente.setBackground(new java.awt.Color(102, 0, 0));
        BotonRegistrarCliente.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        BotonRegistrarCliente.setForeground(new java.awt.Color(255, 255, 255));
        BotonRegistrarCliente.setText("REGISTRAR");
        BotonRegistrarCliente.setBorder(null);
        BotonRegistrarCliente.setBorderPainted(false);
        BotonRegistrarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonRegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRegistrarClienteActionPerformed(evt);
            }
        });
        add(BotonRegistrarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 610, 200, 60));

        ApellidoMaterno.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        ApellidoMaterno.setForeground(new java.awt.Color(255, 255, 255));
        ApellidoMaterno.setText("Apellido Materno:");
        add(ApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, -1, 30));
        add(jTextFieldApellidoMCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 370, 370, 40));

        jLabelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPaneles.jpg"))); // NOI18N
        add(jLabelfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void BotonRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegistrarClienteActionPerformed
        if (validarCampos()) {
            Cliente cliente = new Cliente();
            cliente.setDNICliente(jTextFieldNumeroDNI.getText());
            cliente.setNombreCliente(jTextFieldNombreCliente.getText());
            cliente.setApePaterno(jTextFieldApellidoPCliente.getText());
            cliente.setApeMaterno(jTextFieldApellidoMCliente.getText());
            cliente.setCorreoCliente(jTextFieldCorreoElectronico.getText());

            DAOCliente daoCliente = new DAOClienteImplements();
            boolean registrado = daoCliente.registrarCliente(cliente);

            if (registrado) {
                JOptionPane.showMessageDialog(this, "Cliente registrado con éxito");
                jTextFieldNombreCliente.setText("");
                jTextFieldApellidoMCliente.setText("");
                jTextFieldApellidoPCliente.setText("");
                jTextFieldNumeroDNI.setText("");
                jTextFieldCorreoElectronico.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "El DNI ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BotonRegistrarClienteActionPerformed

    private boolean validarCampos() {
        String nombre = jTextFieldNombreCliente.getText().trim();
        String apellidoP = jTextFieldApellidoPCliente.getText().trim();
        String apellidoM = jTextFieldApellidoMCliente.getText().trim();
        String dni = jTextFieldNumeroDNI.getText().trim();
        String correo = jTextFieldCorreoElectronico.getText().trim();
        

        if (nombre.isEmpty() || apellidoP.isEmpty() || apellidoM.isEmpty() || dni.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.");
            return false;
        }
        if (dni.length() != 8 || !dni.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El DNI debe contener 8 dígitos.");
            return false;
        }
        if (!correo.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            JOptionPane.showMessageDialog(this, "El correo electrónico no tiene un formato válido.");
            return false;
        }
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ApellidoMaterno;
    private javax.swing.JLabel ApellidoPaterno;
    private javax.swing.JButton BotonRegistrarCliente;
    private javax.swing.JButton BotonSubirImagen;
    private javax.swing.JLabel CorreoElectronico;
    private javax.swing.JLabel NumeroDNI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelfondo;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextFieldApellidoMCliente;
    private javax.swing.JTextField jTextFieldApellidoPCliente;
    private javax.swing.JTextField jTextFieldCorreoElectronico;
    private javax.swing.JTextField jTextFieldNombreCliente;
    private javax.swing.JTextField jTextFieldNumeroDNI;
    private javax.swing.JLabel nombres;
    // End of variables declaration//GEN-END:variables
}
