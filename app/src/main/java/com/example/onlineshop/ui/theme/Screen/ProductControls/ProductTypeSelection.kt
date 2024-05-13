package com.example.onlineshop.ui.theme.Screen.ProductControls

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.onlineshop.Navigation.ROUTE_BOAT_ADD
import com.example.onlineshop.Navigation.ROUTE_CAR
import com.example.onlineshop.Navigation.ROUTE_HOME
import com.example.onlineshop.Navigation.ROUTE_PHONE
import com.example.onlineshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductTypeScreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        "Please Select ",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis, fontSize = 19.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(ROUTE_HOME) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
//        ScrollContent(innerPadding)
        Text(modifier = Modifier.padding(innerPadding), text = "")
        Spacer(modifier = Modifier.height(90.dp))

        @Composable
        fun BoxPicker(title: String, imageResId: Int, onClick: () -> Unit) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .border(
                            width = 1.dp,
                            shape = RoundedCornerShape(size = 10.dp),
                            color = Color.White
                        )
                ) {
                    Row(modifier = Modifier.padding(10.dp)) {
                        Card(
                            modifier = Modifier
                                .size(40.dp)
                        ) {
                            Image(
                                painter = painterResource(id = imageResId),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.width(40.dp))
                        Text(text = title, fontSize = 22.sp)
                        Spacer(modifier = Modifier.width(40.dp))
                        IconButton(onClick = {onClick ()} ) {
                            Icon(
                                Icons.Filled.ArrowForward,
                                contentDescription = "Localized description",
                                tint = Color.Magenta,
                            )
                        }



                    }
                }
            }
        }


        Column(
            modifier = Modifier
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(100.dp))
            BoxPicker(title = "Boats", imageResId = R.drawable.images, onClick = {navController.navigate(
                ROUTE_BOAT_ADD)} )
            Spacer(modifier = Modifier.height(100.dp))
            BoxPicker(title = "Phones", imageResId = R.drawable.images, onClick = {navController.navigate(
                ROUTE_PHONE)} )
            Spacer(modifier = Modifier.height(100.dp))
            BoxPicker(title = "Phones", imageResId = R.drawable.images, onClick = {navController.navigate(
                ROUTE_CAR)} )
        }
    }
}