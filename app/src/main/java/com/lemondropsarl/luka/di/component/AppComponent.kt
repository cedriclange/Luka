package com.lemondropsarl.luka.di.component

import com.lemondropsarl.luka.LukaApplication
import com.lemondropsarl.luka.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class])
interface AppComponent : AndroidInjector<LukaApplication> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application : LukaApplication): Builder


        fun build() : AppComponent
    }


}