package Utilidades

import scala.math.pow

object Utilidaes{

    /**
     * Clase que permite implementar utilidades para utilizar sobre Double
     * @param num
     */
    implicit class UtilidadesDouble(val num: Double) {

        /**
         * Método que implementa el operador ~= para comparar doubles hasta una cierta precisión
         * @param num2
         * @param precision
         * @return
         */
        def ~=(num2: Double, precision: Double = 0.00001) = {
            if ((num2 - num).abs < precision) true
            else false
        }
    }

    /**
     * Función que permite obtener la potencia de dos más cercana (mayor o igual) a un número dado
     * @param num
     * @return
     */
    def potenciaDosMasCercana(num: Int): Int ={
        var potencia = 0;

        while (num > pow(2, potencia)) potencia+=1

        potencia
    }
}
