/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PantallasRecepcionista;

import DAO.DAOClienteImplements;
import Informacion.Cliente;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
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

public class PanelListarClientes extends javax.swing.JPanel {

   private DAOClienteImplements DaoListarClientes; 

    public PanelListarClientes() {
        initComponents();
        DaoListarClientes = new DAOClienteImplements();
        cargarTabla(DaoListarClientes.obtenerClientes());
        leertxt();
    }
    
    private void cargarTabla(List<Cliente> cliente) {
        DefaultTableModel model = (DefaultTableModel) jTableCLientes.getModel();
        model.setRowCount(0);
        for (Cliente c : cliente) {
            model.addRow(new Object[]{c.getDNICliente(), c.getNombreCliente(), c.getCorreoCliente(), c.getMinutosGuardados()});
        }
    }
 

    private void filtrarTabla() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTableCLientes.getModel());
        jTableCLientes.setRowSorter(sorter);

        String text = txtBusqueda.getText();
        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            // Filtrar por la columna "Curso"
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
        }
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
                InputStream inputStream = getClass().getResourceAsStream("/resources/PlantillaListarClientes.xls");
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
       private void leertxt() {
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Bttn_ExportarExcel = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtBusqueda = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCLientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Bttn_ExportarExcel.setBackground(new java.awt.Color(6, 99, 44));
        Bttn_ExportarExcel.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        Bttn_ExportarExcel.setForeground(new java.awt.Color(255, 255, 255));
        Bttn_ExportarExcel.setText("EXPORTAR A EXCEL");
        Bttn_ExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bttn_ExportarExcelActionPerformed(evt);
            }
        });
        jPanel1.add(Bttn_ExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(863, 100, -1, 40));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(19, 56, 235));
        jTextField2.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 21)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("  Clientes");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 1080, 80));

        txtBusqueda.setColumns(1);
        txtBusqueda.setLineWrap(true);
        txtBusqueda.setTabSize(0);
        txtBusqueda.setToolTipText("");
        txtBusqueda.setWrapStyleWord(true);
        txtBusqueda.setAutoscrolls(false);
        jScrollPane3.setViewportView(txtBusqueda);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 380, 30));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BUSCAR :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, -1, 30));

        jTableCLientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DNI", "NOMBRE", "CORREO", "MINUTOS ACUMULADOS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableCLientes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 950, 540));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPaneles.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed

    }//GEN-LAST:event_jTextField2ActionPerformed

    private void Bttn_ExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bttn_ExportarExcelActionPerformed
        try {
            exportarExcel(jTableCLientes);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }//GEN-LAST:event_Bttn_ExportarExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bttn_ExportarExcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableCLientes;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextArea txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
