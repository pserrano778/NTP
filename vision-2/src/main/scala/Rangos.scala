import java.io.File

object Rangos extends App{
    // rango de 0 a 9 (until - que no llega al límite superior)
    val rango1 = 0 until 10
    println("rango1: " + rango1.mkString(" "))

    // rango de 0 a 9 (to - que llega hasta el límite superior)

    val rango2 = 0 to 10
    println("rango2: " + rango2.mkString(" "))

    // incrementos en una cantidad determinada
    val rango3 = 0 to 10 by 2
    println("rango3: " + rango3.mkString(" "))

    // también permite incrementos negativos
    val rango4 = 10 to 0 by -2

    println("rango4: " + rango4.mkString(" "))

    // los rangos puden utilizarse con la expresión for
    // for comprehesion es muy potente
    val result: Seq[Int] = for(i <- rango4) yield i*3 // No es una sentencia for, produce un resultado
    // println("resultado: " + result.mkString(" "))

    // for permite filtral los valores de la colección
    var result2 = for(i <- result if i % 4 == 0) yield i

    // forma alternativa de filtrar con filter
    var result3 = result.filter(_%4 == 0).map(_*3) // i => i equivale a _

    // pueden utilizarse varios filtros a la vez
    val archivos: Array[File] = new File("-/src/main/scala/").listFiles
    val seleccionados = for(archivo <- archivos if archivo.isFile if archivo.getName.contains("o")) yield archivo.getName

    println("seleccionados: " + seleccionados.mkString(" "))

    // se pueden usar varios iteradores a la vez
    val combinaciones: Seq[(Int, Int)] = for{i <- 1 to 2
                                             j <- 1 to 4} yield (i,j) // Si se pone en una línea se necesita ;
    println("combinaciones: " + combinaciones.mkString(" "))

    // forma de escribir for...
    val combinaciones2: Seq[(Int, Int)] = for(i <- 1 to 2;
                                             j <- 1 to 4) yield (i,j)
}
