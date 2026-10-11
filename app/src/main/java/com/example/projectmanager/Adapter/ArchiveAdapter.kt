package com.example.projectmanager.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectmanager.Domain.ArchiveDomain
import com.example.projectmanager.R

class ArchiveAdapter(private val items: List<ArchiveDomain>):
    RecyclerView.Adapter<ArchiveAdapter.Viewholder> () {
        inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.titleTxt)
            val subtitle: TextView = itemView.findViewById(R.id.subtitleTxt)
            val pic: ImageView = itemView.findViewById(R.id.pic)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.viewholder_archive, parent, false)
            return Viewholder(view)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: Viewholder, position: Int) {
            val item = items[position]
            holder.title.text = item.title
            holder.subtitle.text = item.subtitle
            Glide.with(holder.itemView.context).load(R.drawable.folder).into(holder.pic)
        }
    }
