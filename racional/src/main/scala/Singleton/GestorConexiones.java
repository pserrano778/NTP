package Singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase a la que se quiere dotar de la capacidad de crea
 * un unico objeto
 */
public class GestorConexiones {
    private static int contadorInstancias = 0;
    /**
     * Referencia a opbjeto de la clase GestorConexiones
     * Esta referencia opuntara al único objeto que debe crearse
     */
    private static GestorConexiones gestor = null;

    /**
     * Constructor de la clase
     * NOTA: Se hace privado para evitar que pueda generarse instancias desde
     * fuerad de la clase
     */
    private GestorConexiones(){
        contadorInstancias++;
    }

    public int obtenerContadorInstancias(){
        return contadorInstancias;
    }

    public static synchronized GestorConexiones obtenerInstancia(){
        if (gestor == null){
            gestor = new GestorConexiones();
        }

        return gestor;
    }

    public static void main(String[] args) {
        GestorConexiones referenciaSingleton = null;

        // se crea un pool de hebras controlado por un ejecutor
        ExecutorService ex = Executors.newFixedThreadPool(1000);

        for(int i=0; i<1000; i++){
            //referenciaSingleton = GestorConexiones.obtenerInstancias();
            ex.execute(new Tarea());
        }
        ex.shutdown();
        GestorConexiones gestor = GestorConexiones.obtenerInstancia();
        System.out.println("contador de instancias: " + gestor.obtenerContadorInstancias());
    }
}
