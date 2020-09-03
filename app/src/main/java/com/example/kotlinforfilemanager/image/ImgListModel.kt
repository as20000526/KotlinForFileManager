package com.example.kotlinforfilemanager.image

import java.io.Serializable

data class ImgListModel( var fileName : String="",
                         var filePath : String ="",
                         var fileSize : String ="",
                         var fileCreatedTime :String = "",
                         var position : Int ?=0) : Serializable