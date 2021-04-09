object Listas extends App{
    // crear lista de enteros: parametrización de clase
    var lista1: List[Int] = List(1, 2, 3, 4) // El tipo de puede deducir
    var lista2 = List(5, 6, 7)

    // concatenación de listas
    val lista12 = lista1 ::: lista2 // las listas son inmutables, se genera otra con cada opertación
    println("lista12: " + lista12.mkString(" "))

    // incluir nuevo elementos al inicio de la lista
    val lista3 = 0 :: lista12 // Se aplica al objeto por la derecha (se añade al 0 la lista por la derecha
    println("lista3: " + lista3.mkString(" "))

    // Las listas, como son inmutable, van a tener referencia y no son ineficientes,
    // ya que lista 3 tendrá una referencia a 0 y otra a lista 12; y lista 12 tendrá referencia a lista1 y lista2

    // tamaño de la lista
    println("tam de lista3: " + lista3.size)
    println("tam de lista3: " + lista3.length)

    // para acceder a los elementos ()
    println("primer valor de lista3: " + lista3(0))

    // conteo de elementos que cumplen una cierta condición
    val numPares: Int = lista3.count(elemento => elemento % 2 == 0)
    val numPares2: Int = lista3.count(_ % 2 == 0)

    // filtrao de elementos pares
    val pares2: List[Int] = lista3.filter(_ % 2 == 0)

    // formas de descartar elementos de la lista
    val lista4 = lista3.drop(1) // Quitar por la izquierda
    val lista5  = lista3.dropRight(1) // Quitar por la derecha
    val lista6 = lista3.dropWhile(_ < 10) // Quitar mientras de cumple una condición

    // Comprobar si existe un elemento
    val existeMultiplo3 = lista3.exists(_ % 3 == 0)

    // funciones muy potentes para manejo recursivo de listas
    val primero = lista3.head // Obtener el primero
    val resto = lista3.tail // Obtener todos menos el primero


}
