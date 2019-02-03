@file:Suppress("ProtectedInFinal", "unused")
package com.lemondropsarl.luka

import com.lemondropsarl.luka.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class LukaApplication : DaggerApplication() {
    private val applicationInjector = DaggerAppComponent.builder()
        .application(this)
        .build()
    override fun onCreate() {
        super.onCreate()
    }
    override fun applicationInjector() = applicationInjector
}