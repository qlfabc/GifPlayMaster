package com.bluesfantasy.april.gifplaymaster

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by Blue on 2017/7/6.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}