package com.example.musicplayer

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.*

enum class CancionType {
    cancion
}

class MainActivity : AppCompatActivity() {

    //Creamos las variables
    private lateinit var mp: MediaPlayer
    private var totalTime: Int = 0
    private lateinit var volumenSeekbar: SeekBar
    private lateinit var posicionSeekBar: SeekBar
    private lateinit var btnPlay: Button
    private lateinit var btnLogOut: Button
    private lateinit var btnLastSong: Button
    private lateinit var btnNextSong: Button
    private lateinit var elapsedTimeTextView: TextView
    private lateinit var remainingTimeTextView: TextView
    private lateinit var emailTxtView: TextView
    private lateinit var cancionTxtView: TextView
    private var resId = 0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializamos las variables
        val s1 = R.raw.cartaadios
        val s2 = R.raw.crazy
        val s3 = R.raw.halloffame
        val s4 = R.raw.human
        val s5 = R.raw.itsmylife
        val s6 = R.raw.lightbringer
        val s7 = R.raw.riptide
        val s8 = R.raw.safeandsound
        val s9 = R.raw.stolendance
        val s10 = R.raw.takemetochurch

        resId = s1
        mp = MediaPlayer.create(this, resId)
        mp.isLooping = true
        mp.setVolume(0.5f, 0.5f)
        totalTime = mp.duration
        volumenSeekbar = findViewById<View>(R.id.volumeBar) as SeekBar
        posicionSeekBar = findViewById<View>(R.id.positionBar) as SeekBar
        btnPlay = findViewById<View>(R.id.playBtn) as Button
        btnLogOut = findViewById<View>(R.id.logOutButton) as Button
        btnLastSong = findViewById<View>(R.id.lastBtn) as Button
        btnNextSong = findViewById<View>(R.id.nextBtn) as Button
        elapsedTimeTextView = findViewById<View>(R.id.elapsedTimeTxt) as TextView
        remainingTimeTextView = findViewById<View>(R.id.remainingTimeTxt) as TextView
        emailTxtView = findViewById<View>(R.id.emailTextView) as TextView
        cancionTxtView = findViewById<View>(R.id.cancionTextView) as TextView

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val cancion = bundle?.getString("cancion")

        //Setup
        setup(email ?: "", cancion ?: "")

        //Barra de volumen
        volumenSeekbar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        var volumeNum = progress / 100.0f
                        mp.setVolume(volumeNum, volumeNum)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            }
        )

        //Barra de posicion de la cancion
        posicionSeekBar.max = totalTime
        posicionSeekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        mp.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }
            }
        )

        //Thread para sacar la posicion de la barra
        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()

        btnLastSong.setOnClickListener {
            if (resId == s1){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s10))
                mp.prepare()
                mp.start()
                resId = s10
            }else
            if (resId == s10){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s9))
                mp.prepare()
                mp.start()
                resId = s9
            }else
            if (resId == s9){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s8))
                mp.prepare()
                mp.start()
                resId = s8
            }else
            if (resId == s8){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s7))
                mp.prepare()
                mp.start()
                resId = s7
            }else
            if (resId == s7){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s6))
                mp.prepare()
                mp.start()
                resId = s6
            }else
            if (resId == s6){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s5))
                mp.prepare()
                mp.start()
                resId = s5
            }else
            if (resId == s5){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s4))
                mp.prepare()
                mp.start()
                resId = s4
            }else
            if (resId == s4){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s3))
                mp.prepare()
                mp.start()
                resId = s3
            }else
            if (resId == s3){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s2))
                mp.prepare()
                mp.start()
                resId = s2
            }else
            if (resId == s2){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s1))
                mp.prepare()
                mp.start()
                resId = s1
            }
        }
        btnNextSong.setOnClickListener {
            if (resId == s1){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s2))
                mp.prepare()
                mp.start()
                resId = s2
            }else
            if (resId == s2){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s3))
                mp.prepare()
                mp.start()
                resId = s3
            }else
            if (resId == s3){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s4))
                mp.prepare()
                mp.start()
                resId = s4
            }else
            if (resId == s4){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s5))
                mp.prepare()
                mp.start()
                resId = s5
            }else
            if (resId == s5){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s6))
                mp.prepare()
                mp.start()
                resId = s6
            }else
            if (resId == s6){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s7))
                mp.prepare()
                mp.start()
                resId = s7
            }else
            if (resId == s7){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s8))
                mp.prepare()
                mp.start()
                resId = s8
            }else
            if (resId == s8){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s9))
                mp.prepare()
                mp.start()
                resId = s9
            }else
            if (resId == s9){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s10))
                mp.prepare()
                mp.start()
                resId = s10
            }else
            if (resId == s10){
                mp.stop()
                mp.reset()
                mp.setDataSource(resources.openRawResourceFd(s1))
                mp.prepare()
                mp.start()
                resId = s1
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.pause()
    }

    private fun setup(email: String, cancion: String){
        title = "Reproductor"
        emailTxtView.text = email
        cancionTxtView.text = cancion

        btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what

            //Actualiza el progreso de la barra
            posicionSeekBar.progress = currentPosition

            //Actualiza los textos de tiempo de la barra
            var elapsedTime = createTimeLabel(currentPosition)
            elapsedTimeTextView.text = elapsedTime
            var remainingTime = createTimeLabel(totalTime - currentPosition)
            remainingTimeTextView.text = "-$remainingTime"
        }
    }

    //Funcion para establecer el tiempo de las canciones
    fun createTimeLabel(time: Int): String{
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }

    //Funcion para iniciar la cancion y cambiar de iconos
    fun playBtnClick(v: View) {
        if (mp.isPlaying) {
            mp.pause()
            btnPlay.setBackgroundResource(R.drawable.play)
        } else {
            mp.start()
            btnPlay.setBackgroundResource(R.drawable.stop)
        }
    }

}