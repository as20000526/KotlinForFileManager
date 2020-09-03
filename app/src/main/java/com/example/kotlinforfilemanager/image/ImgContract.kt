package com.example.kotlinforfilemanager.image

public interface ImgContract{
    interface View {

    }

    interface Adapter{
        abstract fun onItemClick(position: Int)
        abstract  fun setDataList(datalist : ArrayList<ImgListModel>)


    }
}