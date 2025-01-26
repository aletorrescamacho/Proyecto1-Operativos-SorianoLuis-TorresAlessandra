/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import java.util.concurrent.Semaphore;
import proyecto1.operativos.soriano.luis.torres.alessandra.MainClass;
import static proyecto1.operativos.soriano.luis.torres.alessandra.MainClass.colaListos;
import ui.MainWindow;



/**
 *
 * @author Aless
 */

    
public class CPU extends Thread{
    private int id;
    private Proceso proceso; // Puede ser null si no se pasa nada
    private boolean activo;  // Estado de la CPU (true = activo, false = inactivo)
    private static int contadorID = 1; // Contador estático para asignar IDs únicos
    private Semaphore semaforo = MainClass.semaforo ; // Semáforo para exclusión mutua

    // Constructor sin parámetros
    public CPU() {
        this.id = contadorID++;
        this.proceso = null; // No tiene proceso asignado inicialmente
        this.activo = true;  // Estado inicial es activo
        this.semaforo = MainClass.semaforo ;
    }

    // Constructor con parámetros

    // Getters y Setters
    public int getsId() {
        return id;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public void setProceso(Proceso proceso) {
        if (activo) { // Verificar si la CPU está activa
            this.proceso = proceso;
            if(id == 1){
                MainClass.mainWindow.cpuPane1.actualizarConProceso();
            }else if (id == 2){
                MainClass.mainWindow.cpuPane2.actualizarConProceso();
            } else {
                MainClass.mainWindow.cpuPane3.actualizarConProceso();
            }
            
            
            
            System.out.println("Proceso asignado correctamente a la CPU " + id + ": " + proceso.getNombre());
    } else {
        System.out.println("Error: No se puede asignar un proceso a la CPU " + id + " porque está desactivada.");
    }
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Método para limpiar el proceso asignado
    public void liberarProceso() {
        this.proceso = null; // Libera el proceso actual
    }

    @Override
    public String toString() {
        return "CPU{" +
               "id=" + id +
               ", proceso=" + (proceso != null ? proceso.getNombre() : "Sin proceso asignado") +
               ", activo=" + activo +
               '}';
    }
    
   @Override
    public void run() {
        while (activo) { // La CPU sigue ejecutando mientras esté activa
            try {
                // Adquirir el semáforo para acceder a la cola
                semaforo.acquire();

                // Intentar obtener un proceso de la cola
                if (!colaListos.isEmpty()) {
                    proceso = colaListos.dequeue(); // Saca el proceso de la cola
                    System.out.println("CPU " + id + " tomó el proceso: " + proceso.getNombre());
                } else {
                    System.out.println("CPU " + id + ": La cola está vacía, esperando...");
                }

                // Liberar el semáforo después de tomar el proceso
                semaforo.release();

                // Si se obtuvo un proceso, simular su ejecución
                if (proceso != null) {
                    System.out.println("CPU " + id + " ejecutando proceso: " + proceso.getNombre());
                    if(id == 1){
                        MainClass.mainWindow.cpuPane1.actualizarConProceso();
                    }else if (id == 2){
                        MainClass.mainWindow.cpuPane2.actualizarConProceso();
                    } else {
                        MainClass.mainWindow.cpuPane3.actualizarConProceso();
                    }
                    Thread.sleep(5000); // Simular ejecución del proceso (2 segundos)
                    System.out.println("CPU " + id + " terminó proceso: " + proceso.getNombre());
                    proceso = null; // Liberar el proceso actual
                } else {
                    // Si no hay proceso, esperar un tiempo antes de intentar de nuevo
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
                System.out.println("CPU " + id + " fue interrumpida.");
                Thread.currentThread().interrupt();
            }
        }
        
        
    }
    public void detener() {
        activo = false;
    }

}

