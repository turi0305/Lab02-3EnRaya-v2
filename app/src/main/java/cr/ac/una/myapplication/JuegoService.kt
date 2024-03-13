package cr.ac.una.myapplication


class JuegoService  {
    val matriz = Array(3) { CharArray(3) }
    var figura: Char = 'X'

    /**
     * @return X gana, O gana, Empate, Sigue X, Sigue O
     */
    fun jugada(x:Int, y:Int): String{
        matriz[x][y]= figura
        if (verificarGanador().first){
            return ""+ figura
        }
        if (figura == 'X')
            figura = 'O'
        else
            figura = 'X'
        return "Turno de "+ figura
    }
    fun inicializar(){
        matriz[0][0] = ' '
        matriz[0][1] = ' '
        matriz[0][2] = ' '
        matriz[1][0] = ' '
        matriz[1][1] = ' '
        matriz[1][2] = ' '
        matriz[2][0] = ' '
        matriz[2][1] = ' '
        matriz[2][2] = ' '


    }
    fun verificarEmpate(): Boolean {
        for (fila in matriz) {
            for (celda in fila) {
                if (celda == ' ') {
                    return false
                }
            }
        }
        return true
    }
    fun verificarGanador(): Pair<Boolean, String> {
        // Verificar filas
        for (i in 0 until 3) {
            if (matriz[i][0] == matriz[i][1] && matriz[i][1] == matriz[i][2] && matriz[i][0] != ' ') {
                return Pair(true,"row"+i)
            }
        }

        // Verificar columnas
        for (i in 0 until 3) {
            if (matriz[0][i] == matriz[1][i] && matriz[1][i] == matriz[2][i] && matriz[0][i] != ' ') {
                return Pair(true,"col"+i)
            }
        }

        // Verificar diagonal principal
        if (matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2] && matriz[0][0] != ' ') {
            return Pair(true,"diag0")
        }

        // Verificar diagonal secundaria
        if (matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0] && matriz[0][2] != ' ') {
            return Pair(true,"diag1")
        }

        // Si no hay ganador
        return Pair(false,"")
    }


}