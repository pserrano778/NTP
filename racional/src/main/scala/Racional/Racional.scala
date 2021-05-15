package Racional

/**
 * Clase para representar numeros racionales n/d
 * @param n
 * @param d
 */
class Racional (n: Int, d: Int){
    // Se comprueba que se puede crear el objeto
    require(d != 0)

    // se calcula el maximo comun divisor de a y b
    private val mcd = maximoComunDivisor(n, d)

    println("maximo comun divisor: " + mcd)
    // se crean datos miembro para numerador y denominador, de la forma mas reducida posible
    val numerador = n/mcd
    val denominador = d/mcd


    /**
     * Construccto auxiliar para crear racionales de tipo n/1
     * @param n
     */
    def this(n: Int) = this(n,1)

    /**
     * Sobrecarga de toString
     * @return
     */
    override def toString: String = numerador + "/" + denominador

    /**
     * Método que permite sumar dos numeros racionales
     * @param otro
     * @return
     */
    def +(otro: Racional) : Racional = {
        new Racional (numerador*otro.denominador + otro.numerador*denominador, denominador*otro.denominador)
    }

    /**
     * Metodo para comprobar si el numero racional el menor que otro
     * @param otro
     * @return
     */
    def < (otro: Racional): Boolean = {
        this.numerador*otro.denominador < otro.numerador*this.denominador
    }

    /**
     * Metodo que devuelve el mayor numero racional (entre el actual y otro)
     * @param otro
     * @return
     */
    def maximo(otro: Racional): Racional = {
        if (this < otro) otro else this
    }

    /**
     * Metodo recursivo que calcula el maximo comun divisor
     * @param a
     * @param b
     * @return
     */
    private def maximoComunDivisor(a: Int, b: Int) : Int = {
        if (b == 0) a
        else maximoComunDivisor(b, a%b)
    }
}

object Racional extends App{
    val obj1 = new Racional(1,2)
    val obj2 = new Racional(4,3)
    println("obj1: " + obj1)
    println("obj2: " + obj2)
    println("suma " + obj1+obj2)

    println("comprobacion de menor que entre obj1 1 y obj2: " + (obj1 < obj2))

    val obj3 = new Racional(5)
    println("obj3: " + obj3)

    val obj4 = new Racional(2, 2)
    println("obj4: " + obj4)
}
