package ejercicio1

import ejercicio1.TrianguloPascal.calcularValorTrianguloPascal

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object Ejercicio1Check extends Properties("Prueba dePractica 2 Ejercicio1"){
    val MAXIMO = 11

    // Se generan los valores de fila y columna para los bordes
    val coordenadasExtremos = for {
        // se genera numero de fila: valor comprendido entre 0 y MAXIMO
        // (MAXIMO no esta incluido)
        fila <- Gen.choose(0, MAXIMO)

        // se genera numero de columna: o 0 o el valor de fila. Esto
        // asegura que se trata de un valor de los extremos (X,0) o
        // (X,X)
        columna <- Gen.oneOf(0, fila)
        } yield (fila, columna)

    property("Elementos en lados del triangulo valen 1") = {
        forAll(coordenadasExtremos) { (i) => {
            val resultado = calcularValorTrianguloPascal(i._1, i._2)
            println("Fila = " + i._1 + " Columna = " + i._2 + " Resultado = " + resultado)
            resultado == 1
        }}
    }

    // Se generan los valores de fila y columna para el interior
    val coordenadasTrianguloInterior = for {
        // se genera numero de fila: valor comprendido entre 0 y MAXIMO
        // (MAXIMO no esta incluido)
        fila <- Gen.choose(2, MAXIMO)

        // se genera numero de columna en el interior del triángulo
        columna <- Gen.choose(1, fila-1)
    } yield (fila, columna)

    property("Elementos en interior del triangulo valen la suma de sus dos elementos superiores") = {
        forAll(coordenadasTrianguloInterior) { (i) => {
            val resultado = calcularValorTrianguloPascal(i._1, i._2)
            println("Fila = " + i._1 + " Columna = " + i._2 + " Resultado = " + resultado + ". Suma de " + calcularValorTrianguloPascal(i._1-1, i._2-1) + " y " + calcularValorTrianguloPascal(i._1-1, i._2))
            val sumaAnteriores = calcularValorTrianguloPascal(i._1-1, i._2-1) + calcularValorTrianguloPascal(i._1-1, i._2)
            resultado == sumaAnteriores
        }}
    }
}
