package com.luismiguel.audio

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import java.io.IOException

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {
    var buttonGrabar: Button ? = null
    var buttonReproducir: Button ? = null
    var mp : MediaPlayer ? = null
    val CODIGO_GRABAR = 50
    var uri : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonGrabar = findViewById(R.id.btnGrabar)
        buttonReproducir = findViewById(R.id.btnReproducir)
        buttonGrabar?.setOnClickListener{
            grabar()
        }
        buttonReproducir?.setOnClickListener{
            reproducirGrabacion()
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp!!.start()
    }

    fun grabar(){
        val intent = Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION)
        startActivityForResult(intent, CODIGO_GRABAR)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CODIGO_GRABAR && resultCode == Activity.RESULT_OK){
            uri = data!!.data
        }
    }

    fun reproducirGrabacion(){
        val mp = MediaPlayer.create(this, uri)
        mp.start()
    }
}