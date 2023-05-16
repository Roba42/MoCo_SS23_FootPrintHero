package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.view.MainScreen

//MainActivity hauptsächlich für die UI-Steuerung zuständig ist.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

//TODO wofür?
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        MainScreen()
    }
}





