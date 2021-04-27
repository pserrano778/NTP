package ejercicio5

import scala.math.sqrt

object BusquedaSaltosGenerica extends App{
    /**
     * Funcion recursiva que implementa la busqueda binaria
     * @param coleccion
     * @param aBuscar
     * @param criterio
     * @tparam A
     * @return
     */
    def busquedaSaltosGenerica[A](coleccion : Array[A], aBuscar: A)(criterio : (A,A) => Boolean) : Int = {
        // tamaño del bloque = raiz cuadrada del tamaño de la coleccion
        val tamBloque = sqrt(coleccion.length).toInt
        //Funcion recursiva interna
        @annotation.tailrec
        def go(numBloque: Int) : Int = {
            // Calculamos el indice (final del bloque actual)
            val indice = if (tamBloque*numBloque-1 < coleccion.size)tamBloque*numBloque-1
                        else coleccion.size-1

            // Si encontramos el valor, lo devolvemos
            if (aBuscar == coleccion(indice)) indice

            // Si el valor a buscar es mayor que el valor al final del bloque, pasamos al siguiente bloque
            else if (criterio(aBuscar, coleccion(indice))) go(numBloque+1)

            // Si el valor a buscar es meor que el valor al final del bloque, buscamos de forma lineal
            else busquedaLinealBloque(coleccion, aBuscar, tamBloque*(numBloque-1), indice)
        }

        //Primera llamada con toda la coleccion
        go(1)
    }

    /**
     * Función recursiva que implementa la búsqueda lineal en un rango de la coleccion (bloque)
     * @param coleccion
     * @param aBuscar
     * @param inicio
     * @param fin
     * @tparam A
     * @return
     */
    def busquedaLinealBloque[A](coleccion : Array[A], aBuscar: A, inicio: Int, fin: Int) : Int = {
        //Funcion recursiva interna
        @annotation.tailrec
        def go(indice: Int) : Int = {
            // Si encontramos el valor, devolvemos el indice
            if(coleccion(indice) == aBuscar) indice

            // Si no hemos llegado al final, avanzamos el indice
            else if(indice <= fin) go(indice+1)

            // Si no hemos encontrado el valor buscado
            else -1
        }
        go(inicio)
    }

    val array1 : Array[Int] = Array(1, 5, 35, 98, 123, 215)
    var res = busquedaSaltosGenerica(array1, 1) (_ > _)
    println(res)

    res = busquedaSaltosGenerica(array1, 5) (_ > _)
    println(res)

    res = busquedaSaltosGenerica(array1, 35) (_ > _)
    println(res)

    res = busquedaSaltosGenerica(array1, 98) (_ > _)
    println(res)

    res = busquedaSaltosGenerica(array1, 123) (_ > _)
    println(res)

    res = busquedaSaltosGenerica(array1, 215) (_ > _)
    println(res)
}
