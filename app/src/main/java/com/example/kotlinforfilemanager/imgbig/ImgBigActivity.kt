package com.example.kotlinforfilemanager.imgbig

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinforfilemanager.R
import com.example.kotlinforfilemanager.databinding.ActivityImgBigBinding
import com.example.kotlinforfilemanager.image.ImgListModel

class ImgBigActivity : AppCompatActivity() ,ImgBigContract.View {


    lateinit var binding :ActivityImgBigBinding
    var position : Int =0
    private  var dataList:ArrayList<ImgListModel> =ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img_big)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_img_big)
        binding.view=this
        var bundle=intent.extras
        position=bundle!!.getInt("position")
        dataList=bundle.getSerializable("dataList") as ArrayList<ImgListModel>
        var adapter =  FullScreenImageAdapter(this,dataList)
        binding.pager.adapter=adapter
        binding.pager.currentItem=position
        //  binding.pager.adapter=
    }

    override fun onBackClick() {
        finish()
    }
}