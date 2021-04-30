package Introduccion

class A{
    def mensaje = "Saludo desde clase A"

    override def toString: String = getClass.getName
}

class B extends A

class C extends B{
    override def mensaje: String = "Saludo desde C -> " + super.mensaje
}
object Herencia extends App{
    val objA = new A
    val objB = new B
    val objC = new C

    println(objA)
    println(objB)
    println(objC)

    println(objA.mensaje)
    println(objB.mensaje)
    println(objC.mensaje)

    // creamos referencias
    val refB : A = objB
    val refC : A = objC

    // Se llama
    println("mensaje desde la refB con objB " + refB.mensaje)
    println("mensaje desde la refC con objC " + refC.mensaje)

}
