/**
 * Analizador de problemas via TSP
 */
public class Analizador {
   /**
    * Dato miembro con la heuristica a usar
    */
   private HeuristicaTSP heuristicaTSP;

   /**
    * Metodo para asignar la heuristica a usar
    * @param heuristica
    */
   public void asignarHeuristica(HeuristicaTSP heuristica){
      heuristicaTSP = heuristica;
   }

   /**
    * metodo para producir el analisis de un problema
    * @param nombreFichero
    * @return
    */
   public Ruta analizar(String nombreFichero){
      // se crea el problema
      Problema problema = new Problema("./data/" + nombreFichero);

      // se analiza el problema: se delega la funcionalidad en el
      // dato miembro heuristicaTSP
      heuristicaTSP.resolver(problema);

      // se devuelve la ruta optima
      return heuristicaTSP.obtenerOptima();
   }

   /**
    * metodo para producir el analisis de un problema, mediante programación funcional
    * @param nombreFichero
    * @return
    */
   public Ruta analizarFuncional(String nombreFichero){
      // se crea el problema
      Problema problema = new Problema("./data/" + nombreFichero);

      // se analiza el problema: se delega la funcionalidad en el
      // dato miembro heuristicaTSP
      heuristicaTSP.resolverFuncional(problema);

      // se devuelve la ruta optima
      return heuristicaTSP.obtenerOptima();
   }

   /**
    * metodo main para pruebas
    * @param args
    */
   public static void main(String args[]){
      String nombreArchivo = "pr1002.tsp";
      // se crea la heuristica
      HeuristicaVMC heuristicaVMC = new HeuristicaVMC();
      HeuristicaMonteCarlo heuristicaMonteC = new HeuristicaMonteCarlo();
      HeuristicaIntercambio heuristicaIntercambio = new HeuristicaIntercambio();

      // se crea el analizador
      Analizador analizador = new Analizador();

      // se asigna la heuristica
      analizador.asignarHeuristica(heuristicaVMC);

      // se produce el analisis del problema
      Ruta rutaOptimaVMC = analizador.analizar(nombreArchivo);

      // se produce el analisis del problema de forma funcional
      Ruta rutaOptimaVMCFuncional = analizador.analizarFuncional(nombreArchivo);

      // cambiar la heuristica del analizador
      analizador.asignarHeuristica(heuristicaMonteC);

      // se resuelve con la nueva heuristica
      Ruta rutaOptimaMonteC = analizador.analizar(nombreArchivo);

      // se resuelve con la nueva heuristica
      Ruta rutaOptimaMonteCFuncional = analizador.analizarFuncional(nombreArchivo);

      // cambiar la heuristica del analizador
      analizador.asignarHeuristica(heuristicaIntercambio);

      // se resuelve con la nueva heuristica
      Ruta rutaOptimaIntercambio = analizador.analizar(nombreArchivo);

      Ruta rutaOptimaIntercambioFuncional = analizador.analizarFuncional(nombreArchivo);

      // Prueba de la funcionalidad que permite crear un diccionario con la etiqueta de la ciudad,
      // y las veces que se inicia una ruta en ella
      System.out.println("Diccionario de ciudad de inicio (heurística de intercambio)");
      System.out.println(heuristicaIntercambio.obtenerRutasAgrupadasPorCiudad());

      // Prueba de la funcionalidad que permite filtrar las rutas que tienen un coste entre un rango
      System.out.println("Funcionalidad de filtrado de rutas entre rango (heurística de intercambio)");
      System.out.println(heuristicaIntercambio.filtrasRutasPorCosteEnRango(1000.0, 2000.0));

      // Se muestran todos los costes de las rutas optimas obtenidas
      System.out.println("Coste con heuristica Vecino Mas Cercano - Imperativa: " + rutaOptimaVMC.obtenerCoste());
      System.out.println("Coste con heuristica Vecino Mas Cercano - Funcional: " + rutaOptimaVMCFuncional.obtenerCoste());
      System.out.println("Coste con heuristica Monte Carlo - Imperativa: " + rutaOptimaMonteC.obtenerCoste());
      System.out.println("Coste con heuristica Monte Carlo - Funcional: " + rutaOptimaMonteCFuncional.obtenerCoste());
      System.out.println("Coste con heuristica Intercambio - Imperativa: " + rutaOptimaIntercambio.obtenerCoste());
      System.out.println("Coste con heuristica Intercambio - Funcional: " + rutaOptimaIntercambioFuncional.obtenerCoste());

      // se visualizan las rutas
      Visualizador visualizador = new Visualizador(nombreArchivo + " - Heuristicas VMC y Monte Carlo (Imperativa)"
              , rutaOptimaVMC,
              rutaOptimaMonteC);

      Visualizador visualizadorFuncional = new Visualizador(nombreArchivo + " - Heuristicas VMC y Monte Carlo (Funcional)"
              , rutaOptimaVMCFuncional,
              rutaOptimaMonteCFuncional);

      Visualizador visualizadorIntercambio = new Visualizador(nombreArchivo + " - Heuristica Intercambio (Imperativa y Funcional)"
              , rutaOptimaIntercambio, rutaOptimaIntercambioFuncional);
   }
}
