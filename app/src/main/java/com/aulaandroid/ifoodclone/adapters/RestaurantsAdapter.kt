package com.aulaandroid.ifoodclone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aulaandroid.ifoodclone.R
import com.aulaandroid.ifoodclone.data.Restaurant


class RestaurantsAdapter(private val onClick: (Restaurant) -> Unit) :
    RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {

    private val initialRestaurantList = listOf(
        Restaurant(
            1,
            "Spoleto",
            "Spoleto is a Brazilian restaurant chain that develops the concept of fast food to Italian cuisine. The restaurant's name is a tribute to the town of Spoleto, Italy. The company was established in 1999 in Rio de Janeiro by Eduardo Ourivio and Mário Chady."
        ),
        Restaurant(
            2,
            "Subway",
            "Subway is an American multi-national fast food restaurant franchise that primarily sells submarine sandwiches, wraps, salads and beverages. Subway was founded by 17 year old Fred DeLuca and financed by Peter Buck in 1965 as Pete's Super Submarines in Bridgeport"
        ),
        Restaurant(
            3,
            "Mamma Jamma",
            "Na MAMMA JAMMA, levamos a sério essa história de acolhimento, de fazer sentir bem. É aqui que a tradição da Itália e o moderno do mundo se encontram, criando uma pizza saborosa e única. "
        ),
        Restaurant(
            4,
            "Papa Jack",
            "Restaurante e Pizzeria Napoletana com massas e comida italiana no Leblon e em Copacabana e restaurantes na Barra da Tijuca. Cardápio variado de alta qualidade que só o Pappa Jack tem."
        ),
        Restaurant(
            5,
            "Aprazível",
            "O Aprazível Restaurante fica em Santa Teresa, bairro carioca onde a vida segue de forma mais suave. E são muitos os motivos que fazem jus ao nome: a vista da Baía de Guanabara, o entardecer poético, as árvores frondosas que refrescam no verão e protegem no inverno."
        ),
    )

    class RestaurantViewHolder(itemView: View, val onClick: (Restaurant) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val restaurantNameTextView: TextView =
            itemView.findViewById(R.id.tv_restaurant_name)

        private val restaurantDescriptionTextView: TextView =
            itemView.findViewById(R.id.tv_restaurant_description)

        private var currentRestaurant: Restaurant? = null

        init {
            itemView.setOnClickListener {
                currentRestaurant?.let {
                    onClick(it)
                }
            }
        }

        fun bind(res: Restaurant) {
            currentRestaurant = res
            restaurantNameTextView.text = res.name
            restaurantDescriptionTextView.text = res.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item, parent, false)
        return RestaurantViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = initialRestaurantList[position]
        holder.bind(restaurant)

    }

    override fun getItemCount(): Int = initialRestaurantList.size
}
