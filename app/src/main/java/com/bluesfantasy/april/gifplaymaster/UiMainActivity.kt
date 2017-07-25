package com.bluesfantasy.april.gifplaymaster

import android.view.Gravity
import android.view.View
import com.bluesfantasy.april.gifplaymaster.gif.FrescoActivity
import com.bluesfantasy.april.gifplaymaster.gif.GIFDrawableActivity
import com.bluesfantasy.april.gifplaymaster.gif.GlideActivity
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by Blue on 2017/7/25.
 */
class UiMainActivity : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        verticalLayout {
            gravity = Gravity.CENTER
            button("Glide") {
                onClick {
                    startActivity<GlideActivity>()
                }
            }
            button("Fresco") {
                onClick {
                    startActivity<FrescoActivity>()
                }
            }
            button("Android-Gif-Drawable") {
                onClick {
                    startActivity<GIFDrawableActivity>()
                }
            }
        }
    }
}
