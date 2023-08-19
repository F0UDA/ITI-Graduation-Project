package com.mohsen.iti.Ui.third

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.mohsen.iti.adapters.CommentCustomAdapter
import com.mohsen.iti.core.data_source.remote.ApiInterface
import com.mohsen.iti.core.data_source.remote.RetrofitClient
import com.mohsin.iti.databinding.ActivityThirdBinding
import kotlinx.coroutines.launch

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val postId = intent.getIntExtra("postId",0)

        var retrofit = RetrofitClient.getInstance("https://jsonplaceholder.typicode.com/")
        var apiInterface = retrofit.create(ApiInterface::class.java)

        lifecycleScope.launch() {
            try{
                val response = apiInterface.getCommentsByPostId(postId)
                if(response.isSuccessful){
                    val comments = response.body()
                    binding.commentsRecyclerview.adapter = CommentCustomAdapter(response.body() ?: listOf())
                }
            }
            catch (error:Exception){
                Log.d("Error Said",error.toString())
                Toast.makeText(this@ThirdActivity, error.toString(), Toast.LENGTH_SHORT).show()

            }
        }

    }
}