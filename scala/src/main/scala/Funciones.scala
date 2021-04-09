object Funciones extends App{
  // definir una función para el calculo del maximo
  def maximo(x : Int, y : Int) : Int = {
    if(x > y) x //If es una expresión, y se evalua el valor x o y
    else y
  }

  def mostrarSaludo = println("Hola, mundo, desde funcion")

  val vmax = maximo(10, 34)
  println("maximo: " + vmax)

  mostrarSaludo

  val x: (Int, Int) => Int = maximo(_,_) // No se especifican los argumentos

  val res2 = x(23, 11)
  println("res2: " + res2)

  // uso de bloques: delimitados por llaves {}
  val cantidad = {val x = 5*20; x+10}
  println("cantidad: " + cantidad)

  val cantidad2 = {val x = 5*20
    x+10} // Se devuelve la última línea
  println("cantidad2: " + cantidad2)

  print({val a=1; {val b=a*2; {val c = b+4; c}}})

  val v1=10
  val v2=20

  val max: Boolean = if(v1 > v2) true else false
}
