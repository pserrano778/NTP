package ejercicio2

import org.scalatest.funsuite.AnyFunSuite

class Ejercicio2Test extends AnyFunSuite{

    // Se asigna la funcion serieFibonacci
    val fibonacci = ejercicio2.SeriesRecurrentes.serieFibonacci(_)

    // Se asigna la funcion serieLucas
    val lucas = ejercicio2.SeriesRecurrentes.serieLucas(_)

    // Se asigna la funcion seriePell
    val pell = ejercicio2.SeriesRecurrentes.seriePell(_)

    // Se asigna la funcion seriePellLucas
    val pellLucas = ejercicio2.SeriesRecurrentes.seriePellLucas(_)

    // Se asigna la funcion serieJacobsthal
    val jacobsthal = ejercicio2.SeriesRecurrentes.serieJacobsthal(_)


    // Prueba serie Fibonacci: se calcula la serie hasta el termino 15
    test("Serie de Fibonacci hasta termino 15") {
        assert(fibonacci(15) === List(0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377))
    }

    // Prueba serie Lucas: se calcula la serie hasta el termino 15
    test("Serie de Lucas hasta termino 15") {
        assert(lucas(15) === List(2, 1, 3, 4, 7, 11, 18, 29, 47, 76, 123, 199, 322, 521, 843))
    }

    // Prueba serie Pell: se calcula la serie hasta el termino 15
    test("Serie de Pell hasta termino 15") {
        assert(pell(15) === List(2, 6, 14, 34, 82, 198, 478, 1154, 2786, 6726, 16238, 39202, 94642, 228486, 551614))
    }

    // Prueba serie Pell-Lucas: se calcula la serie hasta el termino 15
    test("Serie de Pell-Lucas hasta termino 15") {
        assert(pellLucas(15) === List(2, 2, 6, 14, 34, 82, 198, 478, 1154, 2786, 6726, 16238, 39202, 94642, 228486))
    }

    // Prueba serie Jacobsthal: se calcula la serie hasta el termino 15
    test("Serie de Jacobsthal hasta termino 15") {
        assert(jacobsthal(15) === List(0, 1, 1, 3, 5, 11, 21, 43, 85, 171, 341, 683, 1365, 2731, 5461))
    }

}
