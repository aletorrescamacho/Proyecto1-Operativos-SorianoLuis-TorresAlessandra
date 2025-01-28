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

/**
 *
 * @author Aless
 */
public class GraficoBarrasTiempo extends javax.swing.JPanel {
    private DefaultCategoryDataset dataset;
    /**
     * Creates new form GraficoBarrasTiempo
     */
    public GraficoBarrasTiempo() {
        initComponents();
        
        
        dataset = new DefaultCategoryDataset();
        actualizarDatos(); // Llenar con los datos iniciales

        // Crear el gráfico
        JFreeChart chart = ChartFactory.createBarChart(
                "Comparación de Tiempos de Políticas", // Título del gráfico
                "Políticas",                          // Etiqueta del eje X
                "Tiempo (segundos)",                  // Etiqueta del eje Y
                dataset                              // Dataset
        );

        // Crear un panel para el gráfico
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 400));

        // Configurar el panel actual
        setLayout(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);
    }

    public void actualizarDatos() {
        // Actualizar el dataset con los valores actuales
        dataset.setValue(MainClass.TiempoFCFS, "Tiempo", "FCFS");
        dataset.setValue(MainClass.TiempoRR, "Tiempo", "Round Robin");
        dataset.setValue(MainClass.TiempoSPN, "Tiempo", "SPN");
        dataset.setValue(MainClass.TiempoSRT, "Tiempo", "SRT");
        dataset.setValue(MainClass.TiempoHRRN, "Tiempo", "HRRN");
    
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
