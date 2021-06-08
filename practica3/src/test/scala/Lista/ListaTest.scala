package Lista
import org.scalatest.funsuite.AnyFunSuite
// Importamos el contenido completo de Lista
import Lista._

class ListaTest extends AnyFunSuite{

    // Creamos algunas listas para utilizar en los tests

    // Lista vacia
    val listaVacia = Lista()

    // Lista con 1 elemento entero
    val listaEnteros1 = Lista(1)

    // Lista con 2 elementos enteros
    val listaEnteros2 = Lista(2, 4)

    // Lista con 6 elementos enteros
    val listaEnteros3 = Lista(2, 4, 6, 9, 1, 5)

    // Lista de varios tipos de elementos
    val listaDoubles = Lista(1.5, 3.8, 2.3, 4.4, 5.0)


    //******* Pruebas con Scala test ******* //

    // Prueba 1: Constructor de la lista
    test("Constructor Lista") {

        // Definimos la siguiente función tail recursive para comprobar si la lista creada contiene los
        // mismo elementos que la secuencia de elementos, conservando el orden
        @annotation.tailrec
        def comprobarConstructor[A](lista: Lista[A], elementos: A*): Boolean = {
            lista match {
                // Si es Nil, que no queden elementos
                case Nil => elementos.size == 0

                // Si no es Nil
                case Cons (cabeza, cola) =>
                    // Que el número de elementos sea mayor a 0 y que las cabezas coincidan. Se continúa resto (cola)
                    if (elementos.size > 0 && cabeza == elementos.head) comprobarConstructor(cola, elementos.tail: _*)
                    else false
            }
        }
        assert(comprobarConstructor(listaDoubles, 1.5, 3.8, 2.3, 4.4, 5.0))
    }

    // Prueba 2: Obtener la longitud de una lista con elementos
    test("Longitud lista con elementos") {
        assert(longitud(listaEnteros2) === 2)
    }

    // Prueba 3: Obtener la longitud de una lista vacia
    test("Longitud lista vacia") {
        assert(longitud(listaVacia) === 0)
    }

    // Prueba 4: Suma de los elementos de una lista con un entero
    test("Suma lista 1 entero") {
        assert(sumaEnteros(listaEnteros1) === 1)
    }

    // Prueba 5: Suma de los elementos de una lista con varios enteros
    test("Suma lista varios enteros") {
        assert(sumaEnteros(listaEnteros2) === 6)
    }

    // Prueba 6: Producto de los elementos de una lista con un entero
    test("Producto lista 1 entero") {
        assert(productoEnteros(listaEnteros1) === 1)
    }

    // Prueba 7: Producto de los elementos de una lista con varios enteros
    test("Prodcuto lista varios enteros") {
        assert(productoEnteros(listaEnteros2) === 8)
    }

    // Prueba 8: Concatenar una lista vacia con otra con elementos
    test("Lista vacia con lista con elementos") {
        assert(concatenar(listaVacia, listaDoubles) === listaDoubles)
    }

    // Prueba 9: Concatenar una lista con elementos con una vacia
    test("Lista con elementos con lista vacia") {
        assert(concatenar(listaDoubles, listaVacia) === listaDoubles)
    }

    // Prueba 10: Concatenar una lista vacia con vacia
    test("Lista vacia con Lista vacia") {
        assert(concatenar(listaVacia, listaVacia) === Lista())
    }

    // Prueba 11: Concatenar dos lista con elementos
    test("Concatenar dos listas con elementos") {
        assert(concatenar(listaDoubles, listaEnteros2) === Lista(1.5, 3.8, 2.3, 4.4, 5.0, 2.0, 4.0))
    }

    // Prueba 12: Suma de los elementos de una lista con un entero FoldRight
    test("Suma lista 1 entero FoldRight") {
        assert(sumaFoldRight(listaEnteros1) === 1)
    }

    // Prueba 13: Suma de los elementos de una lista con varios enteros FoldRight
    test("Suma lista varios enteros FoldRight") {
        assert(sumaFoldRight(listaEnteros2) === 6)
    }

    // Prueba 14: Producto de los elementos de una lista con un entero FoldRight
    test("Producto lista 1 entero FoldRight") {
        assert(sumaFoldRight(listaEnteros1) === 1)
    }

    // Prueba 15: Producto de los elementos de una lista con varios enteros FoldRight
    test("Producto lista varios enteros FoldRight") {
        assert(productoFoldRight(listaEnteros2) === 8)
    }

    // Prueba 16: Asignar una cabeza a una lista vacia
    test("Asignar cabeza a lista vacia") {
        assert(asignarCabeza(listaVacia, 'B') === Lista('B'))
    }

    // Prueba 17: Asignar una nueva cabeza a una lista con varios elementos
    test("Asignar una nueva cabeza a una lista con varios elementos") {
        assert(asignarCabeza(listaEnteros2, 'B') === Lista('B', 4))
    }

    // Prueba 18: Asignar una nueva cabeza a una lista con solo un elemento
    test("Asignar una nueva cabeza a una lista con solo un elemento") {
        assert(asignarCabeza(listaEnteros1, 'B') === Lista('B'))
    }

    // Prueba 19: Obtener la cola de una lista vacia
    test("Cola de lista vacia") {
        assert(tail(listaVacia) === Lista())
    }

    // Prueba 20: Obtener la cola de una lista con 1 elemento
    test("Cola de lista con 1 elemento") {
        assert(tail(listaEnteros1) === Lista())
    }

    // Prueba 21: Obtener la cola de una lista con varios elementos
    test("Cola de lista con varios elementos") {
        assert(tail(listaEnteros2) === Lista(4))
    }

    // Prueba 22: Eliminar un elemento de una lista vacia
    test("Eliminar elemento de lista vacia") {
        assert(eliminar(listaVacia, 1) === Lista())
    }

    // Prueba 23: Eliminar un elemento de una lista con un elemento
    test("Eliminar elemento de lista con un elemento") {
        assert(eliminar(listaEnteros1, 1) === Lista())
    }

    // Prueba 24: Eliminar un elemento de una lista con 2 elemento
    test("Eliminar un elemento de una lista con 2 elemento") {
        assert(eliminar(listaEnteros2, 1) === Lista(4))
    }

    // Prueba 25: Eliminar tres elementos de una lista con 2 elementos
    test("Eliminar tres elementos de una lista con 2 elementos") {
        assert(eliminar(listaEnteros2, 3) === Lista())
    }

    // Prueba 26: Eliminar elementos de una lista de enteros mientras sean menor que 9
    test("Eliminar elementos mientras sean menores que 9 de una lista de enteros") {
        def criterio(num1: Int) = num1 < 9
        assert(eliminarMientras(listaEnteros3, criterio) === Lista(9, 1, 5))
    }

    // Prueba 27: Eliminar elementos de una lista de enteros mientras sean menor o iguales a 9
    test("Eliminar elementos mientras sean menores o iguales a 9 de una lista de enteros") {
        def criterio(num1: Int) = num1 <= 9
        assert(eliminarMientras(listaEnteros3, criterio) === Lista())
    }

    // Prueba 28: Eliminar elementos de una lista vacia mientras sean menor que 9
    test("Eliminar elementos mientras sean menores que 9 de una lista vacia") {
        def criterio(num1: Int) = num1 < 9
        assert(eliminarMientras(listaVacia, criterio) === Lista())
    }

    // Prueba 29: Eliminar ultimo en lista vacia
    test("Eliminar Ultimo en lista vacia") {
        assert(eliminarUltimo(listaVacia) === Lista())
    }

    // Prueba 30: Eliminar ultimo en lista con un elemento
    test("Eliminar Ultimo en lista con un elemento") {
        assert(eliminarUltimo(listaEnteros1) === Lista())
    }

    // Prueba 31: Eliminar ultimo en lista con varios elementos
    test("Eliminar Ultimo en lista con varios elemento") {
        assert(eliminarUltimo(listaDoubles) === Lista(1.5, 3.8, 2.3, 4.4))
    }

    // Prueba 32: Test de FoldLeft, aplicando la operacion suma
    test("Suma FoldLeft") {
        assert(foldLeft(listaEnteros2, 0)(_ + _) === 6)
    }

    // Prueba 33: Test de FoldLeft, aplicando la operacion producto
    test("Prodcuto FoldLeft") {
        assert(foldLeft(listaEnteros2, 1)((_ * _)) === 8)
    }
}
