package ejercicio3

object BalanceoCadenasConParentesis extends App{

    /**
     * Funcion recursiva para si una cadena tiene paréntesis balanceados
     * @param cadena
     * @return
     */
    def chequearBalance(cadena: List[Char]) : Boolean = {
        @annotation.tailrec
        def go(cadena: List[Char], balance: Int) : Boolean = {

            // Si hemos llegado al final de la cadena comprobamos el balance
            if (cadena.isEmpty) balance == 0

            // Si en algún momento el balance es negativo [más ')' que '(') devolvemos false
            else if (balance < 0) false

            // Si encontramos '(', aumentamos el balance y comprobamos de forma recursiva el resto
            else if (cadena.head == '(') go(cadena.tail, balance + 1)

            // Si encontramos ')', decrementamos el balance y comprobamos de forma recursiva el resto
            else if (cadena.head == ')') go(cadena.tail, balance - 1)

            // Si no encontramos ni '(' o ')', comprobamos el resto de la cadena
            else go(cadena.tail, balance)
        }

        go(cadena, 0)
    }

    var cadena = "(if (a > b) (b/a) else (a/(b*b)))"
    println(cadena + (": ") + chequearBalance(cadena.toList))

    cadena = "(ccc(ccc)cc((ccc(c))))"
    println(cadena + (": ") + chequearBalance(cadena.toList))

    cadena = "(if (a > b) b/a) else (a/(b*b)))"
    println(cadena + (": ") + chequearBalance(cadena.toList))

    cadena = "(ccc(ccccc((ccc(c))))"
    println(cadena + (": ") + chequearBalance(cadena.toList))

    cadena = "())()())"
    println(cadena + (": ") + chequearBalance(cadena.toList))

    cadena = "())("
    println(cadena + (": ") + chequearBalance(cadena.toList))
}
