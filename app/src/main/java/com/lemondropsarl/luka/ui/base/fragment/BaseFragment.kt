package com.lemondropsarl.luka.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import javax.inject.Inject

abstract class BaseFragment<T : ViewModel> : Fragment() {

    @get:LayoutRes
    abstract val getLayoutViewRes: Int
    val navController: NavController by lazy { findNavController() }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    abstract val viewModelClass: Class<T>
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (getLayoutViewRes > 0) inflater.inflate(getLayoutViewRes, container, false)
        else null
    }


    fun getViewModel(): T {
        return ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)

    }
}