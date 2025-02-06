/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui;

import entities.Cola;
import entities.Nodo;
import entities.Proceso;
import javax.swing.SwingUtilities;

/**
 *
 * @author Aless
 */
public class colasPane extends javax.swing.JPanel {

   private Cola<Proceso> colaProcesos;

    public colasPane(String titulo, Cola<Proceso> cola) {
        initComponents();
        this.colaProcesos = cola;
    }

    public void actualizarCola(javax.swing.JPanel container) {
        SwingUtilities.invokeLater(() -> {
            container.removeAll(); // Limpia el contenedor

            Nodo<Proceso> nodoActual = colaProcesos.peekNode(); // Accede al primer nodo
            while (nodoActual != null) {
                Proceso proceso = nodoActual.getDato();
                ListaPane panelProceso = new ListaPane(proceso);
                container.add(panelProceso); // Agrega el panel del proceso
                nodoActual = nodoActual.getSiguiente(); // Avanza al siguiente nodo
            }

            container.revalidate(); // Refresca la interfaz
            container.repaint();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
