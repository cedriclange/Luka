package com.lemondropsarl.luka.ui

import com.lemondropsarl.luka.di.scopes.FragmentScope
import com.lemondropsarl.luka.ui.main.feed.FeedDetailFragment
import com.lemondropsarl.luka.ui.main.feed.FeedListFragment
import com.lemondropsarl.luka.ui.main.home.HomeFragment
import com.lemondropsarl.luka.ui.main.post.PostAddFragment
import com.lemondropsarl.luka.ui.main.post.PostListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module //to include base fragmentmodule
abstract class FragmentsModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributePostAddFragment(): PostAddFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributePostListFragment(): PostListFragment
    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributeFeedDetailFragment(): FeedDetailFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributeFeedListFragment(): FeedListFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun contributeHomeFragment(): HomeFragment


}