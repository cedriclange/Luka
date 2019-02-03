package com.lemondropsarl.luka.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lemondropsarl.luka.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
