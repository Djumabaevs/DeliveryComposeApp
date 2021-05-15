package com.example.hellocompose.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hellocompose.R
import com.example.hellocompose.ui.theme.HelloComposeTheme
import com.example.hellocompose.ui.theme.itemsColor
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.buttons
import com.vanpra.composematerialdialogs.datetime.datepicker.datepicker


@OptIn(ExperimentalAnimatedInsets::class)
@Composable
fun loginScreen(vm: MainViewModel) {
    val name: String by vm.state.observeAsState("")
    Log.d("Nurs", name)
    ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
        Column(
            modifier = Modifier
                .padding(28.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.registration),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )
            }

            Spacer(Modifier.height(16.dp))
            var name by remember { mutableStateOf("") }
            Text(stringResource(R.string.name))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                value = name,
                onValueChange = { name = it },
//                        label = { Text("имя") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = itemsColor,
                    cursorColor = itemsColor,
                )
            )
            Spacer(Modifier.height(16.dp))
            var surname by remember { mutableStateOf("") }
            Text(stringResource(R.string.surname))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                value = surname,
                onValueChange = { surname = it },
//                        label = { Text("имя") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = itemsColor,
                    cursorColor = itemsColor,
                )
            )
            Spacer(Modifier.height(16.dp))
            var telefon by remember { mutableStateOf("") }
            Text(stringResource(R.string.telefon))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                value = telefon,
                onValueChange = { telefon = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        label = { Text("телефон") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = itemsColor,
                    cursorColor = itemsColor,
                )
            )
            Spacer(Modifier.height(16.dp))
            var email by remember { mutableStateOf("") }
            Text("e-Mail")
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                value = email,
                onValueChange = { email = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                        label = { Text("телефон") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = itemsColor,
                    cursorColor = itemsColor,
                )
            )

            Spacer(Modifier.height(16.dp))
            var birthDate by remember { mutableStateOf("") }
            Text(stringResource(R.string.date_of_birth))
            val dialog = remember { MaterialDialog() }
            dialog.build {

                datepicker { date ->
                    birthDate = date.toString()
                    // Do stuff with java.time.LocalDate object which is passed in
                }
                buttons {
                    positiveButton("Ok")
                    negativeButton("Cancel")
                }
            }
            Spacer(Modifier.padding(4.dp))
            OutlinedButton(
                onClick = { dialog.show() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                border = BorderStroke(
                    ButtonDefaults.OutlinedBorderSize, MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
                )
            ) {
                val style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    letterSpacing = 0.1.sp
                )
                Text(text = birthDate,color = Color.Black,style = style)
            }
            Spacer(Modifier.height(16.dp))

            Spacer(Modifier.height(16.dp))


/* This should be called in an onClick or an Effect */

            OutlinedButton(
                onClick = {
                    vm.login(
                        name = name,
                        surname = surname,
                        phone = telefon,
                        email = email,
                        dateOfBirth = "2021-05-13"
                    )
                },
                modifier = Modifier
                    .navigationBarsWithImePadding()
                    .align(alignment = Alignment.CenterHorizontally),  //avoid the oval shape
                border = BorderStroke(1.dp, itemsColor),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = itemsColor)
            ) {
                val style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    letterSpacing = 0.15.sp
                )
                Text(stringResource(R.string.register), style = style)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.already_signed_in))
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    stringResource(R.string.autorize),
                    color = Color.Blue,
                    modifier = Modifier.clickable {

                    })
            }


            Spacer(Modifier.height(96.dp))
        }
    }
}

@ExperimentalAnimatedInsets
@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    HelloComposeTheme {
//        loginScreen(vm = )
    }
}