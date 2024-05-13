package com.example.onlineshop.ui.theme.Screen.DashBoard

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.Glide.init
import com.example.onlineshop.Model.SignInModels.BiometricPromptManager
import com.example.onlineshop.Navigation.ROUTE_HOME
import com.example.onlineshop.Navigation.ROUTE_LOGIN
import com.example.onlineshop.Navigation.ROUTE_REGISTER

@Composable
fun DashView(navController: NavController) {
        Column {

            Text(text = "Welcome ")

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { navController.navigate(ROUTE_REGISTER) }) {
                Text(text = "GET STARTED")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { navController.navigate(ROUTE_HOME) }) {
                Text(text = "LogIn ")
            }
        }
    }
