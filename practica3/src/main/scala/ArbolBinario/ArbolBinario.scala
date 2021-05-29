package ArbolBinario

import scala.collection.mutable

/**
 * Interfaz generica para el Árbol Binario genérico
 * @tparam A
 */
sealed trait ArbolBinario[+A]

/**
 * Objeto para definir un Árbol vacío (Nodo Nil)
 */
case object Nil extends ArbolBinario[Nothing]

/**
 * Clase abstracta que representa un Nodo genérico
 * compuesto por un Id de tipo string
 * @param id
 * @tparam A
 */
abstract class Nodo[+A] (id: String) extends ArbolBinario[A]

/**
 * Clase para definir un Nodo Interno compuesto por un Id,
 * su hijo izquierdo, y su hijo derecho
 * @param id
 * @param hijoIzq
 * @param hijoDcha
 * @tparam A
 */
case class NodoInterno[+A] (id: String, hijoIzq: ArbolBinario[A], hijoDcha: ArbolBinario[A]) extends Nodo[A](id)

/**
 * Clase para definir un Hodo Hoja compuesto por un Id
 * y un valor
 * @param id
 * @param valor
 * @tparam A
 */
case class NodoHoja[+A] (id: String, valor: A) extends Nodo[A](id)

/**
 * Objeto para definir funcionalidades de los Arboles binarios
 */
object ArbolBinarioDoubles extends App{

    /**
     * Método para permitir crear un Arbol Binario equilibrado de doubles sin utilizar new
     * @param elementos
     * @return
     */
    def apply(elementos: Double*): ArbolBinario[Double] = {
        // Función recursiva interna que permite generar un arbol dada una secuencia de elementos
        // Asignandole un Id que depende de la posición que tenga dentro del arbol ("0" para la rama izquierda y
        // "1" para la derecha, y un "-" que separa los distintos niveles de profudidad)
        def go (id: String, elementos: Double*): ArbolBinario[Double] = {
            // Si no hay elementos, se devuelve Nil
            if (elementos.isEmpty) Nil

            // Si queda un elemento, se devuelve la nueva hoja con ese elemento
            else if(elementos.size == 1) NodoHoja(id, elementos.head)

            // Si quedan varios elementos, se devuelve un nuevo nodo interno, llamando a la función
            // de forma recursiva para crear sus dos hijos (partiendo la secuencia de elementos por la
            // mitad.
            else NodoInterno(id, go(id+"-0", elementos.slice(0, (elementos.size+1)/2):_*),
                go(id+"-1", elementos.slice((elementos.size+1)/2, elementos.size):_*))
        }

        // Primera llamada a go, con id 0 que representa a la raíz
        go("0",  elementos: _*)
    }

    /**
     * Función recursiva que
     * @param arbol
     * @return
     */
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

        while(cola.nonEmpty){
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

    /**
     * Función recursiva que permite obtener el número de hojas de un árbol binario
     * @param arbol
     * @return
     */
    def numeroHojas(arbol: ArbolBinario[Double]): Int = {
        // Comprobamos el tipo de nodo
        arbol match {
            // Si es nulo, devolvemos 0
            case Nil => 0

            // Si es un Nodo Interno, llamamos a la función con los dos hijos del nodo,
            // y sumamos el resultado
            case NodoInterno(id, hijoIzq, hijoDcha) => numeroHojas(hijoIzq) + numeroHojas(hijoDcha)

            // Si es un nodo hoja, devolvemos 1
            case NodoHoja(id, valor) => 1
        }
    }

    /**
     * Función recursiva que permite obtener el número de Nodos Internos de un árbol binario
     * @param arbol
     * @return
     */
    def numeroNodosInternos(arbol: ArbolBinario[Double]): Int = {
        // Comprobamos el tipo de nodo
        arbol match {
            // Si es nulo, devolvemos 0
            case Nil => 0

            // Si es un Nodo Interno, devolvemos 1, más el resultado de la llamada a esta función para
            // los dos hijos del nodo
            case NodoInterno(id, hijoIzq, hijoDcha) => 1 + numeroNodosInternos(hijoIzq) + numeroNodosInternos(hijoDcha)

            // Si es un nodo Hoja, devolvemos 0
            case NodoHoja(id, valor) => 0
        }
    }

    /**
     * Función recursiva que permite aplicar una función de forma sucesiva
     * a todos los valores de las hojas de un árbol binario
     * @param arbol
     * @param neutro
     * @param funcion
     * @return
     */
    def aplicarFuncionAHojas(arbol: ArbolBinario[Double], neutro: Double)(funcion: (Double, Double) => Double): Double = {
        // Comprobamos el tipo de nodo
        arbol match {
            // Si es nulo, devolvemos el elemento neutro
            case Nil => neutro

            // Si es un nodo interno, devolvemos el resultado de aplicar la función, con el resultado
            // de la llamada a esta función para los dos hijos del nodo
            case NodoInterno(id, hijoIzq, hijoDcha) => funcion(aplicarFuncionAHojas(hijoIzq, neutro)(funcion),
                aplicarFuncionAHojas(hijoDcha, neutro)(funcion))

            // Si es un nodo hoja, devolvemos el valor que tiene
            case NodoHoja(id, valor) => valor
        }
    }

    /**
     * Función que permite sumar todos los valores de las hojas de un árbol binario,
     * haciendo uso de la función "aplicarFuncionAHojas"
     * @param arbol
     * @return
     */
    def sumaValoresHojas(arbol: ArbolBinario[Double]): Double = {
        // Llamada a la función "aplicarFuncionAHojas" con la función suma
        aplicarFuncionAHojas(arbol, 0)(_ + _)
    }

    /**
     * Función que permite multiplicar todos los valores de las hojas de un árbol binario,
     * haciendo uso de la función "aplicarFuncionAHojas"
     * @param arbol
     * @return
     */
    def productoValoresHojas(arbol: ArbolBinario[Double]): Double = {
        // Llamada a la función "aplicarFuncionAHojas" con la función producto
        aplicarFuncionAHojas(arbol, 1)(_ * _)
    }

    /**
     * Función recursiva que permite obtener la profundidad máxima de un árbol binario
     * @param arbol
     * @return
     */
    def profundidadArbol(arbol: ArbolBinario[Double]): Int = {
        // Comprobamos el tipo de nodo
        arbol match {
            // Si es nulo, devolvemos 0
            case Nil => 0

            // Si es un Nodo Interno, calculamos la profundidad de ambos hijos,
            // y devolvemos la más grande
            case NodoInterno(id, hijoIzq, hijoDcha) =>
                // Se calcula la profundidad del hijo izquierdo (aumentándola en 1 debido al nodo actual)
                val profundidadIzq = 1 + profundidadArbol(hijoIzq)

                // Se calcula la profundidad del hijo derecho (aumentándola en 1 debido al nodo actual)
                val profundidadDcha = 1 + profundidadArbol(hijoDcha)

                // Se devuelve el valor más grande
                if(profundidadIzq >= profundidadDcha) profundidadIzq
                else profundidadDcha

            // Si es una hoja, se devuelve 1
            case NodoHoja(id, valor) => 1
        }
    }

    /**
     * Función que permite unir el contenido de dos árboles binarios en uno nuevo. Para esto, se crea una nueva raiz,
     * cuyo hijo izquierdo será el primer árbol, y cuyo hijo derecho será el segundo
     * @param arbol1
     * @param arbol2
     * @return
     */
    def unirDosArboles(arbol1: ArbolBinario[Double], arbol2: ArbolBinario[Double]): ArbolBinario[Double] = {

        // Función recursiva interna que crea un nuevo árbol, a partir de otro pasado por parámetro,
        // modificando los IDs de los nodos para aumentar en 1 la profundidad, y tener en cuenta
        // si van ser hijo izquierdo o derecho de la nueva raíz
        def aumentarProfundidadArbol(arbol: ArbolBinario[Double], idNuevo: String): ArbolBinario[Double] = {
            // Se calcula la profundidad del hijo izquierdo (aumentándola en 1 debido al nodo actual)
            arbol match {
                // Si es nulo, devolvemos Nil (nunca debería darse este caso debido a las comprobaciones previas)
                case Nil => Nil

                // Si es un nodo interno, se crea un nuevo NodoInterno con el nuevo id, llamando a esta función
                // para actualzar los IDs de sus dos hijos
                case NodoInterno(id, hijoIzq, hijoDcha) =>
                    NodoInterno (idNuevo, aumentarProfundidadArbol(hijoIzq, idNuevo+"-0"),
                                     aumentarProfundidadArbol(hijoDcha, idNuevo+"-1"))

                // Si es una hoja, se devuelve un nuevo NodoHoja actualizando el ID
                case NodoHoja(id, valor) => NodoHoja(idNuevo, valor)
            }
        }

        // Se comprueba el tipo de árbol al que pertenece el segundo árbol binario
        arbol1 match{
            // Si es nulo, se devuelve directamente el segundo árbol
            case Nil => arbol2

            // En otro caso
            case _ =>

                // Se comprueba el tipo de árbol al que pertenece el primer árbol binario
                arbol2 match {

                    // Si es nulo, se devuelve directamente el primer árbol
                    case Nil => arbol1

                    // En otro caso, se crea un nuevo Nodo Interno que actuará como raíz,
                    // y se llama a la función "AumentarProfundidadArbol" con el primer y segundo árbol,
                    // que serán el hijo izquierdo y derecho respectivamente
                    case _ => NodoInterno("0", aumentarProfundidadArbol(arbol1, "0-0"),
                                               aumentarProfundidadArbol(arbol2, "0-1"))
                }
        }
    }

    val arbol = ArbolBinarioDoubles(1.1, 2.2, 3.3, 4.4, 5.5, 6.6)

    val arbol2 = ArbolBinarioDoubles(1.1, 2.2)

    println(recorridoAnchuraRecursivo(arbol))
    println(recorridoAnchuraUsandoCola(arbol))

    println(unirDosArboles(arbol, arbol2))
}


