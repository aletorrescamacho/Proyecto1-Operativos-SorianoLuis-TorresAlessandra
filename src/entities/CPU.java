/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

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
        this.proceso = proceso;
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

