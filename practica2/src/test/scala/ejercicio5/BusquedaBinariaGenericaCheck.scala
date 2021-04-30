package ejercicio5

import ejercicio5.BusquedaBinariaGenerica.{array1, busquedaBinaria}
import org.scalacheck.Prop.forAll
import org.scalacheck.rng.Seed.random
import org.scalacheck.{Gen, Properties}


object BusquedaBinariaGenericaCheck extends Properties("Prueba de Practica 2 Ejercicio 5"){

    val MAXIMALONGITUD = 20

    val MAXENTERO = 1000
    val MINENTERO = -1000

    // Generacion de arrays de enteros aleatorios
    val enterosAleatorios = Gen.containerOfN[Array, Int](MAXIMALONGITUD, Gen.choose(MINENTERO, MAXENTERO+1))

    // Se define el criterio
    def criterioEnteros (num1 : Int, num2: Int) = num1>num2

    property("Busqueda correcta") = {

        forAll(enterosAleatorios) { (array) => {
            // Se ordena el array
            val arrayOrdenado = array.sorted
            var valido = true

            // Se realiza una búsqueda de todos los elementos del array actual
            for(i <- 0 until arrayOrdenado.length) {
                val resultadoBusquedaBinaria = busquedaBinaria(arrayOrdenado, arrayOrdenado(i), criterioEnteros)

                // Si en algún momento no es correcto
                if(i != resultadoBusquedaBinaria && arrayOrdenado(i) != arrayOrdenado(resultadoBusquedaBinaria)) {
                    valido = false;
                }
            }
            valido
        }}
    }
}