package com.example.compose_basics

import android.os.Bundle
import java.io.BufferedReader
import java.io.InputStreamReader
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_basics.ui.theme.Compose_BasicsTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val longString = readTextFile(R.raw.body)
            Compose_BasicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //val fullText = getString(R.string.title) + "\n" + longString
                    //Greeting(fullText)
                    ScreenContent(getString(R.string.title),longString)
                }
            }
        }
    }

    private fun readTextFile(resourceId: Int): String {
        val inputStream = resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val text = reader.readText()
        reader.close()
        return text
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { },  // Empty for now. You can put a Text Composable here for title.
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,  // Use your desired icon
                contentDescription = "Menu Icon"
            )
        },
        actions = {
            // Add other top bar actions if needed
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary) // Setting background color using a Modifier
    )
}






@Composable
fun ScreenContent(title: String, body: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //TopBar() // This should be at the top

        DisplayImage() // Followed by the image

        //Spacer(modifier = Modifier.height(5.dp)) // Some space after the image

        TitleText(text = title) // The title

        //Spacer(modifier = Modifier.height(5.dp))  // Space between title and body

        BodyText(text = body) // The body
    }
}

@Composable
fun DisplayImage() {
    Image(
        painter = painterResource(id = R.drawable.bg_compose_background),
        contentDescription = "Sample Image",
        modifier = Modifier
            .fillMaxWidth() // Filling the width of the screen
            .height(200.dp), // You can adjust this based on your desired height
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium, // Use h5 typography for 24sp

        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(16.dp) // 16dp padding for all sides
    )
}

@Composable
fun BodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium, // Default typography for body text
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 1.dp, bottom = 16.dp)
    )
}

