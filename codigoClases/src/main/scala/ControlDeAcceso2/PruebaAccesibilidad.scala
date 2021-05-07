package ControlDeAcceso2

import ControlDeAcceso._

object PruebaAccesibilidad {
    // val conf = new Configuracion es inaccesible desde otro paquete
    val obj = new Autenticacion
}
