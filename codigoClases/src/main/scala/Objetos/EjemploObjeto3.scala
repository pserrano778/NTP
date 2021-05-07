package Objetos

class A(val a: Int, val b: Int){
    def sumar = a+b
}

object ObjetoA extends A(8,7) // Hereda de una clase

//class C extends ObjetoA // La clase NO puede derivar de un objeto

object EjemploObjeto3 extends App{
    println(ObjetoA.sumar)
}
