object FuncionesAnidadas extends App{
    // definir la funcioón para calcular el maximo de 3 valores
    // Hace uso de una funcion auxiliar anidada que permite
    // organizar el código mejor

    def max(x : Int, y : Int, z : Int) : Int = {
        //funcion auxiliar para calcular el maximo de 2 valores
        def max(x : Int, y : Int) = if (x > y) x else y

        max (x, max(y, z))
    }

    val maximo = max(3, 7, 1)
    println("maximo: " + maximo)

    // funcion factorial
    def factorial (x: Int) : Int = {
        if (x == 0) 1
        else x * factorial(x - 1)
    }
    println("factorial de 10" + factorial(10))

    // fuincion factorial tail-recursive
    @annotation.tailrec
    def factorialTR1(x : Int, acumulador : Int) : Int = {
        if (x == 0 || x == 1) acumulador
        else factorialTR1(x-1, acumulador*x)
    }
    println("factorial de 10" + factorialTR1(10, 1))

    // funcion factorial tail recursive con una interfaz natural (solo 1 argumento)
    def factorialTR2(x : BigInt) : BigInt = {
        // funcion auxiliar que permite tail-recursive
        @annotation.tailrec
        def go(x : BigInt, acumulador : BigInt) : BigInt = {
            if(x == 0 || x == 1) acumulador
            else go(x-1, x*acumulador)
        }
        go(x, 1)
    }
    println("factorial de 10" + factorialTR2(10))

    // funcion con valores por defecto para los argumentos
    @annotation.tailrec
    def factorialTR3(x : BigInt, acumulador : BigInt = 1) : BigInt = {
        if (x == 0 || x == 1) acumulador
        else factorialTR3(x-1, acumulador*x)
    }
    println("factorial de 10" + factorialTR3(10))
    println("factorial de 10" + factorialTR3(acumulador = 1, x = 10)) // Podemos cambiar el orden si usamos los nombres

    // funciones con numero variable de argumentos
    def sumar(numeros : Int *) : Int = {
        var total = 0
        for (i <- numeros) total+=i
        total
    }
    println("suma de un valor: " +  sumar(1))
    println("suma de tres valor: " +  sumar(1, 8, 12))

    // utilizar genericos en las funciones
    def eliminarPrimero [A] (lista: List[A]) : List[A] = lista.tail

    eliminarPrimero(List(1, 2, 3, 4))
    eliminarPrimero(List('a', 'b', 'c', 'd'))
}
