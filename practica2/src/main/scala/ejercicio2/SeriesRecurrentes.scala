package ejercicio2

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer


object SeriesRecurrentes extends App {
    /**
     * funcion generica de de series Recurrentes mediante recursividad TR
     * @param funcion
     * @param primerTermino
     * @param segundoTermino
     * @param terminoI
     * @return
     */
    def seriesRecurrentes(funcion : (Int, Int) => Int, primerTermino : Int, segundoTermino : Int, terminoI : Int) : Int = {
        @annotation.tailrec
        def go(termino : Int, acum : Int, anterior : Int) : Int = {
            // Cuando hemos lleago al elemento deseado, devolvemos acum
            if (termino == 0) acum

            // Primer Termino (no tiene anterior)
            else if (termino == terminoI) go(termino-1, primerTermino, 0)

            // Segundo Termino (el anterior es el primer termino)
            else if (termino == terminoI-1) go(termino-1, segundoTermino, primerTermino)

            // Calculo del termino I+1, a partir del termino actual, y de su anterior.
            // El anterior al termino I+1 es el termino actual (acum)
            else go (termino-1, funcion(acum, anterior), acum)
        }
        // Primera llamada
        go (terminoI, 0, 0)
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
    def serieFibonacci : Int => Int = seriesRecurrentes(fibonacci, 0, 1, _)

    /**
     * Funcion que define la relación de Lucas y los dos primeros valores, y permite calcular
     * un valor de la serie de forma recursiva
     * @return
     */
    def serieLucas : Int => Int = seriesRecurrentes(lucas, 2, 1, _)

    /**
     * Funcion que define la relación de Pell y los dos primeros valores, y permite calcular
     * un valor de la serie de forma recursiva
     * @return
     */
    def seriePell : Int => Int = seriesRecurrentes(pell, 2, 6, _)

    /**
     * Funcion que define la relación de Pell-Lucas y los dos primeros valores, y permite calcular
     * un valor de la serie de forma recursiva
     * @return
     */
    def seriePellLucas : Int => Int = seriesRecurrentes(pellLucas, 2, 2, _)

    /**
     * Funcion que define la relación de Jacobsthal y los dos primeros valores, y permite calcular
     * un valor de la serie de forma recursiva
     * @return
     */
    def serieJacobsthal : Int => Int = seriesRecurrentes(jacobsthal, 0, 1, _)

    println("Finonacci (5): " + serieFibonacci(5))
    println("Finonacci (6): " + serieFibonacci(6))
    println("Lucas (5): " + serieLucas(5))
    println("Pell (5): " + seriePell(5))
    println("Pell-Lucas (5): " + seriePellLucas(5))
    println("Jacobsthal (5): " + serieJacobsthal(5))
}
