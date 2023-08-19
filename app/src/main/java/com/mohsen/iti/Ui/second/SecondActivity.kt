package com.mohsen.iti.Ui.second

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mohsen.iti.Ui.PostCustomClickListener
import com.mohsen.iti.Ui.login.LoginActivity
import com.mohsen.iti.Ui.third.ThirdActivity
import com.mohsen.iti.Utils.ViewModelFactory
import com.mohsen.iti.adapters.PostCustomAdapter
import com.mohsen.iti.core.data_source.remote.ApiInterface
import com.mohsen.iti.core.data_source.remote.RetrofitClient
import com.mohsen.iti.core.repo.PostRepository
import com.mohsen.iti.models.PostModel
import com.mohsin.iti.R
import com.mohsin.iti.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(), PostCustomClickListener {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var postAdapter: PostCustomAdapter

    private lateinit var viewModel: PostViewModel



    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = applicationContext.getSharedPreferences("UserSharedPreferences", MODE_PRIVATE)
        val userName = preferences.getString("UserName", "")

        val retrofit = RetrofitClient.getInstance("https://jsonplaceholder.typicode.com/")
        val apiInterface = retrofit.create(ApiInterface::class.java)
        val repo = PostRepository(apiInterface)
        val factory = ViewModelFactory(null,repo)
        val viewModel = ViewModelProvider(this, factory).get(PostViewModel::class.java)

        binding.queryIdButton.setOnClickListener() {


            val userId = binding.queryIdEditText.text.toString().toInt()
            viewModel.getPostsByUserId(userId)

        }
        viewModel.posts.observe(this) { posts ->
            postAdapter = PostCustomAdapter(posts, this@SecondActivity)
            binding.postsRecyclerview.adapter = postAdapter
        }
    }

    override fun onItemClickListener(post: PostModel, position: Int) {
        val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
        intent.putExtra("postId", post.id)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_second_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.logout -> {
                val editor = preferences.edit()
                editor.remove("UserName")
                editor.remove("Password")
                editor.putBoolean("IsLogin", false)
                editor.commit()
                startActivity(Intent(this@SecondActivity, LoginActivity::class.java))
                finish()
                true
            }else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }


}