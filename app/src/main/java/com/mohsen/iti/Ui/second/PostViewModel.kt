package com.mohsen.iti.Ui.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohsen.iti.core.repo.PostRepository
import com.mohsen.iti.models.PostModel
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {
    private val _posts = MutableLiveData<List<PostModel>>()
    val posts: LiveData<List<PostModel>> = _posts

    fun getPostsByUserId(userId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getPostsByUserId(userId)
                if (response.isSuccessful) {
                    _posts.value = response.body()
                }
            } catch (error: Exception) {
            // TODO:  {error Handling}

        }
    }
}
}