package Introduccion

class Usuario

// Nueva definicion de la clase usuario
class Usuario2 {
    val nombre = "administradoR"
    val prompt = s"$nombre>"

    println("Creado objeto de la clase Usuario2")
}

// Declaracion de clase con argumentos
class Usuario3(nombreUsuario: String){
    val nombre = nombreUsuario
    val prompt = s"$nombre>"

    // declaracion del metodo toString
    override def toString: String = s"Usuario($nombre)"

    println("Creado objeto de la clase Usuario3")
}

// Declaracion mas adecuada de Usuario (creando el dato miembro en parametros)
class Usuario4(val nombre: String){
    val prompt = s"$nombre>"

    // declaracion del metodo toString
    override def toString: String = s"Usuario($nombre)"

    println("Creado objeto de la clase Usuario4")
}

object Introduccion extends App{
    // Se crea un objeto de la clase usuario
    val usuario1 = new Usuario

    // Se comprueba que el objeto creado se considera instancia de AnyRef
    val esAnyRef = usuario1.isInstanceOf[AnyRef]
    println("es AnyRef: " + esAnyRef)
    //val x = 3
    //println("x es AnyVal: " + x.isInstanceOf[AnyVal])

    val usuario2 = new Usuario2
    println("nombre de usuario 2: " + usuario2.nombre)
    println("prompt de usuario 2: " + usuario2.prompt)

    // creacion de objeto de la clase Usuario3
    val usuario3 = new Usuario3("analista")
    println(usuario3)

    // creacion de listas de la clase Usuario4
    // List no necesita el new porque tiene implementado el metodo apply
    val usuarios = List(new Usuario4("administrador"),
                        new Usuario4("analista"),
                        new Usuario4("gestorBBDD"),
                        new Usuario4("programador"))
    val longitudes = usuarios.map(usuario => usuario.nombre.length)
    println(longitudes)

    val longitudes2 = usuarios map(_.nombre.size)
    println(longitudes2)

    val ordenados = usuarios sortBy(_.nombre)

    println("ordenados: " + ordenados.mkString(" - "))
}