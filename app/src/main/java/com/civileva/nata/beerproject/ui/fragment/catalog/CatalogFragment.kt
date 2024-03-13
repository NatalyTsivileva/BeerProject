package com.civileva.nata.beerproject.ui.fragment.catalog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.civileva.nata.beerproject.R
import com.civileva.nata.beerproject.ui.Error
import com.civileva.nata.beerproject.ui.Loading
import com.civileva.nata.beerproject.ui.Success
import com.civileva.nata.beerproject.ui.fragment.catalog.model.CatalogItem
import kotlinx.coroutines.launch

class CatalogFragment : Fragment(R.layout.fr_catalog) {

	private var viewModel: CatalogViewModel? = null
	private var recyclerAdapter: CatalogRecyclerAdapter? = null

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupUi(view)
		initViewModel()
		subscribeOnUpdates()
	}

	private fun setupUi(view: View) {
		view.findViewById<RecyclerView>(R.id.categoryRecyclerView)?.let {
			it.layoutManager = GridLayoutManager(context, 2)
			recyclerAdapter = CatalogRecyclerAdapter(object : OnCatalogClickListener{
				override fun OnClick(item: CatalogItem) {
					TODO("Navigate to details")
				}
			})
			it.adapter = recyclerAdapter
		}
	}

	private fun initViewModel() {
		viewModel = ViewModelProvider(this)[CatalogViewModel::class.java]
	}

	fun subscribeOnUpdates() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel?.uiState?.collect { uiState ->
					when (uiState) {
						is Error -> TODO()
						is Loading -> TODO()
						is Success -> {
							recyclerAdapter?.submitList(uiState.result)
						}
					}
				}
			}
		}

		viewModel?.requestCatalog()
	}
}