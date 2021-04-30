package Declaracion

class Coche(val marca: String, var enUso: Boolean){
    def reservar(flag: Boolean) = {
        enUso = flag
    }

    override def toString: String = s"Marca: $marca, en uso: $enUso"
}

class Renault(val color: String, enUso: Boolean) extends Coche (marca="Renault", enUso){
    override def toString: String = super.toString + ", color: " + color
}

object DeclaracionCoche extends App{

}
