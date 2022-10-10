//Class Casella y sus métodos y atributos
class Casella {
    var minaMarcada: Boolean = false

    var estaDescoberta: Boolean = false

    var esMina: Boolean = false

    var minas: Int = 0

    //Función que devuelve si la casilla es una mina
    @JvmName("getEsMina1")
    fun getEsMina(): Boolean{
        return esMina
    }
    //Función que cambia el valor de la casilla a mina
    @JvmName("setEsMina1")
    fun setEsMina(value: Boolean){
        this.esMina = value
    }

    //Función que devuelve si la casilla está descubierta
    @JvmName("getDescoberta1")
    fun getDescoberta(): Boolean{
        return estaDescoberta
    }
    //Función que cambia el valor de la casilla a descubierta
    @JvmName("setDescoberta1")
    fun setDescoberta(value: Boolean){
        this.estaDescoberta = value
    }
    //Función que devuelve si la casilla está marcada como mina
    @JvmName("getMinaMarcada1")
    fun getMinaMarcada(): Boolean{
        return minaMarcada
    }
    //Función que cambia el valor de la casilla a marcada como mina
    @JvmName("setMarcaMina1")
    fun setMarcaMina(value: Boolean){
        this.minaMarcada = value
    }

    //Función que devuelve el número de minas
    @JvmName("getMines1")
    fun getMines(): Int{
        return minas
    }
    //Función que cambia el valor de la casilla al número de minas
    @JvmName("setMines1")
    fun setMines(value: Int){
        this.minas = value
    }
    //Funcion toString
    override fun toString(): String {
        if(!estaDescoberta && minaMarcada){
            return " * "
        }else if(estaDescoberta && esMina) {
            return " # "
        }else{
            return " · "
        }

    }


}