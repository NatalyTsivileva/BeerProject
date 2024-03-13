package com.civileva.nata.beerproject.ui.fragment.catalog.model

data class SubCatalogItem(
	override val id: Int,
	override val name: String,
	override val image: String,
	val description: String,
	val price: Double
) : CatalogItem(id, name, image)