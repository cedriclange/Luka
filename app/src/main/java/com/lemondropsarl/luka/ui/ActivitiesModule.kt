package com.lemondropsarl.luka.ui

import com.lemondropsarl.luka.di.scopes.ActivityScope
import com.lemondropsarl.luka.ui.main.MainActivity
import com.lemondropsarl.luka.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    //add activities and their modules
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @ActivityScope
    abstract fun contributeMainActivity() : MainActivity
}