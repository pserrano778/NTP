package ejercicio1

import org.scalameter.{Key, Warmer, config}

import scala.util.Random

object Ejercicio1v1Benchmark extends App{
    // se define la configuración estandar de las pruebas
    val configuracion = config(
        Key.exec.maxWarmupRuns := 10,
        Key.exec.minWarmupRuns := 5,
        Key.exec.benchRuns := 10,
        Key.verbose := true
    ) withWarmer(new Warmer.Default)

    // genereador de intervalos de forma aleatoria
    val generador = new Random
    val intervalos = (0 until 1000).map(index => {
        val inicio = generador.nextInt(1000)
        val fin = inicio + generador.nextInt(1000)

        (inicio, fin)
    }).toList

    // probar tiempo con enfoque no TR
    val tiemposNoTR = configuracion measure(
        intervalos.foreach(intervalo => Ejercicio1v1.sumatorio(intervalo._1, intervalo._2))
    )

    // probar tiempo con enfoque TR
    val tiemposTR = configuracion measure(
        intervalos.foreach(intervalo => Ejercicio1v1.sumatorioTR(intervalo._1, intervalo._2))
    )

    println("tiempo no TR: " + tiemposNoTR)
    println("tiempo TR: " + tiemposTR)
}
