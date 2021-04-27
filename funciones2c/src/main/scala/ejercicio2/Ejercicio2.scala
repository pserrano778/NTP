package ejercicio2

object Ejercicio2 extends App{
    def ordenado[A](as : Array[A], criterio: (A, A) => Boolean) : Boolean = {
        def go(indice : Int) : Boolean = {
            if (indice == as.length - 2) criterio(as(indice), as(indice+1))
            else if(!criterio(as(indice), as(indice+1))) false
            else go(indice + 1)
        }
        go (0)
    }

    val array1 : Array[Int] = Array(1, 5, 35, 98, 123, 215)
    var res = ordenado(array1)(_ < _)
    res = ordenado(array1)(_ > _)
}
