package com.bluesfantasy.april.gifplaymaster.gif

import android.app.Activity
import android.os.Bundle
import com.bluesfantasy.april.gifplaymaster.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import kotlinx.android.synthetic.main.activity_glide.*
import org.jetbrains.anko.ctx
import java.lang.Exception
import java.util.concurrent.TimeUnit
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast


class GlideActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

//        loadWithGlide()
        controlWithGlide()
    }

    private fun loadWithGlide() {
        Glide.with(ctx).load(R.drawable.cat_1).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(gif_view)
    }


    val loopCount: Int = 2
    private fun controlWithGlide() {
        Glide.with(ctx).load(R.drawable.cat_1).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(object : RequestListener<Int, GifDrawable> {
                    override fun onException(e: Exception?, model: Int?, target: Target<GifDrawable>?,
                                             isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: GifDrawable?, model: Int?, target: Target<GifDrawable>?,
                                                 isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        Observable.just(resource).flatMap(Function<GifDrawable?, Observable<GifDrawable>> {
                            val decoder = it.decoder
                            val frameCount = it.frameCount
                            //计算GIF时长
                            val duration = (1..frameCount)
                                    .map { decoder.getDelay(it).toLong() }
                                    .sum()
                            return@Function Observable.just(it).delay(duration * loopCount,
                                    TimeUnit.MILLISECONDS).repeat(1)//可根据需要修改重复次数
                        }).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    //播放完成进行后续操作
                                    it.stop()
                                    toast("Play Over")
                                })
                        return false
                    }
                }).into(gif_view)
    }
}


