package com.codeblin.objectpreference

import android.app.Application
import com.codeblin.objectpreference.models.SharedPrefManager

class SampleApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        SharedPrefManager.initialize(this)
    }
}