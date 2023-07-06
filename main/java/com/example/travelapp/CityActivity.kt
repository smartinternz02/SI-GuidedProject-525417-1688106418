package com.example.travelapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelapp.ui.theme.TravelAppTheme

class CityActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    appy()
                }
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@Composable
fun appy() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(elevation = 10.dp) {
            Text(
                text = "Select Destination",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold
            )
        }

        val cities = mutableStateListOf("Bali", "Paris", "Singapore")
        val images = mutableStateListOf(
            R.drawable.bali,
            R.drawable.paris,
            R.drawable.singapore
        )
        LazyColumn {
            items(cities.size) { ct ->
                Spacer(modifier = Modifier.height(40.dp))
                ImageButton(cities[ct], images[ct])
            }
        }
    }
}

@Composable
fun ImageButton(city: String, image: Int) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(context, HotelActivity::class.java)
            context.startActivity(intent)
        },
        modifier = Modifier
            .padding(0.dp)
            .clip(RoundedCornerShape(10))
            .border(width = 3.dp, shape = RoundedCornerShape(10), color = Color.LightGray),
        elevation = ButtonDefaults.elevation(2.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0x16000000)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id =R.drawable.img),
                contentDescription = "",
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 20.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Explore Hotels in the city of $city",
                fontFamily = FontFamily.Cursive,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = Color.DarkGray
            )
        }
    }
}