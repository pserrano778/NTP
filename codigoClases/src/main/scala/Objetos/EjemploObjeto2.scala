package Objetos

object ConexionBaseDatos{
    private val url = "jdbv::/localhost"
    private val usuario = "admin"
    private val password = "1234"

    def apply() = new ConexionBaseDatos
}

class ConexionBaseDatos {
    private val propiedades = Map("url" -> ConexionBaseDatos.url,
    "usuario" -> ConexionBaseDatos.usuario,
    "password" -> ConexionBaseDatos.password)

    println("he creado un nuevo objeto" + propiedades("url"))
}
object EjemploObjeto2 extends App{
    val conexion1 = ConexionBaseDatos()
    val conexion2 = ConexionBaseDatos()
}
