package paquetes

object Importar extends App{
    // se crea objeto sin hacer uso de import
    val d1 = new java.util.Date

    // se puede hacer importacion parcial
    import java.util
    val d2 = new util.Date

    // importar contenido completo de un paquete
    import collection.mutable._
    val obj1 = new ArrayBuffer[String]
    obj1 += "Hola"
    obj1 += "adios"

    import collection.mutable.{Queue, ArrayBuffer}
    val q1 = new Queue

    // importacion asignando un alias a la clase
    import collection.immutable.{Map => MapaInmutable}
    val m1 = MapaInmutable(2->3, 8->5)

    // importar clases y ocultar algunas de ellas
    import java.util.{Random => _, _} // Se oculta la clase random, y se importa el resto
    // val generador = new Random no funciona
    val arrayList = new util.ArrayList[Int]() // ArrayList si


}
