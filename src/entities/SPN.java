/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Luis Soriano
 */
public class SPN {
    /**
     * Ordena la cola de listos según el algoritmo SPN (Shortest Process Next).
     * 
     * @param colaListos La cola de procesos listos.
     */
    public static void ordenar(Cola<Proceso> colaListos) {
        // Convertir la cola a un arreglo para facilitar el ordenamiento
        Proceso[] procesos = colaListos.toArray();

        // Ordenar los procesos por el tiempo total de servicio requerido (s)
        for (int i = 0; i < procesos.length - 1; i++) {
            for (int j = 0; j < procesos.length - i - 1; j++) {
                if (procesos[j].getCantidadInstrucciones() > procesos[j + 1].getCantidadInstrucciones()) {
                    // Intercambiar los procesos
                    Proceso temp = procesos[j];
                    procesos[j] = procesos[j + 1];
                    procesos[j + 1] = temp;
                }
            }
        }

        // Reconstruir la cola de listos en el nuevo orden
        while (!colaListos.isEmpty()) {
            colaListos.dequeue();
        }
        for (Proceso proceso : procesos) {
            colaListos.enqueue(proceso);
        }
    }
}