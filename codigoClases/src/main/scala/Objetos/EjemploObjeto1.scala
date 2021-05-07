package Objetos

object Saludo{
    println("En objeto saludo.........")
    def saludar = "Hola desde objeto saludo"
}
object EjemploObjeto1 extends App{
    // se hace uso del objeto
    println(Saludo.saludar)
    println(Saludo.saludar)
}
