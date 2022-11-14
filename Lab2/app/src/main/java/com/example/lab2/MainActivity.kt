package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

// this method is for button view
@Composable
fun clickButton(clicked: () -> Unit){
    Button(onClick = clicked,
    // change button size
    modifier = Modifier
        .width(400.dp),

        ) {
        Text(text = stringResource(id = R.string.changeImage))

    }
}

// this method shows the output
@Composable
fun OutputText(outName: String){
    Text(
        text = stringResource(id = R.string.welcome) + " " + stringResource(id = R.string.art_gallery) + " " +outName + "!",
        fontSize = 24.sp,
//        fontFamily = FontFamily.SansSerif,
        textAlign = TextAlign.Center

    )
}

// this method takes the input
@Composable
fun InputText(name: String, changed: (String) -> Unit){
    TextField(value = name, label = { Text(stringResource(id = R.string.enterName))},
        onValueChange = changed,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp, bottom = 10.dp))
}

@Composable
fun Greeting() {
    // generates random number
    val rands = (0..2).random()

    val imgStringResId = listOf(R.string.petdog, R.string.handArt, R.string.earRingsArt)
    val imgDrawableResId = listOf(R.drawable.dog, R.drawable.hand, R.drawable.earings)
    val name = remember {
        mutableStateOf(value = "")
    }
    val textFieldName = remember {
        mutableStateOf(value = "")
    }

    val randIdImg = remember {
        mutableStateOf(value = rands)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        InputText(name = textFieldName.value, changed = {textFieldName.value = it})
        clickButton ({name.value = textFieldName.value; randIdImg.value = rands})

        Image(painter = painterResource(id = imgDrawableResId[randIdImg.value]), contentDescription = stringResource(
            id = imgStringResId[randIdImg.value]),
            modifier = Modifier
                .padding(top = 40.dp, bottom = 40.dp)
                .size(400.dp)

        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
//                .fillMaxWidth()
                .height(60.dp)
                .background(Color.LightGray)
                .padding(bottom = 0.dp)
                .width(400.dp)
        ){
            OutputText(outName = name.value)
        }

    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab2Theme {
        Greeting()
    }
}