package ejercicio2

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer


object SeriesRecurrentes extends App {
    /**
     * funcion generica de de series Recurrentes mediante recursividad TR
     * @param funcion
     * @param termino1
     * @param termino2
     * @param terminoI
     * @return
     */
    def seriesRecurrentes(funcion : (Int, Int) => Int, termino1 : Int, termino2 : Int, terminoI : Int) : Array[Int] = {
        @annotation.tailrec
        def go(restantes : Int, arrayAcum : ArrayBuffer[Int]) : ArrayBuffer[Int] = {

            // Si llegamos a 0, devolvemos el la secuencia
            if (restantes == 0) arrayAcum

            // Primer termino
            else if (restantes == terminoI) go(restantes-1, arrayAcum+=termino1)

            // Segundo termino
            else if (restantes == terminoI-1) go(restantes-1, arrayAcum+=termino2)

            // Calculamos en base a los valores anteriores
            else go (restantes-1, arrayAcum+=funcion(arrayAcum.last, arrayAcum(arrayAcum.length-2)))
        }

        //Devolvemos en forma de array
        Array.from(go (terminoI, ArrayBuffer[Int]()))
    }

     /**
     * Serie de Fibonacci
     * @param termino1
     * @param termino2
     * @return
     */
    def fibonacci(termino1 : Int, termino2 : Int) = termino1 + termino2

    /**
     * Serie de Lucas
     * @param termino1
     * @param termino2
     * @return
     */
    def lucas(termino1 : Int, termino2 : Int) = termino1 + termino2

    /**
     * Serie de Pell
     * @param termino1
     * @param termino2
     * @return
     */
    def pell(termino1 : Int, termino2 : Int) = 2*termino1 + termino2

    /**
     * Serie de Pell-Lucas
     * @param termino1
     * @param termino2
     * @return
     */
    def pellLucas(termino1 : Int, termino2 : Int) = 2*termino1 + termino2

    /**
     * Serie de Jacobsthal
     * @param termino1
     * @param termino2
     * @return
     */
    def jacobsthal(termino1 : Int, termino2 : Int) = termino1 + 2*termino2


    //************** Funciones param hacer pruebas de forma comoda **************//

    /**
     * Funcion que define la relación de Fibonacci y los dos primeros valores, y permite calcular
     * un valor de la serie de forma recursiva
     * @return
     */
    def serieFibonacci : Int => Array[Int] = seriesRecurrentes(fibonacci, 0, 1, _)

    /**
     * Funcion que define la relación de Lucas y los dos primeros valores, y permite calcular
     * un valor de la serie de forma recursiva
     * @return
     */
    def serieLucas : Int => Array[Int] = seriesRecurrentes(lucas, 2, 1, _)

    /**
     * Funcion que define la relación de Pell y los dos primeros valores, y permite calcular
     * un valor de la serie de forma recursiva
     * @return
     */
    def seriePell : Int => Array[Int] = seriesRecurrentes(pell, 2, 6, _)

    /**
     * Funcion que define la relación de Pell-Lucas y los dos primeros valores, y permite calcular
     * un valor de la serie de forma recursiva
     * @return
     */
    def seriePellLucas : Int => Array[Int] = seriesRecurrentes(pellLucas, 2, 2, _)

    /**
     * Funcion que define la relación de Jacobsthal y los dos primeros valores, y permite calcular
     * un valor de la serie de forma recursiva
     * @return
     */
    def serieJacobsthal : Int => Array[Int] = seriesRecurrentes(jacobsthal, 0, 1, _)


    println("Finonacci (15): " + serieFibonacci(15).toList)
    println("Lucas (15): " + serieLucas(15).toList)
    println("Pell (15): " + seriePell(15).toList)
    println("Pell-Lucas (15): " + seriePellLucas(15).toList)
    println("Jacobsthal (15): " + serieJacobsthal(15).toList)
}
