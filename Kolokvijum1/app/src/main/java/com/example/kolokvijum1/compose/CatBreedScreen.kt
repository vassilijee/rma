package com.example.kolokvijum1.compose

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kolokvijum1.compose.model.CatData
import com.example.kolokvijum1.compose.model.createCatApiService
import com.example.kolokvijum1.repository.SampleData
import com.example.kolokvijum1.ui.theme.Kolokvijum1Theme
import kotlinx.coroutines.runBlocking
import java.io.IOException


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatBreedScreen(
    items: List<CatData>,
    onAddClick: () -> Unit,
    onItemClick: (CatData) -> Unit,
) {
    Scaffold(topBar = {
        Column {
            CenterAlignedTopAppBar(title = { Text(text = "Cat Breed List") })
            Divider()
        }

    }, floatingActionButton = {
        FloatingActionButton(onClick = onAddClick) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }, content = {
        // LazyColumn should be used for infinite lists which we will
        // learn soon. In the meantime we can use Column with verticalScroll
        // modifier so it can be scrollable.
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            items.forEach {
                Column {
                    key(it.name) {
                        BreedListItem(
                            data = it,
                            onClick = {
                                onItemClick(it)
                            },
                        )
                    }
                }
            }

        }
    })

}

@Composable
fun TemperamentChip(
    data: String
) {
    SuggestionChip(onClick = { }, label = {
        Text(
            text = data, fontSize = 15.sp
        )
    }, modifier = Modifier.size(Dp.Unspecified, 20.dp)

    )
}

@Composable
private fun BreedListItem(
    data: CatData,
    onClick: () -> Unit,
) {
    Column {
        ListItem(headlineContent = {
            Text(
                text = data.getWithAltNames(), fontWeight = FontWeight.Bold, fontSize = 22.sp
                //modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }, supportingContent = {
            Text(data.getDesc250())
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                data.getTemperamentArray3(data.temperament).forEach { temperament ->
                    TemperamentChip(temperament)
                    Spacer(modifier = Modifier.width(8.dp)) // Add spacing between chips
                }

            }
        }, trailingContent = { Icon(Icons.Default.PlayArrow, "More") })
        Divider()
        //HorizontalDivider()
    }
}


@Preview
@Composable
fun PreviewCatBreedScreen() {
    Kolokvijum1Theme {
        CatBreedScreen(
            items = SampleData,
            onItemClick = {},
            onAddClick = {},
        )
    }
}