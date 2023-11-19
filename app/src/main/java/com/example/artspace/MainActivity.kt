package com.example.artspace

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.unit.TextUnit


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp(widthSizeClass = calculateWindowSizeClass(activity = this).widthSizeClass)
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(widthSizeClass: WindowWidthSizeClass, modifier: Modifier = Modifier) {
    var index by remember {
        mutableStateOf(1)
    }
    val drawables = arrayOf(R.drawable.rynek, R.drawable.downtownlosangeles, R.drawable.toweoflondon)
    val descriptions = arrayOf(R.array.Wroclaw,  R.array.LosAngeles, R.array.London)
    var imgIndex = drawables[index]
    var descriptionIndex = descriptions[index]
    if (widthSizeClass == WindowWidthSizeClass.Compact) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
            ,
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ArtBody(imgIndex, modifier = Modifier.weight(1f))
            ArtDescription(30.sp, descriptionIndex)
            ArtOptions(
                moveLeft = {index = moveLeft(drawables, index) },
                moveRight = {index = moveRight(drawables, index)}
            )
        }
    }
    else {
        Row (
            modifier = modifier,
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArtBody(imgIndex, modifier = Modifier.weight(2f))
            Column (
                modifier = Modifier.weight(1f)
            ) {
                ArtDescription(80.sp, descriptionIndex, modifier = Modifier.weight(6f))
                ArtOptions(
                    moveLeft = {index = moveLeft(drawables, index) },
                    moveRight = {index = moveRight(drawables, index)
                    },
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }


}

@Composable
fun ArtBody(imgIndex: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .background(Color.White)
            .shadow(5.dp)
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier.padding(40.dp),
            painter = painterResource(id = imgIndex),
            contentDescription = null
        )
    }
}

@Composable
fun ArtDescription(fontSize: TextUnit,descriptionIndex: Int, modifier: Modifier = Modifier) {
    val description = stringArrayResource(id = descriptionIndex)
    Column (
        modifier = modifier
            //.height(150.dp)
            .fillMaxSize()
            .background(Color.Green.copy(alpha = 0.4f)),
verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    Text(
        description[0],
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        lineHeight = fontSize
    )
        Row {
            Text(description[1],
                fontSize = fontSize / 2)
            Spacer(modifier = modifier.width(30.dp))
            Text(description[2],
                fontSize = fontSize / 2)
        }
    }
}

@Composable
fun ArtOptions(moveLeft: () -> Unit, moveRight: () -> Unit , modifier: Modifier = Modifier) {
    Row (
        modifier,
    ) {
        Button(
            modifier = modifier
                .weight(1f)
                .fillMaxHeight()
                .testTag("moveLeft"),
            onClick = moveLeft) {
          Text("Previous")
        }
        Button(
            modifier = modifier
                .weight(1f)
                .fillMaxHeight(),
            onClick = moveRight) {
            Text("Next")
        }
    }
}

fun moveLeft(drawables: Array<Int>, index: Int): Int {
    if (index == 0) {
        return drawables.size - 1
    }
    else {
        return index-1
    }
}

fun moveRight(drawables: Array<Int>, index: Int): Int {
    if (index == drawables.size - 1) {
        return 0
    }
    else {
        return index+1
    }
}
