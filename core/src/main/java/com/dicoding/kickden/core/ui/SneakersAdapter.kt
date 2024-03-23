package com.dicoding.kickden.core.ui



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.kickden.core.databinding.ItemSneakerBinding
import com.dicoding.kickden.core.domain.model.Sneakers


class SneakersAdapter(private val onItemClick: (Sneakers) -> Unit): ListAdapter<Sneakers, SneakersAdapter.ListViewHolder>(DIFF_CALLBACK) {

    inner class ListViewHolder(private val binding: ItemSneakerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sneaker: Sneakers){
            binding.apply {
               tvItemName.text = sneaker.name
               tvItemBrand.text = sneaker.brand
                Glide.with(itemView.context)
                    .load(sneaker.photoUrl)
                    .into(imgItemPhoto)
                root.setOnClickListener { onItemClick(sneaker) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemSneakerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val sneaker = getItem(position)
        holder.bind(sneaker)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Sneakers>() {
            override fun areItemsTheSame(
                oldItem: Sneakers,
                newItem: Sneakers
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Sneakers,
                newItem: Sneakers
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}


