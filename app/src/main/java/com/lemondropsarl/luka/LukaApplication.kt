@file:Suppress("ProtectedInFinal", "unused")

package com.lemondropsarl.luka

import android.app.Activity
import android.app.Application
import com.lemondropsarl.luka.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class LukaApplication : Application(), HasActivityInjector {
    override fun activityInjector(): AndroidInjector<Activity> {
        return activityAndroidInjector
    }

    @Inject
    lateinit var activityAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)


    }

}