package com.ahmedmadhoun.unitoneassignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmadhoun.unitoneassignment.R
import com.ahmedmadhoun.unitoneassignment.databinding.CityItemBinding
import com.ahmedmadhoun.unitoneassignment.domain.model.CityModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.city_item.view.*

class CitiesAdapter() : ListAdapter<CityModel, CitiesAdapter.VideoViewHolder>(diff) {

    inner class VideoViewHolder(binding: CityItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val diff = object : DiffUtil.ItemCallback<CityModel>() {
            override fun areItemsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CityModel, newItem: CityModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {
        return VideoViewHolder(
            CityItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.apply {
            if (item != null) {
                Glide
                    .with(this)
                    .load(item.image)
                    .centerCrop()
                    .into(imageView);
            }
        }

    }


}
