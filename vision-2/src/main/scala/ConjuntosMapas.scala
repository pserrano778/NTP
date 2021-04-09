object ConjuntosMapas extends App {
    // ejemplo de creación de conjunto
    var ciudades = Set("Granada", "Jaen", "Almeria") // Por defecto es inmutable

    ciudades += "Malaga" // Se modifica la referencia para que apunte a otro objeto

    // Para usar la versión mutable hay que importar la clase
    // cuidado con el import!! ya que a partir del import todos los conjuntos son mutalbes
    // Se recomienda poner el path para hacer solo mutable ese conjunto
    val asignaturas = scala.collection.mutable.Set("Matematicas", "Fisica")
    asignaturas += "Quimica"

    // uso de tipos especificos de conjuntos
    val ciudades2 = scala.collection.immutable.HashSet("Granada", "Almeria", "Jaen")

    // con los mapas se usa una jerarquia de clases similar a los conjuntos
    val dias = (1 -> "Lunes", 2 -> "Martes", 3 -> "Miercoles")

    val dias2 = scala.collection.mutable.Map()

}
