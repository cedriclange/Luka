package com.lemondropsarl.luka.data

import android.net.Uri

data class UserModel(
    var username: String?,
    var email: String?,
    var profileUrl: Uri?
)

