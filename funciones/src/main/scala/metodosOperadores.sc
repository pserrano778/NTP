import scala.language.postfixOps

val cadena = "Hola, Pepe"
val terminaE = cadena.endsWith("e")
cadena endsWith "e"

val d = 7.87

d.round

d round // para ponerlo así, necesita el import, ya que necesita más contexto

d + 3.5
d.+(3.5) // + es un método