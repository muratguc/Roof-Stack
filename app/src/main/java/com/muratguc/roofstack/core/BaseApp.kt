package com.muratguc.roofstack.core

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Murat Güç on 2/1/2021.
 */
@HiltAndroidApp
class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initLogger()
    }

    private fun initLogger() {
        Timber.plant(object : Timber.DebugTree() {
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                Logger.log(priority, tag, message, t)
            }
        })

        Logger.addLogAdapter(AndroidLogAdapter())
    }

    companion object {
        lateinit var instance: BaseApp
            private set
    }
}