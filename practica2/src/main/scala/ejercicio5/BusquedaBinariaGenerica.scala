package ejercicio5


object BusquedaBinariaGenerica extends App{

    /**
     * Funcion recursiva que implementa la busqueda binaria
     * @param coleccion
     * @param aBuscar
     * @param criterio
     * @tparam A
     * @return
     */
    def busquedaBinaria[A](coleccion : Array[A], aBuscar: A, criterio : (A,A) => Boolean) : Int = {
        //Funcion recursiva interna
        @annotation.tailrec
        def go(inicio : Int, fin: Int) : Int = {
            // Calculamos el indice intermedio
            val mitad = (inicio + fin) / 2

            // Si coincide, se devuelve el indice
            if (coleccion(mitad) == aBuscar) mitad

            // Si es mayor, se busca en la primera mitad de la coleccion actual
            else if (criterio(coleccion(mitad), aBuscar)) go(inicio, mitad - 1)

            // Si es menor, se busca en la segundad mitad de la coleccion actual
            else go(mitad + 1, fin)
        }

        //Primera llamada con toda la coleccion
        go( 0, coleccion.size-1)
    }

    // Se define el criterio
    def criterioEnteros (num1 : Int, num2: Int) = num1>num2

    val array1 : Array[Int] = Array(1, 5, 35, 98, 123, 215)
    var res = busquedaBinaria(array1, 1, criterioEnteros)
    println(res)


    res = busquedaBinaria(array1, 5, criterioEnteros)

    println(res)

    res = busquedaBinaria(array1, 35, criterioEnteros)
    println(res)

    res = busquedaBinaria(array1, 98, criterioEnteros)
    println(res)

    res = busquedaBinaria(array1, 123, criterioEnteros)
    println(res)

    res = busquedaBinaria(array1, 215, criterioEnteros)
    println(res)
}
