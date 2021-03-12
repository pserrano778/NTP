import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class flujoLineas {
    public static void main(String args[]) throws IOException {
        Pattern patron = Pattern.compile("\\s+"); // Expresion regular

        // Obtener todas las lineas del archivo
        List<String> lineas = Files.lines(Paths.get("./data/textoSencillo.txt"), StandardCharsets.ISO_8859_1)
                .map(linea -> linea.replaceAll("(?!')\\p{Punct}", "")).collect(Collectors.toList());

        // mapa de tipo <palabra - contador> (cuántas veces aparece la palabra en el texto
        TreeMap<String, Long> grupos = lineas.stream().flatMap(linea -> patron.splitAsStream(linea))
                .collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()));//Flat map para no tener stream<stream...>> Tree map ordena dirctamente

        grupos.entrySet().stream().forEach(entrada -> {System.out.print(entrada.getKey() + ": " + entrada.getValue());
                                            System.out.println();});

        // Mapa de tipo <caracter (inicial) - lista de palabras>
        TreeMap<Character, List<String>> diccionario = lineas.stream().flatMap(linea -> patron.splitAsStream(linea))
                .collect(Collectors.groupingBy(cadena -> cadena.charAt(0), TreeMap::new, Collectors.toList()));
    }
}
