package com.lemondropsarl.luka.ui.main.post

import android.os.Bundle
import android.widget.ArrayAdapter
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.di.Injectable
import com.lemondropsarl.luka.ui.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.post_add_fragment.*

class PostAddFragment : BaseFragment<PostViewModel>(), Injectable {
    override val getLayoutViewRes: Int
        get() = R.layout.post_add_fragment
    override val viewModelClass: Class<PostViewModel>
        get() = PostViewModel::class.java

    lateinit var model:PostViewModel
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = getViewModel()
        initSpinners()
    }

    private fun initSpinners() {
        //spinner room number
        ArrayAdapter.createFromResource(context!!,
            R.array.number_of_room,
            android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spRoom.adapter =adapter
        }

        //spinner type
        ArrayAdapter.createFromResource(context!!,
            R.array.type_post,
            android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spType.adapter = adapter
        }
    }
    private fun getImage(){

    }
    private fun onSave(){

    }

}