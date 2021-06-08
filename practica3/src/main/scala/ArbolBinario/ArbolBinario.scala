package ArbolBinario

import scala.collection.mutable

/**
 * Interfaz generica para el Árbol Binario genérico
 * @tparam A
 */
sealed trait ArbolBinario[+A]{
    def informacionNodo: String
}

/**
 * Objeto para definir un Árbol vacío (Nodo Nil)
 */
case object Nil extends ArbolBinario[Nothing]{
    override def informacionNodo : String = "Nodo Nulo"
}

/**
 * Clase abstracta que representa un Nodo genérico
 * compuesto por un Id de tipo string
 * @param id
 * @tparam A
 */
abstract class Nodo[+A] (id: String) extends ArbolBinario[A]{
    override def informacionNodo : String = s"id: $id"
}

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
case class NodoHoja[+A] (id: String, valor: A) extends Nodo[A](id){
    override def informacionNodo : String = super.informacionNodo + s" valor: $valor"
}

/**
 * Objeto para definir funcionalidades de los Arboles binarios
 */
object ArbolBinario{

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

            // Si quedan varios elementos, se devuelve un nuevo nodo interno, llamando a la función de forma
            //  recursiva para crear sus dos hijos (partiendo la secuencia de elementos por la mitad
            else NodoInterno(id, go(id+"-0", elementos.slice(0, (elementos.size+1)/2):_*),
                go(id+"-1", elementos.slice((elementos.size+1)/2, elementos.size):_*))
        }

        // Primera llamada a go, con id 0 que representa a la raíz
        go("0",  elementos: _*)
    }

    /**
     * Función recursiva que permite obtener el recorrido en preorden de un árbol binario
     * @param arbol
     * @return
     */
    def recorridoProfundidadPreorden[A](arbol: ArbolBinario[A]): String = {
        // Comprobamos el tipo de nodo
        arbol match {

            // Si es un nodo interno, se devuelve la información del nodo actual, concatenada con las llamadas
            // recursivas con los hijos izquierdo y derecho respectivamente
            case NodoInterno(id, hijoIzq, hijoDcha) => arbol.informacionNodo + " | " + recorridoProfundidadPreorden(hijoIzq) +
                                                            " | " + recorridoProfundidadPreorden(hijoDcha)
            // En otro caso, se devuelve la información del nodo
            case _ => arbol.informacionNodo
        }
    }

    /**
     * Función recursiva que permite obtener el recorrido en inorden de un árbol binario
     * @param arbol
     * @return
     */
    def recorridoProfundidadInorden[A](arbol: ArbolBinario[A]): String = {
        // Comprobamos el tipo de nodo
        arbol match {

            // Si es un nodo interno, se llama a la función recursiva con el hijo izquierdo, se concatena la información
            // del nodo actual, y se llama a la función recursiva con el hijo derecho
            case NodoInterno(id, hijoIzq, hijoDcha) => recorridoProfundidadInorden(hijoIzq) + " | " + arbol.informacionNodo +  " | " +
                                                       recorridoProfundidadInorden(hijoDcha)
            // En otro caso, se devuelve la información del nodo
            case _ => arbol.informacionNodo
        }
    }

    /**
     * Función recursiva que permite obtener el recorrido en postorden de un árbol binario
     * @param arbol
     * @return
     */
    def recorridoProfundidadPostorden[A](arbol: ArbolBinario[A]): String = {
        // Comprobamos el tipo de nodo
        arbol match {

            // Si es un nodo interno, se llama a la función recursiva con el hijo izquierdo y con el derecho
            // respectivamente, y se concatena la información del nodo actual
            case NodoInterno(id, hijoIzq, hijoDcha) => recorridoProfundidadPostorden(hijoIzq) + " | " +
                                                       recorridoProfundidadPostorden(hijoDcha) + " | " + arbol.informacionNodo
            // En otro caso, se devuelve la información del nodo
            case _ => arbol.informacionNodo
        }
    }

    /**
     * Función recursiva que permite obtener el recorrido en en anchura de un árbol binario
     * @param arbol
     * @return
     */
    def recorridoAnchuraRecursivo[A](arbol: ArbolBinario[A]): String = {
        // Función recursiva que permite mostrar la información de los nodos de un nivel
        def recorrerNivel(arbol: ArbolBinario[A], nivel: Int): String = {

            // Si hemos llegado al nivel deseado, devolvemos la inforamción del nodo
            if (nivel == 0) arbol.informacionNodo + " | "

            // Si no hemos llegado al nivel Comprobamos el tipo de nodo
            else arbol match {

                // Si es un nodo interno, llamamos a la función recursiva para los hijos izquierdo y derecho
                // respectivamente, decrementando el nivel
                case NodoInterno(id, hijoIzq, hijoDcha) => recorrerNivel(hijoIzq, nivel - 1) + recorrerNivel(hijoDcha, nivel - 1)

                // En otro caso, devolvemos la cadena vacía
                case _ => ""
            }
        }

        // Función tail recursive que permite obtener la información de un árbol, recorriendolo en anchura,
        // desde el nicel indicado, hasta la profundidad indicada
        @annotation.tailrec
        def go(arbol: ArbolBinario[A], nivel: Int, profundidad: Int, acum: String): String = {

            // Si el nivel es menor que la profundidad, llamamos a esta función, añadiendo al acumulador
            // el la llamada a recorrerNivel con nivel actual, y aumentando el nivel
            if (nivel < profundidad) go (arbol, nivel+1, profundidad, acum + recorrerNivel(arbol, nivel))

            // Si hemos llegado a la profundidad indicada, devolvemos el acumulador
            else acum
        }

        // Primera llamada a la función go, con el nivel º, y la profundidad del árbol, con acum inicializado
        // al valor del primer nodo (para los casos en los que solo haya un nodo Nil
        go (arbol, 1, profundidadArbol(arbol), arbol.informacionNodo + " | ")
    }

    /**
     * Función que permite obtener el recorrido en anchura de un árbol, haciendo uso de una cola mutable
     * @param arbol
     * @return
     */
    def recorridoAnchuraUsandoCola[A](arbol: ArbolBinario[A]): String = {

        // Declaramos la cola
        val cola = mutable.Queue[ArbolBinario[A]]()

        // Cadena vacía inicial
        var resultado = ""

        // Añadimos el primer nodo
        cola.addOne(arbol)

        // Mientras la cola no esté vacía
        while(cola.nonEmpty){

            // Sacamos el nodo de la cola
            val nodo = cola.dequeue

            // Concatenamos a la cadena resultado el valor obtenido al procesar el nodo
            resultado = resultado.concat(nodo.informacionNodo + " | ")

                // Comprobamos el tipo de nodo
                nodo match {

                    // Si es un nodo interno, añadimos los hijos a la cola
                    case NodoInterno(id, hijoIzq, hijoDcha) =>
                        cola.addOne(hijoIzq)
                        cola.addOne(hijoDcha)

                    // En otro caso, no hacemos nada
                    case _ =>
                }
        }
        // Devolvemos el resultado
        resultado
    }

    /**
     * Función recursiva que permite obtener el número de hojas de un árbol binario
     * @param arbol
     * @return
     */
    def numeroHojas[A](arbol: ArbolBinario[A]): Int = {
        // Comprobamos el tipo de nodo
        arbol match {

            // Si es un Nodo Interno, llamamos a la función con los dos hijos del nodo,
            // y sumamos el resultado
            case NodoInterno(id, hijoIzq, hijoDcha) => numeroHojas(hijoIzq) + numeroHojas(hijoDcha)

            // Si es un nodo hoja, devolvemos 1
            case NodoHoja(id, valor) => 1

            // En otro caso (nulo), devolvemos 0
            case _ => 0
        }
    }

    /**
     * Función recursiva que permite obtener el número de Nodos Internos de un árbol binario
     * @param arbol
     * @return
     */
    def numeroNodosInternos[A](arbol: ArbolBinario[A]): Int = {
        // Comprobamos el tipo de nodo
        arbol match {

            // Si es un Nodo Interno, devolvemos 1, más el resultado de la llamada a esta función para
            // los dos hijos del nodo
            case NodoInterno(id, hijoIzq, hijoDcha) => 1 + numeroNodosInternos(hijoIzq) + numeroNodosInternos(hijoDcha)

            // En otro caso, devolvemos 0
            case _ => 0
        }
    }

    /**
     * Función recursiva que permite aplicar una función de forma sucesiva
     * a todos los valores de las hojas de un árbol binario Double
     * @param arbol
     * @param neutro
     * @param funcion
     * @return
     */
    def aplicarFuncionAHojas(arbol: ArbolBinario[Double], neutro: Double)(funcion: (Double, Double) => Double): Double = {
        // Comprobamos el tipo de nodo
        arbol match {

            // Si es un nodo interno, devolvemos el resultado de aplicar la función, con el resultado
            // de la llamada a esta función para los dos hijos del nodo
            case NodoInterno(id, hijoIzq, hijoDcha) => funcion(aplicarFuncionAHojas(hijoIzq, neutro)(funcion),
                aplicarFuncionAHojas(hijoDcha, neutro)(funcion))

            // Si es un nodo hoja, devolvemos el valor que tiene
            case NodoHoja(id, valor) => valor

            // En otro caso (nulo), devolvemos el elemento neutro
            case _ => neutro
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
    def profundidadArbol[A](arbol: ArbolBinario[A]): Int = {
        // Comprobamos el tipo de nodo
        arbol match {

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

            // En otro caso (nulo), devolvemos 0
            case _ => 0
        }
    }

    /**
     * Función que permite unir el contenido de dos árboles binarios en uno nuevo. Para esto, se crea una nueva raiz,
     * cuyo hijo izquierdo será el primer árbol, y cuyo hijo derecho será el segundo
     * @param arbol1
     * @param arbol2
     * @return
     */
    def unirDosArboles[A](arbol1: ArbolBinario[A], arbol2: ArbolBinario[A]): ArbolBinario[A] = {

        // Función recursiva interna que crea un nuevo árbol, a partir de otro pasado por parámetro,
        // modificando los IDs de los nodos para aumentar en 1 la profundidad, y tener en cuenta
        // si van ser hijo izquierdo o derecho de la nueva raíz
        def aumentarProfundidadArbol[A](arbol: ArbolBinario[A], idNuevo: String): ArbolBinario[A] = {

            // Se calcula la profundidad del hijo izquierdo (aumentándola en 1 debido al nodo actual)
            arbol match {
                // Si es un nodo interno, se crea un nuevo NodoInterno con el nuevo id, llamando a esta función
                // para actualzar los IDs de sus dos hijos
                case NodoInterno(id, hijoIzq, hijoDcha) =>
                    NodoInterno (idNuevo, aumentarProfundidadArbol(hijoIzq, idNuevo+"-0"),
                                     aumentarProfundidadArbol(hijoDcha, idNuevo+"-1"))

                // Si es una hoja, se devuelve un nuevo NodoHoja actualizando el ID
                case NodoHoja(id, valor) => NodoHoja(idNuevo, valor)

                // En otro caso (nulo), devolvemos Nil (nunca debería darse este caso debido a las comprobaciones previas)
                case _ => Nil
            }
        }

        // Se comprueba el tipo de árbol al que pertenece el primer árbol binario
        arbol1 match{
            // Si es nulo, se devuelve directamente el segundo árbol
            case Nil => arbol2

            // En otro caso
            case _ =>

                // Se comprueba el tipo de árbol al que pertenece el segundo árbol binario
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

    /**
     * Función recursiva que permite obtener el valor que contiene una determinada hoja de un árbol binario Balanceado
     * en un instancia de la clase Some o None
     * @param arbol
     * @param numHoja
     * @return
     */
    def obtenerValorHoja[A](arbol: ArbolBinario[A], numHoja: Int): Option[A]= {

        // Calculamos el número de hojas del árbol
        val numHojasArbol = numeroHojas(arbol)

        // Comprobamos que la hoja deseada está en un rango válido
        val n = {
            // Si es menor a 0, se devuelve la hoja 0
            if(numHoja < 0) 0

            // Si es mayor o igual al número total de hojas, se devuelve la últiima
            else if (numHoja >= numHojasArbol) numHojasArbol-1

            // Si es válida, se devuelve ese número de hoja
            else numHoja
        }

        // Función tail recursive que permite devolver el valor de una hoja (en instancias de la clase Some o None),
        // buscando en el árbol mediante una cota inferior y otra superior, que indican las hojas que hay en una
        // parte del árbol
        @annotation.tailrec
        def go[A](arbol: ArbolBinario[A], cotaInferior: Int, cotaSuperior: Int): Option[A] = {

            // Se comprueba el tipo de nodo
            arbol match{

                // Si es un nodo interno, comprobamos la rama por la que debemos continuar
                case NodoInterno(id, hijoIzq, hijoDcha) =>
                    // Si la hoja a la que queremos llegar es menor o igual al punto medio actual avanzamos por el
                    // hijo izquierdo, manteniendo la cota inferior, y estableciendo como cota superior el punto medio
                    if (n <= (cotaInferior + cotaSuperior)/2) go(hijoIzq, cotaInferior, (cotaInferior + cotaSuperior)/2)

                    // Si es mayor al punto medio, avanzamos por el hijo derecho, manteniendo la cota superior,
                    // y estableciendo como cota inferior el punto medio + 1
                    else go(hijoDcha, (cotaInferior + cotaSuperior)/2 + 1, cotaSuperior)

                // Si es un nodo hoja, devolvemos el valor
                case NodoHoja(id, valor) => Some(valor)

                // En otro caso (nodo nulo) se devuelve un valor nulo
                case _ => None
            }
        }

        // Primera llamada a la función go, con la cota Inferior a 0, y la superior al número de hojas -1
        go(arbol, 0, numHojasArbol-1)
    }

    /**
     * Funciónpermite obtener el valor que contiene una determinada hoja de un árbol binario de Doubles
     * @param arbol
     * @param numHoja
     * @return
     */
    def obtenerValorHojaDouble(arbol: ArbolBinario[Double], numHoja: Int): Double = {

        // Se llama a la función obtener valor hoja, y se comprueba el resultado
        obtenerValorHoja(arbol, numHoja) match{
            // Si es instancia de Some, se devuelve el valor
            case Some(valor) => valor

            // Si es instancia de None, Se devuelve 0.0
            case None => 0.0
        }
    }
}


