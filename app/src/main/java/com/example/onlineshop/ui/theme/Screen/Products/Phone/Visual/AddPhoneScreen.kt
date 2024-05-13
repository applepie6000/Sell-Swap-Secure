package com.example.onlineshop.ui.theme.Screen.Products.Phone.Visual

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshop.Navigation.ROUTE_ADD_PRODUCT
import com.example.onlineshop.Navigation.ROUTE_BOAT_VIEW_ADD
import com.example.onlineshop.Navigation.ROUTE_HOME
import com.example.onlineshop.Navigation.ROUTE_VIEW_PHONE
import com.example.onlineshop.Navigation.ROUTE_VIEW_UPLOAD
import com.example.onlineshop.ui.theme.Screen.Products.Boats.Logics.AddUpdateDeleteBoat
import com.example.onlineshop.ui.theme.Screen.Products.Phone.Logic.AddDeleteUpdatePhones

@Composable
fun PhoneScreen(navController:NavHostController){
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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Please Fill the Details",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )

            var productName by remember { mutableStateOf("") }
            var productQuantity by remember { mutableStateOf("") }
            var productPrice by remember { mutableStateOf("") }
            val context = LocalContext.current


            Spacer(modifier = Modifier.height(30.dp))

            var modifier = Modifier
            PhoneImage(
                modifier,
                context,
                navController,
                productName.trim(),
                productQuantity.trim(),
                productPrice.trim()
            )


            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text(text = "Phone Brand") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = productQuantity,
                onValueChange = { productQuantity = it },
                label = { Text(text = "Phone Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = productPrice,
                onValueChange = { productPrice = it },
                label = { Text(text = "The Location") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(100.dp))

            Text(text = "Hello")


        }
    }
}


@Composable
fun PhoneImage(modifier: Modifier = Modifier, context: Context, navController: NavHostController, name:String, quantity:String, price:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Please Select an Image")
        Card(modifier = modifier
            .size(200.dp)
            .border(width = 1.dp, shape = RoundedCornerShape(10.dp), color = Color.White)) {
            if (hasImage && imageUri != null) {
                val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
                Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image", contentScale = ContentScale.Crop) }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                },
            ) {
                Text(
                    text = "Select Image"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                var productRepository = AddDeleteUpdatePhones(navController,context)
                productRepository.UploadPhones(name, quantity, price,imageUri!!)
                navController.navigate(ROUTE_VIEW_PHONE)

            }) {
                Text(text = "Upload")
            }
        }
    }
}



