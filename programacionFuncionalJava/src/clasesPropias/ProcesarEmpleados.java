package clasesPropias;

import com.sun.source.tree.Tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcesarEmpleados {
    /**
     * Contador de empleados
     */
    private int numEmpleados;

    /**
     * Lista generica de empleados generados a partir del archivo
     */
    private List<Empleado> empleados;

    /**
     * Agrupamiento por departamentos
     */
    private Map<String, List<Empleado>> porDepartamento;

    public ProcesarEmpleados(String nombreArchivo){
        try{
            // Leer las líneas del archivo
            Stream<String> lineas = Files.lines(Paths.get(nombreArchivo));

            // para cada linea, genero el objeto de la clase empleado asociada
            empleados = lineas.map(linea -> new Empleado(linea)).collect(Collectors.toList());

            // obtener el numero de empleados
            numEmpleados = empleados.size();

            System.out.println("numero de empleados: " + numEmpleados);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problema tratamiento archivo: " + nombreArchivo);
        }

        // Creacion del mapa
        porDepartamento = new HashMap<>();
    }

    /**
     * Devuelve cadena con los datos de todos los empleados
     * @return
     */
    public String toString(){
        return empleados.stream().map(Empleado::toString).collect(Collectors.joining("\n"));
    }

    /**
     * Genera lista quedandose con los empleados con sueldo entre minimo y maximo
     * @param minimo
     * @param maximo
     * @return
     */
    public List<Empleado> filtrarSueldo(double minimo, double maximo){
        Predicate<Empleado> condicion = empleado -> (empleado.obtenerSueldo() >= minimo
                                                    && empleado.obtenerSueldo() <= maximo);

        return empleados.stream().filter(condicion).sorted(Comparator.comparing(Empleado::obtenerSueldo).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Obtiene el empleado mejor pagado
     * @return
     */
    public Empleado obtenerMejorPagado(){
        Stream<Empleado> ordenados = empleados.stream().sorted(Comparator.comparing(Empleado::obtenerSueldo).reversed());

        return ordenados.limit(1).collect(Collectors.toList()).get(0);
    }

    /**
     * Ordenacioon de empleados por apellidos y nombre
     * @return
     */
    public List<Empleado> ordenarApellidosNombre(){
        Comparator<Empleado> comparador = Comparator.comparing(Empleado::obtenerApellidos).thenComparing(Empleado::obtenerNombre);

        return empleados.stream().sorted(comparador).collect(Collectors.toList());
    }

    /**
     * Se obtiene la lista de apellidos sin repeticiones
     * @return
     */
    public List<String> apellidosSinRepeticiones(){
        return empleados.stream().parallel().map(Empleado::obtenerApellidos).
                flatMap(apellido -> Arrays.stream(apellido.split(" "))).
                distinct().sorted().collect(Collectors.toList());
    }

    /**
     * Agrupar los empleados por departamento de forma imperativa
     */
    public void agruparDepartamentosImperativa(){
        String departamento;
        List<Empleado> listaEmpleados;

        // tratamiendo de empleados uno a uno
        for(int i=0; i<empleados.size(); i++){
            departamento = empleados.get(i).obtenerDepartamento();

            // Se comprueba si en el diccionario ya tengo entrada para este departamento
            listaEmpleados = porDepartamento.get(departamento);

            // Si no hay entrada, lista = null
            if (listaEmpleados == null){
                listaEmpleados = new ArrayList<Empleado>();
                listaEmpleados.add(empleados.get(i));

                // agregar al diccionario una entrada con el departamento y la lista

                porDepartamento.put(departamento, listaEmpleados);
            }
            else{
                listaEmpleados.add(empleados.get(i));
            }
        }
    }

    /**
     * Agrupar los empleados por departamento de forma funcional
     */
    public void agruparDepartamentosFuncional(){
        porDepartamento = empleados.stream().collect(Collectors.groupingBy(Empleado::obtenerDepartamento));
    }

    /**
     * Cuenta los trabajadores por departamento
     * @return
     */
    public TreeMap<String, Long> contarPorDepartamento(){
        return empleados.stream().collect(Collectors.groupingBy(Empleado::obtenerDepartamento, TreeMap::new, Collectors.counting()));
    }

    /**
     * Obtiene estadisticas de los sueldos
     * @return
     */
    public DoubleSummaryStatistics obtenerEstadisticas(){
        return empleados.stream().mapToDouble(Empleado::obtenerSueldo).summaryStatistics();
    }

    public static void main(String args[]){
        ProcesarEmpleados procesador = new ProcesarEmpleados(args[0]);
        System.out.println(procesador);

        // Se filtran los trabajadores por sueldo
        System.out.println("----------Sueldos entre 5000 y 6000----------");
        List<Empleado> porSueldo = procesador.filtrarSueldo(5000, 6000);

        porSueldo.forEach(System.out::println);

        System.out.println("Mejor pagado: " + procesador.obtenerMejorPagado());

        System.out.println("Estadisticas de los sueldos:");
        DoubleSummaryStatistics estadisticas = procesador.obtenerEstadisticas();

        System.out.println("Media: " + estadisticas.getAverage());
    }
}
