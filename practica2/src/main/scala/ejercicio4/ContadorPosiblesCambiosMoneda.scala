package ejercicio4

import scala.collection.immutable.List

object ContadorPosiblesCambiosMoneda extends App{

    /**
     * Funcion recursiva que permite obtener las combinaciones de cambio posibles dada una lista de tipos de moneda
     * @param cantidad
     * @param monedas
     * @return
     */
    def listarCambiosPosibles(cantidad: Int, monedas: List[Int]): List[List[Int]] = {
        // Función interna recursiva. Combinación es la lista de monedas que llevamos en la prueba actual (Ej: 5, 5, 4...)
        def go (cantidad: Int, monedas: List[Int], combinacion: List[Int]): List[List[Int]] = {
            var lista = List[List[Int]]()

            // Si quedan monedas
            if (!monedas.isEmpty){

                // Si con la moneda se completa la cantidad a devolver, se finaliza esa combinación
                if (cantidad - monedas.head == 0) lista = List(combinacion:::List(monedas.head))

                // Si necesitamos más cambio, se continua con la moneda actual
                else if (cantidad - monedas.head > 0) lista = go(cantidad - monedas.head, monedas, combinacion:::List(monedas.head))

                // Como quedan monedas, siempre se prueba con las restantes
                lista:::go(cantidad, monedas.tail, combinacion)
            }
            // Si no quedan monedas, se devuelve una lista vacía
            else List()
        }
        // Primera llamada
       go(cantidad, monedas, List())
    }

    println("Cambio:5 Monedas: 5 - " + listarCambiosPosibles(1, List(2, 6, 5)))
    println("Cambio:4 Monedas: 1, 2, 3, 4 - " + listarCambiosPosibles(4, List(1, 2, 3, 4)))
    println("Cambio:20 Monedas: 4, 5, 6, 9, 10, 11 - " + listarCambiosPosibles(20, List(4, 5, 6, 9, 10, 11)))
}
