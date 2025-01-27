/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1.operativos.soriano.luis.torres.alessandra;
import entities.CPU;
import entities.Cola;
import entities.Manejotxt;
import entities.Proceso;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.ListaPane;
import ui.MainWindow; //3

/**
 *
 * @author Aless
 */////////
public class MainClass {
    public static Semaphore semaforo = new Semaphore(1); // Semáforo binario
    public static Cola<Proceso> colaListos = new Cola();
    public static Cola colaBloqueados = new Cola();
    public static Cola colaTerminados = new Cola();
    public static CPU cpu1 = new CPU(); // CPU 1 siempre activo
    public static CPU cpu2 = new CPU(); // CPU 2 siempre activo
    public static CPU cpu3 = new CPU(); // CPU 3 dependerá de CPUsActivos
    public static MainWindow mainWindow = new MainWindow();
    public static int cicloGlobal;
    public static final Object lockColaListos = new Object();
    public static String politicaActual = "FCFS";
    
    //para el hilo que maneja la cola de listos
    public static synchronized String getPoliticaActual() {
        return politicaActual;
    }
    
    //para setearla a la nueva politica que escoja el usuario
    public static synchronized void setPoliticaActual(String nuevaPolitica) {
        politicaActual = nuevaPolitica;
    }  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
         // Reemplaza "MainWindow" con el nombre de tu clase JFrame
        mainWindow.setVisible(true);
        
        iniciarRelojGlobal(); 
        
        
        
        for (int i = 1; i <= 10; i++) {
            colaListos.enqueue(new Proceso("hola"+i, i, "CPU bound",
                   0, 0));
        }
        

        int cpusActivos = Manejotxt.leerCPUsActivos();
        if (cpusActivos == 3) {
            cpu3.setActivo(true); // CPU 3 activo si CPUsActivos es 3
            mainWindow.setColorCPU3ACTIVO();
        } else {
            cpu3.setActivo(false); // CPU 3 desactivado si CPUsActivos es menor a 3
            mainWindow.setColorCPU3DESACTIVADO();
        }
        
        
       
    // Iniciar la actualización automática (mostrar en la interfaz la cola de listos
    mainWindow.iniciarActualizacionAutomatica(colaListos);

        // Iniciar las CPUs
        cpu1.start();
        cpu2.start();
        cpu3.start();
        
        manejarColaBloqueados();

        // Esperar unos segundos para observar la ejecución
        try {
            Thread.sleep(15000); // Simular tiempo de ejecución general
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Detener las CPUs
        cpu1.detener();
        cpu2.detener();
        cpu3.detener();

        System.out.println("Ejecución finalizada.");
  
/////////
        // Imprimir el estado inicial de las CPUs
       /** System.out.println(cpu1); // CPU{id=1, proceso=Sin proceso asignado, activo=true}
        System.out.println(cpu2); // CPU{id=2, proceso=Sin proceso asignado, activo=true}
        System.out.println(cpu3); // CPU{id=3, proceso=Sin proceso asignado, activo=true}
        
     /**   
        Proceso proceso1 = new Proceso("Proceso1", 100, "CPU bound", null, null);
    Proceso proceso2 = new Proceso("Proceso2", 200, "I/O bound", 5, 10);
    Proceso proceso3 = new Proceso("Proceso3", 300, "CPU bound", null, null);

    try {
        // Asignar proceso a CPU 1
        Thread.sleep(2000); 
        System.out.println("Asignando proceso1 a CPU 1...");
        cpu1.setProceso(proceso1);
        Thread.sleep(2000); // Espera 2 segundos

        // Cambiar proceso en CPU 1
        System.out.println("Cambiando proceso en CPU 1 a proceso2...");
        cpu1.setProceso(proceso2);
        Thread.sleep(2000); // Espera 2 segundos

        // Asignar proceso a CPU 2
        System.out.println("Asignando proceso2 a CPU 2...");
        cpu2.setProceso(proceso2);
        Thread.sleep(2000); // Espera 2 segundos

        // Intentar asignar proceso a CPU 3 (inactiva)
        System.out.println("Intentando asignar proceso3 a CPU 3...");
        Thread.sleep(2000); // Espera 2 segundos

        // Activar CPU 3 y asignar proceso
        

        // Imprimir estados finales de las CPUs
        System.out.println("Estado final de las CPUs:");
        System.out.println(cpu1);
        System.out.println(cpu2);
        System.out.println(cpu3);

    } catch (InterruptedException e) {
        System.err.println("Error en la espera: " + e.getMessage());
    } **/
     
    //PRUEBA COLA
    // Crear la cola de procesos
    
    //Cola<Proceso> colaProcesosPrueba = new Cola<>();


 /*
    for (int i = 1; i <= 10; i++) {
        Proceso proceso = new Proceso(
            "Proceso" + i,         // Nombre del proceso
            i * 10,                // Cantidad de instrucciones
            (i % 2 == 0) ? "CPU bound" : "I/O bound", // Tipo alternando entre CPU bound y I/O bound
            (i % 2 == 0) ? null : i + 2, // Ciclos para generar excepción (solo para I/O bound)
            (i % 2 == 0) ? null : i + 3  // Ciclos para satisfacer excepción (solo para I/O bound)
        );
        
        // Añadir el proceso a la cola
        colaProcesosPrueba.enqueue(proceso);
    }*/

    // Imprimir la cola para verificar



     
    
       
        
    
    
 
  
  

// Prueba: Agrega procesos para verificar

    
    



    
    }
    
    
  
    
    
    public static void iniciarRelojGlobal() {
    new Thread(() -> {
        cicloGlobal = 0; // Ciclo inicial
        while (true) {
            try {
                // Lee la duración actual del ciclo desde MainWindow
                int duracionCiclo = mainWindow.getCicloDuracion();
                Thread.sleep(duracionCiclo * 1000L); // Pausa según la duración configurada

                // Incrementa el ciclo global y actualiza en la interfaz
                cicloGlobal++;
                mainWindow.actualizarCicloGlobal(cicloGlobal);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }).start();
    
 
}
    //HILO PARA MANEJAR LA COLA DE BLOQUEADOS
    //DECREMENTA EN UNO LA CANTIDAD DE CICLOS RESTANTES BLOQUEADO PARA LOS PROCESOS EN LA COLA DE BLOQUEADOS
    //UNA VEZ ciclosRestantesBloqueado llega a 0 se vuelve a encolar en la cola de Listos.

    public static void manejarColaBloqueados() {
    new Thread(() -> {
        while (true) {
            synchronized (lockColaListos) {
                // Procesar la cola de bloqueados
                int tamano = colaBloqueados.size();
                for (int i = 0; i < tamano; i++) {
                    Proceso proceso = (Proceso) colaBloqueados.dequeue(); // Desencola el proceso
                    proceso.setCiclosRestantesBloqueado(proceso.getCiclosRestantesBloqueado() - 1);
                    
                    if (proceso.getCiclosRestantesBloqueado() <= 0) {
                        proceso.setEstado("Listo");
                        proceso.setCiclosRestantesBloqueado(0);
                        colaListos.enqueue(proceso); // Mover a la cola de listos
                    } else {
                        colaBloqueados.enqueue(proceso); // Reencolar si no termina
                    }
                }
                lockColaListos.notify(); // Notificar al hilo de la cola de listos
            }
        }
    }).start();
}
    
    //AQUI EL HILO QUE MANEJA LA COLA DE LISTOS, CUYA FUNCION PRINCIPAL ES ORDENAR LA COLA SEGUN EL ALGORITMO DE PLANIFICACION SELECCIONADO
    public static void manejarColaListos() {
    new Thread(() -> {
        while (true) {
            try {
                synchronized (lockColaListos) {
                    lockColaListos.wait(); // Esperar notificación de cambios en la cola

                    // Obtener la política actual desde MainClass
                    String politica = getPoliticaActual();

                    // Ordenar según la política
                    switch (politica) {
                        case "FCFS":
                            //aqui nada, todo se maneja segun el orden de llegada a la cola
                            break;
                        case "SRT":
                            ordenarSRT(colaListos);
                            break;
                        case "SPN":
                            ordenarSPN(colaListos);
                            break;
                        case "HRRN":
                            ordenarHRRN(colaListos, cicloGlobal);
                            break;
                        case "Round Robin":
                            // nada
                            break;
                    }

                    
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }).start();
}
    
   //A Partir de aqui los metodos para ordenar la cola de listos segun la politica de planificacion:
    
    //SRT
    public static void ordenarSRT(Cola<Proceso> colaListos) {
    int size = colaListos.size();
    //array del tamano de la cola
    Proceso[] procesos = new Proceso[size];

    // Desencolar todos los procesos
    for (int i = 0; i < size; i++) {
        procesos[i] = colaListos.dequeue();
    }

    // Ordenar por menor tiempo restante
    for (int i = 0; i < size - 1; i++) {
        for (int j = 0; j < size - i - 1; j++) {
            if (procesos[j].getTiempoRestante() > procesos[j + 1].getTiempoRestante()) {
                Proceso temp = procesos[j];
                procesos[j] = procesos[j + 1];
                procesos[j + 1] = temp;
            }
        }
    }

    // Reencolar procesos ordenados
    for (Proceso p : procesos) {
        colaListos.enqueue(p);
    }
}
    
    //HRRN
    public static void ordenarHRRN(Cola<Proceso> colaListos, int cicloGlobal) {
    // Convertir la cola en un array para facilitar el ordenamiento
    int size = colaListos.size();
    Proceso[] procesos = new Proceso[size];

    for (int i = 0; i < size; i++) {
        procesos[i] = colaListos.dequeue();
    }

    // Ordenar por mayor tasa de respuesta
    for (int i = 0; i < size - 1; i++) {
        for (int j = 0; j < size - i - 1; j++) {
            // Calcular tasa de respuesta dinámicamente para los procesos
            int tiempoEsperaJ = cicloGlobal - procesos[j].getCicloEnqueCola();
            double tasaRespuestaJ = (tiempoEsperaJ + procesos[j].getCantidadInstrucciones()) / (double) procesos[j].getCantidadInstrucciones();

            int tiempoEsperaJ1 = cicloGlobal - procesos[j + 1].getCicloEnqueCola();
            double tasaRespuestaJ1 = (tiempoEsperaJ1 + procesos[j + 1].getCantidadInstrucciones()) / (double) procesos[j + 1].getCantidadInstrucciones();

            if (tasaRespuestaJ < tasaRespuestaJ1) {
                // Intercambiar procesos
                Proceso temp = procesos[j];
                procesos[j] = procesos[j + 1];
                procesos[j + 1] = temp;
            }
        }
    }

    // Volver a encolar los procesos en la cola de listos ordenados
    for (Proceso p : procesos) {
        colaListos.enqueue(p);
    }
}

    //SPN
    public static void ordenarSPN(Cola<Proceso> colaListos) {
    // Convertir la cola en un array para facilitar el ordenamiento
    int size = colaListos.size();
    Proceso[] procesos = new Proceso[size];

    for (int i = 0; i < size; i++) {
        procesos[i] = colaListos.dequeue();
    }

    // Ordenar por menor tiempo de servicio
    for (int i = 0; i < size - 1; i++) {
        for (int j = 0; j < size - i - 1; j++) {
            if (procesos[j].getCantidadInstrucciones() > procesos[j + 1].getCantidadInstrucciones()) {
                // Intercambiar procesos
                Proceso temp = procesos[j];
                procesos[j] = procesos[j + 1];
                procesos[j + 1] = temp;
            }
        }
    }

    // Volver a encolar los procesos en la cola de listos ordenados
    for (Proceso p : procesos) {
        colaListos.enqueue(p);
    }
}


}

