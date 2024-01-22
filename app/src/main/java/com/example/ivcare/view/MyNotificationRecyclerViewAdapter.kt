package com.example.ivcare.view

import PlaceholderItem
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.ivcare.databinding.FragmentItemBinding
import com.example.ivcare.view.notification.NotificationContent

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyNotificationRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyNotificationRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.alertName.text = item.alertName
        holder.emergency.text = item.emergency
        holder.room.text = item.room
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val alertName: TextView = binding.alertName
        val emergency: TextView = binding.emergency
        val room: TextView = binding.room

        override fun toString(): String {
            return super.toString() + " '" + alertName.text + "'"
        }
    }

}