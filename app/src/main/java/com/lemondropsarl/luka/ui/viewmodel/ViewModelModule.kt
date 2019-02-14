package com.lemondropsarl.luka.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lemondropsarl.luka.di.ViewModelKey
import com.lemondropsarl.luka.di.factory.LukaViewModelFactory
import com.lemondropsarl.luka.ui.main.feed.FeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    //declare all viewModules
    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun bindFeedViewModel(feedViewModel: FeedViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: LukaViewModelFactory): ViewModelProvider.Factory
}