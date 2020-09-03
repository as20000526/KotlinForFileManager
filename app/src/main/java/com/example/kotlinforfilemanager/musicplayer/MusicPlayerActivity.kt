package com.example.kotlinforfilemanager.musicplayer

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.example.kotlinforfilemanager.R
import com.example.kotlinforfilemanager.image.ImgListModel
import com.example.kotlinforfilemanager.music.MusicListModel

class MusicPlayerActivity : AppCompatActivity() {

    lateinit var playBtn : Button;
    lateinit var positionBar: SeekBar;
    lateinit var  volumeBar:SeekBar;
    lateinit var elapsedTimeLabel: TextView;
    lateinit var remainingTimeLabel :TextView;
    lateinit var mp: MediaPlayer;
    var  totalTime:Int=0;
    var position : Int =0
    lateinit var msg: Message
    private  var dataList:ArrayList<MusicListModel> =ArrayList()
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player)

        playBtn =  findViewById(R.id.playBtn);
        elapsedTimeLabel =  findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel =  findViewById(R.id.remainingTimeLabel);

        // Media Player
        var bundle=intent.extras
        position=bundle!!.getInt("position")
        dataList=bundle.getSerializable("dataList") as ArrayList<MusicListModel>
        mp = MediaPlayer.create(this, Uri.parse(dataList.get(position).filePath));

        mp.setLooping(true);
        mp.seekTo(0);
        mp.setVolume(0.5f, 0.5f);
        totalTime = mp.getDuration();

        // Position Bar
        positionBar =  findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) {
                    mp.seekTo(p1);
                    positionBar.setProgress(p1);
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        // Volume Bar
        volumeBar =  findViewById(R.id.volumeBar);
        positionBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var  volumeNum : Float = p1 / 100f;
                mp.setVolume(volumeNum, volumeNum);
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        })


        Thread(Runnable {
            while (mp != null) {
                try {
                    msg  = Message();
                    msg.what = mp.getCurrentPosition();
                    handler.sendMessage(msg);
                    Thread.sleep(1000);
                } catch (e :InterruptedException ) {}
            }
        }).start()

    }
    val handler = object:  Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what;
            // Update positionBar.
            positionBar.setProgress(currentPosition);

            // Update Labels.
            var elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            var remainingTime = createTimeLabel(totalTime-currentPosition);
            remainingTimeLabel.setText("- " + remainingTime);
        }
    }


    fun  createTimeLabel( time : Int):String{
        var timeLabel = "";
        var min = time / 1000 / 60;
        var sec = time / 1000 % 60;

        timeLabel = min.toString()+ ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }


    fun playBtnClick(view: View){
        if (!mp.isPlaying()) {
            // Stopping
            mp.start();
            playBtn.setBackgroundResource(R.drawable.ic_launcher_foreground);

        } else {
            // Playing
            mp.pause();
            playBtn.setBackgroundResource(R.drawable.ic_launcher_foreground);
        }
    }


}