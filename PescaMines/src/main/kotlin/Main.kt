fun main(args: Array<String>) {
    print("Introduce la medida del tablero, número entre 1 y 26: ")
    var mida: Int = Integer.valueOf(readLine())
    while(mida !in 1..26){
        println("Error: Por favor, un número entre 1 y 26: ")
        mida = Integer.valueOf(readLine())
    }
    print("Introduce el número de minas, número entre 1 y ${(mida*mida) / 2}: ")
    var mines: Int = Integer.valueOf(readLine())
    while (mines !in 1..(mida*mida)/2){
        print("Error: Por favor, un número entre 1 y ${(mida*mida) / 2}: ")
        mines = Integer.valueOf(readLine())
    }
    //Inicializamos el tablero
    val tablero = Tauler(mida, mines)

    //Ponemos minas
    tablero.posaMines(mines)
    while(true){
        //Mostramos el tablero
        tablero.mostraTauler()
        println("Indique la celda a descubrir")
        print("Fila: ")
        var fila = Integer.valueOf(readLine())
        print("Columna: ")
        var columna = Integer.valueOf(readLine())
        print("Quieres marcarla como mina? (s/n): ")
        var marca = readLine()
        if(marca == "s"){ //Si la respuesta es igual a 's' marcará la casilla como mina, de otro modo la descubrirá
            tablero.marcaMina(fila, columna)
        }else{
            tablero.descobreixCasella(fila, columna) //Descubre la casilla en caso de no ser marcada como Mina
        }
        if(tablero.hiHaMina(fila, columna) && !tablero.minaMarcada(fila, columna)){ // Comprueba si no hay mina y si no está marcada como mina la casilla
            tablero.descobreixTauler() // Descubrirá el tablero
            tablero.mostraTauler()  //Mostrará el tablero
            println("Has perdido")
            break
        }else {
            if (tablero.descobert()) { //Comprueba si el tablero está descubierto
                tablero.mostraTauler() // Mostrará el tablero
                println("Has ganado")
                break
            }
        }
    }
}