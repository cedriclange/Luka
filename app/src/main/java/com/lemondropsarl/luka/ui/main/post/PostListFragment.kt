package com.lemondropsarl.luka.ui.main.post

import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.ui.base.fragment.BaseFragment

class PostListFragment : BaseFragment<PostViewModel>() {
    override val getLayoutViewRes: Int
        get() = R.layout.post_list_fragment
    override val viewModelClass: Class<PostViewModel>
        get() = PostViewModel::class.java
}