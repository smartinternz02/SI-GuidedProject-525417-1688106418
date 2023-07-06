package com.example.travelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelapp.ui.theme.TravelAppTheme

class NetbankActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NetBankingScreen()
                }
            }
        }
    }
}

@Composable
fun NetBankingScreen() {
    var bankName by remember { mutableStateOf(TextFieldValue()) }
    var accountNumber by remember { mutableStateOf(TextFieldValue()) }
    var ifscCode by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Align content vertically to the center
        horizontalAlignment = Alignment.CenterHorizontally // Align content horizontally to the center
    ) {
        Text(
            text = "Net Banking Payment",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = bankName,
            onValueChange = { bankName = it },
            label = { Text("Bank Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 16.dp, end = 16.dp) // Add padding to the TextField
        )

        TextField(
            value = accountNumber,
            onValueChange = { accountNumber = it },
            label = { Text("Account Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, start = 16.dp, end = 16.dp) // Add padding to the TextField
        )

        TextField(
            value = ifscCode,
            onValueChange = { ifscCode = it },
            label = { Text("IFSC Code") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp) // Add padding to the TextField
        )

        Button(
            onClick = { onPaymentComplete(bankName.text, accountNumber.text, ifscCode.text) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp) // Add padding to the Button
                .clip(RoundedCornerShape(28.dp)) // Apply rounded corners to the Button
                .background(Color(0xFF2196F3)) // Set custom background color
                .padding(horizontal = 16.dp), // Add inner padding to the Button
            contentPadding = PaddingValues(0.dp) // Remove default content padding
        ) {
            Text(
                text = "Pay",
                style = MaterialTheme.typography.button,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp) // Add vertical padding to the Text
            )
        }
    }
}

private fun onPaymentComplete(bankName: String, accountNumber: String, ifscCode: String) {
    // Validate the input
    if (bankName.isEmpty() || accountNumber.isEmpty() || ifscCode.isEmpty()) {
        // Show an error message or handle invalid input
        return
    }

    val paymentSuccessful = simulatePaymentProcessing()

    // Handle the payment result
    if (paymentSuccessful) {
        // Payment successful, show success message or navigate to the success screen
        showMessage("Payment successful!")
    } else {
        // Payment failed, show error message or navigate to the error screen
        showMessage("Payment failed. Please try again.")
    }
}

private fun simulatePaymentProcessing(): Boolean {
    // Simulate payment processing by introducing a delay
    Thread.sleep(2000)
    // Return true to simulate a successful payment, or false for a failed payment
    return false
}

private fun showMessage(message: String) {
    println(message)
}
