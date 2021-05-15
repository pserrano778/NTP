package Singleton;

public class Tarea implements Runnable{
    public void run(){
        // se obtiene acceso al Singleton
        GestorConexiones gestor = GestorConexiones.obtenerInstancia();

        // se simula la realizacion de tareas
        for(int i=0; i<1000; i++){
            gestor.obtenerContadorInstancias();
        }
    }
}
