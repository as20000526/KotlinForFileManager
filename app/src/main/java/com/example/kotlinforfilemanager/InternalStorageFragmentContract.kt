package com.example.kotlinforfilemanager

interface InternalStorageFragmentContract {
    interface View
    interface Adapter {
        fun onItemClick(position: Int)
    }
}