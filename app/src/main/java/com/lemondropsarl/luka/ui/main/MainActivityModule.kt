package com.lemondropsarl.luka.ui.main

import androidx.appcompat.app.AppCompatActivity
import com.lemondropsarl.luka.di.scopes.ActivityScope
import dagger.Binds
import dagger.Module

//to include fragmentmodules
@Module
abstract class MainActivityModule {
    @Binds
    @ActivityScope
    abstract fun bindActivity(activity : MainActivity): AppCompatActivity

    //to bind viewmodel in future
}