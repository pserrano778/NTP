object ImpresionArgumentos {
    // funcion para imprimir un conjunto de argumentos sin el estido de programación funcional
    def inprimirArgumentos(args : Array[String]) = {
        var i=0

        // bucle while
        while(i < args.length){
            println("args[" + i + "]" + args(i))
            i = i + 1
        }
    }

    // version 2 más funcional
    def impresionArgumentos2(args : Array[String]) = {
        for(arg <- args) println(arg)
    }

    // version 3 más funcional...
    def impresionArgumentos3(args : Array[String]) = {
        (0 until args.length).foreach(i => println("args[" + i + "]" + args(i)))
    }

    // version 4 más funcional...
    def impresionArgumentos4(args : Array[String]) = {
        args.foreach(println)
    }

    // versión completamente funcional (devolviendo un argumento)
    def impresionArgumentos5(args : Array[String]) = {
        args.mkString("\n")
    }

    val salida = impresionArgumentos5(Array("uno", "dos", "tres"))
    assert(salida == "uno\ndos\ntres") // Genera excepción si no es cierto
}
