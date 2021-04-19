package ejercicio1

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object Ejercicio1v1Check extends Properties("Prueba de ejercicio1 versión 1"){
    val INF : Int = 100
    val SUP : Int = 1000

    // generador de intervalos
    val intervalos = for {
        // limite inferior: comprendido entre INF - INF+50
        inferior <- Gen.choose(INF, INF+50)
        // limite superior: comprendido entre SUP - SUP+50
        superior <- Gen.choose(SUP, SUP+50)

        // tambien se puede hacer con Gen.oneOf(SUP, SUP+50) Coge uno de los 2
    } yield(inferior, superior)

    property("Igualdad de metodos no TR y TR") = {
        forAll(intervalos) { intervalo => {
            println("inf: " + intervalo._1, " sup: " + intervalo._2)
            val resultadorNoTR = Ejercicio1v1.sumatorio(intervalo._1, intervalo._2)
            val resultadorTR = Ejercicio1v1.sumatorioTR(intervalo._1, intervalo._2)

            // comprobacion
            resultadorNoTR == resultadorTR
        }}
    }
}
