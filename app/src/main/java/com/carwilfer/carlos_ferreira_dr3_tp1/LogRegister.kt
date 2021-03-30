package com.carwilfer.carlos_ferreira_dr3_tp1

import android.content.Context

class LogRegister(private val context: Context) {
    //variavel com o nome do arquivo de log
    private val nomeArquivoLog = "logRegister.log"

    // funcao para screver em um arquivo
    fun escreverLog(msg: String){
        //openFileOutput
        val fileOutputStream = context.openFileOutput(nomeArquivoLog, Context.MODE_APPEND)
        fileOutputStream.write(msg.toByteArray())
        fileOutputStream.write("\n".toByteArray())
        fileOutputStream.close()

    }

    fun lerLog(){
        val fileInputStream = context.openFileInput(nomeArquivoLog).bufferedReader().readText()
    }

    companion object{
        private var instance: LogRegister? = null
        fun getInstance(context: Context): LogRegister{
            if(instance == null)
                instance = LogRegister(context)
            return instance as LogRegister
        }
    }
}