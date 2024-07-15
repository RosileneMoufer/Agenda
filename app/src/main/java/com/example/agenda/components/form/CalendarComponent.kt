package com.example.agenda.components.form

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.agenda.viewmodel.TaskFormViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarComponent(formViewModel: TaskFormViewModel) {
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)

    DatePickerDialog(
        colors = DatePickerDefaults.colors(
            containerColor = MaterialTheme.colorScheme.secondary,
        ),
        onDismissRequest = {
            // Action when the dialog is dismissed without selecting a date
            formViewModel.changeStateOpenCalendar(false)
        },
        confirmButton = {
            // Confirm button with custom action and styling
            TextButton(
                onClick = {
                    // Action to set the selected date and close the dialog
                    formViewModel.changeStateOpenCalendar(false)
                    formViewModel.setDateValue(datePickerState.selectedDateMillis?.convertMillisToDate() ?: "")

                }
            ) {
                Text("OK", color = MaterialTheme.colorScheme.primary)
            }
        },
        dismissButton = {
            // Dismiss button to close the dialog without selecting a date
            TextButton(
                onClick = {
                    formViewModel.changeStateOpenCalendar(false)
                }
            ) {
                Text("CANCEL", color = MaterialTheme.colorScheme.error)
            }
        }
    ) {
        // The actual DatePicker component within the dialog
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                selectedDayContentColor = MaterialTheme.colorScheme.secondary,
                selectedYearContainerColor = MaterialTheme.colorScheme.tertiary,
                selectedYearContentColor = MaterialTheme.colorScheme.background,
                todayContentColor = MaterialTheme.colorScheme.primary,
                todayDateBorderColor = MaterialTheme.colorScheme.primary,
            )
        )
    }
}

fun Long.convertMillisToDate(): String {
    // Create a calendar instance in the default time zone
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@convertMillisToDate
        // Adjust for the time zone offset to get the correct local date
        val zoneOffset = get(Calendar.ZONE_OFFSET)
        val dstOffset = get(Calendar.DST_OFFSET)
        add(Calendar.MILLISECOND, -(zoneOffset + dstOffset))
    }
    // Format the calendar time in the specified format
    val sdf = SimpleDateFormat("MMM dd, yyyy", Locale.US)
    return sdf.format(calendar.time)
}