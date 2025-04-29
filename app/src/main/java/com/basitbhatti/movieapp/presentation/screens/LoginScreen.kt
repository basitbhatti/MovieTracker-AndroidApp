package com.basitbhatti.movieapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basitbhatti.movieapp.presentation.theme.LightGrayColor
import com.basitbhatti.movieapp.presentation.theme.fontFamily

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    var passwordVisible by remember {
        mutableStateOf(true)
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
            text = "Sign In",
            fontSize = 28.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
        )

        Text(
            text = "Sign into your account",
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier.padding(top = 5.dp),
        )


        Text(
            text = "Email",
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 50.dp),
            textAlign = TextAlign.Start,
        )

        OutlinedTextField(
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 15.dp, end = 15.dp),
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
                .padding(start = 15.dp, end = 15.dp, top = 25.dp),
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
                .padding(top = 10.dp, start = 15.dp, end = 15.dp),
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
            onClick = { signIn(email, password) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 25.dp),
        ) {
            Text(
                text = "Sign In",
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 5.dp),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
        }


    }


}

fun signIn(email: String, password: String) {

}


@Preview
@Composable
private fun Prev() {
    LoginScreen()
}