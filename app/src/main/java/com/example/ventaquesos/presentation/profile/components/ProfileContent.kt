package com.example.ventaquesos.presentation.profile.components


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.alexmamo.firebasesigninwithemailandpassword.R

@Composable
fun ProfileContent(
    padding: PaddingValues
) {
    val context = LocalContext.current
    val imageUri = Uri.parse("android.resource://${context.packageName}/${R.drawable.queso1}")
    val description = "¡Mira este delicioso queso mantecoso Patagonia kern!"

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // La acción se realizó con éxito
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = padding
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.queso1),
                        contentDescription = null // Cambiado de "null" a null
                    )
                    Text(
                        text = "Queso mantecoso Patagonia kern",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make ",
                        fontSize = 13.sp,
                        modifier = Modifier.padding(6.dp),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Image(
                        modifier = Modifier.height(140.dp),
                        painter = painterResource(id = R.drawable.queso2),
                        contentDescription = null // Cambiado de "null" a null
                    )
                    Text(
                        text = "Queso cheddar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                    Text(
                        text = "Otra descripción de queso. Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                        fontSize = 13.sp,
                        modifier = Modifier.padding(6.dp),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray
                    )
                }
            }
        }

        item {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                onClick = {
                    if (isWhatsAppInstalled(context)) {
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_STREAM, imageUri)
                            putExtra(Intent.EXTRA_TEXT, description)
                            type = "image/*"
                            setPackage("com.whatsapp")
                        }
                        launcher.launch(sendIntent)
                    } else {
                        // WhatsApp no está instalado, muestra un mensaje de error
                        Toast.makeText(context, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Compartir en WhatsApp")
            }
        }
    }
}

// Función para verificar si WhatsApp está instalado en el dispositivo
fun isWhatsAppInstalled(context: Context): Boolean {
    val pm = context.packageManager
    try {
        pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
        return true
    } catch (e: PackageManager.NameNotFoundException) {
        return false
    }
}



@Preview(showBackground = true)
@Composable
fun ProfileContentPreviewWithPadding() {
    ProfileContent(PaddingValues(16.dp))
}