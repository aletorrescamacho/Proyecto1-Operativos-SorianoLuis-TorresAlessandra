/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Luis Soriano
 */
public class SRT {
    /**
     * Ordena la cola de procesos según el tiempo restante, calculado de manera precisa
     * para CPU bound e I/O bound, sin usar librerías externas.
     *
     * @param colaListos Cola de procesos a ordenar.
     */
    public static void ordenar(Cola<Proceso> colaListos) {
        // Convertir la cola en un arreglo manualmente
        int tamano = colaListos.size();
        Proceso[] procesos = new Proceso[tamano];
        int[] tiemposRestantes = new int[tamano];

        // Copiar los procesos y calcular tiempos restantes
        int index = 0;
        while (!colaListos.isEmpty()) {
            Proceso proceso = colaListos.dequeue();
            procesos[index] = proceso;
            tiemposRestantes[index] = calcularTiempoRestante(proceso);
            index++;
        }

        // Ordenar los procesos y sus tiempos restantes manualmente (burbuja)
        for (int i = 0; i < tamano - 1; i++) {
            for (int j = i + 1; j < tamano; j++) {
                if (tiemposRestantes[i] > tiemposRestantes[j]) {
                    // Intercambiar tiempos restantes
                    int tempTiempo = tiemposRestantes[i];
                    tiemposRestantes[i] = tiemposRestantes[j];
                    tiemposRestantes[j] = tempTiempo;

                    // Intercambiar procesos correspondientes
                    Proceso tempProceso = procesos[i];
                    procesos[i] = procesos[j];
                    procesos[j] = tempProceso;
                }
            }
        }

        // Reconstruir la cola con los procesos ordenados
        for (Proceso proceso : procesos) {
            colaListos.enqueue(proceso);
        }

        System.out.println("La cola ha sido ordenada según SRT.");
    }

    /**
     * Calcula el tiempo restante de ejecución de un proceso.
     *
     * @param proceso El proceso para el cual calcular el tiempo restante.
     * @return El tiempo restante total.
     */
    private static int calcularTiempoRestante(Proceso proceso) {
        // Si el proceso es CPU bound, el tiempo restante es simplemente cantidadInstrucciones
        if (proceso.getTipo().equals("CPU bound")) {
            return proceso.getCantidadInstrucciones();
        }

        // Si el proceso es I/O bound, calcular el tiempo restante efectivo
        int cantidadInstrucciones = proceso.getCantidadInstrucciones();
        int ciclosParaGenerarExcepcion = proceso.getCiclosParaGenerarExcepcion();
        int ciclosParaSatisfacerExcepcion = proceso.getCiclosParaSatisfacerExcepcion();

        // Calcular cuántas operaciones I/O realmente ocurrirán
        int numeroDeOperacionesIO = (cantidadInstrucciones - 1) / ciclosParaGenerarExcepcion;

        // Calcular los ciclos adicionales por las operaciones I/O realizadas
        int ciclosIO = numeroDeOperacionesIO * ciclosParaSatisfacerExcepcion;

        // Calcular el tiempo total restante
        return cantidadInstrucciones + ciclosIO;
    }
}

