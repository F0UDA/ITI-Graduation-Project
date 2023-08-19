package com.mohsen.iti.Ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohsen.iti.core.model.response.UserResponse
import com.mohsen.iti.core.repo.LoginRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<Response<UserResponse>>()
    val loginResult: LiveData<Response<UserResponse>> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val response = repository.login(username, password)
            _loginResult.value = response
        }
    }
}