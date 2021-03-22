import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeuristicaIntercambio extends HeuristicaMonteCarlo{

    private TreeMap<String, Long> rutasPorCiudad;

    /**
     * Metodo de resolucion a partir del problema
     * @param problema
     */
    @Override
    public void resolver(Problema problema) {
        super.resolver(problema);
    }

    /**
     * Metodo de resolucion a partir del problema, utilizando programación funcional
     * @param problema
     */
    @Override
    public void resolverFuncional(Problema problema) {
        super.resolver(problema);
    }

    /**
     * Agrupa las rutas por su ciudad de inicio, haciendo uso de programación funcional
     * @param rutas
     */
    public void agruparRutasPorCiudad(ArrayList<Ruta> rutas){
        rutasPorCiudad = rutas.stream().map(ruta -> ruta.obtenerInicio())
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
}
