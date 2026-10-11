package com.example.projectmanager.Adapter

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.projectmanager.Domain.OngoingDomain
import com.example.projectmanager.R

class OngoingAdapter(private val items: List<OngoingDomain>):
    RecyclerView.Adapter<OngoingAdapter.Viewholder> () {
        inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.titleTxt)
            val data: TextView = itemView.findViewById(R.id.dateTxt)
            val progressBarPercent: TextView = itemView.findViewById(R.id.percentTxt)
            val progressTxt: TextView = itemView.findViewById(R.id.progressTxt)
            val progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
            val pic: ImageView = itemView.findViewById(R.id.pic)
            val layout: ConstraintLayout = itemView.findViewById(R.id.layout)

            fun setTextColors(colorRes: Int){
                title.setTextColor(itemView.context.getColor(colorRes))
                data.setTextColor(itemView.context.getColor(colorRes))
                progressTxt.setTextColor(itemView.context.getColor(colorRes))
                progressBarPercent.setTextColor(itemView.context.getColor(colorRes))
                pic.setColorFilter(ContextCompat.getColor(itemView.context, colorRes),
                    PorterDuff.Mode.SRC_IN
                )
                progressBar.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(itemView.context, colorRes))
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.viewholder_ongoing, parent, false)
            return Viewholder(view)
        }

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: Viewholder, position: Int) {
            val item = items[position]
                holder.title.text = item.title
                holder.data.text = item.data
                holder.progressBarPercent.text = "${item.progressPercent}%"

            val drawableResourceId = holder.itemView.context.resources.getIdentifier(
                item.picPath, "drawable", holder.itemView.context.packageName
            )

            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.pic)

            holder.progressBar.progress = item.progressPercent

            with(holder){
                if(position==0){
                    layout.setBackgroundResource(R.drawable.dark_bg)
                    setTextColors(R.color.white)
                }else{
                    layout.setBackgroundResource(R.drawable.light_purple_background)
                    setTextColors(R.color.dark_purple)
                }
            }
        }
    }
