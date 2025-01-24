/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1.operativos.soriano.luis.torres.alessandra;
import entities.CPU;
import entities.Manejotxt;
import entities.Proceso;
import ui.MainWindow; //3
/**
 *
 * @author Aless
 */////////
public class MainClass {
    public static CPU cpu1 = new CPU(); // CPU 1 siempre activo
    public static CPU cpu2 = new CPU(); // CPU 2 siempre activo
    public static CPU cpu3 = new CPU(); // CPU 3 dependerá de CPUsActivos
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        MainWindow mainWindow = new MainWindow(); // Reemplaza "MainWindow" con el nombre de tu clase JFrame
        mainWindow.setVisible(true);
        
         Proceso proceso1 = new Proceso("Proceso1", 100, "CPU bound", null, null);
        System.out.println(proceso1);

        // Crear un proceso I/O bound
        Proceso proceso2 = new Proceso("Proceso2", 200, "I/O bound", 5, 10);
        System.out.println(proceso2);
        
        
        
        

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
    }
    
}
