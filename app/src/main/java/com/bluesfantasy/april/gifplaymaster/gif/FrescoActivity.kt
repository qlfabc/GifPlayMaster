package com.bluesfantasy.april.gifplaymaster.gif

import android.os.Bundle
import android.app.Activity
import android.graphics.drawable.Animatable
import android.net.Uri
import com.bluesfantasy.april.gifplaymaster.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.imagepipeline.animated.base.AbstractAnimatedDrawable
import com.facebook.imagepipeline.image.ImageInfo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_fresco.*
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit

class FrescoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresco)

        controlWithFresco()

    }

    val loopCount = 5
    private fun controlWithFresco() {
        val controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse("res:///" + R.drawable.cat_2))
                .setAutoPlayAnimations(true)
                .setControllerListener(object : BaseControllerListener<ImageInfo?>() {
                    override fun onFinalImageSet(id: String?, imageInfo: ImageInfo?, animatable: Animatable?) {
                        Observable.just(animatable).flatMap(Function<Animatable?, Observable<String?>> {
                            var duration: Long = 0
                            if (animatable is AbstractAnimatedDrawable) {
                                duration = animatable.duration.toLong()
                                animatable.start()
                            }
                            return@Function Observable.just("Play Over").delay(duration * loopCount,
                                    TimeUnit.MILLISECONDS).repeat(1)
                        }).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    //播放完成进行后续操作
                                    toast(it)
                                    animatable?.stop()
                                })
                    }
                })
                .build()
        gif_view.controller = controller
    }
}
