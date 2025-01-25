/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Luis Soriano
 */
public class SPF {
    public static void ordenar(Cola<Proceso> colaListos) {
        // Convertir la cola en un arreglo
        Proceso[] procesos = colaListos.toArray();

        // Ordenar los procesos por cantidad de instrucciones (de menor a mayor)
        for (int i = 0; i < procesos.length - 1; i++) {
            for (int j = i + 1; j < procesos.length; j++) {
                if (procesos[i].getCantidadInstrucciones() > procesos[j].getCantidadInstrucciones()) {
                    // Intercambiar
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

        System.out.println("La cola ha sido ordenada según SPF.");
    }
}