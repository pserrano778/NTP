package UsoTrait

trait UtilidadesHtml{
    def quitarMarca(entrada: String) = {
        entrada + " eliminadas marcas "
    }
}

trait UtilidadesString{
    def quitarEspacios(entrada: String) = {
        entrada + " eliminados espacios"
    }
}

class Texto(val contenido: String) extends UtilidadesHtml with UtilidadesString{ // Unica forma de herencia múltiple
    def simplificar: String = {
        val paso1 = quitarEspacios(contenido)
        val paso2  = quitarMarca(paso1)
        // Se devuelve paso2
        paso2
    }
}

object EjemploTrait extends App {
    val objeto = new Texto("mensaje originarl del objeto")
    println("simplificado " + objeto.simplificar)
}
