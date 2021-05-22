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
    val listaCombinada = Lista(1, 'b', 2.0, 'd', 'c')

    // Prueba 1: Obtener la longitud de una lista con elementos
    test("Longitud lista con elementos") {
        assert(longitud(listaEnteros2) === 2)
    }

    // Prueba 2: Obtener la longitud de una lista vacia
    test("Longitud lista vacia") {
        assert(longitud(listaVacia) === 0)
    }

    // Prueba 3: Suma de los elementos de una lista con un entero
    test("Suma lista 1 entero") {
        assert(sumaEnteros(listaEnteros1) === 1)
    }

    // Prueba 4: Suma de los elementos de una lista con varios enteros
    test("Suma lista varios enteros") {
        assert(sumaEnteros(listaEnteros2) === 6)
    }

    // Prueba 5: Producto de los elementos de una lista con un entero
    test("Producto lista 1 entero") {
        assert(productoEnteros(listaEnteros1) === 1)
    }

    // Prueba 6: Producto de los elementos de una lista con varios enteros
    test("Prodcuto lista varios enteros") {
        assert(productoEnteros(listaEnteros2) === 8)
    }

    // Prueba 7: Concatenar una lista vacia con otra con elementos
    test("Lista vacia con lista con elementos") {
        assert(concatenar(listaVacia, listaCombinada) === listaCombinada)
    }

    // Prueba 8: Concatenar una lista con elementos con una vacia
    test("Lista con elementos con lista vacia") {
        assert(concatenar(listaCombinada, listaVacia) === listaCombinada)
    }

    // Prueba 9: Concatenar una lista vacia con vacia
    test("Lista vacia con Lista vacia") {
        assert(concatenar(listaVacia, listaVacia) === Lista())
    }

    // Prueba 10: Concatenar dos lista con elementos
    test("Concatenar dos listas con elementos") {
        assert(concatenar(listaCombinada, listaEnteros2) === Lista(1, 'b', 2.0, 'd', 'c', 2, 4))
    }

    // Prueba 11: Suma de los elementos de una lista con un entero FoldRight
    test("Suma lista 1 entero FoldRight") {
        assert(sumaFoldRight(listaEnteros1) === 1)
    }

    // Prueba 12: Suma de los elementos de una lista con varios enteros FoldRight
    test("Suma lista varios enteros FoldRight") {
        assert(sumaFoldRight(listaEnteros2) === 6)
    }

    // Prueba 13: Producto de los elementos de una lista con un entero FoldRight
    test("Producto lista 1 entero FoldRight") {
        assert(sumaFoldRight(listaEnteros1) === 1)
    }

    // Prueba 14: Producto de los elementos de una lista con varios enteros FoldRight
    test("Producto lista varios enteros FoldRight") {
        assert(productoFoldRight(listaEnteros2) === 8)
    }

    // Prueba 15: Asignar una cabeza a una lista vacia
    test("Asignar cabeza a lista vacia") {
        assert(asignarCabeza(listaVacia, 'B') === Lista('B'))
    }

    // Prueba 16: Asignar una nueva cabeza a una lista con varios elementos
    test("Asignar una nueva cabeza a una lista con varios elementos") {
        assert(asignarCabeza(listaEnteros2, 'B') === Lista('B', 4))
    }

    // Prueba 17: Asignar una nueva cabeza a una lista con solo un elemento
    test("Asignar una nueva cabeza a una lista con solo un elemento") {
        assert(asignarCabeza(listaEnteros1, 'B') === Lista('B'))
    }

    // Prueba 18: Obtener la cola de una lista vacia
    test("Cola de lista vacia") {
        assert(tail(listaVacia) === Lista())
    }

    // Prueba 19: Obtener la cola de una lista con 1 elemento
    test("Cola de lista con 1 elemento") {
        assert(tail(listaEnteros1) === Lista())
    }

    // Prueba 20: Obtener la cola de una lista con varios elementos
    test("Cola de lista con varios elementos") {
        assert(tail(listaEnteros2) === Lista(4))
    }

    // Prueba 21: Eliminar un elemento de una lista vacia
    test("Eliminar elemento de lista vacia") {
        assert(eliminar(listaVacia, 1) === Lista())
    }

    // Prueba 22: Eliminar un elemento de una lista con un elemento
    test("Eliminar elemento de lista con un elemento") {
        assert(eliminar(listaEnteros1, 1) === Lista())
    }

    // Prueba 23: Eliminar un elemento de una lista con 2 elemento
    test("Eliminar un elemento de una lista con 2 elemento") {
        assert(eliminar(listaEnteros2, 1) === Lista(4))
    }

    // Prueba 24: Eliminar tres elementos de una lista con 2 elementos
    test("Eliminar tres elementos de una lista con 2 elementos") {
        assert(eliminar(listaEnteros2, 3) === Lista())
    }

    // Prueba 25: Eliminar elementos de una lista de enteros mientras sean menor que 9
    test("Eliminar elementos mientras sean menores que 9 de una lista de enteros") {
        def criterio(num1: Int) = num1 < 9
        assert(eliminarMientras(listaEnteros3, criterio) === Lista(9, 1, 5))
    }

    // Prueba 26: Eliminar elementos de una lista de enteros mientras sean menor o iguales a 9
    test("Eliminar elementos mientras sean menores o iguales a 9 de una lista de enteros") {
        def criterio(num1: Int) = num1 <= 9
        assert(eliminarMientras(listaEnteros3, criterio) === Lista())
    }

    // Prueba 27: Eliminar elementos de una lista vacia mientras sean menor que 9
    test("Eliminar elementos mientras sean menores que 9 de una lista vacia") {
        def criterio(num1: Int) = num1 < 9
        assert(eliminarMientras(listaVacia, criterio) === Lista())
    }

    // Prueba 28: Eliminar ultimo en lista vacia
    test("Eliminar Ultimo en lista vacia") {
        assert(eliminarUltimo(listaVacia) === Lista())
    }

    // Prueba 29: Eliminar ultimo en lista con un elemento
    test("Eliminar Ultimo en lista con un elemento") {
        assert(eliminarUltimo(listaEnteros1) === Lista())
    }

    // Prueba 30: Eliminar ultimo en lista con varios elementos
    test("Eliminar Ultimo en lista con varios elemento") {
        assert(eliminarUltimo(listaCombinada) === Lista(1, 'b', 2.0, 'd'))
    }

    // Prueba 31: Test de FoldLeft, aplicando la operacion suma
    test("Suma FoldLeft") {
        // Definimos la operacion suma
        def suma (num1: Int, num2: Int) = num1 + num2
        assert(foldLeft(listaEnteros2, 0)(suma) === 6)
    }

    // Prueba 32: Test de FoldLeft, aplicando la operacion producto
    test("Prodcuto FoldLeft") {
        // Definimos la operacion producto
        def producto (num1: Int, num2: Int) = num1 * num2
        assert(foldLeft(listaEnteros2, 1)(producto) === 8)
    }
}
