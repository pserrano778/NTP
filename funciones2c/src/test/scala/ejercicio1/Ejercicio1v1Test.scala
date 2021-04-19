package ejercicio1

import org.scalatest.funsuite.AnyFunSuite

class Ejercicio1v1Test extends AnyFunSuite{
    // prueba 1: valor de la suma de 1 a 10 (55)
    test("sumatorio no TR, inf = 1, sup = 1"){
        assert(Ejercicio1v1.sumatorio(1, 10) === 55)
    }

    // prueba 1: valor de la suma de 1 a 10 (55) con TR
    test("sumatorio TR, inf = 1, sup = 1"){
        assert(Ejercicio1v1.sumatorioTR(1, 10) === 55)
    }
}
