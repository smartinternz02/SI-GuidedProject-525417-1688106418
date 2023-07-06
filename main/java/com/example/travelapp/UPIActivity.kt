package com.example.travelapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelapp.ui.theme.TravelAppTheme
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext


class UPIActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    UpiPaymentPage()
                }
            }
        }
    }
}


enum class PaymentResult {
    Success,
    Cancelled,
    Failed
}

@Composable
fun UpiPaymentPage() {
    val upiIdState = remember { mutableStateOf("") }
    val amountState = remember { mutableStateOf("") }
    val noteState = remember { mutableStateOf("") }
    val paymentResultState = remember { mutableStateOf("") }

    val context = LocalContext.current

    val performPayment = {
        val paymentUri = Uri.parse("upi://pay?pa=${upiIdState.value}&pn=Payee&mc=123456&tid=1234567890&tr=1234567890&tn=${noteState.value}&am=${amountState.value}&cu=INR")
        val paymentIntent = Intent(Intent.ACTION_VIEW, paymentUri)

        val activity = context as ComponentActivity
        val launcher = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val resultCode = result.resultCode
            if (resultCode == ComponentActivity.RESULT_OK) {
                paymentResultState.value = "Payment Success"
            } else if (resultCode == ComponentActivity.RESULT_CANCELED) {
                paymentResultState.value = "Payment Cancelled"
            } else {
                paymentResultState.value = "Payment Failed"
            }
        }

        launcher.launch(paymentIntent)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "UPI Payment", modifier = Modifier.padding(bottom = 16.dp))

        OutlinedTextField(
            value = upiIdState.value,
            onValueChange = { upiIdState.value = it },
            label = { Text(text = "UPI ID") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = amountState.value,
            onValueChange = { amountState.value = it },
            label = { Text(text = "Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = noteState.value,
            onValueChange = { noteState.value = it },
            label = { Text(text = "Note") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { performPayment() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Proceed to Payment")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = paymentResultState.value, style = MaterialTheme.typography.h5)
    }
}