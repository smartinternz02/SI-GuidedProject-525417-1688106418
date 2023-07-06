package com.example.travelapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelapp.ui.theme.TravelAppTheme

class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        setContent {
            TravelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    paid(intent,this)
                }
            }
        }
    }
}
enum class PaymentScreenState {
    FlightDetails,
    PaymentMethods
}


@Composable
fun paid(intent: Intent, context: Context) {
    val flightNumber = intent.getStringExtra("flightNumber")
    val departure = intent.getStringExtra("departure")
    val arrival = intent.getStringExtra("arrival")
    val price = intent.getStringExtra("price")
    val date = intent.getStringExtra("date")
    val time = intent.getStringExtra("time")

    val screenState = remember { mutableStateOf(PaymentScreenState.FlightDetails) }
    val selectedPaymentMethod = remember { mutableStateOf("") }

    // Example UI
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        when (screenState.value) {
            PaymentScreenState.FlightDetails -> {
                Text(
                    text = "Flight Details:",
                    fontSize = 40.sp,
                    color = Color(120, 40, 251),
                    fontFamily = FontFamily.Cursive
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Flight Number: $flightNumber", style = MaterialTheme.typography.body1)
                Text(text = "Departure: $departure", style = MaterialTheme.typography.body1)
                Text(text = "Arrival: $arrival", style = MaterialTheme.typography.body1)
                Text(text = "Price: $price", style = MaterialTheme.typography.body1)
                Text(text = "Date: $date", style = MaterialTheme.typography.body1)
                Text(text = "Time: $time", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        screenState.value = PaymentScreenState.PaymentMethods
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Proceed to Payment", style = MaterialTheme.typography.button)
                }
            }
            PaymentScreenState.PaymentMethods -> {
                AlertDialog(
                    onDismissRequest = { },
                    title = { Text(text = "Select Payment Method") },
                    text = {
                        RadioButtonGroup(
                            items = listOf("Credit Card", "Netbanking", "UPI"),
                            selectedOption = selectedPaymentMethod.value,
                            onOptionSelected = { selectedPaymentMethod.value = it }
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                when (selectedPaymentMethod.value) {
                                    "Credit Card" -> {
                                        val intent =
                                            Intent(context, CreditDebitCardActivity::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        context.startActivity(intent)
                                    }
                                }
                                when (selectedPaymentMethod.value){
                                    "Netbanking" -> {
                                        val intent = Intent(context, NetbankActivity::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        context.startActivity(intent)
                                    }
                                }
                                when (selectedPaymentMethod.value){
                                    "UPI" -> {
                                        val intent = Intent(context, UPIActivity::class.java)
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        context.startActivity(intent)
                                    }
                                }


                            }
                        ) {
                            Text(text = "Pay")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                screenState.value = PaymentScreenState.FlightDetails
                            }
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                )
            }
        }
    }
}

