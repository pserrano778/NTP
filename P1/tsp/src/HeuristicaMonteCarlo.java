import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Clase para heuristica MonteCarlo
 */
public class HeuristicaMonteCarlo extends HeuristicaTSP{
   /**
    * Dato miembro para guardar el numero de muestras
    */
   protected int muestras;

   /**
    * Dato miembro auxiliar para generar soluciones aleatorias
    */
   protected ArrayList<Integer> indices;

   /**
    * Dato miembro para almacenar al rutas que se han generado
    */
   protected ArrayList<Ruta> rutasGeneradas;

   /**
    * Metodo de resolucion a partir del problema
    * @param problema
    */
   @Override
   public void resolver(Problema problema) {
      // se asigna el problema
      this.problema = problema;

      // se inicializa el array de rutas
      rutasGeneradas = new ArrayList<>();

      // asignar el numero de muestras a generar
      muestras = problema.obtenerDimension() * 100;

      // se genera el array de indices
      indices = new ArrayList<>();
      for(int i=0; i < problema.obtenerDimension(); i++){
         indices.add(i);
      }

      // se genera solucion aleatoria
      rutaOptima = generarAleatoria();

      // considerancion de las muestras indicadas
      for(int i=0; i < muestras; i++){
         Ruta aleatoria = generarAleatoria();
         rutasGeneradas.add(aleatoria);
         // comprobar si hay que actualizar la optima
         if(rutaOptima.obtenerCoste() > aleatoria.obtenerCoste()){
            rutaOptima = aleatoria;
         }
      }
   }

   /**
    * Metodo de resolucion a partir del problema, utilizando programación funcional
    * @param problema
    */
   @Override
   public void resolverFuncional(Problema problema) {
      // se asigna el problema
      this.problema = problema;

      // se inicializa el array de rutas
      rutasGeneradas = new ArrayList<>();

      // asignar el numero de muestras a generar
      muestras = problema.obtenerDimension() * 100;

      // se genera el array de indices
      indices = new ArrayList<>(IntStream.range(0, problema.obtenerDimension()).boxed().collect(Collectors.toList()));

      // se generan las soluciones aleatorias, se ordenan en función del menor coste y se coge la primera
      rutasGeneradas = generarRutasAleatorias();

      rutaOptima = rutasGeneradas.get(0);
   }

   /**
    * Metodo de generacion de rutas aleatorias
    * @return
    */
   protected Ruta generarAleatoria(){
      Ruta resultado = new Ruta();

      // se desordena el array de indices
      Collections.shuffle(indices, new Random(1));

      // se van agregando las ciudades en el orden en que
      // aparecen en indices
      resultado.agregarCiudad(problema.obtenerCiudad(indices.get(0)), 0);

      // agregamos el resto de ciudades
      for(int i=1; i < indices.size(); i++){
         Ciudad previa = problema.obtenerCiudad(indices.get(i-1));
         Ciudad siguiente = problema.obtenerCiudad(indices.get(i));
         double distancia = problema.obtenerDistancia(previa, siguiente);
         resultado.agregarCiudad(siguiente, distancia);
      }

      // se agrega el coste de cierre
      Ciudad inicio = problema.obtenerCiudad(indices.get(0));
      Ciudad fin = problema.obtenerCiudad(indices.get(indices.size()-1));
      double distanciaCierre = problema.obtenerDistancia(inicio, fin);
      resultado.agregarCoste(distanciaCierre);

      // se devuelve el resultado
      return resultado;
   }

   /**
    * Metodo de generacion de rutas aleatorias, mediante programación funcional
    * @return
    */
   protected Ruta generarAleatoriaFuncional(){
      Ruta resultado = new Ruta();

      // se desordena el array de indices
      Collections.shuffle(indices, new Random(1));

      // se van agregando las ciudades en el orden en que
      // aparecen en indices
      resultado.agregarCiudad(problema.obtenerCiudad(indices.get(0)), 0);

      // Expresión tipo consumidor para agregar una ciudad
      Consumer<Integer> procesarCiudad = indice -> {
         Ciudad previa = problema.obtenerCiudad(indices.get(indice-1));
         Ciudad siguiente = problema.obtenerCiudad(indices.get(indice));
         double distancia = problema.obtenerDistancia(previa, siguiente);
         resultado.agregarCiudad(siguiente, distancia);};

      // Recorremos todos los indices
      IntStream.range(1, indices.size()).boxed().forEach(procesarCiudad);

      // se agrega el coste de cierre
      Ciudad inicio = problema.obtenerCiudad(indices.get(0));
      Ciudad fin = problema.obtenerCiudad(indices.get(indices.size()-1));
      double distanciaCierre = problema.obtenerDistancia(inicio, fin);
      resultado.agregarCoste(distanciaCierre);

      // se devuelve el resultado
      return resultado;
   }

   /**
    * Genera un array de rutas generadas de forma aleatoriap
    * @return
    */
   private ArrayList<Ruta> generarRutasAleatorias(){
      return new ArrayList<>(IntStream.range(0, muestras).boxed().map(indice -> generarAleatoriaFuncional())
              .sorted(Comparator.comparing(Ruta::obtenerCoste)).collect(Collectors.toList()));
   }
}
