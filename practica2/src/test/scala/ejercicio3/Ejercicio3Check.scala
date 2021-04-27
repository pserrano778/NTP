package ejercicio3

import ejercicio3.BalanceoCadenasConParentesis.chequearBalance
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object Ejercicio3Check extends Properties("Prueba de Practica 2 Ejercicio 3"){

    val MAXIMALONGITUD = 20

    // Generacion de cadenas de longitud n: forma de uso strGen(10) para cadenas
    // de 10 caracteres
    val strGen =
    (n: Int) =>
    Gen.listOfN(n, Gen.oneOf('(', ')',Gen.alphaChar.sample.get)).
    map(_.mkString)


    property("Balance de cadenas") = {
    forAll(strGen(Gen.choose(1,MAXIMALONGITUD).sample.get)) {
        cadena => {
            val condicion = chequearBalance(cadena.toList)
            var global = true
            for(i <- 2 until cadena.length) {
                    val substring=cadena.substring(0,i)
                    val openCount=substring.filter(c => c == '(').length
                    val closeCount=substring.filter(c => c ==')' ).length
                    global = global && ((openCount-closeCount) >= 0)
                }
            // si se cumple la condicion, entonces global debe
            // ser true
            if (condicion == true) global == true
            // en caso de no cumplirse, la condicion global puede
            // ser positiva o negativa....
            else true
            }
        }
    }
}
