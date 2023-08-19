package com.mohsen.iti.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohsen.iti.models.CommentModel
import com.mohsin.iti.databinding.CommentItemBinding

class CommentCustomAdapter(var commentsList: List<CommentModel>): RecyclerView.Adapter<CommentCustomAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: CommentItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int {
        return commentsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.personTextview.text = "${commentsList[position].name}"
        holder.binding.dateTextview.text = "${commentsList[position].email}"
        holder.binding.contentTextview.text = commentsList[position].body

    }
}