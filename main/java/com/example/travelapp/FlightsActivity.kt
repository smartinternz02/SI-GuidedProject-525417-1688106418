package com.example.travelapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelapp.ui.theme.TravelAppTheme
import java.text.SimpleDateFormat
import java.util.Date

class FlightsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    flightdetails(this)

                }
            }
        }
    }
}

@Composable
fun flightdetails(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            fontSize = 40.sp,
            color = Color(android.graphics.Color.rgb(120, 40, 251)),
            fontFamily = FontFamily.Cursive,
            text = "Flights",
            modifier = Modifier.padding(bottom = 16.dp)
        )




        Spacer(modifier = Modifier.height(16.dp))

        // Add flight search results or flight booking options
        // You can use a LazyColumn or RecyclerView to display the list of flights
        // Each flight item can be a Card or a custom composable representing flight details

        // Example of flight search results using LazyColumn
        val flights = listOf(
            FlightInfo("FL1", "Chennai", "Bali", "₹10000", getCurrentDate(),"5:30 AM"),
            FlightInfo("FL2", "Srinagar", "Chennai", "₹9500", getCurrentDate(),"9:30 AM"),
            FlightInfo("FL3", "Delhi", "Assam", "₹8500", getCurrentDate(),"10:30 PM"),
            FlightInfo("IL1", "Delhi", "Paris", "₹11500", getCurrentDate(),"8:30 AM"),
            FlightInfo("IL2", "Lucknow", "Chennai", "₹5050", getCurrentDate(),"5:45 PM"),
            FlightInfo("IL3", "Delhi", "Chennai", "₹4950", getCurrentDate(),"7:00 AM"),
            FlightInfo("IL4", "Chennai", "Singapore", "₹19000", getCurrentDate(),"11:30 AM"),
            FlightInfo("HL1", "Paris", "Chennai", "₹19500", getCurrentDate(),"11:45 PM"),
            FlightInfo("HL2", "Delhi", "Bali", "₹18500", getCurrentDate(),"12:30 PM"),
            FlightInfo("HL3", "Delhi", "Paris", "₹11500", getCurrentDate(),"11:45 PM"),
            FlightInfo("HL4", "Mumbai", "Singapore", "₹15050", getCurrentDate(),"11:00 AM"),
            FlightInfo("HL9", "Mumbai", "Bali", "₹14950", getCurrentDate(),"10:30 AM"),
        )
        var searchQuery by remember { mutableStateOf("") }
        val filteredFlights = if (searchQuery.isNotEmpty()) {
            flights.filter { flight ->
                flight.departure.contains(searchQuery, ignoreCase = true) ||
                        flight.arrival.contains(searchQuery, ignoreCase = true)
            }
        } else {
            flights
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Departure or Arrival Airport") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
            )
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(filteredFlights) { flight ->
                FlightItem(
                    flightNumber = flight.flightNumber,
                    departure = flight.departure,
                    arrival = flight.arrival,
                    price = flight.price,
                    date = flight.date,
                    time = flight.time,
                    modifier = Modifier.padding(vertical = 8.dp),
                    onClick = {
                        startPaymentActivity(context, flight) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add any additional UI elements or components as per your requirements
        // For example, filters, sorting options, flight details expansion/collapse, etc.
    }
}

data class FlightInfo(
    val flightNumber: String,
    val departure: String,
    val arrival: String,
    val price: String,
    val date: String,
    val time:String
)

@Composable
fun FlightItem(
    flightNumber: String,
    departure: String,
    arrival: String,
    price: String,
    date: String,
    time: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() } ,
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0xFFEFEFEF) // Custom background color
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "\u2708", // Airplane Unicode character
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(end = 8.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Flight $flightNumber",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "Departure Icon",
                    tint = Color.Gray
                )
                Text(
                    text = "Departure: $departure",
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = "Arrival Icon",
                    tint = Color.Gray
                )
                Text(
                    text = "Arrival: $arrival",
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "Time: $time",
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "Date: $date",
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Text(
                text = "Price: $price",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    val currentDate = Date()
    return dateFormat.format(currentDate)
}

fun startPaymentActivity(context: Context, flight: FlightInfo) {
    val intent = Intent(context, PaymentActivity::class.java)
    // Pass the flight details to the PaymentActivity using intent extras
    intent.putExtra("flightNumber", flight.flightNumber)
    intent.putExtra("departure", flight.departure)
    intent.putExtra("arrival", flight.arrival)
    intent.putExtra("price", flight.price)
    intent.putExtra("date", flight.date)
    intent.putExtra("time", flight.time)
    context.startActivity(intent)
}
