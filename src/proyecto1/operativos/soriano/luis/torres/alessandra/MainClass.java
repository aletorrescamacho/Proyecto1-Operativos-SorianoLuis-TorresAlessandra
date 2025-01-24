/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1.operativos.soriano.luis.torres.alessandra;
import entities.Proceso;
import entities.Cola;
import ui.MainWindow; //
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
        
        Cola<Proceso> colaListos = new Cola<>();
        Cola<Proceso> colaBloqueados = new Cola<>();
        Cola<Proceso> colaTerminados = new Cola<>();

        // Crear procesos
        Proceso p1 = new Proceso("P1", 100, "CPU bound", null, null);
        Proceso p2 = new Proceso("P2", 50, "I/O bound", 6, 20);
        Proceso p3 = new Proceso("P3", 70, "CPU bound", null, null);

        // Encolar procesos en la cola de listos
        colaListos.enqueue(p1);
        colaListos.enqueue(p2);
        colaListos.enqueue(p3);
        
        // Mostrar contenido de las colas
        System.out.println("Cola de Listos:");
        colaListos.imprimirCola();
        
        // Mover un proceso a la cola de bloqueados (simular excepción)
        System.out.println("Mover proceso a la cola de bloqueados...");
        colaListos.dequeueTo(colaBloqueados);

        // Mostrar contenido de las colas
        System.out.println("Cola de Listos:");
        colaListos.imprimirCola();

        System.out.println("Cola de Bloqueados:");
        colaBloqueados.imprimirCola();

        // Buscar un proceso en la cola de bloqueados
        System.out.println("Buscando proceso en la cola de bloqueados:");
        Proceso encontrado = colaBloqueados.buscarPorId(2);
        System.out.println(encontrado != null ? encontrado : "No encontrado");
        
        // PURA CACA HAY QUE MOVER ES DE BLOQUEADOS A LISTOS 
        
        //MOVIMIENTOS 
        //1 EL PROCESO ESTA EN LA COLA DE LISTOS Y ES EL SIGUIENTE A EJECUTARSE
        //2 EL PROCESO SE ESTA EJECUTANDO Y REQUIERE DISPOSITIVO DE ENTRADA SALIDA
        //3 DESPUES SALE DE EJECUCION Y VA A LA COLA DE BLOQUEADOS A ESPERAR QUE ESTE LISTO SU OPERACION E/S
        //4 CUANDO ESTE LISTO SU OPERACION DE E/S, PASA A LA DE LISTOS A ESPERAR POR EJECUCION
        //5 CUANDO TERMINA DE EJECUTARSE DEBE ENTRAR EN UNA COLA DE TERMINADOS
    }
    }
    

