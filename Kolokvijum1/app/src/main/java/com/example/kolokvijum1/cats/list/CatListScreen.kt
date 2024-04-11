package com.example.kolokvijum1.cats.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kolokvijum1.cats.list.model.CatUiModel

private val topBarContainerColor = Color.LightGray

fun NavGraphBuilder.cats(
    route: String,
    onCatClick: (String) -> Unit
) = composable(route = route) {
    val catListViewModel = viewModel<CatListViewModel>()
    val state by catListViewModel.state.collectAsState()

    CatListScreen(
        state = state,
        onCatClick = onCatClick,

//        onItemClick = {
//            navController.navigate(route = "cats/${it.id}")
//        },
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(
    state: CatListState,
    onCatClick: (String) -> Unit,
) {
    Scaffold(topBar = {
        Column {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topBarContainerColor,
                    scrolledContainerColor = topBarContainerColor,
                ),
                title = {
                    Text(text = "Cat List")
                },
            )
            Divider()
        }

    }, content = { paddingValues ->
        if (state.loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        } else {
            CatList(
                items = state.cats, contentPadding = paddingValues, onCatClick = onCatClick
            )
        }
    })
}

@Composable
private fun CatList(
    items: List<CatUiModel>, contentPadding: PaddingValues, onCatClick: (String) -> Unit
) {
    // LazyColumn should be used for infinite lists which we will
    // learn soon. In the meantime we can use Column with verticalScroll
    // modifier so it can be scrollable.
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        items.forEach {
            Column {
                key(it.name) {
                    BreedListItem(
                        data = it,
                        onClick = onCatClick
                    )
                }
            }
        }

    }
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
    data: CatUiModel,
    onClick: (String) -> Unit,
) {
    Column {
        ListItem(modifier = Modifier.clickable { onClick(data.id) }, headlineContent = {
            Text(
                text = data.name, fontWeight = FontWeight.Bold, fontSize = 22.sp
                //modifier = Modifier.size(16.dp)
            )
            if (!data.alt_names.equals("")) {
                Text(
                    text = data.alt_names, fontSize = 17.sp
                    //modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }, supportingContent = {
            Text(data.getDesc250())
//            Text(data.description)
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


//@Preview
//@Composable
//fun PreviewCatListScreen() {
//    Kolokvijum1Theme {
//        CatListScreen(
//            state = CatListState(cats = SampleData),
//            onItemClick = {},
//        )
//    }
//}
class CatListStateParameterProvider : PreviewParameterProvider<CatListState> {
    override val values: Sequence<CatListState> = sequenceOf(
//        CatListState(
//            loading = true,
//        ),
//        CatListState(
//            loading = false,
//        ),
        CatListState(
            loading = false,
            cats = listOf(
                CatUiModel(
                    id = "a",
                    name = "macka1",
                    alt_names = "",
                    temperament = "temperament1 , 2, 3",
                    description = "marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22"
                ),
                CatUiModel(
                    id = "a",
                    name = "macka1",
                    alt_names = "jaka, ovakva onakva",
                    temperament = "temperament1 , 2, 3",
                    description = "marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22marko22"
                ),
                CatUiModel(
                    id = "a",
                    name = "macka1",
                    alt_names = "",
                    temperament = "temperament1 , 2, 3",
                    description = "dsadsadsadsadsa"
                ),
                CatUiModel(
                    id = "a",
                    name = "macka1",
                    alt_names = "",
                    temperament = "temperament1 , 2, 3",
                    description = "asd"
                ),
            ),
        ),
    )
}


@Preview
@Composable
private fun PreviewCatListScreen(
    @PreviewParameter(CatListStateParameterProvider::class) catListState: CatListState,
) {
    CatListScreen(
        state = catListState,
        onCatClick = {}
    )
}
