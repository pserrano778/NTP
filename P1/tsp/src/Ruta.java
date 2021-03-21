import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Representa un recorrido sobre un conjunto
 * de ciudades
 */
public class Ruta {
   /**
    * Almacena las ciudades recorridas en la ruta
    */
   private ArrayList<Ciudad> recorridas;

   /**
    * Coste del recorrido
    */
   private double coste;

   /**
    * Constructor por defecto
    */
   public Ruta(){
      recorridas = new ArrayList<>();
      coste = 0;
   }

   /**
    * Se agrega ciudad a la ruta
    * @param ciudad
    */
   public void agregarCiudad(Ciudad ciudad, double distancia){
      recorridas.add(ciudad);
      coste += distancia;
   }

   /**
    * Devuelve las ciudades recorridas hasta el momento
    * @return
    */
   public int obtenerLongitud(){
      return recorridas.size();
   }

   /**
    * Se devuelve la ciudad inicial
    * @return
    */
   public Ciudad obtenerInicio(){
      return recorridas.get(0);
   }

   /**
    * Devuelve la ciudad final del recorrido
    * @return
    */
   public Ciudad obtenerFin(){
      return recorridas.get(recorridas.size()-1);
   }

   /**
    * Se agrega el coste del recorrido final
    * @param distancia
    */
   public void agregarCoste(double distancia){
      coste += distancia;
   }

   /**
    * Verifica si una ciudad ha sido visitada
    * @param ciudad
    * @return
    */
   public boolean visitada(Ciudad ciudad){
      int indice = recorridas.indexOf(ciudad);
      return indice != -1;
   }

   /**
    * Devuelve el coste de la ruta
    * @return
    */
   public double obtenerCoste(){
      return coste;
   }

   /**
    * Devuelve dos ArrayList con los valores de las coordenadas
    * X e Y
    * @return
    */
   public ArrayList<ArrayList<Double>> obtenerCoordenadas(){
      // se crea el array general
      ArrayList<ArrayList<Double>> resultado = new ArrayList<>();

      // se crean los arrays para X e Y
      resultado.add(new ArrayList<Double>());
      resultado.add(new ArrayList<Double>());

      // se recorren las ciudades
      for(int i=0; i < recorridas.size(); i++){
         Ciudad ciudad = recorridas.get(i);
         resultado.get(0).add(ciudad.obtenerX());
         resultado.get(1).add(ciudad.obtenerY());
      }

      // devolver resultado
      return resultado;
   }

   /**
    * Devuelve dos ArrayList con los valores de las coordenadas
    * X e Y, mediante programación funcional
    * @return
    */
   public ArrayList<ArrayList<Double>> obtenerCoordenadasFuncional(){
      // se crea el array general
      ArrayList<ArrayList<Double>> resultado = new ArrayList<>();

      // se crean los arrays para X e Y
      resultado.add(new ArrayList<Double>());
      resultado.add(new ArrayList<Double>());

      // se Obtienen las coordenadas de las ciudades
      recorridas.stream().forEach(ciudad -> {resultado.get(0).add(ciudad.obtenerX());
         resultado.get(1).add(ciudad.obtenerY());});

      // devolver resultado
      return resultado;
   }

   /**
    * Devuelve cadena con contenido de la ruta, de forma imperativa
    * @return
    */
   public String toStringImperativo(){
      String salida = "Ruta: ";

      for(int i=0; i < recorridas.size(); i++){
         salida += recorridas.get(i).obtenerEtiqueta() + " ";
      }
      salida += "coste: " + coste + "\n";

      return salida;
   }

   /**
    * Devuelve cadena con contenido de la ruta, mediante programación funcional
    * @return
    */
   public String toString(){
      String salida = "Ruta: ";
      salida += recorridas.stream().map(Ciudad::obtenerEtiqueta).collect(Collectors.joining(" "));
      salida += "coste: " + coste + "\n";

      return salida;
   }
}
