import scala.io.Source

object LecturaArchivo {

    // primera aproximación
    def procesadoV1(nombre : String) = {
        for(linea <- Source.fromFile(nombre).getLines())
            println(s"${linea.length}: $linea")
    }

    // segunda implementación
    def calcularAnchoTamanioLinea(s: String) = {
        s.length.toString.length
    }

    // Segunda versión del procesado
    def procesadoV2(nombre : String) = {
        val lineas = Source.fromFile(nombre).getLines.toList

        // calcular el máximo tamaño del ancho de línea
        val maximo = lineas.map(calcularAnchoTamanioLinea(_)).max

        // procesar cada línea para ver cuátos blancos tengo que poner de relleno para mantener la indentacion
        for(linea <- lineas){

            val tamLinea = calcularAnchoTamanioLinea(linea)
            val relleno = " "*(maximo-tamLinea)
            println(s"$relleno${linea.length}: $linea")
        }
    }

    def procesadoV3(nombre : String) = {
        val lineas = Source.fromFile(nombre).getLines.toList

        val lineaMasLarga = lineas.reduceLeft((a, b) => if(a.length > b.length) a else b)

        // otra posibilidad
        val lineaMasLarga2 = lineas.sortBy(_.length).head

        // se calcula el ancho de la línea más larga
        val maximo = calcularAnchoTamanioLinea(lineaMasLarga)

        // se muestran las lineas
        for(linea <- lineas){
            val tamLinea = calcularAnchoTamanioLinea(linea)
            val relleno = " "*(maximo-tamLinea)
            println(s"$relleno${linea.length}: $linea")
        }
    }

    def main(args: Array[String]) = { // MAIN
        procesadoV2(args(0))
    }
}
