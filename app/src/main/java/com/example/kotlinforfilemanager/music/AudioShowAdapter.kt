package com.example.kotlinforfilemanager.music

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinforfilemanager.BR
import com.example.kotlinforfilemanager.R
import com.example.kotlinforfilemanager.databinding.ItemImglistBinding
import com.example.kotlinforfilemanager.databinding.ItemMusiclistBinding
import com.example.kotlinforfilemanager.image.ImgListModel
import com.example.kotlinforfilemanager.image.ImgShowAdapter
import com.example.kotlinforfilemanager.imgbig.ImgBigActivity
import com.example.kotlinforfilemanager.musicplayer.MusicPlayerActivity
import com.example.kotlinforfilemanager.videoshow.VideoListModel
import com.example.kotlinforfilemanager.videoshow.VideoShowContract


class AudioShowAdapter : RecyclerView.Adapter<ImgShowAdapter.ViewHolder<ItemMusiclistBinding>>, AudioShowContract.Adapter {


    private lateinit var  binding : ItemMusiclistBinding
    private  var dataList:ArrayList<MusicListModel> =ArrayList()
    private  var context: Context?
    constructor(context: Context?)  {
        this.context= context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgShowAdapter.ViewHolder<ItemMusiclistBinding> {
        var  layoutInflator : LayoutInflater = LayoutInflater.from(parent.context)
        binding=ItemMusiclistBinding.inflate(layoutInflator,parent,false)
        binding.view=this
        return ImgShowAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        var size:Int=if (dataList.size!=0) dataList.size else 0
        return  size
    }

    override fun onBindViewHolder(
        holder: ImgShowAdapter.ViewHolder<ItemMusiclistBinding>,
        position: Int
    ) {
        dataList.get(position).position=position

//        Log.d("fuck", "Hello Kotlin"+dataList.get(position).filePath)
//        val ThumbImage = ThumbnailUtils.extractThumbnail(
//            BitmapFactory.decodeFile(dataList.get(position).filePath),
//            THUMB_SIZE, THUMB_SIZE
//        )
        holder.imgItemIcon.setImageDrawable(context!!.resources.getDrawable(R.drawable.ic_launcher_foreground))
        holder.bind(dataList.get(position))
    }

    override fun onItemClick(position: Int) {
        val intent1  = Intent(context, MusicPlayerActivity::class.java)
        var bundle  = Bundle()
        bundle.putInt("position",position)
        bundle.putSerializable("dataList",dataList)
        intent1.putExtras(bundle)
        context?.startActivity(intent1)
    }

    override fun setDataList(datalist: ArrayList<MusicListModel>) {
        dataList=datalist
    }

    class ViewHolder<B : ViewDataBinding>(private val mViewDataBinding: B) :
        RecyclerView.ViewHolder(mViewDataBinding.root) {
        var musicItemIcon: ImageView =mViewDataBinding.root.findViewById(R.id.icon)
        // why `` ?
        fun bind(`object`: Any) {
            mViewDataBinding.setVariable(BR.data, `object`)
            mViewDataBinding.executePendingBindings()
        }

    }
}