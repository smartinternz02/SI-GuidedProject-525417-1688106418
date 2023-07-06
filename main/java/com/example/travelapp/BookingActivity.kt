package com.example.travelapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.travelapp.ui.theme.TravelAppTheme
import androidx.compose.ui.platform.LocalContext

class BookingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current

            TravelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BookingPage(context)
                }
            }
        }
    }
}
@Composable
fun BookingPage(context: Context) {
    val trips = listOf(
        Trip("Bali", "$9,500"),
        Trip("Paris", "$11,000"),
        Trip("Singapore", "$11,900")
    )

    var selectedTrip by remember { mutableStateOf<Trip?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.travel),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Text(
                text = "Choose a Trip",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Display trip options
            Column {
                trips.forEach { trip ->
                    TripOption(
                        trip = trip,
                        onTripSelected = { selectedTrip = trip },
                        isSelected = selectedTrip == trip
                    )
                    Divider(color = Color.LightGray, thickness = 1.dp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { showDialog = true },
                enabled = selectedTrip != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3))
            ) {
                Text(
                    text = "Proceed to Payment",
                    style = MaterialTheme.typography.button,
                    color = Color.White
                )
            }
        }

        if (showDialog) {
            PaymentDialog(selectedTrip, { paymentMethod ->
                showDialog = false
                selectedTrip = null

                // Navigate to the respective payment method activity based on the selected payment method
                when (paymentMethod) {
                    "Credit Card/Debit Card" -> {
                        val intent = Intent(context, CreditDebitCardActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    }
                }
                when (paymentMethod){
                    "Net Banking" -> {
                        val intent = Intent(context, NetbankActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    }
                }
                when (paymentMethod){
                    "UPI" -> {
                        val intent = Intent(context, UPIActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    }
                }

            }) {
                showDialog = false
                selectedTrip = null
            }
        }


        Spacer(modifier = Modifier.height(28.dp))
        // Illustration and text overlay
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Discover Your Dream Destination",
                style = MaterialTheme.typography.h5,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Choose from a variety of amazing trips around the world. Book your adventure today!",
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun TripOption(trip: Trip, onTripSelected: () -> Unit, isSelected: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTripSelected() },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = if (isSelected) Color(0xFF2196F3) else Color.White
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = trip.destination,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(1f),
                color = if (isSelected) Color.White else Color.Black
            )
            Text(
                text = trip.price,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp),
                color = if (isSelected) Color.White else Color.Black
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PaymentDialog(trip: Trip?, onPaymentConfirmed: (String) -> Unit, onCancel: () -> Unit) {
    var paymentMethod by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onCancel() },
        title = { Text(text = "Payment Confirmation") },
        text = {
            trip?.let {
                Text(
                    text = "Confirm payment for ${it.destination} (${it.price})?",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            Column(Modifier.padding(top = 24.dp)) {
                Text(text = "Select Payment Method:")
                Spacer(modifier = Modifier.height(16.dp))
                RadioButtonGroup(
                    items = listOf("Credit Card/Debit Card", "Net Banking", "UPI"),
                    selectedOption = paymentMethod,
                    onOptionSelected = { paymentMethod = it }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onPaymentConfirmed(paymentMethod)
                    onCancel()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF2196F3))
            ) {
                Text(text = "Pay", color = Color.White)
            }
        },
        dismissButton = {
            Button(
                onClick = { onCancel() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Cancel")
            }
        },
        shape = RoundedCornerShape(8.dp),
        properties = DialogProperties(usePlatformDefaultWidth = false)
    )
}


@Composable
fun RadioButtonGroup(
    items: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Column {
        items.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = { onOptionSelected(option) },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = option)
            }
        }
    }
}

data class Trip(val destination: String, val price: String)
