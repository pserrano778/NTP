package ejercicio1

object TrianguloPascal {

    /**
     * Método para calcular un valor del triangulo pascal de forma  tail recursive
     * @param fila
     * @param columna
     * @return
     */
    def calcularValorTrianguloPascal(fila: Int, columna: Int): Int = {
        @annotation.tailrec
        def go(num: Int, acum: Int) : Int = {
            if (num == 1 || num == 0) acum
            else go(num - 1, acum*num)
        }
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
