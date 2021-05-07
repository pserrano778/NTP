package ClasesCase

abstract class Notificacion

// Las clases case tienen como características:
// - los datos miembro son val (aunque no se ponga nada)
// - cada clase case dispone de un companion object con varios métodos diponibles (entre ellos apply)
// Son utiles cuando se van a descomponer
case class CorreoElectronico(direccion: String, titulo: String, cuerpo: String) extends Notificacion

case class MensajeSMS(numero: String, mensaje: String) extends Notificacion

case class MensajeVoz(contacto: String, enlace: String) extends Notificacion

object PruebaNotificacion extends App {
    val mensajeSMS = MensajeSMS("123456", "Por favor, llama....") // No hay new
    val mensajeCorreo = CorreoElectronico("alberto@gmail.com", "AvisoUrgenete", "Conferencia suspendida")
    val mensajeVoz = MensajeVoz("Tomas", "http`://voiceService.ord/id/123")

    def mostrarNotificacion(notificacion: Notificacion) : String = {
        notificacion match{
            case CorreoElectronico(direccion, titulo, cuerpo) =>
                "Recibido correo de: " + direccion + " titulo: " + titulo + " cuerpo: " + cuerpo
            case MensajeSMS(numero, mensaje) => {
                "Recibido SMS de numero: " + numero + " mensaje: " + mensaje
            }
            case MensajeVoz(contacto, enlace) =>
                "Recibido mensaje de voz de : " + contacto + " enlace para escucharlo: " + enlace
        }
    }

    val resultado = mostrarNotificacion(mensajeSMS)
    println("resultado: " + resultado)
}
