package com.example.kotlinforfilemanager.internal_storage

import androidx.databinding.ObservableField

class InternalStorageFilesModel {

    var extension :String ?=""
    var fileName: String? = ""
    var filePath: String = ""
    var isSelected: Boolean = false
    private var isDir: Boolean = false
    var isCheckboxVisible: Boolean = false
    var isChecked : Boolean =false
    var  imgViewResource :Int ?= null
    var position : Int ?=0

    constructor() {}


    fun setDir(dir: Boolean) {
        isDir = dir
    }


    fun setIsDir(isDir: Boolean) {
        this.isDir = isDir
    }
}