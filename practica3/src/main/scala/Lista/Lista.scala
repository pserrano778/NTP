package Lista

/**
* Interfaz generica para la lista
* @tparam A
*/
sealed trait Lista[+A]

/**
* Objeto para definir lista vacia
*/
case object Nil extends Lista[Nothing]

/**
* Clase para definir la lista compuesta por elemento inicial
* (cabeza) y resto (cola)
* @param cabeza
* @param cola
* @tparam A
*/
case class Cons[+A](cabeza : A, cola : Lista[A]) extends Lista[A]

object Lista{
    /**
     * Metodo para permitir crear listas sin usar new
     *
     * @param elementos secuencia de elementos a incluir en la lista
     * @tparam A
     * @return
     */
    def apply[A](elementos: A*): Lista[A] = {
        // Si no quedan elementos, devolvemos lista Nil (sin elementos)
        if (elementos.size == 0) Nil

        // Si quedan elementos, creamos una lista asignando la cabeza, y llamando de forma recursiva
        // a esta función para procesar los elementos restantes
        else Cons(elementos.head, Lista(elementos.tail: _*))
    }
    /**
     * Obtiene la longitud de una lista
     *
     * @param lista
     * @tparam A
     * @return
     */
    def longitud[A](lista: Lista[A]): Int = {
        // Función interna tail rec
        @annotation.tailrec
        def go(lista: Lista[A], acum: Int): Int = {
            // Comprobamos qué tipo de lista tenemos
            lista match {

                // Si es lista Nil (vacía) devolvemos el acumulador
                case Nil => acum

                // Si hay elementos, procesamos el actual y procesamos el resto,
                // aumentando en 1 la longitud
                case Cons(cabeza, cola) => go(cola, acum+1)
            }
        }
        // Primera llamada con longitud 0
        go(lista, 0)
    }

    /**
     * Metodo para sumar los valores de una lista de enteros
     *
     * @param enteros
     * @return
     */
    def sumaEnteros(enteros: Lista[Int]): Double = {
        // Función interna tail rec
        @annotation.tailrec
        def go(lista: Lista[Int], acum: Int): Int = {
            // Comprobamos qué tipo de lista tenemos
            lista match {

                // Si es lista Nil (vacía) devolvemos el acumulador
                case Nil => acum

                // Si hay elementos, procesamos el actual y procesamos el resto,
                // sumando al acumulador el valor actual
                case Cons(cabeza, cola) => go(cola, acum+cabeza)
            }
        }
        // Primera llamada con el elemento 0 (elemento neutro de la suma)
        go(enteros, 0)
    }

    /**
     * Metodo para multiplicar los valores de una lista de enteros
     *
     * @param enteros
     * @return
     */
    def productoEnteros(enteros: Lista[Int]): Double = {
        // Función interna tail rec
        @annotation.tailrec
        def go(lista: Lista[Int], acum: Int): Int = {
            // Comprobamos qué tipo de lista tenemos
            lista match {

                // Si es lista Nil (vacía) devolvemos el acumulador
                case Nil => acum

                // Si hay elementos, procesamos el actual y procesamos el resto,
                // multiplicando el acumulador por el valor actual
                case Cons(cabeza, cola) => go(cola, acum*cabeza)
            }
        }
        // Primera llamada con el elemento 1 (elemento neutro del producto)
        go(enteros, 1)
    }

    /**
     * Metodo para agregar el contenido de dos listas
     *
     * @param lista1
     * @param lista2
     * @tparam A
     * @return
     */
    def concatenar[A](lista1: Lista[A], lista2: Lista[A]): Lista[A] = {
        // Se comprueba el tipo de lista 1
        lista1 match {

            // Si es lista Nil (vacía) devolvemos la segunda lista
            case Nil => lista2

            // Si hay elementos comenzamos, se construye la lista, procesando la cola de forma recursiva
            case Cons(cabeza, cola) => Cons(cabeza, concatenar(cola, lista2))
        }
    }

    /**
     * Funcion de utilidad para aplicar una funcion de forma sucesiva a los
     * elementos de la lista con asociatividad por la derecha
     *
     * @param lista
     * @param neutro
     * @param funcion
     * @tparam A
     * @tparam B
     * @return
     */
    def foldRight[A, B](lista: Lista[A], neutro: B)(funcion: (A, B) => B): B = {
        // Comprobamos qué tipo de lista tenemos
        lista match {

            // Si es lista Nil (vacía) devolvemos el valor neutro
            case Nil => neutro

            // Si hay elementos, aplicamos la función con la cabeza, y llamamos a foldRight con la cola
            case Cons(cabeza, cola) => funcion(cabeza, foldRight(cola, neutro)(funcion))
        }
    }

    /**
     * Suma mediante foldRight
     *
     * @param listaEnteros
     * @return
     */
    def sumaFoldRight(listaEnteros: Lista[Int]): Double = {
        // Llamamos a foldRight utilizando la funcion suma y el elemento neutro 0
        foldRight(listaEnteros, 0)(_ + _)
    }

    /**
     * Producto mediante foldRight
     *
     * @param listaEnteros
     * @return
     */
    def productoFoldRight(listaEnteros: Lista[Int]): Double = {
        // Llamamos a foldRight utilizando la funcion producto y el elemento neutro 1
        foldRight(listaEnteros, 1)(_ * _)
    }

    /**
     *  67 * Reemplaza la cabeza por nuevo valor. Se asume que si la lista esta vacia
     * se devuelve una lista con el nuevo elemento
     *
     * @param lista
     * @param cabezaNueva
     * @tparam A
     * @return
     */
    def asignarCabeza[A](lista: Lista[A], cabezaNueva: A): Lista[A] = {
        // Comprobamos qué tipo de lista tenemos
        lista match {

            // Si es lista Nil (vacía) creamos una lista con la nueva cabeza
            case Nil => Cons(cabezaNueva, Nil)

            // Si ya tenía elementos, creamos una nueva lista cambiando la cabeza
            // y matiendo la cola
            case Cons(cabeza, cola) => Cons(cabezaNueva, cola)
        }
    }

    /**
     * Elimina el elemento cabeza de la lista
     *
     * @param lista
     * @tparam A
     * @return
     */
    def tail[A](lista: Lista[A]): Lista[A] = {
        // Comprobamos qué tipo de lista tenemos
        lista match {

            // Si es lista Nil (vacía), se devuelve vacía
            case Nil => Nil

            // Si hay elementos, se devuelve la cola
            case Cons(cabeza, cola) => cola
        }
    }

    /**
     * Elimina los n primeros elementos de una lista
     *
     * @param lista lista con la que trabajar
     * @param n     numero de elementos a eliminar
     * @tparam A tipo de datos
     * @return
     */
    // Función tail rec
    @annotation.tailrec
    def eliminar[A](lista: Lista[A], n: Int): Lista[A] = {
        // Comprobamos qué tipo de lista tenemos
        lista match {

            // Si es una lista Nil (vacía), se devuelve (no quedan elementos para eliminar)
            case Nil => Nil

            // Si quedan elementos
            case Cons (cabeza, cola) =>

                // Si el número de elmentos a eliminar es 0,
                // devolvemos la lista actual
                if (n == 0) lista

                // Si quedan elementos se procesa la cola, decrementando en 1
                // los elementos que quedan por eliminar
                else eliminar(cola, n-1)
        }
    }

    /**
     * Elimina elementos mientra se cumple la condicion pasada como
     * argumento
     *
     * @param lista    lista con la que trabajar
     * @param criterio predicado a considerar para continuar con el borrado
     * @tparam A tipo de datos a usar
     * @return
     */
    // Función tail rec
    @annotation.tailrec
    def eliminarMientras[A](lista: Lista[A], criterio: A => Boolean): Lista[A] = {
        // Comprobamos qué tipo de lista tenemos
        lista match {

            // Si es una lista Nil (vacía), se devuelve (no quedan elementos para eliminar)
            case Nil => Nil

            // Si quedan elementos
            case Cons (cabeza, cola) => {
                // Si se cumple el criterio para seguir eliminando,
                // procesamos el resto de elementos (cola)
                if(criterio(cabeza)) eliminarMientras(cola, criterio)

                // Si no se cumple el criterio, devolvemos la lista actual
                else lista
            }
        }
    }

    /**
     * Elimina el ultimo elemento de la lista. Aqui no se pueden compartir
     * datos en los objetos y hay que generar una nueva lista copiando
     * datos
     *
     * @param lista lista con la que trabajar
     * @tparam A tipo de datos de la lista
     * @return
     */
    def eliminarUltimo[A](lista: Lista[A]): Lista[A] = {
        // Comprobamos qué tipo de lista tenemos
        lista match {

            // Si es una lista Nil (vacía), se devuelve Nil (no quedan elementos
            case Nil => Nil

            // Si quedan elementos
            case Cons (cabeza, cola) =>
                // Comprobamos la cola para ver si es el último elemento
                cola match {

                    // Si la cola es Nil, es el último elemento y devolvemos Nil
                    case Nil => Nil

                    // Si quedan elementos, se añade la cabeza y se continua procesando la cola
                    case Cons (sigCabeza, sigCola) => Cons(cabeza, eliminarUltimo(cola))
                }
        }
    }

    /**
     * foldLeft con recursividad tipo tail
     *
     * @param lista   lista con la que trabajar
     * @param neutro  elemento neutro
     * @param funcion funcion a aplicar
     * @tparam A parametros de tipo de elementos de la lista
     * @tparam B parametro de tipo del elemento neutro
     * @return
     */
    @annotation.tailrec
    def foldLeft[A, B](lista: Lista[A], neutro: B)(funcion: (B, A) => B): B = {
        // Comprobamos qué tipo de lista tenemos
        lista match {

            // Si es lista Nil (vacía) devolvemos el valor acumulado
            case Nil => neutro

            // Si hay elementos, realizamos la operacion con la cabeza y el valor acumulado, y continuamos con el resto
            // llamando a foldLeft
            case Cons(cabeza, cola) => foldLeft(cola, funcion(neutro, cabeza))(funcion)
        }
    }
}