package ejercicio3

import org.scalatest.funsuite.AnyFunSuite


class Ejercicio3Test  extends AnyFunSuite {

    // Prueba 1
    test("chequear balance: (if (zero? x) max (/ 1 x))esta balanceada") {
        assert(ejercicio3.BalanceoCadenasConParentesis.chequearBalance("(if (zero? x) max (/ 1 x))".toList))
    }

    // Prueba 2
    test("chequear balance: Te lo dije ...esta balanceada") {
        assert(ejercicio3.BalanceoCadenasConParentesis.chequearBalance("Te lo dije (eso esta (todavia) hecho)".toList))
    }

    // Prueba 3
    test("chequear balance: :-) no esta balanceada") {
        assert(!ejercicio3.BalanceoCadenasConParentesis.chequearBalance(":-)".toList))
    }

    // Prueba 4
    test("chequear balance: no basta con contar sin mas") {
        assert(!ejercicio3.BalanceoCadenasConParentesis.chequearBalance("())(".toList))
    }

    // Prueba 5
    test("(if (a > b) (b/a) else (a/(b*b)))"){
        assert(ejercicio3.BalanceoCadenasConParentesis.chequearBalance("(if (a > b) (b/a) else (a/(b*b)))".toList))
    }

    // Prueba 6
    test("(ccc(ccc)cc((ccc(c))))"){
        assert(ejercicio3.BalanceoCadenasConParentesis.chequearBalance("(ccc(ccc)cc((ccc(c))))".toList))
    }

    // Prueba 7
    test("(if (a > b) b/a) else (a/(b*b)))"){
        assert(!ejercicio3.BalanceoCadenasConParentesis.chequearBalance("(if (a > b) b/a) else (a/(b*b)))".toList))
    }

    // Prueba 7
    test("(ccc(ccccc((ccc(c))))"){
        assert(!ejercicio3.BalanceoCadenasConParentesis.chequearBalance("(ccc(ccccc((ccc(c))))".toList))
    }

    // Prueba 8
    test("())()())"){
        assert(!ejercicio3.BalanceoCadenasConParentesis.chequearBalance("())()())".toList))
    }

    // Prueba 8
    test("()()())("){
        assert(!ejercicio3.BalanceoCadenasConParentesis.chequearBalance("()()())(".toList))
    }

}
