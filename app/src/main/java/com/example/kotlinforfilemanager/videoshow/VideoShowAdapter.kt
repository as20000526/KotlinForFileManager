package com.example.kotlinforfilemanager.videoshow

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.ThumbnailUtils
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinforfilemanager.BR
import com.example.kotlinforfilemanager.R
import com.example.kotlinforfilemanager.databinding.ItemVideoshowBinding
import com.example.kotlinforfilemanager.image.ImgListModel
import com.example.kotlinforfilemanager.imgbig.ImgBigActivity
import com.example.kotlinforfilemanager.videobig.VideoBigActivity

class VideoShowAdapter : RecyclerView.Adapter<VideoShowAdapter.ViewHolder<ItemVideoshowBinding>>,VideoShowContract.Adapter{



    private  var dataList:ArrayList<VideoListModel> =ArrayList()
    var context :Context?
    lateinit var binding: ItemVideoshowBinding

    constructor(context: Context?)  {
        this.context= context
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ItemVideoshowBinding> {
        var inflater : LayoutInflater= LayoutInflater.from(context)
        binding=ItemVideoshowBinding.inflate(inflater,parent,false)
        binding.view=this
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        var size:Int=if (dataList.size!=0) dataList.size else 0
        return  size
    }

    override fun onBindViewHolder(holder: ViewHolder<ItemVideoshowBinding>, position: Int) {
        dataList.get(position).position=position
        holder.bind(dataList.get(position))


        // 拿影片縮圖 但是很卡要找方法
        val videoBitmap = ThumbnailUtils.createVideoThumbnail(
            dataList.get(position).filePath,
            MediaStore.Video.Thumbnails.MICRO_KIND
        )
        holder.videoItemIcon.setImageBitmap(videoBitmap)
    }


    override fun setDataList(datalist: ArrayList<VideoListModel>) {
        this.dataList=datalist
    }

    override fun onItemClick(position: Int) {
        val intent1  = Intent(context, VideoBigActivity::class.java)
        var bundle  = Bundle()
        bundle.putInt("position",position)
        bundle.putSerializable("dataList",dataList)
        intent1.putExtras(bundle)
        context?.startActivity(intent1)
    }
    class ViewHolder<B : ViewDataBinding>(private val mViewDataBinding: B) :
        RecyclerView.ViewHolder(mViewDataBinding.root) {
        var videoItemIcon: ImageView =mViewDataBinding.root.findViewById(R.id.icon)
        // why `` ?
        fun bind(`object`: Any) {
            mViewDataBinding.setVariable(BR.data, `object`)
            mViewDataBinding.executePendingBindings()
        }

    }

}