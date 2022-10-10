//Class Tauler y sus métodos y atributos
class Tauler(var n: Int = 0, var nm: Int = 0) {

    var matriz  = Array(n){Array<Casella>(n){Casella()}}

    //Función que muestra el tablero
    fun mostraTauler(){
        if(matriz.size > 10){
            for(top in 0..matriz.size-1){
                if(top == 0){
                    print("   $top  ")
                }else if(top < 10){
                    print(" $top  ")
                }else{
                    print(" $top ")
                }

            }
            println()
            for (i in 0..matriz.size-1){
                print(i)
                for(j in 0..matriz.size-1){
                    if(!matriz[i][j].esMina && matriz[i][j].getDescoberta()) {
                        if(i < 10){
                            print("  " + comptaMines(i, j) + " ")
                        }else{
                            print(" " + comptaMines(i, j) + "  ")
                        }
                    }else if(i < 10){
                        print(" " + matriz[i][j])
                    }else{
                        print("${matriz[i][j]} ")
                    }
                }
                println()
            }
        }else{
            for(top in 0..matriz.size-1){
                if(top == 0){
                    print("  $top ")
                }else{
                    print(" $top ")
                }

            }
            println()
            for (i in 0..matriz.size-1){
                print(i)
                for(j in 0..matriz.size-1){
                    if(!matriz[i][j].esMina && matriz[i][j].getDescoberta()) {
                        print(" " + comptaMines(i, j) + " ")
                    }else{
                        print(matriz[i][j])
                    }
                }
                println()
            }
        }

    }

    //Función que genera las minas en el tablero
    fun posaMines(nm: Int){
        var mines = 0
        while(mines < nm){
            var fila = (0..matriz.size-1).random()
            var columna = (0..matriz.size-1).random()
            if(!matriz[fila][columna].esMina){
                matriz[fila][columna].esMina = true
                mines++
            }
        }
    }

    //Función que devuelve el número de minas que hay alrededor de una casilla
    fun comptaMines(x: Int, y: Int): Int{
        var minas = 0
        var top = x-1
        var bottom = x+1
        var left = y-1
        var right = y+1
        if(top < 0){
            top = 0
        }
        if(bottom > n-1){
            bottom = n-1
        }
        if(left < 0){
            left = 0
        }
        if(right > n-1){
            right = n-1
        }
        for(i in top..bottom){
            for(j in left..right){
                if(matriz[i][j].esMina){
                    minas++
                }
            }
        }
        return minas
    }

    //Función que indica si hay una mina en la casilla
    fun hiHaMina(x: Int, y: Int): Boolean{
        return matriz[x][y].getEsMina()
    }

    //Función que indica si el tablero está descubierto
    fun descobert(): Boolean{
        for (i in 0..n-1){
            for (j in 0..n-1){
                if(!matriz[i][j].esMina && !matriz[i][j].getDescoberta()){
                    return false
                }
            }
        }
        return true
    }
    //Función que descubre el tablero entero
    fun descobreixTauler(){
        for (i in 0..matriz.size-1) {
            for (j in 0..matriz.size - 1) {
                matriz[i][j].setDescoberta(true)
            }
        }
    }
    // Función que descubre una casilla y todas las posibles alrededor de ella
    fun descobreixCasella(x: Int, y: Int){
        matriz[x][y].setDescoberta(true)
        var top = x-1
        var bottom = x+1
        var left = y-1
        var right = y+1
        if(top < 0){
            top = 0
        }
        if(bottom > n-1){
            bottom = n-1
        }
        if(left < 0){
            left = 0
        }
        if(right > n-1){
            right = n-1
        }
        if(comptaMines(x, y) == 0){
            for(i in top..bottom){
                for(j in left..right){
                    if(!matriz[i][j].getDescoberta()){
                        descobreixCasella(i, j)
                    }
                }
            }
        }
    }
    //Función que comprueba si una casiila está descubierta
    fun descoberta(x: Int, y: Int): Boolean{
        return matriz[x][y].getDescoberta()
    }
    //Función que marca una casilla como mina
    fun marcaMina(x: Int, y: Int){
        if(!minaMarcada(x, y) && !descoberta(x,y)){
            matriz[x][y].setMarcaMina(true)
        }else{
            matriz[x][y].setMarcaMina(false)
        }

    }
    //Funcion que indica si una casilla está marcada como mina
    fun minaMarcada(x: Int, y: Int): Boolean{
        return matriz[x][y].getMinaMarcada()
    }

    //Función toString
    override fun toString() : String{
        return " · "
    }
}