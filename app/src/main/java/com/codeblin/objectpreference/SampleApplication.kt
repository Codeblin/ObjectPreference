package com.codeblin.objectpreference

import android.app.Application

class SampleApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        SharedPrefManager.initialize(this)
    }
}