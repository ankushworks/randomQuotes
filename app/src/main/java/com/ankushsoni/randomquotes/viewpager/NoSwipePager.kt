package com.ankushsoni.randomquotes.viewpager

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import android.view.MotionEvent


class NoSwipePager(context: Context?, attrs: AttributeSet?) :
    ViewPager(context!!, attrs) {

    private var enabled : Boolean? = null

    init {
        enabled = true
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (enabled == true) {
            super.onTouchEvent(event)
        } else false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (enabled == true) {
            super.onInterceptTouchEvent(event)
        } else false
    }

    fun setPagingEnabled(enabled: Boolean) {
        this.enabled = enabled
    }
}