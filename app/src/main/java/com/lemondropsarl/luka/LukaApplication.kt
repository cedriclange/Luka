@file:Suppress("ProtectedInFinal", "unused")

package com.lemondropsarl.luka

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.PersistableBundle
import com.lemondropsarl.luka.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class LukaApplication : Application(), HasActivityInjector {
    override fun activityInjector(): AndroidInjector<Activity> {
        return activityAndroidInjector
    }

    @Inject
    lateinit var activityAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

    }

}