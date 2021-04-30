package Construccion

class Multiplicador(val factor: Int){
    def apply(valor: Int) = valor*factor
}

// Companion Object. Nos sirve para crear métodos Static
object Multiplicador{
    def apply(factor: Int) = new Multiplicador(factor)

    def componer(obj1: Multiplicador, obj2: Multiplicador) = new Multiplicador(obj1.factor*obj2.factor)
}

object Construccion extends App{
    val multiplicadorPor3 = new Multiplicador(3)

    // Se usa el metodo apply
    println("multiplicador * 8 : " + multiplicadorPor3.apply(8))

    // por llamarse apply, se puede usar de forma más directa
    val resultado = multiplicadorPor3(8)

    val lista = List(1,2,3,4,5)
    val tercer = lista(3)
    val tercer2 = lista.apply(3)

    val multiplicadorPor10 = Multiplicador(10)

    // Método Static (se llama con el nombre de la clase
    // Debido a esto, no existe Static
    val multiplicadorCompuesto = Multiplicador.componer(multiplicadorPor3, multiplicadorPor10)
}
