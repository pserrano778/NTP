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


    // Prueba serie Fibonacci: primer termino
    test("Serie de Fibonacci - termino 1") {
        assert(fibonacci(1) === 0)
    }

    // Prueba serie Fibonacci: segundo termino
    test("Serie de Fibonacci - termino 2") {
        assert(fibonacci(2) === 1)
    }

    // Prueba serie Lucas: primer termino
    test("Serie de Lucas - termino 1") {
        assert(lucas(1) === 2)
    }

    // Prueba serie Lucas: segundo termino
    test("Serie de Lucas - termino 2") {
        assert(lucas(2) === 1)
    }

    // Prueba serie Pell: primer termino
    test("Serie de Pell - termino 1") {
        assert(pell(1) === 2)
    }

    // Prueba serie Pell: segundo termino
    test("Serie de Pell - termino 2") {
        assert(pell(2) === 6)
    }

    // Prueba serie Pell-Lucas: primer termino
    test("Serie de Pell-Lucas - termino 1") {
        assert(pellLucas(1) === 2)
    }

    // Prueba serie Pell-Lucas: segundo termino
    test("Serie de Pell-Lucas - termino 2") {
        assert(pellLucas(2) === 2)
    }

    // Prueba serie Jacobsthal: primer termino
    test("Serie de Jacobsthal - termino 1") {
        assert(jacobsthal(1) === 0)
    }

    // Prueba serie Jacobsthal: segundo termino
    test("Serie de Jacobsthal - termino 2") {
        assert(jacobsthal(2) === 1)
    }
}
