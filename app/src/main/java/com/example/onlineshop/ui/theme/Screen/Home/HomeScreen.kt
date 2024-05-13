package com.example.onlineshop.ui.theme.Screen.Home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import coil.compose.rememberAsyncImagePainter
import com.example.onlineshop.Navigation.ROUTE_ADD_PRODUCT_CATEGORY
import com.example.onlineshop.Navigation.ROUTE_BOAT_ADD
import com.example.onlineshop.Navigation.ROUTE_BOAT_VIEW_ADD
import com.example.onlineshop.Navigation.ROUTE_HOME
import com.example.onlineshop.Navigation.ROUTE_LOGIN
import com.example.onlineshop.Navigation.ROUTE_SETTINGS
import com.example.onlineshop.Navigation.ROUTE_VIEW_CAR
import com.example.onlineshop.Navigation.ROUTE_VIEW_ELECTRONICS
import com.example.onlineshop.Navigation.ROUTE_VIEW_PHONE
import com.example.onlineshop.R
import java.net.URL

@Composable
fun PhoneCard(imageResId: Int, title: String, onClick: () -> Unit  ) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(300.dp)
            .height(250.dp).clickable { onClick }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
            , horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Spacer(modifier = Modifier.height(10.dp))
            Card (modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(), elevation = CardDefaults.cardElevation(10.dp), ){
                Image(
                    painter = rememberAsyncImagePainter(imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                    alignment = Alignment.TopCenter, contentScale = ContentScale.FillBounds
                )
            }

            Text(text = title,
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center, fontFamily = FontFamily.SansSerif)
            
        }
    }
}


@Composable
fun HomeScreen(navController: NavController) {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        IconButton(
                            onClick = { navController.navigate(ROUTE_HOME) }) {
                            Icon(
                                Icons.Filled.Home,
                                contentDescription = "Localized description",
                                )
                        }
                        Spacer(modifier = Modifier.width(30.dp))
                        IconButton(onClick = { navController.navigate(ROUTE_ADD_PRODUCT_CATEGORY) }) {
                            Icon(
                                Icons.Filled.Add,
                                contentDescription = "Localized description",
                                tint = Color.Magenta,
                                )
                        }
                        Spacer(modifier = Modifier.width(30.dp))
                        IconButton(onClick = {  }) {
                            Icon(
                                Icons.Filled.ShoppingCart,
                                contentDescription = "Localized description",
                            )
                        }
                        Spacer(modifier = Modifier.width(30.dp))
                        IconButton(onClick = { navController.navigate(ROUTE_SETTINGS) }) {
                            Icon(
                                Icons.Filled.Settings,
                                contentDescription = "Localized description",
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(text = "What are you Looking for")
                Spacer(modifier = Modifier.height(40.dp))
                PhoneCard(
                    imageResId = R.drawable.boat,
                    title = "Boats",
                    onClick = {navController.navigate(ROUTE_BOAT_VIEW_ADD)}
                )
                Spacer(modifier = Modifier.height(30.dp))

                PhoneCard(
                    imageResId = R.drawable.cars,
                    title = "Cars",
                    onClick = {navController.navigate(ROUTE_VIEW_CAR)}
                )

                Spacer(modifier = Modifier.height(30.dp))

                PhoneCard(
                    imageResId = R.drawable.phone,
                    title = "Phones",
                    onClick = {navController.navigate(ROUTE_VIEW_PHONE)}
                )

                Spacer(modifier = Modifier.height(30.dp))

                PhoneCard(
                    imageResId = R.drawable.laptop,
                    title = "Electronics",
                    onClick = {navController.navigate(ROUTE_VIEW_ELECTRONICS)}
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(text = "Not Used")


            }

            Text(
                modifier = Modifier.padding(innerPadding),
                text = ""
            )
        }
    }

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}