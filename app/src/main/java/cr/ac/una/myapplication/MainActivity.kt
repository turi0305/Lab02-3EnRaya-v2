package cr.ac.una.myapplication

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.os.Handler


class MainActivity : AppCompatActivity() {
    lateinit var jugar :Button
    lateinit var pos1: ImageButton
    lateinit var pos2: ImageButton
    lateinit var pos3: ImageButton
    lateinit var pos4: ImageButton
    lateinit var pos5: ImageButton
    lateinit var pos6: ImageButton
    lateinit var pos7: ImageButton
    lateinit var pos8: ImageButton
    lateinit var pos9: ImageButton
    var juegoService = JuegoService()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jugar = findViewById(R.id.jugar)

        pos1 = findViewById(R.id.pos1)
        pos2 = findViewById(R.id.pos2)
        pos3 = findViewById(R.id.pos3)
        pos4 = findViewById(R.id.pos4)
        pos5 = findViewById(R.id.pos5)
        pos6 = findViewById(R.id.pos6)
        pos7 = findViewById(R.id.pos7)
        pos8 = findViewById(R.id.pos8)
        pos9 = findViewById(R.id.pos9)

        enableDisableButton()
        jugar.setOnClickListener() {
            iniciarJuego()
            jugar.isEnabled =  !jugar.isEnabled

        }
        pos1.setOnClickListener(){
            seleccionafigura(it as ImageButton)

            muestraDialogo (juegoService.jugada(0,0))
            pos1.isEnabled =  !pos1.isEnabled


        }
        pos2.setOnClickListener(){
            seleccionafigura(it as ImageButton)

            muestraDialogo (juegoService.jugada(0,1))
            pos2.isEnabled =  !pos2.isEnabled


        }
        pos3.setOnClickListener(){
            seleccionafigura(it as ImageButton)

            muestraDialogo (juegoService.jugada(0,2))
            pos3.isEnabled =  !pos3.isEnabled


        }
        pos4.setOnClickListener(){
            seleccionafigura(it as ImageButton)

            muestraDialogo (juegoService.jugada(1,0))
            pos4.isEnabled =  !pos4.isEnabled


        }
        pos5.setOnClickListener(){
            seleccionafigura(it as ImageButton)

            muestraDialogo (juegoService.jugada(1,1))
            pos5.isEnabled =  !pos5.isEnabled


        }
        pos6.setOnClickListener(){
            seleccionafigura(it as ImageButton)

            muestraDialogo (juegoService.jugada(1,2))
            pos6.isEnabled =  !pos6.isEnabled

        }
        pos7.setOnClickListener(){
            seleccionafigura(it as ImageButton)

            muestraDialogo (juegoService.jugada(2,0))
            pos7.isEnabled =  !pos7.isEnabled

            }
        pos8.setOnClickListener() {
            seleccionafigura(it as ImageButton)

            muestraDialogo(juegoService.jugada(2, 1))
            pos8.isEnabled =  !pos8.isEnabled

        }
        pos9.setOnClickListener() {
            seleccionafigura(it as ImageButton)
            muestraDialogo(juegoService.jugada(2, 2))
            pos9.isEnabled =  !pos9.isEnabled

        }





    }
    fun iniciarJuego(){
        juegoService.inicializar()
        muestraDialogo("Inicia")
        reiniciarJuego()
        habilitarBotones()
    }
    private fun reiniciarJuego(){
        jugar.isEnabled = true
        juegoService.inicializar()
        pos1.setBackgroundResource(R.drawable.limpio1)
        pos2.setBackgroundResource(R.drawable.limpio1)
        pos3.setBackgroundResource(R.drawable.limpio1)
        pos4.setBackgroundResource(R.drawable.limpio1)
        pos5.setBackgroundResource(R.drawable.limpio1)
        pos6.setBackgroundResource(R.drawable.limpio1)
        pos7.setBackgroundResource(R.drawable.limpio1)
        pos8.setBackgroundResource(R.drawable.limpio1)
        pos9.setBackgroundResource(R.drawable.limpio1)
    }
    private fun habilitarBotones(){
        pos1.isEnabled =  true
        pos2.isEnabled =  true
        pos3.isEnabled =  true
        pos4.isEnabled =  true
        pos5.isEnabled =  true
        pos6.isEnabled =  true
        pos7.isEnabled =  true
        pos8.isEnabled =  true
        pos9.isEnabled =  true
    }
    private fun enableDisableButton(){
        pos1.isEnabled =  false
        pos2.isEnabled =  false
        pos3.isEnabled =  false
        pos4.isEnabled =  false
        pos5.isEnabled =  false
        pos6.isEnabled =  false
        pos7.isEnabled =  false
        pos8.isEnabled =  false
        pos9.isEnabled =  false
    }

    private fun seleccionafigura(imageButton: ImageButton){
        if (juegoService.figura == 'O')
            imageButton.setBackgroundResource(R.drawable.circulo)
        else
            imageButton.setBackgroundResource(R.drawable.cruz)
        imageButton.isEnabled=true
    }
    private fun muestraDialogo(mensaje: String){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        if (juegoService.verificarGanador().first!=false&& mensaje!="Inicia"){
            pintarGanador(juegoService.verificarGanador().second)
            builder.
            setTitle("Ganador: " + juegoService.figura)
                .setPositiveButton(" ") { dialog, which ->
                    enableDisableButton()
                    jugar.isEnabled =  true
                }
        }else if(juegoService.verificarEmpate()&& mensaje!="Comienza el juego"){
            builder.
            setTitle("Empate")
                .setPositiveButton(" ") { dialog, which ->
                    enableDisableButton()
                    jugar.isEnabled =  true
                }

        }else{
            builder
                .setMessage("Aviso")
                .setTitle(mensaje)


                }
        val dialog: AlertDialog = builder.create()
        dialog.show()
        val handler = Handler()
        handler.postDelayed({
            dialog.dismiss()
        }, 1000)
        }


    private fun pintarGanador(linea: String) {
        val buttons = when (linea) {
            "row0" -> listOf(pos1, pos2, pos3)
            "row1" -> listOf(pos4, pos5, pos6)
            "row2" -> listOf(pos7, pos8, pos9)
            "col0" -> listOf(pos1, pos4, pos7)
            "col1" -> listOf(pos2, pos5, pos8)
            "col2" -> listOf(pos3, pos6, pos9)
            "diag0" -> listOf(pos1, pos5, pos9)
            "diag1" -> listOf(pos3, pos5, pos7)
            else -> listOf()
        }

        for (button in buttons) {
            if (juegoService.figura == 'O')
                for(button in buttons)
                button.setBackgroundResource(R.drawable.circulo_rojo)
            else
                button.setBackgroundResource(R.drawable.cruz_roja)
        }

    }

}

