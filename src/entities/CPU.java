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
    private Proceso proceso; // 
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
        if(id == 1){
                MainClass.mainWindow.cpuPane1.liberarCPU();
            }else if (id == 2){
                MainClass.mainWindow.cpuPane2.liberarCPU();
            } else {
                MainClass.mainWindow.cpuPane3.liberarCPU();
            }
        proceso = null;
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
                this.setProceso(proceso);
                System.out.println("CPU " + id + " tomó el proceso: " + proceso.getNombre());////cuando se toma un proceso
            } else {
                System.out.println("CPU " + id + proceso + ": La cola está vacía, esperando...");
            }

            // Liberar el semáforo después de tomar el proceso
            semaforo.release();

            // Si se obtuvo un proceso, simular su ejecución
            if (proceso != null) {
                int duracionCiclo = MainClass.mainWindow.getCicloDuracion();

                // Lógica específica según la política de planificación
                switch (MainClass.politicaActual) {
                    case "Round Robin":
                        quantumRestante = 3; // Resetear el quantum inicial para cada nuevo proceso
                        while (proceso.getCantidadInstrucciones() > 0) {
                            // Pausar un ciclo de ejecución
                            Thread.sleep(duracionCiclo * 1000L);

                            // Decrementar atributos del proceso
                            proceso.setCantidadInstrucciones(proceso.getCantidadInstrucciones() - 1);
                            proceso.setInstruccionesEjecutadas(proceso.getInstruccionesEjecutadas() + 1);
                            proceso.setPC(proceso.getPC() + 1);
                            proceso.setMAR(proceso.getMAR() + 1);
                            this.setProceso(this.getProceso()); 

                            // Manejo de procesos I/O bound
                            if (proceso.getTipo().equalsIgnoreCase("I/O bound")) {
                                proceso.setCiclosEjecutadosDesdeUltimoBloqueo(
                                    proceso.getCiclosEjecutadosDesdeUltimoBloqueo() + 1
                                );
                                if (proceso.getCiclosEjecutadosDesdeUltimoBloqueo() >= proceso.getCiclosParaGenerarExcepcion()) {
                                    proceso.setEstado("Bloqueado");
                                    proceso.setCiclosEjecutadosDesdeUltimoBloqueo(0);
                                    proceso.setCiclosRestantesBloqueado(proceso.getCiclosParaSatisfacerExcepcion());
                                    System.out.println("CPU " + id + ": Proceso " + proceso.getNombre() + " bloqueado por I/O.");
                                    MainClass.colaBloqueados.enqueue(proceso);
                                    
                                    this.liberarProceso(); // Liberar CPU
                                    break;
                                }
                            }

                            quantumRestante--; // Decrementar el quantum restante

                            // Si cantidadInstrucciones llega a 0 antes de consumir el quantum
                            if (proceso.getCantidadInstrucciones() <= 0) {
                                proceso.setEstado("Terminado");
                                MainClass.colaTerminados.enqueue(proceso);
                                this.liberarProceso();
                                break;
                            }

                            // Si se consume el quantum y quedan instrucciones
                            if (quantumRestante <= 0) {
                                proceso.setEstado("Listo");
                                MainClass.colaListos.enqueue(proceso);
                                this.liberarProceso();
                                break;
                            }
                        }
                        break;

                    case "SRT":
                        while (proceso.getCantidadInstrucciones() > 0) {
                            // Verificar si el proceso actual tiene menor tiempo restante que el primero en la cola
                            semaforo.acquire();
                            if (!colaListos.isEmpty() && 
                                proceso.getTiempoRestante() > colaListos.peek().getTiempoRestante()) {
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
                            this.setProceso(this.getProceso());

                            // Manejo de procesos I/O bound
                            if (proceso.getTipo().equalsIgnoreCase("I/O bound")) {
                                proceso.setCiclosEjecutadosDesdeUltimoBloqueo(
                                    proceso.getCiclosEjecutadosDesdeUltimoBloqueo() + 1
                                );
                                if (proceso.getCiclosEjecutadosDesdeUltimoBloqueo() >= proceso.getCiclosParaGenerarExcepcion()) {
                                    proceso.setEstado("Bloqueado");
                                    proceso.setCiclosEjecutadosDesdeUltimoBloqueo(0);
                                    proceso.setCiclosRestantesBloqueado(proceso.getCiclosParaSatisfacerExcepcion());
                                    System.out.println("CPU " + id + ": Proceso " + proceso.getNombre() + " bloqueado por I/O.");
                                    MainClass.colaBloqueados.enqueue(proceso);
                                    this.liberarProceso();
                                    break;
                                }
                            }

                            if (proceso.getCantidadInstrucciones() <= 0) {
                                proceso.setEstado("Terminado");
                                MainClass.colaTerminados.enqueue(proceso);
                                this.liberarProceso();
                                break;
                            }
                        }
                        break;

                    default:
                        while (proceso.getCantidadInstrucciones() > 0) {
                            duracionCiclo = MainClass.mainWindow.getCicloDuracion();
                            Thread.sleep(duracionCiclo * 1000L);

                            proceso.setCantidadInstrucciones(proceso.getCantidadInstrucciones() - 1);
                            proceso.setInstruccionesEjecutadas(proceso.getInstruccionesEjecutadas() + 1);
                            proceso.setPC(proceso.getPC() + 1);
                            proceso.setMAR(proceso.getMAR() + 1);
                            this.setProceso(this.getProceso());//actualizas la interfaz con el pc, mar etc actualizados
                            System.out.println("aja si esto esta debajo de la inconcordancia me maman el huevo");
                            
                            
                            /////ERROR SE DUPLICAAAA REVISAR
                            // Manejo de procesos I/O bound
                            if (proceso.getTipo().equalsIgnoreCase("I/O bound")) {
                                
                                proceso.setCiclosEjecutadosDesdeUltimoBloqueo(proceso.getCiclosEjecutadosDesdeUltimoBloqueo() + 1);
                                
                                if (proceso.getCiclosEjecutadosDesdeUltimoBloqueo() >= proceso.getCiclosParaGenerarExcepcion() && proceso.getEstado().equals("Bloqueado") == false) {
                                    System.out.println("hey se esta metiendo en la cola de bloqueados");
                                    proceso.setEstado("Bloqueado");
                                    proceso.setCiclosEjecutadosDesdeUltimoBloqueo(0);//eyyyy
                                    proceso.setCiclosRestantesBloqueado(proceso.getCiclosParaSatisfacerExcepcion());
                                    System.out.println("CPU " + id + ": Proceso " + proceso.getNombre() + " bloqueado por I/O.");
                                    MainClass.colaBloqueados.enqueue(proceso);
                                    this.liberarProceso();
                                    break;
                                }
                            }

                            if (proceso.getCantidadInstrucciones() <= 0) {
                                proceso.setEstado("Terminado");
                                MainClass.colaTerminados.enqueue(proceso);
                                this.liberarProceso();
                                break;
                            }
                        }//
                        break;
                }
                
            } else {
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("CPU " + id + " fue interrumpida.");
            Thread.currentThread().interrupt();
        }
    }
}

}
