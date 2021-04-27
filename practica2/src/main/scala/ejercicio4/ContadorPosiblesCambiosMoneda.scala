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
        // Función interna recursiva
        def go (cantidad: Int, monedas: List[Int], combinacion: List[Int]): List[List[Int]] = {
            // Si no quedan monedas
            if(monedas.isEmpty) List()

            // Si con la moneda se completa la cantidad a devolver, se finaliza esa combinación, y se prueba con el resto
            else if (cantidad - monedas.head == 0) List(combinacion:::List(monedas.head)):::go(cantidad, monedas.tail, combinacion)

            // Si necesitamos más cambio, se continua con la moneda actual, y se prueba con el resto
            else if (cantidad - monedas.head > 0) go(cantidad - monedas.head, monedas, combinacion:::List(monedas.head)):::go(cantidad, monedas.tail, combinacion)

            // Si no se puede continuar con la moneda actual, se prueba con el resto
            else go(cantidad, monedas.tail, combinacion)
        }

       go(cantidad, monedas, List[Int]())
    }

    println("Cambio:5 Monedas: 5 - " + listarCambiosPosibles(5, List(4)))
    println("Cambio:4 Monedas: 1, 2, 3, 4 - " + listarCambiosPosibles(4, List(1, 2, 3, 4)))
    println("Cambio:20 Monedas: 4, 5, 6, 9, 10, 11 - " + listarCambiosPosibles(20, List(4, 5, 6, 9, 10, 11)))
}
