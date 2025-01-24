/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Aless
 *////
public class Manejotxt {
    public static int leerCPUsActivos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/configuracion.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("=");
                if (partes[0].equals("CPUsActivos")) {
                    return Integer.parseInt(partes[1]); // Retorna el valor de CPUsActivos
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer configuracion.txt: " + e.getMessage());
        }
        return 2; // Valor por defecto si hay error
    }
}
