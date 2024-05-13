package com.example.onlineshop.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ehsankhormali.userprofileusingfirebase.screens.LogInScreen
import com.example.onlineshop.ui.theme.Screen.DashBoard.DashView
import com.example.onlineshop.ui.theme.Screen.Home.HomeScreen
import com.example.onlineshop.ui.theme.Screen.ProductControls.AddProductTypeScreen
import com.example.onlineshop.ui.theme.Screen.Products.Boats.Visuals.AddBoatScreen
import com.example.onlineshop.ui.theme.Screen.Products.Boats.Visuals.BoatViewAddScreen
import com.example.onlineshop.ui.theme.Screen.Products.Cars.Visual.CarScreen
import com.example.onlineshop.ui.theme.Screen.Products.Cars.Visual.ViewCarScreen
import com.example.onlineshop.ui.theme.Screen.Products.Electronics.Visual.ElectronicsScreen
import com.example.onlineshop.ui.theme.Screen.Products.Electronics.Visual.ViewElectronicScreen
import com.example.onlineshop.ui.theme.Screen.Products.Phone.Visual.PhoneScreen
import com.example.onlineshop.ui.theme.Screen.Products.Phone.Visual.ViewPhoneScreen
import com.example.onlineshop.ui.theme.Screen.Register.RegisterScreen
import com.example.onlineshop.ui.theme.Screen.Settings.SettingsScreen

@Composable
fun AppNavControl(modifier: Modifier=Modifier,
               navController: NavHostController= rememberNavController()
               ,startDestination: String ,
) {
    NavHost(
        navController = navController,
        modifier = Modifier,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
            LogInScreen(navController)
        }
        composable(ROUTE_DASH) {
            DashView(navController = navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_BOAT_ADD) {
            AddBoatScreen(navController)
        }
        composable(ROUTE_BOAT_VIEW_ADD) {
            BoatViewAddScreen(navController)
        }
        composable(ROUTE_PHONE) {
            PhoneScreen(navController)
        }
        composable(ROUTE_ADD_PRODUCT_CATEGORY) {
            AddProductTypeScreen(navController)
        }
        composable(ROUTE_VIEW_PHONE) {
            ViewPhoneScreen(navController)
        }
        composable(ROUTE_CAR) {
            CarScreen(navController)
        }
        composable(ROUTE_VIEW_CAR) {
            ViewCarScreen(navController)
        }
        composable(ROUTE_ELECTRONICS) {
            ElectronicsScreen(navController)
        }
        composable(ROUTE_VIEW_ELECTRONICS) {
            ViewElectronicScreen(navController)
        }
        composable(ROUTE_SETTINGS) {
            SettingsScreen(navController)
        }

    }
}


