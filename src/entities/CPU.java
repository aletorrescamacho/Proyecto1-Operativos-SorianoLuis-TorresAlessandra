/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import proyecto1.operativos.soriano.luis.torres.alessandra.MainClass;
import ui.MainWindow;

/**
 *
 * @author Aless
 */


public class CPU {
    private int id;
    private Proceso proceso; // Puede ser null si no se pasa nada
    private boolean activo;  // Estado de la CPU (true = activo, false = inactivo)
    private static int contadorID = 1; // Contador estático para asignar IDs únicos

    // Constructor sin parámetros
    public CPU() {
        this.id = contadorID++;
        this.proceso = null; // No tiene proceso asignado inicialmente
        this.activo = true;  // Estado inicial es activo
    }

    // Constructor con parámetros
    public CPU(Proceso proceso, boolean activo) {
        this.id = contadorID++;
        this.proceso = proceso;
        this.activo = activo;
    }

    // Getters y Setters
    public int getId() {
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
                MainClass.mainWindow.cpuPane2.actualizarConProceso();
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
}

