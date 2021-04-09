object Bucles extends App{
    // estructuras iterativas (sentencias) porque no devuelven nada
    // No suele usarse: el estilo de programación funcional desaconseja su uso

    var x = 10
    while(x > 0){
        println(x)
        x = x-1
    }

    x = 10
   do{
        println(x)
        x = x-1
    } while(x > 0)
}
