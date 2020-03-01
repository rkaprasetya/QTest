package com.raka.qtest.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.raka.qtest.R
import com.raka.qtest.data.model.compact.ProductCompact
import com.raka.qtest.data.model.local.ProductPromoLocal
import com.raka.qtest.databinding.ItemProductBinding
import com.raka.qtest.presentation.adapter.ProductListAdapter.Holder
import com.squareup.picasso.Picasso

class ProductListAdapter(products: ObservableArrayList<ProductCompact>,val context: Context) :
    ObservableRecyclerViewAdapter<ProductCompact, Holder>(products) {
    lateinit var onLikedListener: (restaurant: ProductCompact) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClickListener,onLikedListener,context
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(getItem(position))

    class Holder(
        private val binding: ItemProductBinding,
        private val onItemClickListener: ((item: Any) -> Unit)?,
        private val onLikedListener:((item: ProductCompact) -> Unit)?,
        private val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var product: ProductCompact
        fun bind(product: ProductCompact) {
            this.product = product
            Picasso.get().load(product.imageUrl).into(binding.ivItemProduct)
            setLiked(product.liked,binding)
            binding.tvItemProductName.text = product.productName
            binding.root.setOnClickListener { onItemClickListener?.invoke(product) }
            binding.ivProductLike.setOnClickListener{onLikedListener?.invoke(product)}
        }
        private fun setLiked(liked:Int,binding: ItemProductBinding){
            if (liked == 1) {
                binding.ivProductLike.setImageDrawable(context.resources.getDrawable(R.drawable.ic_heart_filled,null))
            }else{
                binding.ivProductLike.setImageDrawable(context.resources.getDrawable(R.drawable.ic_heart_unfill,null))
            }
        }
    }
}