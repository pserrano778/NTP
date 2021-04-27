package ejercicio5

import ejercicio5.BusquedaBinariaGenerica.{array1, busquedaBinaria}
import org.scalacheck.Prop.forAll
import org.scalacheck.rng.Seed.random
import org.scalacheck.{Gen, Properties}


object BusquedaBinariaGenericaCheck extends Properties("Prueba de Practica 2 Ejercicio 5"){

    val MAXIMALONGITUD = 20

    val MAXENTERO = 1000
    val MINENTERO = -1000

    // Generacion de cadenas de longitud n: forma de uso strGen(10) para cadenas
    // de 10 caracteres
    val enterosAleatorios = Gen.containerOfN[Array, Int](MAXIMALONGITUD, Gen.choose(MINENTERO, MAXENTERO+1))

    property("Busqueda correcta") = {

        forAll(enterosAleatorios) { (array) => {
            val arrayOrdenado = array.sorted
            var valido = true
            println(arrayOrdenado.toList)
            for(i <- 0 until arrayOrdenado.length) {
                val resultadoBusquedaBinaria = busquedaBinaria(arrayOrdenado, arrayOrdenado(i)) (_ > _)

                if(i != resultadoBusquedaBinaria && arrayOrdenado(i) != arrayOrdenado(resultadoBusquedaBinaria)) {
                    valido = false;
                }
            }
            valido
        }}
    }
}