package com.example.kotlinforfilemanager.videobig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.DataBinderMapperImpl
import com.example.kotlinforfilemanager.R
import com.example.kotlinforfilemanager.databinding.ActivityVideoBigBinding
import com.example.kotlinforfilemanager.image.ImgListModel
import com.example.kotlinforfilemanager.videoshow.VideoListModel

class VideoBigActivity : AppCompatActivity(),VideoBigContract.View {
    lateinit var binding : ActivityVideoBigBinding;
    var position : Int =0
    private  var dataList:ArrayList<VideoListModel> =ArrayList()
    lateinit var mController : MediaController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_big)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_video_big)
        binding.view=this
        mController= MediaController(this)
        var bundle=intent.extras
        position=bundle!!.getInt("position")
        dataList=bundle.getSerializable("dataList") as ArrayList<VideoListModel>
        binding.videoView.setVideoPath(dataList.get(position).filePath)
        mController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mController)
        binding.videoView.start()
    }
}