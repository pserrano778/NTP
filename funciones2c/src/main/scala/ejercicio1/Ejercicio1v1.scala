package ejercicio1

object Ejercicio1v1 extends App{
    /**
     * funcion suamtorio entre dos limites
     * @param inf
     * @param sup
     * @return
     */
    def sumatorio(inf : Int, sup : Int) : Int = {
        // caso base
        if(inf > sup) 0
        else inf + sumatorio(inf + 1, sup)
    }

    /**
     * Funcion sumatorio con TR
     * @param inf
     * @param sup
     * @return
     */
    def sumatorioTR(inf : Int, sup : Int) : Int = {
        @annotation.tailrec
        def go (inf : Int, sup : Int, acum : Int) : Int = {
            if(inf > sup) acum
            else go(inf+1, sup, acum+inf)
        }

        go(inf, sup, 0);
    }

    // comprobación
    var time1 = System.nanoTime()
    val res1 = sumatorio(1, 10)
    var time2 = System.nanoTime()
    println("tiempo version no TR: " + (time2-time1))
    println("res1: " + res1)

    time1 = System.nanoTime()
    val res2 = sumatorioTR(1, 10)
    time2 = System.nanoTime()
    println("tiempo version no TR: " + (time2-time1))
    println("res2: " + res2)
}
