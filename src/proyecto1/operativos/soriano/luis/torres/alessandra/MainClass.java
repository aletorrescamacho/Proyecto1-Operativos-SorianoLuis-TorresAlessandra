/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1.operativos.soriano.luis.torres.alessandra;
import entities.CPU;
import entities.Cola;
import entities.Manejotxt;
import entities.Proceso;
import ui.MainWindow; //3

/**
 *
 * @author Aless
 */////////
public class MainClass {
    
    public static Cola colaListos = new Cola();
    public static Cola colaBloqueados = new Cola();
    public static Cola colaTerminados = new Cola();
    
    public static CPU cpu1 = new CPU(); // CPU 1 siempre activo
    public static CPU cpu2 = new CPU(); // CPU 2 siempre activo
    public static CPU cpu3 = new CPU(); // CPU 3 dependerá de CPUsActivos
    public static MainWindow mainWindow = new MainWindow();
    public static int cicloGlobal;
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
         // Reemplaza "MainWindow" con el nombre de tu clase JFrame
        mainWindow.setVisible(true);
        
        iniciarRelojGlobal(); 
        
        
        
        

        int cpusActivos = Manejotxt.leerCPUsActivos();
        if (cpusActivos == 3) {
            cpu3.setActivo(true); // CPU 3 activo si CPUsActivos es 3
        } else {
            cpu3.setActivo(false); // CPU 3 desactivado si CPUsActivos es menor a 3
        }

/////////
        // Imprimir el estado inicial de las CPUs
        System.out.println(cpu1); // CPU{id=1, proceso=Sin proceso asignado, activo=true}
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

}
