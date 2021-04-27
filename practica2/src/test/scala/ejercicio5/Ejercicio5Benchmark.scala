package ejercicio5

import org.scalameter.{Key, Warmer, config}

import scala.util.Random

object Ejercicio5Benchmark extends App{
    // se define la configuración estandar de las pruebas
    val configuracion = config(
        Key.exec.maxWarmupRuns := 10,
        Key.exec.minWarmupRuns := 5,
        Key.exec.benchRuns := 100,
        Key.verbose := true
    ) withWarmer(new Warmer.Default)

    // genereador de array de enteros
    val generador = new Random

    // Generamos arrays de enteros de distintos tamaños

    val arrayEnterosTam10 = (0 until 10).map(indice => generador.between(-1000, 1001)).toArray.sorted

    val arrayEnterosTam50 = (0 until 50).map(indice => generador.between(-1000, 1001)).toArray.sorted

    val arrayEnterosTam100 = (0 until 100).map(indice => generador.between(-1000, 1001)).toArray.sorted

    val arrayEnterosTam500 = (0 until 500).map(indice => generador.between(-1000, 1001)).toArray.sorted

    val arrayEnterosTam1000 = (0 until 1000).map(indice => generador.between(-1000, 1001)).toArray.sorted


    // Generamos arrays de double de distintos tamaños

    val arrayDoubleTam10 = (0 until 10).map(indice => generador.between(-1000.0, 1001.0)).toArray.sorted

    val arrayDoubleTam50 = (0 until 50).map(indice => generador.between(-1000.0, 1001.0)).toArray.sorted

    val arrayDoubleTam100 = (0 until 100).map(indice => generador.between(-1000.0, 1001.0)).toArray.sorted

    val arrayDoubleTam500 = (0 until 500).map(indice => generador.between(-1000.0, 1001.0)).toArray.sorted

    val arrayDoubleTam1000 = (0 until 1000).map(indice => generador.between(-1000.0, 1001.0)).toArray.sorted


    // Tiempos con busqueda binaria
    //Arrays de enteros
    val tiemposBusquedaBinariaEnteros10 = configuracion measure(
        arrayEnterosTam10.foreach(valor =>
            ejercicio5.BusquedaBinariaGenerica.busquedaBinaria(arrayEnterosTam10, valor)(_ > _))
        )

    val tiemposBusquedaBinariaEnteros50 = configuracion measure(
        arrayEnterosTam50.foreach(valor =>
            ejercicio5.BusquedaBinariaGenerica.busquedaBinaria(arrayEnterosTam50, valor)(_ > _))
        )

    val tiemposBusquedaBinariaEnteros100 = configuracion measure(
        arrayEnterosTam100.foreach(valor =>
            ejercicio5.BusquedaBinariaGenerica.busquedaBinaria(arrayEnterosTam100, valor)(_ > _))
        )

    val tiemposBusquedaBinariaEnteros500 = configuracion measure(
        arrayEnterosTam500.foreach(valor =>
            ejercicio5.BusquedaBinariaGenerica.busquedaBinaria(arrayEnterosTam500, valor)(_ > _))
        )

    val tiemposBusquedaBinariaEnteros1000 = configuracion measure(
        arrayEnterosTam1000.foreach(valor =>
            ejercicio5.BusquedaBinariaGenerica.busquedaBinaria(arrayEnterosTam1000, valor)(_ > _))
        )

    // Arrays de double
    val tiemposBusquedaBinariaDouble10 = configuracion measure(
        arrayDoubleTam10.foreach(valor =>
            ejercicio5.BusquedaBinariaGenerica.busquedaBinaria(arrayDoubleTam10, valor)(_ > _))
        )

    val tiemposBusquedaBinariaDouble50 = configuracion measure(
        arrayDoubleTam50.foreach(valor =>
            ejercicio5.BusquedaBinariaGenerica.busquedaBinaria(arrayDoubleTam50, valor)(_ > _))
        )

    val tiemposBusquedaBinariaDouble100 = configuracion measure(
        arrayDoubleTam100.foreach(valor =>
            ejercicio5.BusquedaBinariaGenerica.busquedaBinaria(arrayDoubleTam100, valor)(_ > _))
        )

    val tiemposBusquedaBinariaDouble500 = configuracion measure(
        arrayDoubleTam500.foreach(valor =>
            ejercicio5.BusquedaBinariaGenerica.busquedaBinaria(arrayDoubleTam500, valor)(_ > _))
        )

    val tiemposBusquedaBinariaDouble1000 = configuracion measure(
        arrayDoubleTam1000.foreach(valor =>
            ejercicio5.BusquedaBinariaGenerica.busquedaBinaria(arrayDoubleTam1000, valor)(_ > _))
        )

    // Tiempos con busqueda a saltos
    //Arrays de enteros
    val tiemposBusquedaSaltosEnteros10 = configuracion measure(
        arrayEnterosTam10.foreach(valor =>
            ejercicio5.BusquedaSaltosGenerica.busquedaSaltosGenerica(arrayEnterosTam10, valor)(_ > _))
        )

    val tiemposBusquedaSaltosEnteros50 = configuracion measure(
        arrayEnterosTam50.foreach(valor =>
            ejercicio5.BusquedaSaltosGenerica.busquedaSaltosGenerica(arrayEnterosTam50, valor)(_ > _))
        )

    val tiemposBusquedaSaltosEnteros100 = configuracion measure(
        arrayEnterosTam100.foreach(valor =>
            ejercicio5.BusquedaSaltosGenerica.busquedaSaltosGenerica(arrayEnterosTam100, valor)(_ > _))
        )

    val tiemposBusquedaSaltosEnteros500 = configuracion measure(
        arrayEnterosTam500.foreach(valor =>
            ejercicio5.BusquedaSaltosGenerica.busquedaSaltosGenerica(arrayEnterosTam500, valor)(_ > _))
        )

    val tiemposBusquedaSaltosEnteros1000 = configuracion measure(
        arrayEnterosTam1000.foreach(valor =>
            ejercicio5.BusquedaSaltosGenerica.busquedaSaltosGenerica(arrayEnterosTam1000, valor)(_ > _))
        )

    // Arrays de double
    val tiemposBusquedaSaltosDouble10 = configuracion measure(
        arrayDoubleTam10.foreach(valor =>
            ejercicio5.BusquedaSaltosGenerica.busquedaSaltosGenerica(arrayDoubleTam10, valor)(_ > _))
        )

    val tiemposBusquedaSaltosDouble50 = configuracion measure(
        arrayDoubleTam50.foreach(valor =>
            ejercicio5.BusquedaSaltosGenerica.busquedaSaltosGenerica(arrayDoubleTam50, valor)(_ > _))
        )

    val tiemposBusquedaSaltosDouble100 = configuracion measure(
        arrayDoubleTam100.foreach(valor =>
            ejercicio5.BusquedaSaltosGenerica.busquedaSaltosGenerica(arrayDoubleTam100, valor)(_ > _))
        )

    val tiemposBusquedaSaltosDouble500 = configuracion measure(
        arrayDoubleTam500.foreach(valor =>
            ejercicio5.BusquedaSaltosGenerica.busquedaSaltosGenerica(arrayDoubleTam500, valor)(_ > _))
        )

    val tiemposBusquedaSaltosDouble1000 = configuracion measure(
        arrayDoubleTam1000.foreach(valor =>
            ejercicio5.BusquedaSaltosGenerica.busquedaSaltosGenerica(arrayDoubleTam1000, valor)(_ > _))
        )

    println("tiempo Busqueda Binaria en array de enteros de tam 10: " + tiemposBusquedaBinariaEnteros10)
    println("tiempo Busqueda a Saltos en array de enteros de tam 10: " + tiemposBusquedaSaltosEnteros10)
    println()
    println("tiempo Busqueda Binaria en array de enteros de tam 50: " + tiemposBusquedaBinariaEnteros50)
    println("tiempo Busqueda a Saltos en array de enteros de tam 50: " + tiemposBusquedaSaltosEnteros50)
    println()
    println("tiempo Busqueda Binaria en array de enteros de tam 100: " + tiemposBusquedaBinariaEnteros100)
    println("tiempo Busqueda a Saltos en array de enteros de tam 100: " + tiemposBusquedaSaltosEnteros100)
    println()
    println("tiempo Busqueda Binaria en array de enteros de tam 500: " + tiemposBusquedaBinariaEnteros500)
    println("tiempo Busqueda a Saltos en array de enteros de tam 500: " + tiemposBusquedaSaltosEnteros500)
    println()
    println("tiempo Busqueda Binaria en array de enteros de tam 1000: " + tiemposBusquedaBinariaEnteros1000)
    println("tiempo Busqueda a Saltos en array de enteros de tam 1000: " + tiemposBusquedaSaltosEnteros1000)
    println()
    println("tiempo Busqueda Binaria en array de double de tam 10: " + tiemposBusquedaBinariaDouble10)
    println("tiempo Busqueda a Saltos en array de double de tam 10: " + tiemposBusquedaSaltosDouble10)
    println()
    println("tiempo Busqueda Binaria en array de double de tam 50: " + tiemposBusquedaBinariaDouble50)
    println("tiempo Busqueda a Saltos en array de double de tam 50: " + tiemposBusquedaSaltosDouble50)
    println()
    println("tiempo Busqueda Binaria en array de double de tam 100: " + tiemposBusquedaBinariaDouble100)
    println("tiempo Busqueda a Saltos en array de double de tam 100: " + tiemposBusquedaSaltosDouble100)
    println()
    println("tiempo Busqueda Binaria en array de double de tam 500: " + tiemposBusquedaBinariaDouble500)
    println("tiempo Busqueda a Saltos en array de double de tam 500: " + tiemposBusquedaSaltosDouble500)
    println()
    println("tiempo Busqueda Binaria en array de double de tam 1000: " + tiemposBusquedaBinariaDouble1000)
    println("tiempo Busqueda a Saltos en array de double de tam 1000: " + tiemposBusquedaSaltosDouble1000)

    println("En genreal, en arrays pequeños, la Búsqueda a Saltos puede llegar a ser un poco más rápida.")
    println("Para arrays grandes, la Búsqueda Binaria es bastate más rápida.")
    println("El tipo de dato no parece influir en exceso entre ambas búsquedas.")
}
