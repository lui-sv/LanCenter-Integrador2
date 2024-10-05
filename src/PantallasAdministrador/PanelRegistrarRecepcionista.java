package PantallasAdministrador;

import PantallasGenerales.*;
import DAO.DAOClienteImplements;
import DAO.DAORecepcionistaImplements;
import DAOInterfaces.DAOCliente;
import Informacion.Usuario;
import javax.swing.JOptionPane;


public class PanelRegistrarRecepcionista extends javax.swing.JPanel {

    DAO.DAORecepcionistaImplements Re=new DAORecepcionistaImplements();
    Usuario usu=new Usuario();
    
    public PanelRegistrarRecepcionista() {
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
        txtnombres = new javax.swing.JTextField();
        txtapema = new javax.swing.JTextField();
        txtcorreo = new javax.swing.JTextField();
        txtnomusu = new javax.swing.JTextField();
        BotonRegistrarCliente = new javax.swing.JButton();
        ApellidoMaterno = new javax.swing.JLabel();
        txtapepa = new javax.swing.JTextField();
        CorreoElectronico1 = new javax.swing.JLabel();
        txtdirec = new javax.swing.JTextField();
        CorreoElectronico2 = new javax.swing.JLabel();
        txttelef = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ApellidoMaterno1 = new javax.swing.JLabel();
        txtcontra = new javax.swing.JTextField();
        jLabelfondo = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1060, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(19, 56, 235));
        jTextField2.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 21)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("  Registrar Recepcionista");
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

        ApellidoPaterno.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        ApellidoPaterno.setForeground(new java.awt.Color(255, 255, 255));
        ApellidoPaterno.setText("Contraseña:");
        add(ApellidoPaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, -1, 30));

        CorreoElectronico.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        CorreoElectronico.setForeground(new java.awt.Color(255, 255, 255));
        CorreoElectronico.setText("Correo");
        add(CorreoElectronico, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 540, -1, 30));

        NumeroDNI.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        NumeroDNI.setForeground(new java.awt.Color(255, 255, 255));
        NumeroDNI.setText("Apellido Materno:");
        add(NumeroDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, -1, 30));

        nombres.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        nombres.setForeground(new java.awt.Color(255, 255, 255));
        nombres.setText("Nombres de Usuario:");
        add(nombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, -1, 30));

        BotonSubirImagen.setBackground(new java.awt.Color(102, 102, 102));
        BotonSubirImagen.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        BotonSubirImagen.setForeground(new java.awt.Color(255, 255, 255));
        BotonSubirImagen.setText("Subir imagen...");
        BotonSubirImagen.setBorder(null);
        BotonSubirImagen.setBorderPainted(false);
        BotonSubirImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonSubirImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSubirImagenActionPerformed(evt);
            }
        });
        add(BotonSubirImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 560, 210, 30));
        add(txtnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, 370, 30));
        add(txtapema, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, 370, 30));
        add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 570, 370, 30));
        add(txtnomusu, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 370, 30));

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
        add(BotonRegistrarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 640, 200, 60));

        ApellidoMaterno.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        ApellidoMaterno.setForeground(new java.awt.Color(255, 255, 255));
        ApellidoMaterno.setText("Nombres:");
        add(ApellidoMaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, -1, 30));
        add(txtapepa, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 370, 30));

        CorreoElectronico1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        CorreoElectronico1.setForeground(new java.awt.Color(255, 255, 255));
        CorreoElectronico1.setText("Direccion:");
        add(CorreoElectronico1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 420, -1, 30));
        add(txtdirec, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 370, 30));

        CorreoElectronico2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        CorreoElectronico2.setForeground(new java.awt.Color(255, 255, 255));
        CorreoElectronico2.setText("Telefono:");
        add(CorreoElectronico2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 480, -1, 30));
        add(txttelef, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 510, 370, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 1, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("ROL:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 610, -1, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Recepcionista");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 610, -1, -1));

        ApellidoMaterno1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        ApellidoMaterno1.setForeground(new java.awt.Color(255, 255, 255));
        ApellidoMaterno1.setText("Apellido Paterno:");
        add(ApellidoMaterno1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 300, -1, 30));
        add(txtcontra, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 370, 30));

        jLabelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPaneles.jpg"))); // NOI18N
        add(jLabelfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void BotonRegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegistrarClienteActionPerformed
        if (validarCampos()) {
            
            usu.setNombre_usuario(txtnomusu.getText());
            usu.setClave_usuario(txtcontra.getText());
            usu.setNombres_usu(txtnombres.getText());
            usu.setApellidoPaterno_usu(txtapepa.getText());
            usu.setApellidoMaterno_usu(txtapema.getText());
            usu.setDireccion_usu(txtdirec.getText());
            usu.setTelefono_usu(txttelef.getText());
            usu.setCorreo_usu(txtcorreo.getText());
           

            boolean registrado = Re.agregarRecepcionista(usu);

            if (registrado) {
                JOptionPane.showMessageDialog(this, "Recepcionista registrado con éxito");
                txtnomusu.setText("");
                txtcontra.setText("");
                txtnombres.setText("");
                txtapepa.setText("");
                txtapema.setText("");
                txtdirec.setText("");
                txttelef.setText("");
                txtcorreo.setText("");
                
            } 
        }
    }//GEN-LAST:event_BotonRegistrarClienteActionPerformed

    private void BotonSubirImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSubirImagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonSubirImagenActionPerformed

    private boolean validarCampos() {
        String nombreusu = txtnomusu.getText().trim();
        String contra = txtcontra.getText().trim();
        String nombres = txtnombres.getText().trim();
        String apellidoP = txtapepa.getText().trim();
        String apellidoM = txtapepa.getText().trim();
        String direc = txtdirec.getText().trim();
        String telef = txttelef.getText().trim();
        String correo = txtcorreo.getText().trim();
        

        if (nombreusu.isEmpty() || contra.isEmpty() ||  nombres.isEmpty() || apellidoP.isEmpty() || apellidoM.isEmpty() || direc.isEmpty() || telef.isEmpty() || correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.");
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
    private javax.swing.JLabel ApellidoMaterno1;
    private javax.swing.JLabel ApellidoPaterno;
    private javax.swing.JButton BotonRegistrarCliente;
    private javax.swing.JButton BotonSubirImagen;
    private javax.swing.JLabel CorreoElectronico;
    private javax.swing.JLabel CorreoElectronico1;
    private javax.swing.JLabel CorreoElectronico2;
    private javax.swing.JLabel NumeroDNI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabelfondo;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel nombres;
    private javax.swing.JTextField txtapema;
    private javax.swing.JTextField txtapepa;
    private javax.swing.JTextField txtcontra;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdirec;
    private javax.swing.JTextField txtnombres;
    private javax.swing.JTextField txtnomusu;
    private javax.swing.JTextField txttelef;
    // End of variables declaration//GEN-END:variables
}
