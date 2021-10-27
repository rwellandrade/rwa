package com.aulaandroid.ifoodclone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aulaandroid.ifoodclone.R
import com.aulaandroid.ifoodclone.data.Product


class ProductsAdapter(private val productList: List<Product>, private val onClick: (Product) -> Unit) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View, val onClick: (Product) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val restaurantNameTextView: TextView =
            itemView.findViewById(R.id.tv_restaurant_name)

        private val restaurantDescriptionTextView: TextView =
            itemView.findViewById(R.id.tv_restaurant_description)

        private var currentProduct: Product? = null

        init {
            itemView.setOnClickListener {
                currentProduct?.let {
                    onClick(it)
                }
            }
        }

        fun bind(res: Product) {
            currentProduct = res
            restaurantNameTextView.text = res.name
            restaurantDescriptionTextView.text = res.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item, parent, false)
        return ProductViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val restaurant = productList[position]
        holder.bind(restaurant)

    }

    override fun getItemCount(): Int = productList.size
}
