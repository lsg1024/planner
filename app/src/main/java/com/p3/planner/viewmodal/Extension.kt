package com.p3.planner.viewmodal

import android.view.View

fun View.isVisible(isShowingLoading : Boolean, container : View){
    if (isShowingLoading) {
        this.visibility = View.VISIBLE
        container.visibility = View.GONE
    } else {
        this.visibility = View.GONE
        container.visibility = View.VISIBLE
    }
}