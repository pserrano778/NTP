package ControlDeAcceso

// Privado a nivel de paquete
private [ControlDeAcceso] class Configuracion{
    val url = "http://localhost"
}
class Autenticacion {
    // control a nivel de objeto
    private [this] val password = "1234"

    def validar = password.size > 8

    def comparar(otro : Autenticacion) : Boolean = {
       true //password == otro.password privado para this
    }
}
