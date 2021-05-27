package com.burkclik.movieapp.common.navigation

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController

class NavigationObserver {
    fun observer(
        navigation: Navigation,
        navController: NavController,
        lifecycleOwner: LifecycleOwner
    ) {
        navigation.navigateTo.observe(lifecycleOwner) { directions ->
            directions?.let {
                navController.navigate(it)
                navigation.onNavigationComplete()
            }
        }
    }
}