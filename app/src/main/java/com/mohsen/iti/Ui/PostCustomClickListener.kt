package com.mohsen.iti.Ui

import com.mohsen.iti.models.PostModel

interface PostCustomClickListener {
    fun onItemClickListener(post: PostModel, position: Int)
}