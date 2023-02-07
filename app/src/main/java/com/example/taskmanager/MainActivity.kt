package com.example.taskmanager

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskmanager.data.Pref
import com.example.taskmanager.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        pref = Pref(this)
//
//        auth = FirebaseAuth.getInstance()
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        if (!pref.isUserSeen())
//            navController.navigate(R.id.onBoardingFragment)
//
//        if (auth.currentUser == null) {
//            navController.navigate(R.id.authFragment)
//        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.taskFragment,
                R.id.navigation_profile
            )

        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val bottomNavFragments = arrayListOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.taskFragment,
            R.id.navigation_profile
        )
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            navView.isVisible = bottomNavFragments.contains(destination.id)
            if (destination.id == R.id.onBoardingFragment || destination.id == R.id.splashFragment) {
                supportActionBar?.hide()
            } else supportActionBar?.show()
        }
    }
}