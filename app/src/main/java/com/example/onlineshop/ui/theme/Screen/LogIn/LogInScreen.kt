//package com.example.onlineshop.ui.theme.Screen.LogIn
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.wrapContentWidth
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
//import androidx.compose.material3.Button
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.AbsoluteAlignment
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.example.onlineshop.Data.AuthViewModel
//import com.example.onlineshop.Navigation.ROUTE_HOME
//import com.example.onlineshop.Navigation.ROUTE_REGISTER
//
//@Composable
//fun LogInScreen(navController: NavController) {
//
//    var Email by remember { mutableStateOf(TextFieldValue("")) }
//    var Password by remember { mutableStateOf(TextFieldValue("")) }
//    var isPasswordSeen by remember {
//        mutableStateOf(false)
//    }
//    var context= LocalContext.current
//
//    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        Spacer(modifier = Modifier.height(20.dp))
//        Text(text = "Welcome Back")
//        Spacer(modifier = Modifier.height(20.dp))
//        OutlinedTextField(value = Email, onValueChange = {Email = it},
//            label = { Text(text = "Email or Phone Number")})
//        OutlinedTextField(value = Password,
//            onValueChange = {Password = it},
//            label = { Text(text = "PassWord")},
//            visualTransformation = if (isPasswordSeen) VisualTransformation.None else PasswordVisualTransformation(),
//            trailingIcon = {
//                IconButton(onClick = { isPasswordSeen  =! isPasswordSeen}) {
//                    val icon = if (isPasswordSeen) Icons.Default.VisibilityOff else Icons.Default.Visibility
//                    Icon(imageVector = icon, contentDescription = null)
//
//                }
//            }
//            )
//        Spacer(modifier = Modifier.height(20.dp))
//        Row (modifier =Modifier, horizontalArrangement = Arrangement.SpaceBetween  ){
//            Button(onClick = {
//                val mylogin= AuthViewModel(navController, context )
//                mylogin.login(Email.text.trim(),Password.text.trim())
//                navController.navigate(ROUTE_HOME)
//            }) {
//                Text(text = "LogIn")
//            }
//
//            Button(onClick = {
//                navController.navigate(ROUTE_REGISTER)
//            }) {
//                Text(text = "Sign Up")
//            }
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Text(text = "Forgot PassWord", modifier = Modifier.clickable {  })
//        Text(text = "")
//
//    }
//
//
//}
//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun LogInPreview(){
//    LogInScreen(navController = rememberNavController())
//}







package com.ehsankhormali.userprofileusingfirebase.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.onlineshop.Navigation.ROUTE_HOME
import com.example.onlineshop.Navigation.ROUTE_REGISTER
import com.google.firebase.auth.FirebaseAuth
import com.example.onlineshop.R

@Composable
fun LogInScreen(navController: NavController){
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Login",
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 40.sp
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(text = "Email address") },
                placeholder = { Text(text = "Email address") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                trailingIcon = {
                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(
                            imageVector = if (passwordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Password visibility",
                            tint = if (passwordVisible.value) colorResource(id = R.color.purple_700) else Color.Gray
                        )
                    }
                },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Password") },
                singleLine = true,
                visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.padding(10.dp))
            val context = LocalContext.current
            Button(
                onClick = {
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email.value, password.value)
                        .addOnSuccessListener() {
                            navController.navigate(ROUTE_HOME)
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                context,
                                it.message,
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
            ) {
                Text(
                    text = "Login"
                )
            }

            Spacer(modifier = Modifier.padding(20.dp))
            Row {
                Text(
                    text = "Don't have an account? ",
                )
                Text(
                    text = "Register",
                    modifier = Modifier.clickable(onClick = {
                        navController.navigate(ROUTE_REGISTER)
                    }),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.padding(20.dp))
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLogin(navController: NavController= rememberNavController()){
    LogInScreen(navController = navController)
}