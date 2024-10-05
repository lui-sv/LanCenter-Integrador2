package PantallasAdministrador;

import javax.swing.JOptionPane;
import DAO.DAOEquipoImplements;
import Informacion.Equipo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class PanelAgregarEquipo extends javax.swing.JPanel {
    DAOEquipoImplements Le=new DAOEquipoImplements();
    Map<String, Integer> estadoMap;
    Equipo equipo = new Equipo();
        
    public PanelAgregarEquipo() {
        initComponents();
        equipo = new Equipo();
        Le.CargarTablaEquipos(jTableEquipos);
        Le=new DAOEquipoImplements();
        leertxt();

        BotonPCGaming.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEquipo("PC");
            }
        });

        BotonPS5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEquipo("PS5nro");
            }
        });

        BotonXBOX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEquipo("XBOXnro");
            }
        });

        BotonNSwitch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEquipo("SWITCHnro");
            }
        });
        
        BotonPCGaming.setOpaque(false);
        BotonPCGaming.setContentAreaFilled(false);
        BotonPCGaming.setBorderPainted(false);

        BotonPS5.setOpaque(false);
        BotonPS5.setContentAreaFilled(false);
        BotonPS5.setBorderPainted(false);

        BotonXBOX.setOpaque(false);
        BotonXBOX.setContentAreaFilled(false);
        BotonXBOX.setBorderPainted(false);

        BotonNSwitch.setOpaque(false);
        BotonNSwitch.setContentAreaFilled(false);
        BotonNSwitch.setBorderPainted(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btt_EquiposN = new javax.swing.ButtonGroup();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEquipos = new javax.swing.JTable();
        jNSwitch = new javax.swing.JLabel();
        BotonPCGaming = new javax.swing.JToggleButton();
        BotonPS5 = new javax.swing.JToggleButton();
        BotonXBOX = new javax.swing.JToggleButton();
        BotonNSwitch = new javax.swing.JToggleButton();
        jXbox = new javax.swing.JLabel();
        jPS5 = new javax.swing.JLabel();
        jPCGaming = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtBusqueda = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabelfondo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 15, 55));
        setPreferredSize(new java.awt.Dimension(1060, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(19, 56, 235));
        jTextField2.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 21)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("  Agregar Equipos");
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

        jTableEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID EQUIPO", "NOMBRE", "ESTADO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEquipos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 180, 410, 460));

        jNSwitch.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jNSwitch.setForeground(new java.awt.Color(255, 255, 255));
        jNSwitch.setText("NINTENDO SWITCH");
        add(jNSwitch, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 610, -1, 30));

        BotonPCGaming.setBackground(new java.awt.Color(32, 31, 63));
        btt_EquiposN.add(BotonPCGaming);
        BotonPCGaming.setForeground(new java.awt.Color(32, 31, 63));
        BotonPCGaming.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PCGaming.png"))); // NOI18N
        BotonPCGaming.setBorder(null);
        add(BotonPCGaming, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 210, 210));

        BotonPS5.setBackground(new java.awt.Color(32, 31, 63));
        btt_EquiposN.add(BotonPS5);
        BotonPS5.setForeground(new java.awt.Color(32, 31, 63));
        BotonPS5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/PS5-2.png"))); // NOI18N
        BotonPS5.setBorder(null);
        add(BotonPS5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 210, 210));

        BotonXBOX.setBackground(new java.awt.Color(32, 31, 63));
        btt_EquiposN.add(BotonXBOX);
        BotonXBOX.setForeground(new java.awt.Color(32, 31, 63));
        BotonXBOX.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Xbox.png"))); // NOI18N
        BotonXBOX.setBorder(null);
        BotonXBOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonXBOXActionPerformed(evt);
            }
        });
        add(BotonXBOX, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 210, 210));

        BotonNSwitch.setBackground(new java.awt.Color(32, 31, 63));
        btt_EquiposN.add(BotonNSwitch);
        BotonNSwitch.setForeground(new java.awt.Color(32, 31, 63));
        BotonNSwitch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/NSwitch.png"))); // NOI18N
        BotonNSwitch.setBorder(null);
        BotonNSwitch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonNSwitchActionPerformed(evt);
            }
        });
        add(BotonNSwitch, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, 210, 210));

        jXbox.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jXbox.setForeground(new java.awt.Color(255, 255, 255));
        jXbox.setText("XBOXONE");
        add(jXbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 610, -1, 30));

        jPS5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jPS5.setForeground(new java.awt.Color(255, 255, 255));
        jPS5.setText("PLAY STATION 5");
        add(jPS5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 350, -1, 30));

        jPCGaming.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jPCGaming.setForeground(new java.awt.Color(255, 255, 255));
        jPCGaming.setText("PC GAMING");
        add(jPCGaming, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, -1, 30));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("BUSCAR:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, -1, 30));

        txtBusqueda.setColumns(20);
        txtBusqueda.setRows(1);
        txtBusqueda.setToolTipText("");
        txtBusqueda.setAutoscrolls(false);
        jScrollPane3.setViewportView(txtBusqueda);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 320, 30));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("SELECCIONE EL EQUIPO QUE DESEA AÑADIR:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, 30));

        jLabelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPaneles.jpg"))); // NOI18N
        add(jLabelfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void BotonXBOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonXBOXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonXBOXActionPerformed

    private void BotonNSwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonNSwitchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonNSwitchActionPerformed

    private void agregarEquipo(String nombre) {
        if (Le.agregarEquipo(nombre)) {
            JOptionPane.showMessageDialog(this, "Equipo agregado: " + nombre);
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el equipo.");
        }
        Le.CargarTablaEquipos(jTableEquipos); 
    }
    public void filtrarTabla() {
    TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTableEquipos.getModel());
    jTableEquipos.setRowSorter(sorter);

    String text = txtBusqueda.getText();
    if (text.trim().length() == 0) {
        sorter.setRowFilter(null);
    } else {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
        }
    }
    
    
    public void leertxt() {
        txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTabla();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarTabla();
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BotonNSwitch;
    private javax.swing.JToggleButton BotonPCGaming;
    private javax.swing.JToggleButton BotonPS5;
    private javax.swing.JToggleButton BotonXBOX;
    private javax.swing.ButtonGroup btt_EquiposN;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelfondo;
    private javax.swing.JLabel jNSwitch;
    private javax.swing.JLabel jPCGaming;
    private javax.swing.JLabel jPS5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableEquipos;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel jXbox;
    private javax.swing.JTextArea txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
