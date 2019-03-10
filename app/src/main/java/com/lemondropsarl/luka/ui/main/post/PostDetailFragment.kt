package com.lemondropsarl.luka.ui.main.post

import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.di.Injectable
import com.lemondropsarl.luka.ui.base.fragment.BaseFragment

class PostDetailFragment : BaseFragment<PostViewModel>(), Injectable {
    override val getLayoutViewRes: Int
        get() = R.layout.post_detail_fragment
    override val viewModelClass: Class<PostViewModel>
        get() = PostViewModel::class.java
}