package PantallasAdministrador;

import DAO.DAOEstadoImplements;
import Informacion.Equipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class PanelAdministrarEstadoEquipos extends javax.swing.JPanel {

    DAOEstadoImplements daoEstado= new DAOEstadoImplements();
    DefaultTableModel tableModel;
    Equipo equipo = new Equipo();
    
    public PanelAdministrarEstadoEquipos() {
        initComponents();
        tableModel = (DefaultTableModel) jTableeEstadoEquipos.getModel();
        cargarDatos();

        jTableeEstadoEquipos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = jTableeEstadoEquipos.getSelectedRow();
                if (row != -1) {
                    jTextField1.setText(tableModel.getValueAt(row, 1).toString());
                    jTextField4.setText(tableModel.getValueAt(row, 1).toString());
                }
            }
        });

        BotonAgregarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String descripcion = jTextField3.getText();
                if (!descripcion.isEmpty()) {
                    equipo.setNombre_estado_equipo(descripcion);
                    daoEstado.agregar(equipo);
                    cargarDatos();
                    jTextField3.setText("");
                    jTextField1.setText("");
                    jTextField4.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "No escribio un nuevo estado");
                }
            }
        });

        BotonModificarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = jTableeEstadoEquipos.getSelectedRow();
                if (row != -1) {
                    int id = (int) tableModel.getValueAt(row, 0);
                    String descripcion = jTextField4.getText();
                    if (!descripcion.isEmpty()) {
                        equipo.setEstado_id_equipo(id);
                        equipo.setNombre_estado_equipo(descripcion);
                        daoEstado.actualizar(equipo);
                        cargarDatos();
                        jTextField1.setText("");
                        jTextField4.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No seleccionó un estado válido");
                }
            }
        });

        BotonEliminarEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = jTableeEstadoEquipos.getSelectedRow();
                if (row != -1) {
                    int id = (int) tableModel.getValueAt(row, 0);
                    daoEstado.eliminar(id);
                    cargarDatos();
                    jTextField1.setText("");
                    jTextField4.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "No seleccionó un estado válido");
                }
            }
        });
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableeEstadoEquipos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        BotonEliminarEstado = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        BotonAgregarEstado = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        BotonModificarEstado = new javax.swing.JButton();
        jLabelfondo = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1060, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(19, 56, 235));
        jTextField2.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 21)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("  Administrar Estado Equipo");
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

        jTableeEstadoEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ESTADO ID", "DESCRIPCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableeEstadoEquipos);
        if (jTableeEstadoEquipos.getColumnModel().getColumnCount() > 0) {
            jTableeEstadoEquipos.getColumnModel().getColumn(0).setResizable(false);
            jTableeEstadoEquipos.getColumnModel().getColumn(1).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 490, 520));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("MODIFICAR ESTADO:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 350, -1, 30));

        jTextField1.setEditable(false);
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 510, 240, 30));

        BotonEliminarEstado.setBackground(new java.awt.Color(102, 0, 0));
        BotonEliminarEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BotonEliminarEstado.setForeground(new java.awt.Color(255, 255, 255));
        BotonEliminarEstado.setText("ELIMINAR");
        BotonEliminarEstado.setBorder(null);
        BotonEliminarEstado.setBorderPainted(false);
        BotonEliminarEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(BotonEliminarEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 510, 140, 30));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ELIMINAR ESTADO:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 460, -1, 30));
        add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 290, 240, 30));

        BotonAgregarEstado.setBackground(new java.awt.Color(102, 0, 0));
        BotonAgregarEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BotonAgregarEstado.setForeground(new java.awt.Color(255, 255, 255));
        BotonAgregarEstado.setText("AGREGAR");
        BotonAgregarEstado.setBorder(null);
        BotonAgregarEstado.setBorderPainted(false);
        BotonAgregarEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(BotonAgregarEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 290, 140, 30));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("AGREGAR NUEVO ESTADO:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, -1, 30));
        add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 400, 240, 30));

        BotonModificarEstado.setBackground(new java.awt.Color(102, 0, 0));
        BotonModificarEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BotonModificarEstado.setForeground(new java.awt.Color(255, 255, 255));
        BotonModificarEstado.setText("MODIFICAR");
        BotonModificarEstado.setBorder(null);
        BotonModificarEstado.setBorderPainted(false);
        BotonModificarEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(BotonModificarEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 400, 140, 30));

        jLabelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPaneles.jpg"))); // NOI18N
        add(jLabelfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void cargarDatos() {
        tableModel.setRowCount(0);
        List<Equipo> lista = daoEstado.obtenerTodos();
        for (Equipo equipo : lista) {
            tableModel.addRow(new Object[]{equipo.getEstado_id_equipo(), equipo.getNombre_estado_equipo()});
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregarEstado;
    private javax.swing.JButton BotonEliminarEstado;
    private javax.swing.JButton BotonModificarEstado;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelfondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableeEstadoEquipos;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
