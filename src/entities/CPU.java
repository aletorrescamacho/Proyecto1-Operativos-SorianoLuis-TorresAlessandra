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
    int quantumRestante = 3; // Quantum de tiempo inicial para Round Robin

    while (activo) { // La CPU sigue ejecutando mientras esté activa
        try {
            // Adquirir el semáforo para acceder a la cola
            semaforo.acquire();

            // Intentar obtener un proceso de la cola
            if (!colaListos.isEmpty()) {
                proceso = colaListos.dequeue(); // Saca el proceso de la cola
                proceso.setEstado("Ejecutando"); // Cambiar estado a "Ejecutando"
                System.out.println("CPU " + id + " tomó el proceso: " + proceso.getNombre());
            } else {
                System.out.println("CPU " + id + ": La cola está vacía, esperando...");
            }

            // Liberar el semáforo después de tomar el proceso
            semaforo.release();

            // Si se obtuvo un proceso, simular su ejecución
            if (proceso != null) {
                int duracionCiclo = MainClass.mainWindow.getCicloDuracion();

                // Lógica específica según la política de planificación
                switch (MainClass.politicaActual) {
                    case "Round Robin":
                        // Round Robin: Ejecutar con un quantum
                        quantumRestante = 3; // Resetear el quantum inicial para cada nuevo proceso
                        while (proceso.getCantidadInstrucciones() > 0) {
                            // Pausar un ciclo de ejecución
                            Thread.sleep(duracionCiclo * 1000L);

                            // Decrementar atributos del proceso
                            proceso.setCantidadInstrucciones(proceso.getCantidadInstrucciones() - 1);
                            proceso.setInstruccionesEjecutadas(proceso.getInstruccionesEjecutadas() + 1);
                            proceso.setPC(proceso.getPC() + 1);
                            proceso.setMAR(proceso.getMAR() + 1);

                            quantumRestante--; // Decrementar el quantum restante

                            // Si cantidadInstrucciones llega a 0 antes de consumir el quantum
                            if (proceso.getCantidadInstrucciones() <= 0) {
                                proceso.setEstado("Terminado");
                                MainClass.colaTerminados.enqueue(proceso);
                                proceso = null; // Liberar el proceso actual
                                break;
                            }

                            // Si se consume el quantum y quedan instrucciones
                            if (quantumRestante <= 0) {
                                proceso.setEstado("Listo");
                                MainClass.colaListos.enqueue(proceso); // Volver a encolar el proceso
                                proceso = null; // Liberar el proceso actual
                                break;
                            }
                        }
                        break;

                    case "SRT":
                        // Shortest Remaining Time: Preemptivo
                        while (proceso.getCantidadInstrucciones() > 0) {
                            // Verificar si el proceso actual tiene menor tiempo restante que el primero en la cola
                            semaforo.acquire();
                            if (!colaListos.isEmpty() && 
                                proceso.getTiempoRestante() > colaListos.peek().getTiempoRestante()) {
                                // Intercambiar el proceso actual con el primero de la cola
                                proceso.setEstado("Listo");
                                MainClass.colaListos.enqueue(proceso);
                                proceso = colaListos.dequeue();
                                proceso.setEstado("Ejecutando");
                                System.out.println("CPU " + id + " preempte proceso: " + proceso.getNombre());
                            }
                            semaforo.release();

                            // Pausar un ciclo de ejecución
                            Thread.sleep(duracionCiclo * 1000L);

                            // Decrementar atributos del proceso
                            proceso.setCantidadInstrucciones(proceso.getCantidadInstrucciones() - 1);
                            proceso.setInstruccionesEjecutadas(proceso.getInstruccionesEjecutadas() + 1);
                            proceso.setPC(proceso.getPC() + 1);
                            proceso.setMAR(proceso.getMAR() + 1);

                            // Si el proceso termina
                            if (proceso.getCantidadInstrucciones() <= 0) {
                                proceso.setEstado("Terminado");
                                MainClass.colaTerminados.enqueue(proceso);
                                proceso = null;
                                break;
                            }
                        }
                        break;

                    default:
                        // Otras políticas (FCFS, SPN, HRRN)
                        while (proceso.getCantidadInstrucciones() > 0) {
                            // Pausar según la duración del ciclo
                            Thread.sleep(duracionCiclo * 1000L);

                            // Decrementar atributos del proceso
                            proceso.setCantidadInstrucciones(proceso.getCantidadInstrucciones() - 1);
                            proceso.setInstruccionesEjecutadas(proceso.getInstruccionesEjecutadas() + 1);
                            proceso.setPC(proceso.getPC() + 1);
                            proceso.setMAR(proceso.getMAR() + 1);

                            // Si el proceso termina
                            if (proceso.getCantidadInstrucciones() <= 0) {
                                proceso.setEstado("Terminado");
                                MainClass.colaTerminados.enqueue(proceso);
                                proceso = null;
                                break;
                            }
                        }
                        break;
                }
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



