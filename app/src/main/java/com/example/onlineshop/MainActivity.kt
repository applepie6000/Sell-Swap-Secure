package com.example.onlineshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.onlineshop.Navigation.AppNavControl
import com.example.onlineshop.Navigation.ROUTE_HOME
import com.example.onlineshop.Navigation.ROUTE_LOGIN
import com.example.onlineshop.Navigation.ROUTE_REGISTER
import com.example.onlineshop.ui.theme.OnlineShopTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge(
                navigationBarStyle = SystemBarStyle.dark(
                    android.graphics.Color.TRANSPARENT),
            )
            OnlineShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val firebaseUser = FirebaseAuth.getInstance().currentUser
                    val startDestination  =
                        if (firebaseUser == null){
                            AppNavControl(startDestination = ROUTE_LOGIN)
                        }else{
                            AppNavControl(startDestination = ROUTE_HOME)
                        }
                }
            }
        }
    }
}




