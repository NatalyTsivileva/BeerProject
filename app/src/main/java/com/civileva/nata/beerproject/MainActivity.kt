package com.civileva.nata.beerproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.ac_main)
		setupUI()
	}

	private fun setupUI() {
		val navbar = findViewById<BottomNavigationView>(R.id.bottomNavigationBar)
		val fragment =
			supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
		val controller = fragment?.navController

		if (navbar != null && controller != null)
			NavigationUI.setupWithNavController(navbar, controller)
	}
}