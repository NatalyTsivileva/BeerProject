package com.civileva.nata.beerproject.ui.fragment.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.civileva.nata.beerproject.ui.States
import com.civileva.nata.beerproject.ui.Success
import com.civileva.nata.beerproject.ui.fragment.catalog.model.CatalogItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {

	private val _uiState = MutableStateFlow(Success<List<CatalogItem>>(emptyList()))

	val uiState: StateFlow<States<List<CatalogItem>>> = _uiState

	fun requestCatalog() {
		viewModelScope.launch {
			downloadCatalog()
		}
	}

	private suspend fun downloadCatalog() {
		val list = listOf(
			CatalogItem(1, "Разливное пиво", "https://i.postimg.cc/SNgLzN1H/beer-bottle.png"),
			CatalogItem(
				2,
				"Бутилированное пиво",
				"https://i.postimg.cc/13Ng59kG/beer-bottle-2.png"
			),
			CatalogItem(3, "Разливное пиво", "https://i.postimg.cc/SNgLzN1H/beer-bottle.png"),
			CatalogItem(4, "Бутилированное пиво", "https://i.postimg.cc/13Ng59kG/beer-bottle-2.png")
		)
		flowOf(list).collect {
			_uiState.value = Success(it)
		}
	}
}