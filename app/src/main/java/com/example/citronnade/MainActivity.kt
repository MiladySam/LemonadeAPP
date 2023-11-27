package com.example.citronnade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.citronnade.ui.theme.CitronnadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CitronnadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun DesignUI(modifier: Modifier) {

    var lindexImage by remember { mutableStateOf(1) }
    var pressageCitron by remember { mutableStateOf(2) }

    var apparition_text = when (lindexImage) {
        1 -> R.string.etape_1
        2 -> R.string.etape_2
        3 -> R.string.etape_3
        4 -> R.string.etape_4
        else -> R.string.etape_1
    }

    var sourceImage = when (lindexImage) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = sourceImage),
            contentDescription = "1",
            modifier = Modifier
                .wrapContentSize()
                .clickable {


                    when (lindexImage) {
                        1 -> {
                            pressageCitron = (1..6).random()
                            lindexImage += 1
                        }

                        2 -> {
                            if (pressageCitron == 0) {
                                lindexImage += 1
                            } else {
                                pressageCitron--
                            }
                        }

                        3 -> {
                            lindexImage += 1
                        }

                        4 -> {
                            lindexImage = 1
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = apparition_text))
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    CitronnadeTheme {
        DesignUI(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
    }
}
