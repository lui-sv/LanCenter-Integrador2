/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package PantallasRecepcionista;

import DAO.DAOComprobanteImplements;
import Informacion.Comprobante;
import java.awt.Desktop;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Jhon SM
 */
public class PanelComprobantePago extends javax.swing.JPanel {

    private DAOComprobanteImplements daoComprobante;

    public PanelComprobantePago() {
        initComponents();
        daoComprobante = new DAOComprobanteImplements();
        cargarTabla(daoComprobante.obtenerTodos());
        jRBTodos.addActionListener(this::filtroActionPerformed);
        jRBBoleta.addActionListener(this::filtroActionPerformed);
        jRBFactura.addActionListener(this::filtroActionPerformed);
        jRBRecibo.addActionListener(this::filtroActionPerformed);
        jRBTodos.setSelected(true);

        jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("calendar".equals(evt.getPropertyName())) {
                    // Filtrar la tabla cuando cambia la fecha seleccionada
                    filtrarPorFecha((GregorianCalendar) evt.getNewValue());
                }
            }
        });
    }

    private void cargarTabla(List<Comprobante> comprobantes) {
        DefaultTableModel model = (DefaultTableModel) jTComprobantes.getModel();
        model.setRowCount(0);
        for (Comprobante c : comprobantes) {
            Object[] row = new Object[]{
                c.getId_comprobante(),
                c.getEquipo().getNombre_equipo(),
                c.getCliente().getNombreCliente(),
                c.getFechaComprobante(),
                c.getMonto_Comprobante(),
                c.getTipo_Comprobante()
            };
            model.addRow(row);
        }
    }

    private void filtroActionPerformed(java.awt.event.ActionEvent evt) {
        String filtro = "";
        if (evt.getSource() == jRBTodos) {
            filtro = "Todos";
        } else if (evt.getSource() == jRBBoleta) {
            filtro = "BOLETA";
        } else if (evt.getSource() == jRBFactura) {
            filtro = "FACTURA";
        } else if (evt.getSource() == jRBRecibo) {
            filtro = "RECIBO";
        }
        if (filtro.equals("Todos")) {
            cargarTabla(daoComprobante.obtenerTodos());
        } else {
            cargarTabla(daoComprobante.filtrarPorTipo(filtro));
        }
    }

    private void filtrarPorFecha(GregorianCalendar fechaSeleccionada) {
        // Convertir GregorianCalendar a Date
        Date fechaDate = fechaSeleccionada.getTime();

        // Formatear la fecha seleccionada en formato yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaEnFormato = sdf.format(fechaDate);

        // Filtrar y cargar la tabla según la fecha seleccionada
        cargarTabla(daoComprobante.filtrarPorFecha(fechaEnFormato));
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
                InputStream inputStream = getClass().getResourceAsStream("/resources/PlantillaExportarComprobantes.xls");
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

        BGTipoComprobante = new javax.swing.ButtonGroup();
        jCalendar = new com.toedter.calendar.JCalendar();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTComprobantes = new javax.swing.JTable();
        jRBBoleta = new javax.swing.JRadioButton();
        jRBFactura = new javax.swing.JRadioButton();
        jRBRecibo = new javax.swing.JRadioButton();
        jRBTodos = new javax.swing.JRadioButton();
        jButtonExportar = new javax.swing.JButton();
        jLabelFiltro = new javax.swing.JLabel();
        jLabelfondo = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCalendar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jCalendar.setDecorationBordersVisible(true);
        jCalendar.setFont(new java.awt.Font("Segoe UI", 2, 11)); // NOI18N
        jCalendar.setWeekOfYearVisible(false);
        add(jCalendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 320, 300));

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(19, 56, 235));
        jTextField2.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 21)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(255, 255, 255));
        jTextField2.setText("  Comprobante de Pago");
        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 1080, 80));

        jTComprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID COMPROBANTE", "NOMBRE EQUIPO", "NOMBRE CLIENTE", "FECHA PAGO", "MONTO", "TIPO DE COMPROBANTE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTComprobantes);
        if (jTComprobantes.getColumnModel().getColumnCount() > 0) {
            jTComprobantes.getColumnModel().getColumn(0).setResizable(false);
            jTComprobantes.getColumnModel().getColumn(1).setResizable(false);
            jTComprobantes.getColumnModel().getColumn(2).setResizable(false);
            jTComprobantes.getColumnModel().getColumn(3).setResizable(false);
            jTComprobantes.getColumnModel().getColumn(4).setResizable(false);
            jTComprobantes.getColumnModel().getColumn(5).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 680, 570));

        BGTipoComprobante.add(jRBBoleta);
        jRBBoleta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRBBoleta.setForeground(new java.awt.Color(255, 255, 255));
        jRBBoleta.setText("BOLETA");
        jRBBoleta.setContentAreaFilled(false);
        jRBBoleta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jRBBoleta.setVerifyInputWhenFocusTarget(false);
        jRBBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBBoletaActionPerformed(evt);
            }
        });
        add(jRBBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, -1, 40));

        BGTipoComprobante.add(jRBFactura);
        jRBFactura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRBFactura.setForeground(new java.awt.Color(255, 255, 255));
        jRBFactura.setText("FACTURA");
        jRBFactura.setContentAreaFilled(false);
        add(jRBFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, -1, 40));

        BGTipoComprobante.add(jRBRecibo);
        jRBRecibo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRBRecibo.setForeground(new java.awt.Color(255, 255, 255));
        jRBRecibo.setText("RECIBO");
        jRBRecibo.setContentAreaFilled(false);
        jRBRecibo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        add(jRBRecibo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 70, 40));

        BGTipoComprobante.add(jRBTodos);
        jRBTodos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRBTodos.setForeground(new java.awt.Color(255, 255, 255));
        jRBTodos.setText("TODOS");
        jRBTodos.setToolTipText("");
        jRBTodos.setContentAreaFilled(false);
        add(jRBTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 40));

        jButtonExportar.setBackground(new java.awt.Color(6, 99, 44));
        jButtonExportar.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jButtonExportar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonExportar.setText("EXPORTAR A EXCEL");
        jButtonExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportarActionPerformed(evt);
            }
        });
        add(jButtonExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, 170, 40));

        jLabelFiltro.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabelFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FILTRAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        add(jLabelFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 340, 470));

        jLabelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondoPaneles.jpg"))); // NOI18N
        add(jLabelfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jRBBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBBoletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBBoletaActionPerformed

    private void jButtonExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportarActionPerformed
        try {
            exportarExcel(jTComprobantes);
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }//GEN-LAST:event_jButtonExportarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BGTipoComprobante;
    private javax.swing.JButton jButtonExportar;
    private com.toedter.calendar.JCalendar jCalendar;
    private javax.swing.JLabel jLabelFiltro;
    private javax.swing.JLabel jLabelfondo;
    private javax.swing.JRadioButton jRBBoleta;
    private javax.swing.JRadioButton jRBFactura;
    private javax.swing.JRadioButton jRBRecibo;
    private javax.swing.JRadioButton jRBTodos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTComprobantes;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
