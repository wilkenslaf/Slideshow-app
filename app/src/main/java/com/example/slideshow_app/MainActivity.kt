package com.example.slideshow_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slideshow_app.ui.theme.SlideshowappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideshowappTheme {
                SlideShow()
            }
        }
    }
}

@Composable
fun SlideShow(modifier: Modifier = Modifier) {
    var current by remember { mutableIntStateOf(1) }
    var inputText by remember { mutableStateOf("") }

    // Use Triple to store image resource, caption, and description
    val (imageResId, caption, description) = when (current) {
        1 -> Triple(R.drawable.cpfm_moss, "1. CPFM Moss", "The CPFM Moss is a collaboration with Nike on the iconic Air Force 1.")
        2 -> Triple(R.drawable.balenci_mules, "2. Balenciaga Mules", "Balenciaga's mules are a modern take on classic footwear, featuring a sleek design and premium materials.")
        3 -> Triple(R.drawable.rick_hood, "3. Rick Owens Hood", "The Rick Owens hoodie is a high-fashion piece, characterized by its oversized fit and minimalist design.")
        4 -> Triple(R.drawable.balenci_cargos, "4. Balenciaga Cargos", "Balenciaga's cargo pants combine utility and style, with multiple pockets and a tailored fit.")
        5 -> Triple(R.drawable.balenci_losttapes, "5. Balenciaga Lost Tapes Flared Denim", "The Balenciaga Lost Tapes Flared Denim features a retro-inspired flared silhouette with a distressed finish, blending 70s nostalgia with modern high-fashion.")
        6 -> Triple(R.drawable.erd_italian_romance, "6. ERD Italian Romance", "ERD's Italian Romance collection showcases intricate embroidery and luxurious fabrics, inspired by vintage Italian aesthetics.")
        else -> Triple(R.drawable.erd_italian_romance, "Unknown", "No description available.")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display Image
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = description,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display Caption
        Text(
            text = caption,
            fontSize = 16.sp,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Display Description
        Text(
            text = description,
            fontSize = 14.sp,
            lineHeight = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Navigation Buttons
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    current = if (current > 1) current - 1 else 6
                }
            ) {
                Text(stringResource(R.string.back))
            }

            Button(
                onClick = {
                    current = if (current < 6) current + 1 else 1
                }
            ) {
                Text(stringResource(R.string.next))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Jump to Specific Image
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text(stringResource(R.string.image_search)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Go Button
        val number = inputText.toIntOrNull()
        Button(
            onClick = {
                if (number != null && number in 1..6) {
                    current = number
                    inputText = ""
                }
            },
            enabled = number in 1..6
        ) {
            Text(stringResource(R.string.goToImage))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SlideshowappTheme {
        SlideShow()
    }
}



