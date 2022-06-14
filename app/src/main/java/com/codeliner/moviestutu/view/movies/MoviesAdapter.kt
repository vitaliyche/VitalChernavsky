package com.codeliner.moviestutu.view.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codeliner.moviestutu.R
import com.codeliner.moviestutu.model.Item
import kotlinx.android.synthetic.main.item_movies.view.*

val callback = object : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.git_url == newItem.git_url
    } //это точно такой же элемент или нет

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    } // есть ли различия внутри элемента
}

class MoviesAdapter : PagingDataAdapter<Item, MoviesAdapter.MyViewHolder>(callback) {

    private var moviesList = emptyList<Item>()
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
            listener?.invoke(moviesList[position])
        }

        Glide.with(context)
            .load(item.owner.avatar_url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.itemView.item_img_movie)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Item>) {
        moviesList = list
        notifyDataSetChanged()
    }

} //в PagingDataAdapter уже зашит список, не нужно создавать и обновлять. Обновляется через submitData