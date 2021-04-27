package ejercicio4
import org.scalatest.funsuite.AnyFunSuite


class Ejercicio4Test  extends AnyFunSuite {

    // Prueba 1
    test("Combinaciones para devolver cambio de 4 con monedas tipo 1, 2, 3, 4") {
        ejercicio4.ContadorPosiblesCambiosMoneda.listarCambiosPosibles(4, List(1, 2, 3, 4)).foreach(combinacion => assert(combinacion.sum === 4))
    }

    // Prueba 2
    test("Combinaciones para devolver cambio de 1 con monedas tipo 2, 3, 4 (No hay posibilidad de devolver cambio") {
        assert(ejercicio4.ContadorPosiblesCambiosMoneda.listarCambiosPosibles(1, List(2, 3, 4)) === List())
    }

    // Prueba 3
    test("Combinaciones para devolver cambio de 10 sin monedas ") {
        assert(ejercicio4.ContadorPosiblesCambiosMoneda.listarCambiosPosibles(10, List()) === List())
    }

    // Prueba 4
    test("Combinaciones para devolver cambio de 20 con monedas tipo 4, 5, 6, 9, 10, 11") {
        ejercicio4.ContadorPosiblesCambiosMoneda.listarCambiosPosibles(20, List(4, 5, 6, 9, 10, 11)).foreach(combinacion => assert(combinacion.sum === 20))
    }
}
