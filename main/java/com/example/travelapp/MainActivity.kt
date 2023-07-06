package com.example.travelapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelapp.ui.theme.TravelAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Dashboard(this)
                }
            }
        }
    }
}
@Composable
fun Dashboard(context:Context){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            Text(
                fontSize = 40.sp,
                color = Color(android.graphics.Color.rgb(120, 40, 251)),
                fontFamily = FontFamily.Cursive,
                text = "Wanderlust Travel"
            )
            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable {
                        val intent = Intent(context, DestinationActivity::class.java)
                        context.startActivity(intent)
                    },
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ) {
                Box(Modifier.padding(16.dp)) {
                    Text(
                        text = "Explore amazing destinations",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    Image(
                        painter = painterResource(R.drawable.travel),
                        contentDescription = "Destination",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(120.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context, BookingActivity::class.java)
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3))
                ) {
                    Text(
                        text = "Bookings",
                        style = MaterialTheme.typography.button,
                        color = Color.White
                    )
                }

                Button(
                    onClick = {
                        val intent = Intent(context, FlightsActivity::class.java)
                        context.startActivity(intent)},
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))
                ) {
                    Text(
                        text = "Flights",
                        style = MaterialTheme.typography.button,
                        color = Color.White
                    )
                }

                Button(
                    onClick = {
                        val intent = Intent(context, CityActivity::class.java)
                        context.startActivity(intent)},
                    modifier = Modifier
                        .weight(1f)
                        .height(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF9800))
                ) {
                    Text(
                        text = "Hotels",
                        style = MaterialTheme.typography.button,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Featured Destinations",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = "View All",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color(0xFF2196F3),
                    modifier = Modifier.clickable(onClick = { /* Handle click */ })
                )
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(end = 16.dp)
            ) {
                item {
                    DestinationItem(
                        name="Bali",
                        imageResId = R.drawable.bali,
                        rating = 4.5f
                    )
                }
                item {
                    DestinationItem(
                        name="Paris",
                        imageResId = R.drawable.paris,
                        rating = 4.0f
                    )
                }
                item {
                    DestinationItem(
                        name="Singapore",
                        imageResId = R.drawable.singapore,
                        rating = 4.8f
                    )
                }
            }
        }
    }
}

@Composable
fun DestinationItem(name: String, imageResId: Int, rating: Float) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .size(180.dp, 240.dp)
            .padding(end = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Box(Modifier.background(Color.LightGray)) {
            Image(
                painter = painterResource(imageResId),
                contentDescription = "Destination",
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.h6,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$rating",
                            style = MaterialTheme.typography.body2,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            val intent = when (name) {
                                "Bali" -> Intent(context, BaliActivity::class.java)
                                "Paris" -> Intent(context, ParisActivity::class.java)
                                "Singapore" -> Intent(context, SingaporeActivity::class.java)
                                else -> throw IllegalArgumentException("Invalid destination: $name")
                            }
                            context.startActivity(intent)
                                  },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3))
                    ) {
                        Text(
                            text = "More Details",
                            style = MaterialTheme.typography.button,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}



