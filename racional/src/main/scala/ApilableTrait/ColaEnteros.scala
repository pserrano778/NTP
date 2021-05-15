package ApilableTrait

import scala.collection.mutable.ArrayBuffer

abstract class ColaEnteros {
    def get: Int
    def put(x: Int)
}

class ColaEnterosArray extends ColaEnteros{
    // Estructura de almacenamiento
    private val cola = new ArrayBuffer[Int]

    /**
     * Metodo que devuelve la cabeza de la cola y lo elimina de esta
     * @return
     */
    def get = cola.remove(0)

    /**
     * Metodo que permite añadir un elemento al final de la cola
     * @param x
     */
    def put(x: Int) = {
        cola += x
    }
}

/**
 * Trait que dota a la clase cola Enteros la funcionalidad de añadir a la cola
 * elementos multiplicados por 2
 */
trait Doblar extends ColaEnteros{
    abstract override def put(x: Int): Unit ={
        println("put en trait doblar - a insertar x: " + x)
        super.put(x*2)
    }
}

/**
 * Trait que permite evitar añadir negativos
 */
trait FiltrarNegativos extends ColaEnteros{
    abstract override def put(x: Int) = {
        if (x >= 0){
            println("put en trait FiltradoNegativos - a insertar x: " + x)
            super.put(x)
        }
    }
}

/**
 *
 */
trait Incrementar extends ColaEnteros{
    abstract override def put(x: Int) = {
        println("put en trait Incrementar - a insertar x: " + x)
        super.put(x+1)
    }
}

// Se pueden definir clases especificas mezclando funcionabilidad
class ColaDoblando extends ColaEnterosArray with Doblar

object PruebaTraitsApilables extends App{
    val cola1 = new ColaDoblando
    cola1.put(2)
    println("valor en la cabeza de cola1: " + cola1.get)

    //puedo crear objetos mezclando la funcionalidad que me
    //interesa sin necesidad de definar las clases de forma completa
    //EL orden es de atras hacia delante (Ultima a Primera)
    val cola2 = new ColaEnterosArray with Incrementar with FiltrarNegativos
    cola2.put(-1)
    cola2.put(0)
    cola2.put(1)
    println("primero de la cola2: " + cola2.get)

    val cola3 = new ColaEnterosArray with FiltrarNegativos with Incrementar
    cola3.put(-1)
    cola3.put(0)
    cola3.put(1)
    println("primero de la cola3: " + cola3.get)
}