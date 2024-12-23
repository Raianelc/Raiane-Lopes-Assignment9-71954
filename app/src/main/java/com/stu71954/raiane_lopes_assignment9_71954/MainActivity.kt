package com.stu71954.raiane_lopes_assignment9_71954

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.time.LocalDate
import java.time.temporal.ChronoUnit

// Main activity class
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Enable edge-to-edge display
        setContent {
            AppContent() // Set the content to the composable function
        }
    }
}

// Function to check if a number is positive
fun checkPositiveNumber(number: Int) {
    require(number > 0) { "The number must be positive." }
}

// Custom exception class for date validation
class DateTooFarException(message: String) : Exception(message)

// Function to validate if the selected date is within 6 months from today
@RequiresApi(Build.VERSION_CODES.O)
fun validateDate(selectedDate: LocalDate) {
    val today = LocalDate.now()
    val maxDate = today.plus(6, ChronoUnit.MONTHS)

    if (selectedDate.isAfter(maxDate)) {
        throw DateTooFarException("Selected date exceeds 6 months from today.")
    }
}

// Composable function to display the app content
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center) // Center the content
    ) {
        PreconditionExampleCatch() // Display precondition example with catch
        PreconditionExampleThrow() // Display precondition example with throw
        DateValidationExample() // Display date validation example
    }
}

// Composable function to demonstrate catching an exception
@Composable
fun PreconditionExampleCatch() {
    val message = remember { mutableStateOf("") }

    try {
        checkPositiveNumber(-5) // This will throw an exception
    } catch (e: IllegalArgumentException) {
        message.value = "Exception caught: ${e.message}"
    }

    Text(text = "Catching Exception Example: ${message.value}")
}

// Composable function to demonstrate throwing an exception
@Composable
fun PreconditionExampleThrow() {
    val message = remember { mutableStateOf("") }

    try {
        checkPositiveNumber(-5) // This will throw an exception
    } catch (e: IllegalArgumentException) {
        message.value = "Throw example ${e.localizedMessage}"
    }
    Text(text = message.value)
}

// Composable function to demonstrate date validation
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateValidationExample() {
    val message = remember { mutableStateOf("") }

    try {
        val futureDate = LocalDate.now().plus(7, ChronoUnit.MONTHS)
        validateDate(futureDate) // This will throw an exception
    } catch (e: DateTooFarException) {
        message.value = "Exception: ${e.message}"
    }

    Text(text = "Date Validation Example: ${message.value}")
}

// Preview function for the composable content
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewAppContent() {
    AppContent()
}