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
    def busquedaBinaria[A](coleccion : Array[A], aBuscar: A)(criterio : (A,A) => Boolean) : Int = {
        //Funcion recursiva interna
        @annotation.tailrec
        def go(coleccion : Array[A], aBuscar: A, inicio : Int, fin: Int) : Int = {
            // Calculamos el indice intermedio
            val mitad = (inicio + fin) / 2

            // Si coincide, se devuelve el indice
            if (coleccion(mitad) == aBuscar) mitad

            // Si es mayor, se busca en la primera mitad de la coleccion actual
            else if (criterio(coleccion(mitad), aBuscar)) go(coleccion, aBuscar, inicio, mitad - 1)

            // Si es menor, se busca en la segundad mitad de la coleccion actual
            else go(coleccion, aBuscar, mitad + 1, fin)
        }

        //Primera llamada con toda la coleccion
        go(coleccion, aBuscar, 0, coleccion.size-1)
    }

    val array12 : Array[Int] = Array(-1, 5)
    var res2 = busquedaBinaria(array12, -1) (_ > _)
    println(res2)

    val array1 : Array[Int] = Array(1, 5, 35, 98, 123, 215)
    var res = busquedaBinaria(array1, 1) (_ > _)
    println(res)

    res = busquedaBinaria(array1, 5) (_ > _)
    println(res)

    res = busquedaBinaria(array1, 35) (_ > _)
    println(res)

    res = busquedaBinaria(array1, 98) (_ > _)
    println(res)

    res = busquedaBinaria(array1, 123) (_ > _)
    println(res)

    res = busquedaBinaria(array1, 215) (_ > _)
    println(res)
}
