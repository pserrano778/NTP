package ejercicio1

object Ejercicio1v2 extends App{
    def sumatorio(funcion : Int => Int, inf : Int, sup : Int) : Int = {
        if(inf > sup) 0
        else funcion(inf) + sumatorio(funcion, inf+1, sup)
    }

    /**
     * funcion general de suma mediante recursividad TR
     * @param funcion
     * @param inf
     * @param sup
     * @return
     */
    def sumatorioTR(funcion : Int => Int, inf : Int, sup : Int) : Int = {
        @annotation.tailrec
        def go(inf : Int, acum : Int) : Int = {
            if (inf > sup) acum
            else go(inf+1, acum + funcion(inf))
        }

        go (inf, 0)
    }

    /**
     * Funcion identidad
     * @param x
     * @return
     */
    def identidad(x : Int) = x

    /**
     * Funcion cuadrado
     * @param x
     * @return
     */
    def cuadrado(x : Int) = x*x

    /**
     * Funcion potencia de 2
     * @param x
     * @return
     */
    def potencia2(x : Int) : Int = {
        @annotation.tailrec
        def go(x : Int, acum : Int) : Int = {
            if (x == 0) acum
            else go(x-1, 2*acum)
        }
        go(x, 1)
    }

    // uso de sumatorio generico

    var suma1_10 = sumatorio(identidad, 1, 10)
    println("suma de 1 a 10: " + suma1_10)

    suma1_10 = sumatorio(cuadrado, 1, 10)
    println("cuadrado de 1 a 10: " + suma1_10)

    // se generan nuevas funciones

    val sumatorioBasico: (Int, Int) => Int = sumatorio(identidad, _, _)
    suma1_10 = sumatorioBasico(1, 10);
    println("suma de 1 a 10: " + suma1_10)

    def sumatorioBasico2 = sumatorio(identidad, _, _)
    sumatorioBasico2(1, 10)

    // funciones con varias listas de argumentos (currying)
    def sumatorioTRV2(funcion : Int => Int)(inf : Int, sum : Int) : Int ={
        @annotation.tailrec
        def go(inf : Int, acum : Int) : Int = {
            if (inf > sum) acum
            else go(inf+1, funcion(inf) + acum)
        }
        go(inf, 0)
    }

    val sumatorioBasico3 : (Int, Int) => Int = sumatorioTRV2(identidad)
    def sumatorioBasico4 = sumatorioTRV2(identidad)_
}
