package com.davydikes.rickandmorty.screen.main

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.davydikes.rickandmorty.models.Characters
import androidx.recyclerview.widget.ListAdapter
import com.davydikes.rickandmorty.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class RecyclerViewAdapterCharacters(
    private val onClick: (Characters) -> Unit,
) : ListAdapter<Characters, RecyclerView.ViewHolder>(CharactersAdapterDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = CharacterViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_character, parent, false),
        ::onItemClick
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CharacterViewHolder).bind(getItem(position))
    }

    inner class CharacterViewHolder(
        itemView: View,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {


        private val tvImage = itemView.findViewById<ImageView>(R.id.tvImage)
        private val tvName = itemView.findViewById<TextView>(R.id.tvDetails)

        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(item: Characters) {
            Picasso.get()
                .load(item.image)
                .into(tvImage, object : Callback {
                    override fun onSuccess() {
                        Log.d(ContentValues.TAG, "success1")
                    }

                    override fun onError(e: Exception?) {
                        Log.d(ContentValues.TAG, "error")
                    }
                })
            tvName.text = item.name
        }
    }

    private fun onItemClick(position: Int) {
        onClick(getItem(position))
    }
}

class CharactersAdapterDiffCallback : DiffUtil.ItemCallback<Characters>() {
    override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
        return oldItem.name == newItem.name && oldItem.image == newItem.image
    }
}
