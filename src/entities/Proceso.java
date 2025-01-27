/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;//

/**
 *
 * @author Aless
 */
public class Proceso {
    private String nombre;
    private int cantidadInstrucciones;
    private String tipo;
    private int ciclosParaGenerarExcepcion; // default 0 si no es I/O bound //este siempre sera un valor estatico
    private int ciclosParaSatisfacerExcepcion; // default 0 si no es I/O bound // este siemrpe sera un valor estatico
    private int ciclosRestantesBloqueado; // Ciclos restantes para desbloqueo procesos I/O Bound //cuando 
    private int ciclosEjecutadosDesdeUltimoBloqueo; // Ciclos ejecutados desde el último bloqueo o desde que se empieza a ejecutar el proceso // cuando este sea igual a ciclos para generar excepcion, se cambiara el estado del proceso y se llevara a la cola de listos.
    private int id;
    private String estado; // default Listo
    private int PC; // Program Counter default 0
    private int MAR; // Memory Address Register default 0
    private int cpuIdThread;
    private int cicloEnqueCola;//HRRN
    private int instruccionesEjecutadas; //SRT

    // Contador de ID único para cada proceso
    private static int contadorID = 1;

    // Constructor
    public Proceso(String nombre, int cantidadInstrucciones, String tipo,
                   Integer ciclosParaGenerarExcepcion, Integer ciclosParaSatisfacerExcepcion) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }

        if (cantidadInstrucciones <= 0) {
            throw new IllegalArgumentException("La cantidad de instrucciones debe ser mayor que 0.");
        }

        if (tipo == null || (!tipo.equals("CPU bound") && !tipo.equals("I/O bound"))) {
            throw new IllegalArgumentException("El tipo debe ser 'CPU bound' o 'I/O bound'.");
        }

        this.nombre = nombre;
        this.cantidadInstrucciones = cantidadInstrucciones;
        this.tipo = tipo;

        // Si el tipo es I/O bound, los parámetros son obligatorios
        if (tipo.equals("I/O bound")) {
            if (ciclosParaGenerarExcepcion == null || ciclosParaSatisfacerExcepcion == null) {
                throw new IllegalArgumentException("Ciclos para generar y satisfacer excepción son obligatorios para procesos I/O bound.");
            }
            this.ciclosParaGenerarExcepcion = ciclosParaGenerarExcepcion;
            this.ciclosParaSatisfacerExcepcion = ciclosParaSatisfacerExcepcion;
        } else {
            // Si no es I/O bound, los valores son por defecto
            this.ciclosParaGenerarExcepcion = 0;
            this.ciclosParaSatisfacerExcepcion = 0;
        }
        this.ciclosRestantesBloqueado = 0;
        this.ciclosEjecutadosDesdeUltimoBloqueo = 0;
        this.id = contadorID++;
        this.estado = "Listo"; // Estado inicial
        this.PC = 0; // Valor inicial por defecto
        this.MAR = 0; // Valor inicial por defecto
        this.cpuIdThread = 0;
        this.cicloEnqueCola = -1;
        this.instruccionesEjecutadas = 0;
    }

    // Métodos Getters y Setters 
    public int getCicloEnqueCola() {
        return cicloEnqueCola;
    }

    public void setCicloEnqueCola(int cicloEnqueCola) {
        this.cicloEnqueCola = cicloEnqueCola;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public int getCantidadInstrucciones() {
        return cantidadInstrucciones;
    }
    
    public void setCantidadInstrucciones(int cantidadInstrucciones) {
    
    this.cantidadInstrucciones = cantidadInstrucciones;
}

    public String getTipo() {
        return tipo;
    }
    
    public int getCiclosRestantesBloqueado() {
        return ciclosRestantesBloqueado;
    }
    
    public void setCiclosRestantesBloqueado(int ciclosRestantesBloqueado) {
        this.ciclosRestantesBloqueado = ciclosRestantesBloqueado;
    }
    
    public int getCiclosEjecutadosDesdeUltimoBloqueo() {
        return ciclosEjecutadosDesdeUltimoBloqueo;
    }

    public void setCiclosEjecutadosDesdeUltimoBloqueo(int ciclosEjecutadosDesdeUltimoBloqueo) {
        this.ciclosEjecutadosDesdeUltimoBloqueo = ciclosEjecutadosDesdeUltimoBloqueo;
    }
    
    public void incrementarCiclosEjecutados() {
        this.ciclosEjecutadosDesdeUltimoBloqueo++;
    }


    public int getCiclosParaGenerarExcepcion() {
        return ciclosParaGenerarExcepcion;
    }

    public int getCiclosParaSatisfacerExcepcion() {
        return ciclosParaSatisfacerExcepcion;
    }

    public int getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public int getMAR() {
        return MAR;
    }

    public void setMAR(int MAR) {
        this.MAR = MAR;
    }
    
    public int getcpuIdThread() {
        return cpuIdThread;
    }
    
    public int getInstruccionesEjecutadas() {
    return instruccionesEjecutadas;
}

    public void setInstruccionesEjecutadas(int instruccionesEjecutadas) {
    this.instruccionesEjecutadas = instruccionesEjecutadas;
}

    // Calcula el tiempo restante para poilitica SRT
    public int getTiempoRestante() {
    return cantidadInstrucciones - instruccionesEjecutadas;
}

    public void setcpuIdThread(int cpuIdThread) {
        this.cpuIdThread = cpuIdThread;
    }
    // Método toString() para imprimir el proceso
    @Override
    public String toString() {
        return "Proceso{" +
                "nombre='" + nombre + '\'' +
                ", cantidadInstrucciones=" + cantidadInstrucciones +
                ", tipo='" + tipo + '\'' +
                ", ciclosParaGenerarExcepcion=" + ciclosParaGenerarExcepcion +
                ", ciclosParaSatisfacerExcepcion=" + ciclosParaSatisfacerExcepcion +
                ", id=" + id +
                ", estado='" + estado + '\'' +
                ", PC=" + PC +
                ", MAR=" + MAR +
                ", Ciclo=" + cicloEnqueCola +
                ", InstruccionesEjecutadas=" + instruccionesEjecutadas +
                '}';
    }
}

