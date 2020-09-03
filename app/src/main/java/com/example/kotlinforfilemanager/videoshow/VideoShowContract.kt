
package com.example.kotlinforfilemanager.videoshow

import com.example.kotlinforfilemanager.image.ImgListModel

public interface VideoShowContract{
    interface View {

    }

    interface Adapter{
        abstract fun onItemClick(position: Int)
        abstract  fun setDataList(datalist : ArrayList<VideoListModel>)


    }
}