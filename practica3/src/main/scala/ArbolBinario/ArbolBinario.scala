package ArbolBinario

import scala.annotation.tailrec
import scala.collection.mutable


sealed trait ArbolBinario[+A]


case object Nil extends ArbolBinario[Nothing]

abstract class Nodo[+A] (id: String) extends ArbolBinario[A]

case class NodoInterno[+A] (id: String, hijoIzq: ArbolBinario[A], hijoDcha: ArbolBinario[A]) extends Nodo[A](id)

case class NodoHoja[+A] (id: String, valor: A) extends Nodo[A](id)

object ArbolBinarioDoubles extends App{
    def apply(elementos: Double*): ArbolBinario[Double] = {
        def go (profundidad: Int, id: String ,elementos: Double*): ArbolBinario[Double] = {
            // Si no quedan elementos, devolvemos lista Nil (sin elementos)
            if (elementos.size == 0) Nil

            // Si quedan elementos, creamos una lista asignando la cabeza, y llamando de forma recursiva
            // a esta función para procesar los elementos restantes
            else if(elementos.size == 1) NodoHoja(id, elementos.head)

            else NodoInterno(id, go(profundidad+1, id+"."+0, elementos.slice(0, (elementos.size+1)/2):_*),
                go(profundidad, id+"."+1, elementos.slice((elementos.size+1)/2, elementos.size):_*))
        }
        go(0, "0",  elementos: _*)
    }

    def recorridoProfundidadPreorden(arbol: ArbolBinario[Double]): String = {
        arbol match {
            case Nil => ""
            case NodoInterno(id, hijoIzq, hijoDcha) => s"id: $id" + " | " + recorridoProfundidadPreorden(hijoIzq) +
                                                            " | " + recorridoProfundidadPreorden(hijoDcha)
            case NodoHoja(id, valor) => s"id: $id, valor: $valor"
        }
    }

    def recorridoProfundidadInorden(arbol: ArbolBinario[Double]): String = {
        arbol match {
            case Nil => ""
            case NodoInterno(id, hijoIzq, hijoDcha) => recorridoProfundidadInorden(hijoIzq) + " | " + s"id: $id" +  " | " +
                                                       recorridoProfundidadInorden(hijoDcha)
            case NodoHoja(id, valor) => s"id: $id, valor: $valor"
        }
    }

    def recorridoProfundidadPostorden(arbol: ArbolBinario[Double]): String = {
        arbol match {
            case Nil => ""
            case NodoInterno(id, hijoIzq, hijoDcha) => recorridoProfundidadPostorden(hijoIzq) + " | " +
                                                       recorridoProfundidadPostorden(hijoDcha) + " | " + s"id: $id"
            case NodoHoja(id, valor) => s"id: $id, valor: $valor"
        }
    }

    def recorridoAnchuraRecursivo(arbol: ArbolBinario[Double]): String = {
        def recorrerNivel(arbol: ArbolBinario[Double], nivel: Int): String = {
            arbol match {
                case Nil => ""
                case NodoInterno(id, hijoIzq, hijoDcha) =>
                    if(nivel == 0) s"id: $id"
                    else recorrerNivel(hijoIzq, nivel - 1) + recorrerNivel(hijoDcha, nivel - 1)
                case NodoHoja(id, valor) =>
                    if(nivel == 0) s"id: $id, valor: $valor"
                    else ""
            }
        }

        @annotation.tailrec
        def go(arbol: ArbolBinario[Double], nivel: Int, profundidad: Int, acum: String): String = {
            if (nivel < profundidad) go (arbol, nivel+1, profundidad, acum + recorrerNivel(arbol, nivel))
            else acum + recorrerNivel(arbol, nivel)
        }

        go (arbol, 0, profundidadArbol(arbol), "")
    }

    def recorridoAnchuraUsandoCola(arbol: ArbolBinario[Double]): String = {
        val cola = mutable.Queue[ArbolBinario[Double]]()
        var resultado = ""

        cola.addOne(ArbolBinarioDoubles.arbol)

        while(cola.size > 0){
            val nodo = cola.dequeue
            resultado = resultado.concat(nodo match {
                case Nil => ""
                case NodoInterno(id, hijoIzq, hijoDcha) =>
                    cola.addOne(hijoIzq)
                    cola.addOne(hijoDcha)
                    s"id: $id"
                case NodoHoja(id, valor) => s"id: $id, valor: $valor"
            })
        }
        resultado
    }

    def numeroHojas(arbol: ArbolBinario[Double]): Int = {
        arbol match {
            case Nil => 0
            case NodoInterno(id, hijoIzq, hijoDcha) => numeroHojas(hijoIzq) + numeroHojas(hijoDcha)
            case NodoHoja(id, valor) => 1
        }
    }

    def numeroNodosInternos(arbol: ArbolBinario[Double]): Int = {
        arbol match {
            case Nil => 0
            case NodoInterno(id, hijoIzq, hijoDcha) => 1 + numeroNodosInternos(hijoIzq) + numeroNodosInternos(hijoDcha)
            case NodoHoja(id, valor) => 0
        }
    }

    def aplicarFuncionAHojas(arbol: ArbolBinario[Double])(operacion: (Double, Double) => Double): Double = {
        arbol match {
            case Nil => 0.0
            case NodoInterno(id, hijoIzq, hijoDcha) => operacion(aplicarFuncionAHojas(hijoIzq)(operacion),
                aplicarFuncionAHojas(hijoDcha)(operacion))
            case NodoHoja(id, valor) => valor
        }
    }

    def sumaValoresHojas(arbol: ArbolBinario[Double]): Double = {
        aplicarFuncionAHojas(arbol)(_ + _)
    }

    def productoValoresHojas(arbol: ArbolBinario[Double]): Double = {
        aplicarFuncionAHojas(arbol)(_ * _)
    }

    def profundidadArbol(arbol: ArbolBinario[Double]): Int = {
        arbol match {
            case Nil => 0
            case NodoInterno(id, hijoIzq, hijoDcha) => {
                val profundidadIzq = 1 + profundidadArbol(hijoIzq)
                val profundidadDcha = 1 + profundidadArbol(hijoDcha)
                if(profundidadIzq >= profundidadDcha) profundidadIzq
                else profundidadDcha
            }
            case NodoHoja(id, valor) => 1
        }
    }

    val arbol = ArbolBinarioDoubles(1.1, 2.2, 3.3, 4.4, 5.5, 6.6)

    val arbol2 = ArbolBinarioDoubles(1.1, 2.2);

    println(recorridoAnchuraRecursivo(arbol))
    println(recorridoAnchuraUsandoCola(arbol))
}


