/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import proyecto1.operativos.soriano.luis.torres.alessandra.MainClass;
/*
 *
 * @author Aless
 */
public class GraficoThroughput extends javax.swing.JPanel {

   private DefaultCategoryDataset dataset;
    private JFreeChart chart;

    public GraficoThroughput() {
        // Crear el dataset inicial
        dataset = new DefaultCategoryDataset();
        actualizarDataset(); // Inicializar con datos actuales

        // Crear el gráfico
        chart = ChartFactory.createBarChart(
                "Comparación de Throughputs", // Título
                "Política", // Eje X
                "Throughput", // Eje Y
                dataset
        );

        // Crear el panel con el gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        this.setLayout(new BorderLayout());
        this.add(chartPanel, BorderLayout.CENTER);

        // Iniciar el hilo para actualizar el gráfico
        iniciarActualizacion();
    }

    private void actualizarDataset() {
        // Actualizar los valores del dataset
        dataset.setValue(MainClass.ThroughputFCFS, "FCFS", "FCFS");
        dataset.setValue(MainClass.ThroughputRR, "Round Robin", "Round Robin");
        dataset.setValue(MainClass.ThroughputSPN, "SPN", "SPN");
        dataset.setValue(MainClass.ThroughputSRT, "SRT", "SRT");
        dataset.setValue(MainClass.ThroughputHRRN, "HRRN", "HRRN");
    }

    private void iniciarActualizacion() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Actualizar cada segundo
                    actualizarDataset(); // Actualizar los datos
                    SwingUtilities.invokeLater(() -> chart.fireChartChanged()); // Refrescar el gráfico
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
