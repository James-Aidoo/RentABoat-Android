package com.questdev.rentaboat


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavHost
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val navHostFragment = childFragmentManager.findFragmentById(R.id.homeContainer) as NavHost

        view.findViewById<BottomNavigationView>(R.id.homeNav)
            .setupWithNavController(navHostFragment.navController)

        val toolbar = view.findViewById<Toolbar>(R.id.homeToolbar)
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            toolbar.title = destination.label
        }

        return view
    }


}
