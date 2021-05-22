import org.scalameter.{ Key, Warmer, config}

object TiemposEjecucion extends App{
    val configuracion = config {
        Key.exec.maxWarmupRuns := 10;
        Key.exec.minWarmupRuns := 5;
        Key.exec.benchRuns := 10;
        Key.verbose := true
    } withWarmer(new Warmer.Default)

    // Se determina el intervalo de a usar en todas las pruebas
    val inf = 10000
    val sup = 1000000
    val n = 5

    // se prueba el metodo usando secuencias, filtrando a
    // partir de un rango de valores

    // Metodo 1
    val tiempoMetodo1 = configuracion measure{
        NumerosPrimos.enesimoPrimoEnRango(inf, sup, n)
    }
    println("Tiempo de ejecucion con el metodo 1: " + tiempoMetodo1)

    // Metodo 2
    val tiempoMetodo2 = configuracion measure{
        NumerosPrimos.enesimoPrimoEnRangoRecursivo(inf, sup, n)
    }
    println("Tiempo de ejecucion con el metodo 2: " + tiempoMetodo2)

    // Metodo 3
    val tiempoMetodo3 = configuracion measure{
        NumerosPrimos.enesimoPrimoEnRangoPerezoso(inf, sup, n)
    }
    println("Tiempo de ejecucion con el metodo 3: " + tiempoMetodo3)

    // Metodo 4
    val tiempoMetodo4 = configuracion measure{
        NumerosPrimos.enesimoPrimoEnRangoView(inf, sup, n)
    }
    println("Tiempo de ejecucion con el metodo 4: " + tiempoMetodo4)
}
