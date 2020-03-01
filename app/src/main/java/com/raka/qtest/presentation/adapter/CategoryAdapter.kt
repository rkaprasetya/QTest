package com.raka.qtest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.raka.qtest.data.model.compact.CategoryCompact
import com.raka.qtest.databinding.ItemCategoryBinding
import com.raka.qtest.presentation.adapter.CategoryAdapter.Holder
import com.squareup.picasso.Picasso

class CategoryAdapter(products: ObservableArrayList<CategoryCompact>):
    ObservableRecyclerViewAdapter<CategoryCompact, Holder>(products){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(getItem(position))

    class Holder(
        private val binding: ItemCategoryBinding
        ) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var category: CategoryCompact
        fun bind(category: CategoryCompact) {
            this.category = category
            Picasso.get().load(category.imageUrl).into(binding.ivItemCategory)
            binding.tvItemCategory.text = category.name
        }
    }
}