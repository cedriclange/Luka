package com.lemondropsarl.luka.ui.main.post

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.MimeTypeMap
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference
import com.lemondropsarl.luka.R
import com.lemondropsarl.luka.data.remote.model.Post
import com.lemondropsarl.luka.di.Injectable
import com.lemondropsarl.luka.ui.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.post_add_fragment.*

class PostAddFragment : BaseFragment<PostViewModel>(), Injectable {
    companion object {
        const val RC_IMAGE = 123
    }

    override val getLayoutViewRes: Int
        get() = R.layout.post_add_fragment
    override val viewModelClass: Class<PostViewModel>
        get() = PostViewModel::class.java

    lateinit var model: PostViewModel
    lateinit var storage: StorageReference


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = getViewModel()
        initSpinners()
        postImageView.setOnClickListener {
            getImage()
        }
        saveBtn.setOnClickListener {
            onSave(model.imageUri!!)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_post_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_post -> {
                onSave(model.imageUri!!)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initSpinners() {
        //spinner room number
        ArrayAdapter.createFromResource(
            context!!,
            R.array.number_of_room,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spRoom.adapter = adapter
        }

        //spinner type
        ArrayAdapter.createFromResource(
            context!!,
            R.array.type_post,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
            spType.adapter = adapter
        }
    }

    private fun getImage() {
        //getting image from intent
        val intent = Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, RC_IMAGE)
    }

    private fun onSave(uri: Uri) {
        //call the save function
        uploadImageToCloud(uri)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                model.imageUri = data?.data
                model.imageUri?.let {

                    Glide.with(postImageView.context!!)
                        .load(it)
                        .into(postImageView)
                }

            } else {
                //show some actions
            }
        }
    }

    private fun uploadImageToCloud(uri: Uri) {
        //call function to to talk to cloud
        val mime = getFileExtension(uri)
        model.uploadImage(uri, mime).addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener { uri ->
                //get the url
                val handler = Handler()
                handler.postDelayed({
                    progressBar.progress = 0
                }, 500)
                val downloadUrl = uri!!.toString()
                syncFirestore(downloadUrl)
            }
        }.addOnProgressListener {

            val progress = (100.0 * it.bytesTransferred / it.totalByteCount)
            progressBar.progress = progress.toInt()

        }
    }

    private fun syncFirestore(downoadUrl: String) {
        val current = FirebaseAuth.getInstance().currentUser
        var post = Post()
        post.city = editCity.text.toString()
        post.area = editArea.text.toString()
        post.title = editTitle.text.toString()
        post.price = editPrice.text.toString().toDouble()
        post.guaranty = editGuaranty.text.toString().toDouble()
        post.rooms = spRoom.selectedItem.toString().toInt()
        post.author = current?.displayName!!
        post.type = spType.selectedItem.toString()
        post.photoUrl = downoadUrl
        //specification
        val tags = editSpec.text.toString()
        val tagArray = tags.split("\\s*,\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val spec = HashMap<String, Boolean>()
        for (tag: String in tagArray) {
            spec[tag] = true
        }
        post.specification = spec
        model.syncFirestore(post, current!!.uid).addOnSuccessListener {
            navController.navigateUp()
        }

    }

    private fun getFileExtension(uri: Uri): String {
        val cr = context!!.contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cr.getType(uri))!!

    }

}