package PantallasRecepcionista;

import DAO.DAOEquipoImplements;
import Informacion.Equipo;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class PanelListarEquipos extends javax.swing.JPanel {

    DAOEquipoImplements Le=new DAOEquipoImplements();
    Map<String, Integer> estadoMap;
     // Crear un objeto Equipo y asignarle los valores
        Equipo equipo = new Equipo();
        
    public PanelListarEquipos() {
        initComponents();
        Le.CargarTablaEquipos(jTableEquipos);
       estadoMap = Le.ListarComboBox(cbestado);
        leertxt();
        controlCb();
    }
    
    public void controlCb(){
          jTableEquipos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int filaSeleccionada = jTableEquipos.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        String estadoSeleccionado = (String) jTableEquipos.getValueAt(filaSeleccionada, 2);
                        if(estadoSeleccionado.equals("LIBRE")){
                            cbestado.setSelectedItem("FUERA DE SERVICIO");
                            
                        }
                        
                        if(estadoSeleccionado.equals("FUERA DE SERVICIO")){
                            cbestado.setSelectedItem("LIBRE");
                            
                        }
                        

                        String nombreEquipo = (String) jTableEquipos.getValueAt(filaSeleccionada, 1);
                        txtnomequipo.setText(nombreEquipo);
                    }
                }
            }
        });
        
    }
    
    public void exportarExcel(JTable t) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();

                // Cargar la plantilla desde los recursos del paquete
                InputStream inputStream = getClass().getResourceAsStream("/resources/PlantillaListarEquipos.xls");
                Workbook libro = new HSSFWorkbook(inputStream);
                Sheet hoja = libro.getSheetAt(0); // Suponemos que los datos se escriben en la primera hoja
                inputStream.close();

                // Escribir los datos del JTable en la hoja
                int filaInicio = 6; // Comenzar en la fila 7 (índice 6)
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.getRow(filaInicio);
                    if (fila == null) {
                        fila = hoja.createRow(filaInicio);
                    }
                    int colExcel = 1; // Empezar en la columna B (índice 1)
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.getCell(colExcel);
                        if (celda == null) {
                            celda = fila.createCell(colExcel);
                        }
                        Object value = t.getValueAt(f, c);
                        if (value instanceof Double) {
                            celda.setCellValue((Double) value);
                        } else if (value instanceof Float) {
                            celda.setCellValue((Float) value);
                        } else if (value instanceof Boolean) {
                            celda.setCellValue((Boolean) value);
                        } else {
                            if (value != null) {
                                celda.setCellValue(value.toString());
                            } else {
                                celda.setCellValue("");  // O asigna otro valor predeterminado
                            }
                        }
                        colExcel++;
                    }
                    filaInicio++;
                }
                // Crear un estilo de celda con fondo blanco
                CellStyle estiloBlanco = libro.createCellStyle();
                estiloBlanco.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                estiloBlanco.setFillPattern((short) FillPatternType.SOLID_FOREGROUND.ordinal());

                // Hacer blancas las celdas por debajo de los datos exportados
                int ultimaFilaUtilizada = filaInicio - 1;
                int ultimaFilaHoja = hoja.getLastRowNum();
                for (int i = ultimaFilaUtilizada + 1; i <= ultimaFilaHoja; i++) {
                    Row row = hoja.getRow(i);
                    if (row == null) {
                        row = hoja.createRow(i);
                    }
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        Cell cell = row.getCell(j);
                        if (cell == null) {
                            cell = row.createCell(j);
                        }
                        cell.setCellStyle(estiloBlanco);
                    }
                }
                // Guardar el archivo modificado
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                libro.write(archivo);
                archivo.close();

                // Abrir el archivo guardado
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEquipos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtBusqueda = new javax.swing.JTextArea();
        btnActualizar = new javax.swing.JButton();
        cbestado = new javax.swing.JComboBox<>();
        btn_exporExcel = new javax.swing.JButton();
        txtnomequipo = new javax.swing.JTextField();
        jLabelfondo = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1060, 720));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(19, 56, 235));
        jTextField2.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 21)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("  Equipos");
        jTextField2.setBorder(null);
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
        if (jTableEquipos.getColumnModel().getColumnCount() > 0) {
            jTableEquipos.getColumnModel().getColumn(0).setResizable(false);
            jTableEquipos.getColumnModel().getColumn(1).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 790, 490));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BUSCAR :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, 30));

        txtBusqueda.setColumns(20);
        txtBusqueda.setRows(1);
        txtBusqueda.setToolTipText("");
        txtBusqueda.setAutoscrolls(false);
        jScrollPane3.setViewportView(txtBusqueda);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 380, 30));

        btnActualizar.setBackground(new java.awt.Color(102, 0, 0));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(null);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 300, 110, 40));

        add(cbestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 180, 160, -1));

        btn_exporExcel.setBackground(new java.awt.Color(6, 99, 44));
        btn_exporExcel.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        btn_exporExcel.setForeground(new java.awt.Color(255, 255, 255));
        btn_exporExcel.setText("EXPORTAR A EXCEL");
        btn_exporExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exporExcelActionPerformed(evt);
            }
        });
        add(btn_exporExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 100, -1, 40));
        add(txtnomequipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 250, 160, -1));

        jLabelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPaneles.jpg"))); // NOI18N
        add(jLabelfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
       int filaSeleccionada = jTableEquipos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione una fila.");
            return;
        }

        // Obtengo el ID del equipo desde la tabla
        int equipoId = (int) jTableEquipos.getValueAt(filaSeleccionada, 0);

        // Obtengo el estado seleccionado desde el ComboBox
        String estadoSeleccionado = (String) cbestado.getSelectedItem();

        // Obtengo el estado_id correspondiente al estado seleccionado
        Integer estadoId = estadoMap.get(estadoSeleccionado);
        if (estadoId == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un estado válido.");
            return;
        }

       
        equipo.setId_equipo(equipoId);
        equipo.setEstado_id_equipo(estadoId);

        // Actualizar el estado del equipo en la base de datos
        boolean actualizado = Le.actualizarEstado(equipo);

        // Solo actualizar la tabla si la base de datos se actualizó correctamente
        if (actualizado) {
            jTableEquipos.setValueAt(estadoSeleccionado, filaSeleccionada, 2);
            JOptionPane.showMessageDialog(null, "El estado del equipo ha sido actualizado exitosamente.");
   
        } 
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btn_exporExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exporExcelActionPerformed
    try {
            exportarExcel(jTableEquipos);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }//GEN-LAST:event_btn_exporExcelActionPerformed

    
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
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btn_exporExcel;
    private javax.swing.JComboBox<String> cbestado;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelfondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableEquipos;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextArea txtBusqueda;
    private javax.swing.JTextField txtnomequipo;
    // End of variables declaration//GEN-END:variables
}
