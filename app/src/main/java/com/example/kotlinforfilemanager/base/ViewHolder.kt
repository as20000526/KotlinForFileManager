package com.example.kotlinforfilemanager.base


import androidx.databinding.ViewDataBinding

import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinforfilemanager.BR


class ViewHolder<B : ViewDataBinding>(private val mViewDataBinding: B) :
    RecyclerView.ViewHolder(mViewDataBinding.root) {

    // why `` ?
    fun bind(`object`: Any) {
        mViewDataBinding.setVariable(BR.data, `object`)
        mViewDataBinding.executePendingBindings()
    }

}