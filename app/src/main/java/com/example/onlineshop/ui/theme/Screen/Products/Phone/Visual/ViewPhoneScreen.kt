package com.example.onlineshop.ui.theme.Screen.Products.Phone.Visual

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import coil.compose.rememberAsyncImagePainter
import com.example.onlineshop.Model.SignInModels.Upload
import com.example.onlineshop.Navigation.ROUTE_ADD_PRODUCT
import com.example.onlineshop.Navigation.ROUTE_BOAT_ADD
import com.example.onlineshop.Navigation.ROUTE_HOME
import com.example.onlineshop.Navigation.ROUTE_VIEW_UPLOAD
import com.example.onlineshop.ui.theme.Screen.Products.Boats.Logics.AddUpdateDeleteBoat
import com.example.onlineshop.ui.theme.Screen.Products.Phone.Logic.AddDeleteUpdatePhones

@Composable
fun ViewPhoneScreen(navController:NavHostController) {
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
                    IconButton(onClick = { navController.navigate(ROUTE_ADD_PRODUCT) }) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Localized description",
                            tint = Color.Magenta,
                        )
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    IconButton(onClick = { navController.navigate(ROUTE_VIEW_UPLOAD) }) {
                        Icon(
                            Icons.Filled.ShoppingCart,
                            contentDescription = "Localized description",
                        )
                    }
                    Spacer(modifier = Modifier.width(30.dp))
                    IconButton(onClick = { navController.navigate(ROUTE_VIEW_UPLOAD) }) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "Localized description",
                        )
                    }

                    Spacer(modifier = Modifier.width(30.dp))
                    IconButton(onClick = { navController.navigate(ROUTE_VIEW_UPLOAD) }) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "Localized description",
                        )
                    }


                }
            )
        }
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = ""
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var context = LocalContext.current
            var productRepository = AddDeleteUpdatePhones(navController, context)
            val emptyProductState = remember { mutableStateOf(Upload("", "", "", "", "")) }
            var emptyProductsListState = remember { mutableStateListOf<Upload>() }
            var uploads = productRepository.AllPhones(emptyProductState, emptyProductsListState)


            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "All Phones",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Cursive,
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn() {
                    items(uploads) {
                        PhoneItem(
                            name = it.name,
                            quantity = it.quantity,
                            price = it.price,
                            id = it.id,
                            navController = navController,
                            productRepository = productRepository,
                            uploadImage = it.imageUrl
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun PhoneItem(name:String, quantity:String, price:String, id:String,
             navController:NavHostController,
             productRepository:AddDeleteUpdatePhones, uploadImage:String) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(300.dp).height(350.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
//            Spacer(modifier = Modifier.height(10.dp))
            Card (modifier = Modifier.height(150.dp).fillMaxWidth(), elevation = CardDefaults.cardElevation(10.dp), ){
                Image(
                    painter = rememberAsyncImagePainter(uploadImage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { navOptions { ROUTE_BOAT_ADD } }
                        .clip(shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                    alignment = Alignment.TopCenter, contentScale = ContentScale.FillBounds
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp).wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = quantity,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = price,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Call")
                    }
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Message")
                    }
                    Button(onClick = {
                        productRepository.deletePhone(id)
                    }) {
                        Text(text = "Delete")
                    }
                }
            }
        }
    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BoatsPreview() {
    ViewPhoneScreen(navController = rememberNavController())
}