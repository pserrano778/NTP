package ejercicio5

import ejercicio5.BusquedaSaltosGenerica.busquedaSaltosGenerica
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}


object BusquedaSaltosGenericaCheck extends Properties("Prueba de Practica 2 Ejercicio 5"){

    val MAXIMALONGITUD = 20

    val MAXENTERO = 1000
    val MINENTERO = -1000

    // Generacion de arrays de enteros aleatorios
    val enterosAleatorios = Gen.containerOfN[Array, Int](MAXIMALONGITUD, Gen.choose(MINENTERO, MAXENTERO+1))

    property("Busqueda correcta") = {

        forAll(enterosAleatorios) { (array) => {
            // Se ordena el array
            val arrayOrdenado = array.sorted
            var valido = true

            // Se realiza una búsqueda de todos los elementos del array actual
            for(i <- 0 until arrayOrdenado.length) {
                val resultadoBusquedaSaltos = busquedaSaltosGenerica(arrayOrdenado, arrayOrdenado(i)) (_ > _)

                // Si en algún momento no es correcto
                if(i != resultadoBusquedaSaltos && arrayOrdenado(i) != arrayOrdenado(resultadoBusquedaSaltos)) {
                    valido = false;
                }
            }
            valido
        }}
    }
}