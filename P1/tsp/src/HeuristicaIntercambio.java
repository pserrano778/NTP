import java.security.SecureRandom;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeuristicaIntercambio extends HeuristicaMonteCarlo{

    /**
     * Atributo para almacenar las veces que comienza una ruta por cada ciudad
     */
    private TreeMap<String, Long> rutasPorCiudad;

    /**
     * Atributo para contar las veces que un intercambio ha empeorado una ruta
     */
    private int intercambiosPeores;

    /**
     * Atributo para definir el máximo número de veces que puede empeorar una ruta
     */
    private int maxIntercambiosPeores;

    /**
     * Función lambda que permite intercambiar ciudades de una ruta hasta obtener un coste menor
     */
    private Consumer<Ruta> intercambiaCiudades;
    {
        intercambiaCiudades = ruta -> {
            // Se copia la ruta
            Ruta copiaRuta = new Ruta(ruta);

            //Se escogen dos ciudades aleatorias diferentes
            SecureRandom random = new SecureRandom();
            int ciudad1 = random.nextInt(ruta.obtenerLongitud());
            int ciudad2 = generarEnteroAleatorioDiferente(ciudad1, ruta.obtenerLongitud());

            // Se cambian de lugar las ciudades
            copiaRuta.cambiarDosCiudades(ciudad1, ciudad2);

            // Se calcula el coste de la nueva ruta
            double costeNuevaRuta = calcularCosteRuta(copiaRuta);
            copiaRuta.modificarCoste(costeNuevaRuta);

            // Si el coste es mejor, se reemplaza la anterior ruta
            if(costeNuevaRuta < ruta.obtenerCoste()){
                rutasGeneradas.set(rutasGeneradas.indexOf(ruta), copiaRuta);
                ruta = copiaRuta;
            }
            else {
                intercambiosPeores++;
            }

            // Si mejora con respecto a la media, continuamos los intercambios
            if(costeNuevaRuta < calcularMediaCosteRutas() && intercambiosPeores < maxIntercambiosPeores){
                intercambiaCiudades.accept(ruta);
            }
            
            //Se termina de procesar la ruta y se reinicia el contador de veces que ha empeorado su coste
            intercambiosPeores = 0;
        };
    }

    /**
     * Metodo de resolucion a partir del problema
     * @param problema
     */
    @Override
    public void resolver(Problema problema) {
        // Se genera una lista de rutas por medio de la heurística Monte Carlo
        super.resolver(problema);

        double mediaCosteRutas = calcularMediaCosteRutas();

        // Se define el número máximo de fallos según la dimensión del problema
        maxIntercambiosPeores = problema.obtenerDimension();
                
        for (int i=0; i<rutasGeneradas.size(); i++){
            intercambiosPeores = 0;

            double costeNuevaRuta = Double.MAX_VALUE;
            do{
                // Se copia la ruta
                Ruta copiaRuta = new Ruta(rutasGeneradas.get(i));

                //Se escogen dos ciudades aleatorias diferentes
                SecureRandom random = new SecureRandom();
                int ciudad1 = random.nextInt(rutasGeneradas.get(i).obtenerLongitud());
                int ciudad2 = generarEnteroAleatorioDiferente(ciudad1, rutasGeneradas.get(i).obtenerLongitud());

                // Se cambian de lugar las ciudades
                copiaRuta.cambiarDosCiudades(ciudad1, ciudad2);

                // Se calcula el coste de la nueva ruta
                costeNuevaRuta = calcularCosteRuta(copiaRuta);
                copiaRuta.modificarCoste(costeNuevaRuta);

                // Si el coste es mejor, se reemplaza la anterior ruta
                if(costeNuevaRuta < rutasGeneradas.get(i).obtenerCoste()){
                    rutasGeneradas.set(i, copiaRuta);
                }
                else {
                    intercambiosPeores++;
                }
            // Realizamos intercambios mientras sea mejor que la media
            }while(costeNuevaRuta < mediaCosteRutas && intercambiosPeores < maxIntercambiosPeores);
        }
        // Agrupamos por ciudad
        agruparRutasPorCiudad();

        // Asignamos la ruta optima
        asignarOptima();
    }

    /**
     * Metodo de resolucion a partir del problema, utilizando programación funcional
     * @param problema
     */
    @Override
    public void resolverFuncional(Problema problema) {
        // Se genera una lista de rutas por medio de la heurística Monte Carlo
        super.resolver(problema);

        // Se define el número máximo de fallos según la dimensión del problema
        maxIntercambiosPeores = problema.obtenerDimension();

        intercambiosPeores = 0;
        
        rutasGeneradas.stream().forEach(intercambiaCiudades);

        // Agrupamos por ciudad
        agruparRutasPorCiudad();

        // Asignamos la ruta optima
        asignarOptima();
    }

    /**
     * Método que calcula la media del coste de las rutas, mediante programación funcional
     * @return
     */
    private Double calcularMediaCosteRutas(){
        return  rutasGeneradas.stream().mapToDouble(Ruta::obtenerCoste).summaryStatistics().getAverage();
    }

    /**
     * Método para calcular el coste de una ruta
     * @param ruta
     * @return
     */
    private Double calcularCosteRuta(Ruta ruta){
        return IntStream.range(1, ruta.obtenerLongitud()).boxed().mapToDouble(indiceCiudad ->
            problema.obtenerDistancia(ruta.obtenerCiudadRuta(indiceCiudad-1), ruta.obtenerCiudadRuta(indiceCiudad)))
            .reduce(0, (x, y) -> x + y) + problema.obtenerDistancia(ruta.obtenerInicio(), ruta.obtenerFin());
    }

    /**
     * Agrupa las rutas por su ciudad de inicio, haciendo uso de programación funcional
     */
    public void agruparRutasPorCiudad(){
        rutasPorCiudad = rutasGeneradas.stream().map(Ruta::obtenerInicio)
                .collect(Collectors.groupingBy(Ciudad::obtenerEtiqueta, TreeMap::new, Collectors.counting()));
    }

    /**
     * Devuelve el número de rutas que comienzan en cada ciudad
     * @return
     */
    public TreeMap<String, Long> obtenerRutasAgrupadasPorCiudad(){
        return rutasPorCiudad;
    }

    /**
     * Devuelve las rutas que tienen un coste entre un rango
     * @param minimo
     * @param maximo
     * @return
     */
    public ArrayList<Ruta> filtrasRutasPorCosteEnRango(Double minimo, Double maximo){
        Predicate<Ruta> condicion = ruta -> (ruta.obtenerCoste() >= minimo && ruta.obtenerCoste() <= maximo);
        return new ArrayList<>(rutasGeneradas.stream().filter(condicion).collect(Collectors.toList()));
    }

    private void asignarOptima(){
        rutaOptima = rutasGeneradas.stream().sorted(Comparator.comparing(Ruta::obtenerCoste)).collect(Collectors.toList()).get(0);
    }

    /**
     * Métopdo que permite generar un entero aleatorio disinto del pasado como parametro, dentro de un rango
     * y de forma recursiva
     * @param entero1
     * @param rango
     * @return
     */
    private int generarEnteroAleatorioDiferente(int entero1, int rango){
        SecureRandom random = new SecureRandom();
        int nuevoEntero = random.nextInt(rango);

        if (entero1 != nuevoEntero) {
            return nuevoEntero;
        }
        else{
            return generarEnteroAleatorioDiferente(entero1, rango);
        }
    }
}
