package Construccion

class PuntoAleatorio{
    val x = {
        println("Asignación de X")
        util.Random.nextDouble()
    }

    // No se realiza hasta que se necesite el valor de y
    lazy val y = {
        println("Asignación de Y")
        util.Random.nextDouble()
    }
}

object PruebaLazy extends App{
    // Se crea un objeto de la clase punto aleatorio
    val p1 = new PuntoAleatorio
    println("coordenadas de p1: " + p1.x + " - " + p1.y)
}
