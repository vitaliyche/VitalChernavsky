package com.codeliner.vitalchernavsky.view.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codeliner.vitalchernavsky.R
import com.codeliner.vitalchernavsky.model.Item
import kotlinx.android.synthetic.main.item_movies.view.*

val callback = object : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.git_url == newItem.git_url
    } //это точно такой же элемент или нет

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    } // есть ли различия внутри элемента
}

class MoviesAdapter : ListAdapter<Item, MoviesAdapter.MyViewHolder>(callback) {

    var listener: ((Item) -> Unit)? = null

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movies, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.itemView.item_tv_title.text = item.name
        holder.itemView.item_tv_description.text = item.description
        val context = holder.itemView.context

        holder.itemView.setOnClickListener {
            listener?.invoke(item)
        }

        Glide.with(context)
            .load(item.owner.avatar_url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.itemView.item_img_movie)
    }

} //в PagingDataAdapter уже зашит список, не нужно создавать и обновлять. Обновляется через submitData