package ejercicio1

object TrianguloPascal {

    /**
     * Método para calcular un valor del triangulo pascal de forma  tail recursive
     * @param fila
     * @param columna
     * @return
     */
    def calcularValorTrianguloPascal(fila: Int, columna: Int): BigInt = {
        @annotation.tailrec
        def go(num: BigInt, acum: BigInt) : BigInt = {
            // Si es 1 o 0, se devuelve el valor
            if (num == 1 || num == 0) acum
            // En otro caso, se continua el calculo del factorial
            else go(num - 1, acum*num)
        }

        //Formula para calcular el valor del triangulo deseado
        go(fila, 1) / (go(columna, 1) * go(fila - columna, 1))
    }

    /**
    * Metodo main: en realidad no es necesario porque el desarrollo
    * deberia guiarse por los tests de prueba
    *
    * @param args
    */
    def main(args: Array[String]) {
        println("................... Triangulo de Pascal ...................")

        // Se muestran 10 filas del trinagulo de Pascal
        for (row <- 0 to 10) {
            // Se muestran 10 y 10 filas
            for (col <- 0 to row)
                print(calcularValorTrianguloPascal(row, col) + " ")

            // Salto de linea final para mejorar la presentacion
            println()
        }

        // Se muestra el valor que debe ocupar la fila 10 y columna 5
        print(calcularValorTrianguloPascal(10, 5))
    }
}
