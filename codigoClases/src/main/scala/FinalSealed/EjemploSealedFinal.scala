package FinalSealed

class A(final val datoFinal: Int, val datoNormal: Int){
    // No se va a poder redefinir
    final override def toString: String = {
        s"dato final: $datoFinal - datonormar: $datoNormal"
    }
}
// no se puede hacer porque no se puede heredar de h modificando la definicion del dato miembro datoFinal
//class B (val datoFinal: Int, dato1: Int) extends A(datoFinal, dato1)

class C(dato1: Int, dato2: Int) extends A(dato1, dato2){
    // No se puede redefinir toString
    //override def toString: String = "metodo propio clase C"
}

// hacer una clase completa Final (prohibir la herencia)
final class D

//class E extends D; No se puede porque implica la posibilidad de redefinir su comportamiento


object EjemploSealedFinal extends App {

}
