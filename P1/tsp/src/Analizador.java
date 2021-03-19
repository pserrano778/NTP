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
      String nombreArchivo = "small10.tsp";
      // se crea la heuristica
      HeuristicaVMC heuristicaVMC = new HeuristicaVMC();
      HeuristicaMonteCarlo heuristicaMonteC = new HeuristicaMonteCarlo();

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

      // se visualiza la ruta
      Visualizador visualizador = new Visualizador(nombreArchivo, rutaOptimaVMC,
              rutaOptimaMonteC);

      Visualizador visualizadorFuncional = new Visualizador(nombreArchivo, rutaOptimaVMCFuncional,
              rutaOptimaMonteCFuncional);
   }
}
