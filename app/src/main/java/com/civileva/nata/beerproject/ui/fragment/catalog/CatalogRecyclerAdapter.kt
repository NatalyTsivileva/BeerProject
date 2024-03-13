package com.civileva.nata.beerproject.ui.fragment.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.civileva.nata.beerproject.R
import com.civileva.nata.beerproject.ui.fragment.catalog.model.CatalogItem

class CatalogRecyclerAdapter(
	private val onCatalogClickListener: OnCatalogClickListener
) : ListAdapter<CatalogItem, CatalogRecyclerAdapter.CatalogViewHolder>(comporator) {

	inner class CatalogViewHolder(itemView: View) : ViewHolder(itemView) {
		val cardView: CardView? = itemView.findViewById(R.id.cardView)
		val imageView: ImageView? = itemView.findViewById(R.id.image)
		val titleView: TextView? = itemView.findViewById(R.id.title)

		fun bind(data: CatalogItem) {
			if (imageView != null) Glide.with(imageView.context).load(data.image).into(imageView)
			titleView?.text = data.name

			cardView?.setOnClickListener { onCatalogClickListener.OnClick(data) }
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_catalog, parent, false)
		return CatalogViewHolder(view)
	}

	override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	companion object {
		val comporator = object : DiffUtil.ItemCallback<CatalogItem>() {
			override fun areItemsTheSame(oldItem: CatalogItem, newItem: CatalogItem): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(oldItem: CatalogItem, newItem: CatalogItem): Boolean {
				return oldItem.image == newItem.image && oldItem.name == newItem.name && oldItem.id == newItem.id
			}
		}
	}
}