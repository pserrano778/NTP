import java.security.SecureRandom;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GeneracionAleatorios {
    public static void main(String[] args){
        SecureRandom random = new SecureRandom();

        long muestras = 100_000_000;

        // se generan numeros entre 1 y 6
        Map<Integer, Long> coleccion = random.ints(muestras, 1, 7).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        coleccion.entrySet().stream().forEach(entrada -> {System.out.printf("%10d - %10d - %f\n", entrada.getKey(), entrada.getValue(), entrada.getValue()/(double)muestras);});
    }
}
