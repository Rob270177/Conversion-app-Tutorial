package com.flashapps.unitconverter


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.flashapps.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val iConversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }


    fun convertUnit(){
        // ?: = Elvis Operator (defaults null value to 0.0)
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * iConversionFactor.value * 100 /
                oConversionFactor.value).roundToInt() / 100
        outputValue = result.toString()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Text("Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
            inputValue = it
        // Here goes what should happen, when the value of the OutLinedTextField changes
        },
            label = {Text("Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            // Input Box
            Box{
                // Input button
                Button(onClick = { iExpanded = true }) {
                    Text(inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false })
                {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            iConversionFactor.value = 0.01
                            convertUnit()

                        }
                    )

                    DropdownMenuItem(
                        text = { Text("meters") },
                        onClick = { iExpanded = false
                            inputUnit = "meters"
                            iConversionFactor.value = 1.0
                            convertUnit() }
                    )

                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = { iExpanded = false
                            inputUnit = "Feet"
                            iConversionFactor.value = 0.3048
                            convertUnit() }
                    )

                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = { iExpanded = false
                            inputUnit = "Millimeters"
                            iConversionFactor.value = 0.001
                            convertUnit() }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Output Box
            Box{
                // Output Button
                Button(onClick = { oExpanded = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {  oExpanded = false
                            outputUnit = "Centimeters"
                            iConversionFactor.value = 0.01
                            convertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = { Text("meters") },
                        onClick = { oExpanded = false
                            outputUnit = "meters"
                            iConversionFactor.value = 1.00
                            convertUnit() }
                    )

                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = { oExpanded = false
                            outputUnit = "feet"
                            iConversionFactor.value = 0.3048
                            convertUnit() }
                    )

                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = { oExpanded = false
                            outputUnit = "Millimeters"
                            iConversionFactor.value = 0.001
                            convertUnit() }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
            Text("Result:")
    }
}



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()

}



