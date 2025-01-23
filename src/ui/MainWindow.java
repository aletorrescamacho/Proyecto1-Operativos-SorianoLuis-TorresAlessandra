/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import entities.Proceso;
import javax.swing.JOptionPane;

/**
 *
 * @author Aless
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        
        
         TipoCB.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            // Obtener el elemento seleccionado en el ComboBox
            String tipoSeleccionado = (String) TipoCB.getSelectedItem();

            // Comprobar si es "CPU bound" o "I/O bound"
            if ("CPU bound".equals(tipoSeleccionado)) {
                // Deshabilitar los spinners de ciclos para excepciones
                CiclosParaGenSpinner.setEnabled(false);
                CiclosParaSatisExcSpinner.setEnabled(false);
            } else if ("I/O bound".equals(tipoSeleccionado)) {
                // Habilitar los spinners de ciclos para excepciones
                CiclosParaGenSpinner.setEnabled(true);
                CiclosParaSatisExcSpinner.setEnabled(true);
            }
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

        TabContainer = new javax.swing.JTabbedPane();
        jPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        TituloCrearProceso = new javax.swing.JLabel();
        NombreTF = new javax.swing.JTextField();
        NombreLabel = new javax.swing.JLabel();
        CantInstruccionesSpinner = new javax.swing.JSpinner();
        CantInstruccionesLabel = new javax.swing.JLabel();
        TipoLabel = new javax.swing.JLabel();
        TipoCB = new javax.swing.JComboBox<>();
        CrearProcesoButton = new javax.swing.JButton();
        CiclosParaSatisExcSpinner = new javax.swing.JSpinner();
        CiclosParaGenSpinner = new javax.swing.JSpinner();
        CiclosParaSatisExcSpinner1 = new javax.swing.JLabel();
        CiclosParaSatisExcSpinner2 = new javax.swing.JLabel();
        CiclosParaGenSpinner1 = new javax.swing.JLabel();
        CiclosParaGenSpinner2 = new javax.swing.JLabel();
        ConfiguracionTab = new javax.swing.JPanel();
        EstadisticasTab = new javax.swing.JPanel();
        GraficosTab = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel.setBackground(new java.awt.Color(192, 204, 255));

        TituloCrearProceso.setText("Crear Proceso");

        NombreTF.setToolTipText("Ingresa el nombre");
        NombreTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreTFActionPerformed(evt);
            }
        });

        NombreLabel.setText("Nombre:");

        CantInstruccionesLabel.setText("Cant. Instrucciones:");

        TipoLabel.setText("Tipo:");

        TipoCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "I/O bound", "CPU bound" }));

        CrearProcesoButton.setText("Crear Proceso");
        CrearProcesoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearProcesoButtonActionPerformed(evt);
            }
        });

        CiclosParaSatisExcSpinner1.setText("Ciclos para Satisfacer");

        CiclosParaSatisExcSpinner2.setText("Excepción:");

        CiclosParaGenSpinner1.setText("Ciclos para Generar");

        CiclosParaGenSpinner2.setText("Excepción:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(TituloCrearProceso))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(NombreLabel)
                        .addGap(18, 18, 18)
                        .addComponent(NombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(CantInstruccionesLabel)
                        .addGap(18, 18, 18)
                        .addComponent(CantInstruccionesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(TipoLabel)
                        .addGap(18, 18, 18)
                        .addComponent(TipoCB, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(CiclosParaGenSpinner2))
                            .addComponent(CiclosParaGenSpinner1))
                        .addGap(16, 16, 16)
                        .addComponent(CiclosParaGenSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CiclosParaSatisExcSpinner1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(CiclosParaSatisExcSpinner2)))
                        .addGap(6, 6, 6)
                        .addComponent(CiclosParaSatisExcSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(CrearProcesoButton)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(TituloCrearProceso)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(NombreLabel))
                    .addComponent(NombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(CantInstruccionesLabel))
                    .addComponent(CantInstruccionesSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(TipoLabel))
                    .addComponent(TipoCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(CiclosParaGenSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CiclosParaGenSpinner1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(CiclosParaGenSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CiclosParaSatisExcSpinner1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CiclosParaSatisExcSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CiclosParaSatisExcSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addComponent(CrearProcesoButton)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(448, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        TabContainer.addTab("Simulador", jPanel);

        javax.swing.GroupLayout ConfiguracionTabLayout = new javax.swing.GroupLayout(ConfiguracionTab);
        ConfiguracionTab.setLayout(ConfiguracionTabLayout);
        ConfiguracionTabLayout.setHorizontalGroup(
            ConfiguracionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
        );
        ConfiguracionTabLayout.setVerticalGroup(
            ConfiguracionTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        TabContainer.addTab("Configuración", ConfiguracionTab);

        javax.swing.GroupLayout EstadisticasTabLayout = new javax.swing.GroupLayout(EstadisticasTab);
        EstadisticasTab.setLayout(EstadisticasTabLayout);
        EstadisticasTabLayout.setHorizontalGroup(
            EstadisticasTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
        );
        EstadisticasTabLayout.setVerticalGroup(
            EstadisticasTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        TabContainer.addTab("Estadísticas", EstadisticasTab);

        javax.swing.GroupLayout GraficosTabLayout = new javax.swing.GroupLayout(GraficosTab);
        GraficosTab.setLayout(GraficosTabLayout);
        GraficosTabLayout.setHorizontalGroup(
            GraficosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
        );
        GraficosTabLayout.setVerticalGroup(
            GraficosTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        TabContainer.addTab("Gráficos", GraficosTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabContainer)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabContainer)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NombreTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreTFActionPerformed

    private void CrearProcesoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearProcesoButtonActionPerformed
      
    try {
        // Obtener datos de los campos
        String nombre = NombreTF.getText();
        int cantidadInstrucciones = (int) CantInstruccionesSpinner.getValue();
        String tipo = (String) TipoCB.getSelectedItem();
        int ciclosParaGenerarExcepcion = (int) CiclosParaGenSpinner.getValue();
        int ciclosParaSatisfacerExcepcion = (int) CiclosParaSatisExcSpinner.getValue();

        // Validar que el nombre no esté vacío
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }

        // Validar condiciones para CPU bound o I/O bound
        if ("CPU bound".equals(tipo)) {
            ciclosParaGenerarExcepcion = 0;
            ciclosParaSatisfacerExcepcion = 0;
        }

        // Crear el objeto Proceso
        Proceso proceso = new Proceso(
                nombre,
                cantidadInstrucciones,
                tipo,
                ciclosParaGenerarExcepcion,
                ciclosParaSatisfacerExcepcion
        );

        // Imprimir el objeto en la consola
        System.out.println(proceso);

        // Restablecer los valores de los campos
        resetForm();

        // Mostrar un mensaje de éxito
        JOptionPane.showMessageDialog(this, "Proceso creado con éxito:\n" + proceso, 
                                      "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
        // Mostrar errores en caso de que algo falle
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }    
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_CrearProcesoButtonActionPerformed

    private void resetForm() {
    NombreTF.setText(""); // Vaciar el campo de texto
    CantInstruccionesSpinner.setValue(0); // Restablecer el spinner a 0
    TipoCB.setSelectedIndex(0); // Seleccionar el primer elemento del ComboBox
    CiclosParaGenSpinner.setValue(0); // Restablecer el spinner a 0
    CiclosParaSatisExcSpinner.setValue(0); // Restablecer el spinner a 0
    CiclosParaGenSpinner.setEnabled(false); // Deshabilitar los spinners de ciclos
    CiclosParaSatisExcSpinner.setEnabled(false);
}
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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CantInstruccionesLabel;
    private javax.swing.JSpinner CantInstruccionesSpinner;
    private javax.swing.JSpinner CiclosParaGenSpinner;
    private javax.swing.JLabel CiclosParaGenSpinner1;
    private javax.swing.JLabel CiclosParaGenSpinner2;
    private javax.swing.JSpinner CiclosParaSatisExcSpinner;
    private javax.swing.JLabel CiclosParaSatisExcSpinner1;
    private javax.swing.JLabel CiclosParaSatisExcSpinner2;
    private javax.swing.JPanel ConfiguracionTab;
    private javax.swing.JButton CrearProcesoButton;
    private javax.swing.JPanel EstadisticasTab;
    private javax.swing.JPanel GraficosTab;
    private javax.swing.JLabel NombreLabel;
    private javax.swing.JTextField NombreTF;
    private javax.swing.JTabbedPane TabContainer;
    private javax.swing.JComboBox<String> TipoCB;
    private javax.swing.JLabel TipoLabel;
    private javax.swing.JLabel TituloCrearProceso;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
