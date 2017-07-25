package com.bluesfantasy.april.gifplaymaster

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.setContentView

/**
 * Created by Blue on 2017/7/25.
 */
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UiMainActivity().setContentView(this)
    }

}