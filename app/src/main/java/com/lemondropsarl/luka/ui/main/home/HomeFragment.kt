package com.lemondropsarl.luka.ui.main.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.di.Injectable

class HomeFragment : Fragment(), Injectable {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}