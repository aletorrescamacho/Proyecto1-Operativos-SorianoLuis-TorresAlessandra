/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Luis Soriano
 */
public class FCFS {
    /**
     * Ordena la cola de procesos según el orden en que fueron encolados, utilizando el atributo cicloEnqueCola.
     *
     * @param colaListos Cola de procesos a ordenar.
     */
    public static void ordenar(Cola<Proceso> colaListos) {
        // Convertir la cola en un arreglo
        Proceso[] procesos = colaListos.toArray();

        // Ordenar los procesos según el ciclo en que fueron encolados (cicloEnqueCola)
        for (int i = 0; i < procesos.length - 1; i++) {
            for (int j = i + 1; j < procesos.length; j++) {
                if (procesos[i].getCicloEnqueCola() > procesos[j].getCicloEnqueCola()) {
                    // Intercambiar procesos
                    Proceso temp = procesos[i];
                    procesos[i] = procesos[j];
                    procesos[j] = temp;
                }
            }
        }

        // Reconstruir la cola con los procesos ordenados
        colaListos.vaciar();
        for (Proceso proceso : procesos) {
            colaListos.enqueue(proceso);
        }

        System.out.println("La cola ha sido ordenada según FCFS.");
    }
}

