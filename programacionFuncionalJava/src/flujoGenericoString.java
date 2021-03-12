import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class flujoGenericoString {
    public static void main(String args[]){
        String [] cadenas = {"Rojo", "Naranja", "Amarillo", "Violeta", "Verde", "azul", "indigo"};

        // Creación de flujo y se convierten las cadenas a mayúsculas
        List<String> mayuscula = Arrays.stream(cadenas).map(cadena -> cadena.toUpperCase()).collect(Collectors.toList());

        // Generacion de cadena completa con toda la lista para imprimir
        String contenido = mayuscula.stream().collect(Collectors.joining(" ")); // Collectors joining une todos los elementos usando el delimitador

        System.out.println(contenido);

        // Filtrar las cadenas con iniciales mayores que m, ordenacionn inversa
        List<String> filtradas = Arrays.stream(cadenas).filter(cadena -> cadena.compareToIgnoreCase("m") > 0).sorted(String.CASE_INSENSITIVE_ORDER.reversed()).collect(Collectors.toList());


        // Generacion de cadena completa con toda la lista para imprimir
        contenido = filtradas.stream().collect(Collectors.joining(" ")); // Collectors joining une todos los elementos usando el delimitador

        System.out.println(contenido);
    }
}
