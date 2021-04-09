object ExpresionesMatch extends App{
  val x = 100
  val y = 50

  // Expresion match para implementar el maximo
  val maximo = x > y match {
    case true => x
    case false => y
  }

  println("maximo: " + maximo)

  val error=800
  val mensaje = error match {
    case 200 => "funcionamiento correcto"
    case 400 => {
      println("Error de ejecucion")
      "Error 400: error de ejecucion"
    }
    case _ => "Error no idenfiticado"
  }

  println("mensaje: " + mensaje)

  val dia="lunes"

  val laborable = dia match {
    case "lunes" | "martes" | "miercoles" | "jueves" | "viernes" => "laborable"
    case _ => "festivo"
  }

  val estado = mensaje match {
    case "OK" => 200
    case otro => { // como _ pero asignandole un nombre
      println("cadena usada " + otro)
      -1
    }
  }

  val w = 12345
  val z : Any = w

  val result = z match {
    case z:String => s"tipo string - $z"
    case z:Double => f"tipo double - $z%.2f"
    case z:Float => f"tipo float - $z%.2f"
    case z:Int => s"tipo entero - $z"
  }

  println("resultado del match: " + result)
}
