package ClasesCase

case class Personaje(nombre: String, heroe: Boolean)

object Personajes extends App {
    val personaje1 = Personaje("Gollum", false)
    val personaje2 = Personaje("Gandalf", true)
    val personaje3 = Personaje("Gandalf", true)
    // Se comprueba que los datos miembro son val
    //personaje2.heroe = true

    // las clases Case tienen un método equal que compara dato miembro a dato miembro

    val comparacion = personaje1 == personaje2
    println("comparacion: " + comparacion)

    val comparacio2 = personaje3 == personaje2
    println("comparacion: " + comparacio2)

    // permite el uso directo de toString
    println(personaje1)

    // permite el uso de de copiar para crear copias en las que varia un dato
    val personaje4 = personaje1.copy("Sauron")
    println(personaje4)

    // se dispone de un metodo unapply (contrario a apply):
    // dezpieza el objeto y compone una tupla con los valores de los datos miembro
    val resultado: Option[(String, Boolean)] = Personaje.unapply(personaje4) // Método del companion object
    println(resultado)
}
