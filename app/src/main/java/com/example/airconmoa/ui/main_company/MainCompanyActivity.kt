package com.example.airconmoa.ui.main_company

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.airconmoa.R
import com.example.airconmoa.config.BaseActivityVB
import com.example.airconmoa.databinding.ActivityMainCompanyBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainCompanyActivity :
    BaseActivityVB<ActivityMainCompanyBinding>(ActivityMainCompanyBinding::inflate) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.bottomNV.itemIconTintList = null

        setBottomNavigation()

    }


    private fun setBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frame) as NavHostFragment
        navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottomNV)
            .setupWithNavController(navController)

        binding.bottomNV.setOnItemSelectedListener { item ->
            if (binding.bottomNV.selectedItemId != item.itemId) {
                NavigationUI.onNavDestinationSelected(item, navController, false)
            }
            true
        }
    }
}
/*class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirconmoaandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
   // Scaffold() {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
  //  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AirconmoaandroidTheme {
        Greeting("Android")
    }
}

 */