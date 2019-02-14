package com.lemondropsarl.luka.ui.base.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {
    //this is for injection
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    abstract fun layoutView(): Int //get the layout from activity


    protected fun <T : ViewModel> getViewModel(cls: Class<T>): T {
        return ViewModelProviders.of(this, viewModelFactory).get(cls)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //AndroidInjection.inject(this)//inject the activity
        super.onCreate(savedInstanceState)
        setContentView(layoutView())//set the contentView

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}