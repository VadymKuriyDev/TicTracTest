package com.tictracapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tictracapp.data.model.UserListItemData
import com.tictracappTest.R
import kotlinx.android.synthetic.main.user_item.view.*

class UsersAdapter(private val listener: UsersListener,
                   private var items: List<UserListItemData> = emptyList()) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: UserListItemData) {
            with(itemView) {
                user_name.text = item.name
                Glide.with(context)
                    .load(item.profilePicture)
                    .apply(RequestOptions.circleCropTransform())
                    .into(user_logo)
                setOnClickListener { listener.userSelected(item) }
            }
        }
    }

    //TODO need implement Diff or sorted list for user for better updates
    fun updateData(users: List<UserListItemData>){
        items = users
        notifyDataSetChanged()
    }
}

interface UsersListener{
    fun userSelected(user: UserListItemData)
}