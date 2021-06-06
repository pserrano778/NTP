package ArbolBinario

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

//Importamos el contenido de ArbolBinario
import ArbolBinario._

import Utilidades.Utilidaes._

object ArbolBinarioCheck extends Properties("Prueba de Árboles Binarios"){

    val MIN_DOUBLE = -1000.0
    val MAX_DOUBLE = 1000.0

    // Generacion de arrays de doubles aleatorios, de longitud aleatoria
    val doublesAleatorios = Gen.containerOf[Seq, Double](Gen.choose(MIN_DOUBLE, MAX_DOUBLE))

    // Generacion de arrays de doubles aleatorios, para probar la funcionalidad de profucto
    val doublesAleatoriosProducto = Gen.containerOf[Seq, Double](Gen.choose(-1.0, 1.0))

    // Comprobamos que la profundidad es correcta
    property("Profundidad correcta") = {
        forAll(doublesAleatorios) { (elementos) => {
            val arbolBinario = ArbolBinario(elementos:_*)

            // Se establece el número de nodos internos que debe tener el árbol
            val profundidad = if(elementos.size == 0) 0
            else potenciaDosMasCercana(elementos.size) + 1

            profundidadArbol(arbolBinario) == profundidad
        }}
    }

    // Comprobamos que el número de nodos internos es correcto
    property("Número de nodos internos correcto") = {
        forAll(doublesAleatorios) { (elementos) => {
            val arbolBinario = ArbolBinario(elementos:_*)

            // Se establece el número de nodos internos que debe tener el árbol
            val nodosIntenos = if(elementos.size == 0) 0
            else elementos.size-1

            numeroNodosInternos(arbolBinario) == nodosIntenos
        }}
    }

    // Comprobamos que el número de nodos hoja es correcto
    property("Número de nodos hoja correcto") = {
        forAll(doublesAleatorios) { (elementos) => {
            val arbolBinario = ArbolBinario(elementos:_*)
            numeroHojas(arbolBinario) == elementos.length
        }}
    }

    // Comprobamos que el valor de la hojas es correcto y está en el mismo orden que la secuencia de elementos
    property("Valor Elementos hojas correcto y en orden") = {
        forAll(doublesAleatorios) { (elementos) => {
            val arbolBinario = ArbolBinario(elementos:_*)

            var valido = true

            // Se realiza una búsqueda de todos los elementos del arbol
            for(i <- 0 until elementos.length ) {
                // Si en algún momento no es correcto
                if(!(obtenerValorHoja(arbolBinario, i) == elementos(i))) valido = false;
            }
            valido
        }}
    }

    // Comprobamos que se realiza la suma de los elementos de un árbol correctamente
    property("Suma elementos hojas correcta") = {
        forAll(doublesAleatorios) { (elementos) => {
            val arbolBinario = ArbolBinario(elementos:_*)

            val resultado = if (elementos.size > 0) elementos.reduce(_+_)
            else 0.0

            sumaValoresHojas(arbolBinario) ~= resultado
        }}
    }

    // Comprobamos que se realiza el producto de los elementos de un árbol correctamente
    property("Producto elementos hojas correcta") = {
        forAll(doublesAleatoriosProducto) { (elementos) => {
            val arbolBinario = ArbolBinario(elementos:_*)

            val resultado = if (elementos.size > 0) elementos.fold(1.0)(_*_)
            else 1.0

            productoValoresHojas(arbolBinario) ~= resultado
        }}
    }

    // Comprobamos que se unen dos árboles binarios de forma correcta
    property("Elementos de las hojas al unir dos árboles formando uno nuevo balanceado" +
        " = concatener ambas secuencias") = {
            forAll(doublesAleatorios) { (elementos) => {
                // Se crea un arbol, uniendo dos arboles
                val arbolBinarioConcatenado = unirDosArboles(ArbolBinario(elementos:_*), ArbolBinario(elementos:_*))
                val elementosConcatenados = elementos ++ elementos
                var valido = true

                // Se realiza una búsqueda de todos los elementos del arbol
                for(i <- 0 until elementosConcatenados.length ) {
                    // Si en algún momento no es correcto
                    if(!(obtenerValorHoja(arbolBinarioConcatenado, i) == elementosConcatenados(i))) valido = false;
                }
                valido
            }
        }
    }
}
