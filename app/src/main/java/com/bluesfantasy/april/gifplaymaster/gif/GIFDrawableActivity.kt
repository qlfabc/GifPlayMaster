package com.bluesfantasy.april.gifplaymaster.gif

import android.app.Activity
import android.os.Bundle
import com.bluesfantasy.april.gifplaymaster.R
import kotlinx.android.synthetic.main.activity_gif_drawable.*
import org.jetbrains.anko.toast
import pl.droidsonroids.gif.GifDrawable

/**
 * Created by Blue on 2017/7/25.
 */
class GIFDrawableActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_drawable)

        controlWithAndroidGifDrawable()

    }

    val loop: Int = 2;

    private fun controlWithAndroidGifDrawable() {
        val gifDrawable: GifDrawable = GifDrawable(resources, R.drawable.cat_3)
        gifDrawable.loopCount = loop
        gifDrawable.addAnimationListener {
            if (it >= loop - 1) {
                //播放完成后续操作
                toast("Play Over")
            }
        }
        gif_view.setImageDrawable(gifDrawable)
    }


}