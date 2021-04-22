package ejercicio2

object SeriesRecurrentes extends App {
    /**
     * funcion general de suma mediante recursividad TR
     * @param funcion
     * @param inf
     * @param sup
     * @return
     */
    def seriesRecurrentes(funcion : (Int, Int) => Int, termino1 : Int, termino2 : Int, terminoI : Int) : Int = {

        def go(termino : Int, acum : Int) : Int = {
            if (termino == 1) termino1
            else if (termino == 2) termino2
            else funcion(go(termino-1, acum), go(termino-2, acum))
        }

        go (terminoI, 0)
    }

    def seriesRecurrentesV2(funcion : (Int, Int) => Int, termino1 : Int, termino2 : Int, terminoI : Int) : Int = {

        def go(termino : Int, acum : Int, anterior : Int) : Int = {

            if (termino == 1) termino1
            else if (termino == 2) termino2
            else if (termino == 3) acum
            else go (termino-1, funcion(acum, anterior), acum)

        }
        go (terminoI, funcion(termino2, termino1), termino2)
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
     * Serie de Pell
     * @param termino1
     * @param termino2
     * @return
     */
    def Jacobsthal(termino1 : Int, termino2 : Int) = termino1 + 2*termino2

    var suma1_10 = seriesRecurrentes(fibonacci, 0, 1, 6)
    println("2: " + suma1_10)

    suma1_10 = seriesRecurrentesV2(fibonacci, 0, 1, 6)
    println("2: " + suma1_10)
}
