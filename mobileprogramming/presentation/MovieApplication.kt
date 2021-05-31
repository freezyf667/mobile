package com.example.mobileprogramming.presentation

import android.app.Application
import android.content.Context

class MovieApplication : Application(){
    companion object{
        var context: Context? =null
    }
    override fun onCreate() {
        super.onCreate()
        context = this
    }
}