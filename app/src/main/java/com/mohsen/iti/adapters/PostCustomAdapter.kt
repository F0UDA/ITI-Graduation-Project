package com.mohsen.iti.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohsen.iti.Ui.PostCustomClickListener
import com.mohsen.iti.models.PostModel
import com.mohsin.iti.databinding.PostItemBinding

class PostCustomAdapter(private var postsList: List<PostModel>, private var listener: PostCustomClickListener): RecyclerView.Adapter<PostCustomAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: PostItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.personTextview.text = "User Id: ${postsList[position].userId}"
        holder.binding.dateTextview.text = postsList[position].title
        holder.binding.contentTextview.text = postsList[position].body

        holder.binding.viewDetailsButton.setOnClickListener {
            listener.onItemClickListener(postsList[position], position)
        }

    }
}