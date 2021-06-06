package ArbolBinario
import org.scalatest.funsuite.AnyFunSuite
// Importamos el contenido completo de Arbol binario

import ArbolBinario._

class ArbolBinarioTest extends AnyFunSuite{



    // Creamos algunos Árboles para utilizar en los tests

    // Arbol binario vacío
    val arbolBinarioVacio = ArbolBinario()

    // Arbol binario con un elemento
    val arbolBinario1 = ArbolBinario(1.1)

    // Arbol binario con cinco elementos
    val arbolBinario2 = ArbolBinario(1.0, 2.0, 3.0, 4.0, 5.0)


    //******* Pruebas con Scala test de recorridos sobre los árboles ******* //

    // Prueba 1:Recorrido en Profundidad Preorden Lista Vacía
    test("Recorrido en Profundidad Preorden Lista Vacía") {
        assert(recorridoProfundidadPreorden(arbolBinarioVacio) === "Nodo Nulo")
    }

    // Prueba 2:Recorrido en Profundidad Preorden Lista con cinco elementos
    test("Recorrido en Profundidad Preorden Lista con un elemento") {
        assert(recorridoProfundidadPreorden(arbolBinario1) === "id: 0 valor: 1.1")
    }

    // Prueba 3:Recorrido en Profundidad Preorden Lista con cinco elementos
    test("Recorrido en Profundidad Preorden Lista con cinco elemento") {
        assert(recorridoProfundidadPreorden(arbolBinario2) === "id: 0 | id: 0-0 | id: 0-0-0 | id: 0-0-0-0 " +
            "valor: 1.0 | id: 0-0-0-1 valor: 2.0 | id: 0-0-1 valor: 3.0 | id: 0-1 | id: 0-1-0 valor: 4.0 " +
            "| id: 0-1-1 valor: 5.0")
    }

    // Prueba 4:Recorrido en Profundidad Postorden Lista Vacía
    test("Recorrido en Profundidad Postorden Lista Vacía") {
        assert(recorridoProfundidadPostorden(arbolBinarioVacio) === "Nodo Nulo")
    }

    // Prueba 5:Recorrido en Profundidad Postorden Lista con un elemento
    test("Recorrido en Profundidad Postorden Lista con un elemento") {
        assert(recorridoProfundidadPostorden(arbolBinario1) === "id: 0 valor: 1.1")
    }

    // Prueba 6:Recorrido en Profundidad Postorden Lista con cinco elementos
    test("Recorrido en Profundidad Postorden Lista con cinco elementos") {
        assert(recorridoProfundidadPostorden(arbolBinario2) === "id: 0-0-0-0 valor: 1.0 | id: 0-0-0-1 valor: 2.0" +
            " | id: 0-0-0 | id: 0-0-1 valor: 3.0 | id: 0-0 | id: 0-1-0 valor: 4.0 | id: 0-1-1 valor: 5.0 " +
            "| id: 0-1 | id: 0")
    }

    // Prueba 7:Recorrido en Profundidad Inorden Lista Vacía
    test("Recorrido en Profundidad Inorden Lista Vacía") {
        assert(recorridoProfundidadInorden(arbolBinarioVacio) === "Nodo Nulo")
    }

    // Prueba 8:Recorrido en Profundidad Inorden Lista con un elemento
    test("Recorrido en Profundidad Inorden con un elemento") {
        assert(recorridoProfundidadInorden(arbolBinario1) === "id: 0 valor: 1.1")
    }

    // Prueba 9:Recorrido en Profundidad Inorden Lista con cinco elementos
    test("Recorrido en Profundidad Inorden con cinco elementos") {
        assert(recorridoProfundidadInorden(arbolBinario2) === "id: 0-0-0-0 valor: 1.0 | id: 0-0-0 | " +
            "id: 0-0-0-1 valor: 2.0 | id: 0-0 | id: 0-0-1 valor: 3.0 | id: 0 | id: 0-1-0 valor: 4.0 " +
            "| id: 0-1 | id: 0-1-1 valor: 5.0")
    }

    // Prueba 10:Recorrido en Anchura Recursivo Lista Vacía
    test("Recorrido en Anchura Recursivo Lista Vacía") {
        assert(recorridoAnchuraRecursivo(arbolBinarioVacio) === "Nodo Nulo | ")
    }

    // Prueba 11:Recorrido en Anchura Recursivo Lista con un elemento
    test("Recorrido en Anchura Recursivo Lista con un elemento") {
        assert(recorridoAnchuraRecursivo(arbolBinario1) === "id: 0 valor: 1.1 | ")
    }

    // Prueba 12:Recorrido en Anchura Recursivo Lista con cinco elementos
    test("Recorrido en Anchura Recursivo Lista con cinco elementos") {
        assert(recorridoAnchuraRecursivo(arbolBinario2) === "id: 0 | id: 0-0 | id: 0-1 | id: 0-0-0 | " +
            "id: 0-0-1 valor: 3.0 | id: 0-1-0 valor: 4.0 | id: 0-1-1 valor: 5.0 | id: 0-0-0-0 valor: 1.0" +
            " | id: 0-0-0-1 valor: 2.0 | ")
    }

    // Prueba 13:Recorrido en Anchura Usando cola Lista Vacía
    test("Recorrido en Anchura usando cola Lista Vacía") {
        assert(recorridoAnchuraUsandoCola(arbolBinarioVacio) === recorridoAnchuraRecursivo(arbolBinarioVacio))
    }

    // Prueba 14:Recorrido en Anchura Usando cola Lista con un elemento
    test("Recorrido en Anchura usando cola Lista con un elemento") {
        assert(recorridoAnchuraUsandoCola(arbolBinario1) === recorridoAnchuraRecursivo(arbolBinario1))
    }

    // Prueba 15:Recorrido en Anchura Usando cola Lista con cinco elementos
    test("Recorrido en Anchura usando cola con cinco elementos") {
        assert(recorridoAnchuraUsandoCola(arbolBinario2) === recorridoAnchuraRecursivo(arbolBinario2))
    }
}
