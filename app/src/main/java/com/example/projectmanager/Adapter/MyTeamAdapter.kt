package com.example.projectmanager.Adapter

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.projectmanager.Domain.TeamDomain
import com.example.projectmanager.R

class MyTeamAdapter(private val items: List<TeamDomain>):
    RecyclerView.Adapter<MyTeamAdapter.Viewholder> () {
        inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.titleTxt)
            val status: TextView = itemView.findViewById(R.id.statusTxt)
            val rightBadge: TextView = itemView.findViewById(R.id.rightBadgeTxt)
            val pic: ImageView = itemView.findViewById(R.id.pic)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.viewholder_team, parent, false)
            return Viewholder(view)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: Viewholder, position: Int) {
            val item = items[position]
            holder.title.text = item.title
            holder.status.text = item.status
            holder.rightBadge.text = item.rightBadge

            val drawableResourceId = holder.itemView.context.resources.getIdentifier(
                item.picPath, "drawable", holder.itemView.context.packageName
            )
            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.pic)

            holder.pic.setColorFilter(
                ContextCompat.getColor(holder.itemView.context, R.color.dark_purple),
                PorterDuff.Mode.SRC_IN
            )
        }
    }
