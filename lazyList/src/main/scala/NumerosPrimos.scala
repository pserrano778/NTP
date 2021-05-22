import scala.math.sqrt

object NumerosPrimos {

    /**
     * Comprobación sobre si un número es primo
     * @param numero
     * @return
     */
    def esPrimo(numero : Int) : Boolean = {
        !(2 to sqrt(numero).toInt).exists(x => numero % x == 0)
    }

    /**
     * Obtiene si el n-ésimo número es primo
     * @param inf
     * @param sup
     * @param n
     * @return
     */
    def enesimoPrimoEnRango(inf: Int, sup: Int, n: Int): Int = {
        val secuenciaPrimos : Seq[Int] = (inf to sup).filter(x => esPrimo(x))
        secuenciaPrimos(n)
    }

    /**
    * Obtiene si el n-ésimo número es primo de forma recursiva
        * @param inf
        * @param sup
        * @param n
        * @return
    */
    def enesimoPrimoEnRangoRecursivo(inf: Int, sup: Int, n: Int): Int = {
        if(esPrimo(inf)){
            if (n == 1) inf
            else enesimoPrimoEnRangoRecursivo(inf+1, sup, n-1)
        }
        else enesimoPrimoEnRangoRecursivo(inf+1, sup, n)
    }

    /**
     * Construye un rango perezoso mediante el uso de LazyList
     * @param inf
     * @param sup
     * @return
     */
    def rangoPerezoso(inf: Int, sup: Int) : LazyList[Int] = {
        if (inf > sup) LazyList.empty
        else LazyList.cons(inf, rangoPerezoso(inf+1, sup))
    }

    /**
     * Metodo para obtener el n-esimo primo pero usando rangos perezosos
     * @param inf
     * @param sup
     * @param n
     * @return
     */
    def enesimoPrimoEnRangoPerezoso(inf: Int, sup: Int, n: Int) = {
        rangoPerezoso(inf, sup).filter(x => esPrimo(x))(n)
    }

    /**
     * Metodo para obtener el n-esimo primo usando view
     * @param inf
     * @param sup
     * @param n
     * @return
     */
    def enesimoPrimoEnRangoView(inf: Int, sup: Int, n: Int): Int = {
        (inf to sup).view.filter(x => esPrimo(x)).drop(n-1).head
    }
}
