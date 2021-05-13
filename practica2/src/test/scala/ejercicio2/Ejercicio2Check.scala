package ejercicio2

import ejercicio1.TrianguloPascal.calcularValorTrianguloPascal
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object Ejercicio2Check extends Properties("Prueba de Practica 2 Ejercicio 2"){
    val MAXIMO = 15
    val MAXNUMEROS = 10
    // Se generan los terminos que se van a calcular
    val terminosACalcular = Gen.choose(3, MAXIMO)

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

    // Se va a comprobar la propiedad de que el termino I es obtenido a partir de los terminos I-1 e I-2

    property("Serie Fibonacci") = {
        forAll(terminosACalcular) { (termino) => {
            val resultado = fibonacci(termino)
            resultado == ejercicio2.SeriesRecurrentes.fibonacci(fibonacci(termino-1), fibonacci(termino-2))
        }}
    }

    property("Serie Lucas") = {
        forAll(terminosACalcular) { (termino) => {
            val resultado = lucas(termino)
            resultado == ejercicio2.SeriesRecurrentes.lucas(lucas(termino-1), lucas(termino-2))
        }}
    }

    property("Serie Pell") = {
        forAll(terminosACalcular) { (termino) => {
            val resultado = pell(termino)
            resultado == ejercicio2.SeriesRecurrentes.pell(pell(termino-1), pell(termino-2))
        }}
    }

    property("Serie Pell-Lucas") = {
        forAll(terminosACalcular) { (termino) => {
            val resultado = pellLucas(termino)
            resultado == ejercicio2.SeriesRecurrentes.pellLucas(pellLucas(termino-1), pellLucas(termino-2))
        }}
    }

    property("Serie Jacobsthal") = {
        forAll(terminosACalcular) { (termino) => {
            val resultado = jacobsthal(termino)
            resultado == ejercicio2.SeriesRecurrentes.jacobsthal(jacobsthal(termino-1), jacobsthal(termino-2))
        }}
    }
}
