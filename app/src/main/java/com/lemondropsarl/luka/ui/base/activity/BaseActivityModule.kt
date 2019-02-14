package com.lemondropsarl.luka.ui.base.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lemondropsarl.luka.di.factory.LukaViewModelFactory
import com.lemondropsarl.luka.di.qualifier.ActivityContext
import com.lemondropsarl.luka.di.scopes.ActivityScope
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class BaseActivityModule {
    @Binds
    @ActivityScope
    @ActivityContext
    abstract fun bindActivityContext(activity: AppCompatActivity): Context

    //@Binds
    //abstract fun bindViewModelFactory(viewModelFactory: LukaViewModelFactory): ViewModelProvider.Factory
}