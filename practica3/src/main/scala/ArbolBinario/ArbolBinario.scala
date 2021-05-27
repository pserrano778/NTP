package ArbolBinario


sealed trait ArbolBinario[+A]


case object Nil extends ArbolBinario[Nothing]

abstract class Nodo[+A] (id: String) extends ArbolBinario[A]

case class NodoInterno[+A] (id: String, hijoIzq: ArbolBinario[A], hijoDcha: ArbolBinario[A]) extends Nodo[A](id)

case class NodoHoja[+A] (id: String, valor: A) extends Nodo[A](id)

object ArbolBinarioDoubles extends App{
    def apply(elementos: Double*): ArbolBinario[Double] = {
        def go (profundidad: Int, idAnterior: String ,elementos: Double*): ArbolBinario[Double] = {
            // Si no quedan elementos, devolvemos lista Nil (sin elementos)
            if (elementos.size == 0) Nil

            // Si quedan elementos, creamos una lista asignando la cabeza, y llamando de forma recursiva
            // a esta función para procesar los elementos restantes
            else if(elementos.size == 1) NodoHoja(idAnterior+"."+profundidad, elementos.head)

            else NodoInterno(idAnterior+"."+profundidad, go(profundidad+1, idAnterior+"."+profundidad, elementos.slice(0, (elementos.size+1)/2):_*),
                go(profundidad, idAnterior+"."+profundidad, elementos.slice((elementos.size+1)/2, elementos.size):_*))
        }
        go(0, "",  elementos: _*)
    }

    def recorridoProfundidad(arbol: ArbolBinario[Double]): String = {
        arbol match {
            case Nil => "Nil"
            case NodoInterno(id, hijoIzq, hijoDcha) => id + " - " + recorridoProfundidad(hijoIzq) +
                                                            " - " + recorridoProfundidad(hijoDcha)
            case NodoHoja(id, valor) => id
        }
    }

    def recorridoAnchura(arbol: ArbolBinario[Double]): String = {
        ""
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

    def profundidadArbol(arbol: ArbolBinario[Double]): Double = {
        arbol match {
            case Nil => 0.0
            case NodoInterno(id, hijoIzq, hijoDcha) => {
                if(profundidadArbol(hijoIzq) > profundidadArbol(hijoDcha))
            },
        profundidadArbol
            case NodoHoja(id, valor) => valor
        }
    }

    val arbol = ArbolBinarioDoubles(1.1, 2.2, 3.3, 4.4, 5.5, 6.6)
    println(arbol)

    val arbol2 = ArbolBinarioDoubles(1.1, 2.2);
    println(recorridoProfundidad(arbol))
}


