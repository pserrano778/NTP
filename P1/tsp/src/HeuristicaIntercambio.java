import java.security.SecureRandom;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeuristicaIntercambio extends HeuristicaMonteCarlo{

    private TreeMap<String, Long> rutasPorCiudad;

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

                // Si mejora con respecto a la media, continuamos los intercambios
                if(costeNuevaRuta < calcularMediaCosteRutas()){
                    intercambiaCiudades.accept(ruta);
                }
            }
        };
    }

    /**
     * Metodo de resolucion a partir del problema
     * @param problema
     */
    @Override
    public void resolver(Problema problema) {
        super.resolver(problema);
        double mediaCosteRutas = calcularMediaCosteRutas();

        for (int i=0; i<rutasGeneradas.size(); i++){
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
            }while(costeNuevaRuta < mediaCosteRutas); // Realizamos intercambios mientras sea mejor que la media
        }
        asignarOptima();
    }

    /**
     * Metodo de resolucion a partir del problema, utilizando programación funcional
     * @param problema
     */
    @Override
    public void resolverFuncional(Problema problema) {
        super.resolver(problema);
        rutasGeneradas.stream().forEach(intercambiaCiudades);
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
     * @param rutas
     */
    public void agruparRutasPorCiudad(ArrayList<Ruta> rutas){
        rutasPorCiudad = rutas.stream().map(Ruta::obtenerInicio)
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
        System.out.println("COSTE: " + rutaOptima.obtenerCoste());
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
