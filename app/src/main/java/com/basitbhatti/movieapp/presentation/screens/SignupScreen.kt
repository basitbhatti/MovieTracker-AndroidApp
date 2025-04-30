package com.basitbhatti.movieapp.presentation.screens

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.basitbhatti.movieapp.R
import com.basitbhatti.movieapp.navigation.Screen
import com.basitbhatti.movieapp.presentation.theme.LightGrayColor
import com.basitbhatti.movieapp.presentation.theme.fontFamily
import com.basitbhatti.movieapp.presentation.viewmodel.AuthenticationViewModel
import com.basitbhatti.movieapp.utils.SESSION_ID
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pdftoexcel.bankstatementconverter.utils.PrefManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AuthenticationViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val fieldColors = TextFieldDefaults.colors(
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        focusedContainerColor = LightGrayColor,
        unfocusedContainerColor = LightGrayColor,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Sign Up",
            fontSize = 28.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
        )

        Text(
            text = "Create your account",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier.padding(top = 5.dp),
        )

        Text(
            text = "Name",
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 50.dp),
            textAlign = TextAlign.Start,
        )

        OutlinedTextField(
            value = name,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { name = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp),
            placeholder = {
                Text("John Doe")
            },
            shape = RoundedCornerShape(12.dp),
            colors = fieldColors
        )



        Text(
            text = "Email",
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 15.dp),
            textAlign = TextAlign.Start,
        )

        OutlinedTextField(
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp),
            placeholder = {
                Text("example@gmail.com")
            },
            shape = RoundedCornerShape(12.dp),
            colors = fieldColors
        )

        Text(
            text = "Password",
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 15.dp),
            textAlign = TextAlign.Start,
        )

        OutlinedTextField(
            value = password,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            onValueChange = { password = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 20.dp, end = 20.dp),
            placeholder = {
                Text("*******")
            },
            trailingIcon = {
                val icon = if (!passwordVisible) Icons.Rounded.Visibility
                else Icons.Rounded.VisibilityOff

                IconButton(onClick = {
                    passwordVisible = !passwordVisible
                }) {
                    Icon(imageVector = icon, contentDescription = "Show Password")
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = fieldColors
        )

        Button(
            onClick = {

                if (name.isEmpty()) {
                    Toast.makeText(context, "Enter name", Toast.LENGTH_SHORT).show()
                } else if (email.isEmpty()) {
                    Toast.makeText(context, "Enter email address", Toast.LENGTH_SHORT).show()
                } else if (password.isEmpty()) {
                    Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show()
                } else {
                    signUp(context, viewModel, navController, name, email, password)
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 25.dp),
        ) {
            Text(
                text = "Sign In",
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 5.dp),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
        }

        Text(
            text = "Or", fontFamily = fontFamily, fontWeight = FontWeight.Normal
        )

        Card(
            modifier
                .padding(vertical = 15.dp)
                .width(230.dp)
                .height(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightGrayColor
            )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.google),
                    contentDescription = "Google"
                )

                Text(
                    text = "Sign in with Google",
                    modifier = Modifier.padding(start = 15.dp),
                    fontFamily = fontFamily,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }


        Row(
            modifier = Modifier.padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account?",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "Sign in",
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                modifier = modifier
                    .padding(start = 5.dp)
                    .clickable {
                        navController.navigate(Screen.LoginScreen.route) {
                            popUpTo(Screen.SignupScreen.route) {
                                inclusive = true
                            }
                        }
                    },
                textAlign = TextAlign.End,
            )
        }

    }

}

fun signUp(
    context: Context,
    viewmodel: AuthenticationViewModel,
    controller: NavHostController,
    name: String,
    email: String,
    password: String
) {

    val auth = Firebase.auth
    val pref = PrefManager(context)

    val dialog = ProgressDialog(context)
    dialog.setCancelable(false)
    dialog.setMessage("Creating your account...")
    dialog.show()

    auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
        viewmodel.getRequestToken()

        dialog.setMessage("Setting up the environment...")

        CoroutineScope(Dispatchers.IO).launch {

            viewmodel.requestToken.collect { token ->

                Log.d("TAGTOKEN", "signUp: $token")

                if (token != null) {
                    viewmodel.getSession()
                    viewmodel.session.collect { session ->
                        dialog.setMessage("Finalizing...")
                        pref.saveString(SESSION_ID, session?.session_id!!)



                    }


                }

            }

        }

    }.addOnFailureListener {
        dialog.dismiss()
        Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
    }

}

@Preview
@Composable
private fun Prev() {
    SignupScreen(navController = rememberNavController())
}