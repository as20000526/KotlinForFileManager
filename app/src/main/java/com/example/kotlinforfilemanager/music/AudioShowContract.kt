package com.example.kotlinforfilemanager.music

import com.example.kotlinforfilemanager.image.ImgListModel

interface AudioShowContract{
    interface View{

    }

    interface Adapter{
        abstract fun onItemClick(position: Int)
        abstract  fun setDataList(datalist : ArrayList<MusicListModel>)

    }
}