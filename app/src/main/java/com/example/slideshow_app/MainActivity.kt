package com.example.slideshow_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
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
                FashionGrid()
            }
        }
    }
}



@Composable
fun FashionGrid(modifier: Modifier = Modifier) {
    val fashionItems = remember {
        listOf(
            FashionItem(R.drawable.cpfm_moss, "CPFM Moss", "The CPFM Moss is a collaboration with Nike on the iconic Air Force 1."),
            FashionItem(R.drawable.balenci_mules, "Balenciaga Mules", "Balenciaga's mules are a modern take on classic footwear."),
            FashionItem(R.drawable.rick_hood, "Rick Owens Hood", "The Rick Owens hoodie is a high-fashion piece."),
            FashionItem(R.drawable.balenci_cargos, "Balenciaga Cargos", "Balenciaga's cargo pants combine utility and style."),
            FashionItem(R.drawable.balenci_losttapes, "Balenciaga Lost Tapes", "Flared Denim with retro-inspired silhouette."),
            FashionItem(R.drawable.erd_italian_romance, "ERD Italian Romance", "Showcases intricate embroidery and luxurious fabrics."),
            FashionItem(R.drawable.alyx_bag, "Alyx Backpack", "Technical nylon vest."),
            FashionItem(R.drawable.margi_tabi, "Maison Margiela Tabi", "Split-toe flats"),
            FashionItem(R.drawable.vivi_orb, "Vivienne Westwood Orb", "Signature necklace"),
            FashionItem(R.drawable.undercover_85, "BUndercover 85 Jeans", "Denim jeans by Jun Takahashi’s brand, featuring edgy designs, distressing, and graphic details with a punk-inspired aesthetic.")
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 150.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(fashionItems) { item ->
            FashionItemCard(item)
        }
    }
}

@Composable
fun FashionItemCard(item: FashionItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = item.title,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = item.description,
                    fontSize = 12.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SlideshowappTheme {
        FashionGrid()
    }
}



