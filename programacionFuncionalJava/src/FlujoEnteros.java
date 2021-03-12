import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Practicas con flujos especificos para valores enteros
 */
public class FlujoEnteros {
    /**
     * Dato miembro para guardar enteros
     */
    int [] valores;

    /**
     * Constructor
     * @param numValores
     */
    public FlujoEnteros(int numValores){
        valores = new int[numValores];
        Random generador = new Random();

        for (int i=0; i<numValores; i++){
            valores[i] = generador.nextInt();
        }
    }

    /**
     * Iteracion externa
     */
    public void listadoImperativo(){
        System.out.println("Listado imperativo...");
        for(int i=0; i<valores.length; i++){
            System.out.println(valores[i] + " ");
        }
        System.out.println();
    }

    /**
     * Iteracion interna (version larga)
     */
    public void listadoFuncional(){
        System.out.println("Listado funcional...");
        // crear flujo de valores
        IntStream flujo = IntStream.of(valores);

        // funcion lambda a  aplicar a cada miembro del flujo
        IntConsumer operacion = valor -> System.out.println(valor + " ");

        // recorrido
        flujo.forEach(operacion);
    }

    /**
     * Iteracion interna (version corto) (Se pueden encadenar operaciones)
     */
    public void listadoFuncionalCorto(){
        System.out.println("Listado funcional corto...");
        // Encadenar operaciones
        IntStream.of(valores).forEach(valor -> System.out.println(valor + " "));
        IntStream.of(valores).forEach(System.out::println);
    }

    /**
     * Suma imperativa
     * @return
     */
    public int sumar(){
        int suma = 0;
        for (int i=0; i<valores.length; i++){
            suma = suma + valores[i];
        }
        return suma;
    }

    /**
     * Suma funcional
     * @return
     */
    public int sumaFuncional(){
        return IntStream.of(valores).sum();
    }

    public static void main(String args[]){
        FlujoEnteros objeto = new FlujoEnteros(10);
        objeto.listadoImperativo();
        objeto.listadoImperativo();
    }

    /**
     * Suma reduce (Reduce coge el primer argumentos, la identidad, y lo suma. EL resultado lo suma con el siguiente
     * Por eso, como es suma, el elemento identidad, es decir el primero, debe ser 0
     * @return
     */
    public int sumaReduce(){
        return IntStream.of(valores).reduce(0, (x,y) -> x+y);
    }

    public int sumarCuadrados(){
        return IntStream.of(valores).reduce(0, (x,y) -> x+y*2);
    }

    /**
     * Sumar cuadrados transformando cada elemento con su cuadrado
     * @return
     */
    public int sumarCuadradosMap(){
        // Flujo que contiene el cuadrado de cada elemento del flujo anterior
        return IntStream.of(valores).map(x -> x * x).sum();
    }

    /**
     * Obtener el mínimo de forma imperativa
     * @return
     */
    public int obtenerMinimo(){
        int minimo = valores[0];
        for (int i=0; i< valores.length; i++){
            if (valores[i] < minimo){
                minimo = valores[i];
            }
        }
        return minimo;
    }

    /**
     * Minimo de forma funcional
     * @return
     */
    public int obtenerMinimoFuncional(){
        OptionalInt min = IntStream.of(valores).min(); // Problema si la colección está vacía

        return min.orElse(-1);
    }

    /**
     * Minimo con reduce
     * @return
     */
    public int obtenerMinimoFuncionalReduce(){
        return IntStream.of(valores).reduce(Integer.MAX_VALUE, (x, y) -> {if (x<y) return x; else return y;}); // Problema si la colección está vacía
    }

    /**
     * Mostrar solo valores pares
     */
    public void mostrarValoresPares(){
        IntStream.of(valores).filter(x -> x%2 == 0).forEach(System.out::println);
    }

    /**
     * Coleccion solo con valores pares
     */
    public int [] obtenerValoresPares(){
        return IntStream.of(valores).filter(x -> x%2 == 0).sorted().distinct().toArray();
    }

    /**
     * Generacion de array de valores int, interesa iterar sobre los indices
     */
    public int [] generar(int numeroValores){
        int resultado[] = new int[numeroValores];

        Random generador = new Random();

        IntStream.range(0, numeroValores).forEach(indice -> {resultado[indice] = generador.nextInt();});

        return resultado;
    }

    /**
     * Generacion de array de valores double, interesa iterar sobre los indices
     */
    public List<Double> generarDouble(int numeroValores){
        double resultado[] = new double[numeroValores];

        Random generador = new Random();

        Stream<Integer> boxed = IntStream.range(0, numeroValores).boxed();//Genérico



        return IntStream.range(0, numeroValores).boxed().map(indice -> resultado[indice] = generador.nextInt()*1.0).collect(Collectors.toList());
    }
}
