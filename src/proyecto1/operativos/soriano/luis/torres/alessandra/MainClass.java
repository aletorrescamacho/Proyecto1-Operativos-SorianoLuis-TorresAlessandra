/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1.operativos.soriano.luis.torres.alessandra;
import entities.Proceso;
import ui.MainWindow;
/**
 *
 * @author Aless
 */
public class MainClass {

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
    }
    
}
