package com.lemondropsarl.luka.ui.main

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.lemondropsarl.luka.di.ViewModelKey
import com.lemondropsarl.luka.di.scopes.ActivityScope
import com.lemondropsarl.luka.ui.FragmentsModule
import com.lemondropsarl.luka.ui.base.activity.BaseActivity
import com.lemondropsarl.luka.ui.base.activity.BaseActivityModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

//to include fragmentmodules
@Suppress("unused")
@Module(includes = [FragmentsModule::class])
abstract class MainActivityModule {
    @Binds
    @ActivityScope
    abstract fun bindActivity(activity : MainActivity): AppCompatActivity

    //@Binds
    //@IntoMap
    //@ViewModelKey
    //@ActivityScope//to bind viewmodel in future of the activity
    //abstract fun bindViewModel(viewModel: ViewModel): ViewModel
}