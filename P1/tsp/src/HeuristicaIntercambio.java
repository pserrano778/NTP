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
     * Metodo de resolucion a partir del problema
     * @param problema
     */
    @Override
    public void resolver(Problema problema) {
        super.resolver(problema);
        double mediaCosteRutas = calcularMediaCosteRutas();
        int ciudad1 = 0;
        int ciudad2 = 0;

        for (int i=0; i<rutasGeneradas.size(); i++){
            double costeNuevaRuta = Double.MAX_VALUE;
            do{
                Ruta copiaRuta = new Ruta(rutasGeneradas.get(i));
                SecureRandom random = new SecureRandom();
                ciudad1 = random.nextInt(rutasGeneradas.get(i).obtenerLongitud());
                do{
                    ciudad2 = random.nextInt(rutasGeneradas.get(i).obtenerLongitud());
                } while(ciudad2 == ciudad1);
                copiaRuta.cambiarDosCiudades(ciudad1, ciudad2);
                costeNuevaRuta = calcularCosteRuta(copiaRuta);
                copiaRuta.modificarCoste(costeNuevaRuta);
                if(costeNuevaRuta < rutasGeneradas.get(i).obtenerCoste()){
                    rutasGeneradas.set(i, copiaRuta);
                }
            }while(costeNuevaRuta < mediaCosteRutas);
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

        super.resolver(problema);
        double mediaCosteRutas = calcularMediaCosteRutas();
        int ciudad1 = 0;
        int ciudad2 = 0;

        for (int i=0; i<rutasGeneradas.size(); i++){
            double costeNuevaRuta = Double.MAX_VALUE;
            do{
                Ruta copiaRuta = new Ruta(rutasGeneradas.get(i));
                SecureRandom random = new SecureRandom();
                ciudad1 = random.nextInt(rutasGeneradas.get(i).obtenerLongitud());
                do{
                    ciudad2 = random.nextInt(rutasGeneradas.get(i).obtenerLongitud());
                } while(ciudad2 == ciudad1);
                copiaRuta.cambiarDosCiudades(ciudad1, ciudad2);
                costeNuevaRuta = calcularCosteRuta(copiaRuta);
                copiaRuta.modificarCoste(costeNuevaRuta);
                if(costeNuevaRuta < rutasGeneradas.get(i).obtenerCoste()){
                    rutasGeneradas.set(i, copiaRuta);
                }
            }while(costeNuevaRuta < mediaCosteRutas);
        }

        rutasGeneradas.stream().forEach(ruta -> {});

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
    }
}
