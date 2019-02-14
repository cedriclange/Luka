package com.lemondropsarl.luka.ui.main.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.di.Injectable

class FeedDetailFragment : Fragment(), Injectable {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.feed_detail_fragment, container, false)
    }


}