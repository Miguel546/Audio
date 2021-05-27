package com.luismiguel.audio

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import java.io.IOException

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {
    var buttonGrabar: Button ? = null
    var buttonReproducir: Button ? = null
    var buttonReproducirSonido: Button ? = null
    var mp : MediaPlayer ? = null
    val CODIGO_GRABAR = 1
    var uri : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonGrabar = findViewById(R.id.btnGrabar)
        buttonReproducir = findViewById(R.id.btnReproducir)
        buttonReproducirSonido = findViewById(R.id.btnReproducirSonido)
        buttonGrabar?.setOnClickListener{
            grabar()
        }
        buttonReproducir?.setOnClickListener{
            reproducirGrabacion()
        }

        buttonReproducirSonido?.setOnClickListener{
            sonido()
        }
    }

    fun sonido(){
        val mediaPlayer = MediaPlayer.create(this, R.raw.happy).start()
    }

    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }

    fun grabar(){
        val intent = Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION)
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CODIGO_GRABAR);
        } else {
            Toast.makeText(this, "No sound record application found", Toast.LENGTH_SHORT).show();
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CODIGO_GRABAR && resultCode == Activity.RESULT_OK){
            uri = data?.data
        }
    }

    fun reproducirGrabacion(){
        val mp = MediaPlayer.create(this, uri)
        mp.start()
    }
}