/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1.operativos.soriano.luis.torres.alessandra;
import entities.CPU;
import entities.Cola;
import entities.Manejotxt;
import entities.Proceso;
import java.util.Random;
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
    public static volatile boolean actualizarColaListos = true;
    
    
    // ESTADISTICASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
    public static int ProcesosTotales = 0;
    public static int Cicloact = 0;
    public static int ProcesosIOtotales = 0;
    public static int ProcesosCPUtotales = 0;
    public static double ThroughputTotal = 0.0;
    
   
     public static int TiempoFCFS = 0;
     public static int nroProcesoFCFS = 0;
     public static int ProcesosIOFCFS = 0;
    public static int ProcesosCPUFCFS = 0;
    public static double ThroughputFCFS = 0;
     
     
     public static int TiempoRR = 0;
     public static int nroProcesoRR = 0;
     public static int ProcesosIORR = 0;
    public static int ProcesosCPURR = 0;
    public static double ThroughputRR = 0;
     
     
     public static int TiempoSPN = 0;
     public static int nroProcesoSPN = 0;
     public static int ProcesosIOSPN = 0;
    public static int ProcesosCPUSPN = 0;
    public static double ThroughputSPN = 0;
     
     
     public static int TiempoSRT = 0;
     public static int nroProcesoSRT = 0;
     public static int ProcesosIOSRT = 0;
    public static int ProcesosCPUSRT = 0;
    public static double ThroughputSRT = 0;
     
     public static int TiempoHRRN = 0;
     public static int nroProcesoHRRN = 0;
     public static int ProcesosIOHRRN = 0;
    public static int ProcesosCPUHRRN = 0;
    public static double ThroughputHRRN = 0;
    // ESTADISTICASSSSSSSSSS
    
    
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
        manejarColaListosSinLocks();
        
        //generarProcesosAutomáticamente();
        
        actualizarVarEstadisticas();

        int cpusActivos = Manejotxt.leerCPUsActivos();
        if (cpusActivos == 3) {
            cpu3.setActivo(true); // CPU 3 activo si CPUsActivos es 3
            mainWindow.setColorCPU3ACTIVO();
        } else {
            cpu3.setActivo(false); // CPU 3 desactivado si CPUsActivos es menor a 3
            mainWindow.setColorCPU3DESACTIVADO();
        }
        
        
       
    // Iniciar la actualización automática
  //  mainWindow.iniciarActualizacionAutomatica(colaListos);
    

        // Iniciar las CPUs
        cpu1.start();
        cpu2.start();
        cpu3.start();
        
        manejarColaBloqueados();
        
        
        
        
        // Esperar unos segundos para observar la ejecución
        try {
            Thread.sleep(1500000); // Simular tiempo de ejecución general
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Detener las CPUs
        //cpu1.detener();
        //cpu2.detener();
        //cpu3.detener();

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
            try { 
                int duracionCiclo = mainWindow.getCicloDuracion();
                Thread.sleep(duracionCiclo * 1000L);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
                // Procesar la cola de bloqueados
                //PROBLEMASSSSSSSSSSSSS
                int tamano = colaBloqueados.size();
                for (int i = 0; i < tamano; i++) {
                    Proceso proceso = (Proceso) colaBloqueados.dequeue(); // 
                    proceso.setCiclosRestantesBloqueado(proceso.getCiclosRestantesBloqueado() - 1);
                    //System.out.println("ciclosrestantes"+proceso.getCiclosRestantesBloqueado());
                    
                    if (proceso.getCiclosRestantesBloqueado() <= 0) {
                        proceso.setEstado("Listo");
                        proceso.setCiclosRestantesBloqueado(0);
                        colaListos.enqueue(proceso); // Mover a la cola de listos
                        //System.out.println("encolacion en LISTOS papaappaapaa");
                        //System.out.println(proceso.getCiclosRestantesBloqueado());
                    } else {
                        proceso.setEstado("Bloqueado");//solo nos aseguramos que su estado este bien
                        colaBloqueados.enqueue(proceso); // Reencolar si no termina
                    }
                }
                
            
        }
    }).start();
}
    
public static void manejarColaListosSinLocks() {
    new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(100); // Pausa de 1 segundo (o el tiempo que prefieras)

                // Obtener la política actual desde MainClass
                String politica = MainClass.getPoliticaActual();

                // Ordenar según la política
                switch (politica) {
                    case "FCFS":
                        // No se necesita ordenar
                        break;
                    case "SRT":
                        ordenarSRT(MainClass.colaListos);
                        break;
                    case "SPN":
                        ordenarSPN(MainClass.colaListos);
                        break;
                    case "HRRN":
                        ordenarHRRN(MainClass.colaListos, MainClass.cicloGlobal);
                        break;
                    case "Round Robin":
                        // No se necesita ordenar
                        break;
                }

                // Actualizar la interfaz (si es necesario)

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }).start();
}

//VARIABLES ESTADISTICAS
public static void actualizarVarEstadisticas() {
    new Thread(() -> {
        while (true) {
            try {
                int duracionCiclo = mainWindow.getCicloDuracion();
                System.out.println(cicloGlobal);
                Thread.sleep(duracionCiclo * 1000L); // Pausa de 1 segundo (o el tiempo que prefieras)

                // Obtener la política actual desde MainClass
                String politica = MainClass.getPoliticaActual();
                System.out.println(politica);

                // Ordenar según la política
                switch (politica) {
                    case "FCFS":
                        TiempoFCFS = TiempoFCFS + 1;
                        break;
                    case "SRT":
                        TiempoSRT = TiempoSRT + 1;
                        break;
                    case "SPN":
                        TiempoSPN = TiempoSPN + 1;
                        break;
                    case "HRRN":
                        TiempoHRRN = TiempoHRRN + 1;
                        break;
                    case "Round Robin":
                        TiempoRR = TiempoRR + 1;
                        break;
                 
                }
                 ProcesosIOtotales = ProcesosIOFCFS + ProcesosIOHRRN+ ProcesosIORR + ProcesosIOSPN + ProcesosIOSRT;
                 ProcesosCPUtotales = ProcesosCPUFCFS + ProcesosCPUHRRN+ ProcesosCPURR + ProcesosCPUSPN + ProcesosCPUSRT;
                
                
                
                if(TiempoFCFS !=0){
                    ThroughputFCFS = (double)nroProcesoFCFS / TiempoFCFS;
                    
                }
                
                 if(TiempoRR !=0){
                    ThroughputRR = (double)nroProcesoRR / TiempoRR;
                    
                 }
                 
                  if(TiempoSPN !=0){
                    ThroughputSPN = (double)nroProcesoSPN / TiempoSPN;
                    
                  }
                  
                   if(TiempoSRT !=0){
                    ThroughputHRRN = (double)nroProcesoSRT / TiempoSRT;
                    
                   }
                   
                    if(TiempoHRRN !=0){
                    ThroughputHRRN = (double) nroProcesoHRRN / TiempoHRRN;
                    }

                if(cicloGlobal !=0){
                    ThroughputTotal = (double) ProcesosTotales / cicloGlobal;  
                    System.out.println();
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


public static void generarProcesosAutomáticamente() {
    String[] tipos = {"CPU bound", "I/O bound"}; // Tipos de procesos disponibles
    Random random = new Random();

    for (int i = 1; i <= 10; i++) {
        // Generar características aleatorias para los procesos
        String nombre = "Proceso_" + i;
        int cantidadInstrucciones = random.nextInt(10) + 1; // Entre 1 y 10 instrucciones
        String tipo = tipos[random.nextInt(tipos.length)]; // Aleatorio entre "CPU bound" y "I/O bound"
        int ciclosParaGenerarExcepcion = tipo.equals("I/O bound") ? random.nextInt(5) + 1 : 0; // Si es I/O bound, genera entre 1 y 5 ciclos
        int ciclosParaSatisfacerExcepcion = tipo.equals("I/O bound") ? random.nextInt(3) + 1 : 0; // Si es I/O bound, genera entre 1 y 3 ciclos

        // Crear el proceso
        Proceso nuevoProceso = new Proceso(
            nombre,
            cantidadInstrucciones,
            tipo,
            ciclosParaGenerarExcepcion,
            ciclosParaSatisfacerExcepcion
        );

        // Encolar el proceso en la cola de listos
        MainClass.colaListos.enqueue(nuevoProceso);

        // Log para verificar los procesos generados
        //System.out.println("Generado: " + nuevoProceso);
    }
}
}

