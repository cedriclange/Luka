@file:Suppress("unused")
package com.lemondropsarl.luka.di.module

import android.app.Application
import android.content.Context
import com.lemondropsarl.luka.LukaApplication
import com.lemondropsarl.luka.di.qualifier.ApplicationContext
import com.lemondropsarl.luka.ui.ActivitiesModule
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(includes = [AndroidSupportInjectionModule::class,
                ActivitiesModule::class] )
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application : LukaApplication) : Application
    @Binds
    @Singleton
    @ApplicationContext
    abstract fun bindApplicationContext(application: Application): Context
}