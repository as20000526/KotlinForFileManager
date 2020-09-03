package com.example.kotlinforfilemanager.image

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinforfilemanager.base.ViewHolder
import com.example.kotlinforfilemanager.databinding.ItemImglistBinding
import com.example.kotlinforfilemanager.imgbig.ImgBigActivity
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import com.example.kotlinforfilemanager.BR
import com.example.kotlinforfilemanager.R
import kotlin.math.log


class ImgShowAdapter : RecyclerView.Adapter<ImgShowAdapter.ViewHolder<ItemImglistBinding>>, ImgContract.Adapter {

    private lateinit var  binding : ItemImglistBinding
    private  var dataList:ArrayList<ImgListModel> =ArrayList()
    internal val THUMB_SIZE = 64
    private  var context:Context?
    constructor(context: Context?)  {
        this.context= context
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ItemImglistBinding> {
        var  layoutInflator : LayoutInflater = LayoutInflater.from(parent.context)
        binding=ItemImglistBinding.inflate(layoutInflator,parent,false)
        binding.view=this
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder<ItemImglistBinding>, position: Int) {
        dataList.get(position).position=position

        Log.d("fuck", "Hello Kotlin"+dataList.get(position).filePath)
        val ThumbImage = ThumbnailUtils.extractThumbnail(
            BitmapFactory.decodeFile(dataList.get(position).filePath),
            THUMB_SIZE, THUMB_SIZE
        )
        holder.imgItemIcon.setImageBitmap(ThumbImage)
        holder.bind(dataList.get(position))
    }


    override fun getItemCount(): Int {
        var size:Int=if (dataList.size!=0) dataList.size else 0
        return  size
    }

    override fun setDataList(datalist: ArrayList<ImgListModel>){
        dataList=datalist
    }

    override fun onItemClick(position: Int) {
        val intent1  = Intent(context, ImgBigActivity::class.java)
        var bundle  = Bundle()
        bundle.putInt("position",position)
        bundle.putSerializable("dataList",dataList)
        intent1.putExtras(bundle)
        context?.startActivity(intent1)
    }
    class ViewHolder<B : ViewDataBinding>(private val mViewDataBinding: B) :
        RecyclerView.ViewHolder(mViewDataBinding.root) {
        var imgItemIcon: ImageView=mViewDataBinding.root.findViewById(R.id.icon)
        // why `` ?
        fun bind(`object`: Any) {
            mViewDataBinding.setVariable(BR.data, `object`)
            mViewDataBinding.executePendingBindings()
        }

    }
}