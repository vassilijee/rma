package com.example.kolokvijum1.cats.details

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.SubcomposeAsyncImage

private val topBarContainerColor = Color.LightGray

fun NavGraphBuilder.catDetail(
    route: String, arguments: List<NamedNavArgument>, onClose: () -> Unit
) = composable(
    route = route,
    arguments = arguments,
) { navBackStackEntry ->
    val dataId = navBackStackEntry.arguments?.getString("dataId")
        ?: throw IllegalStateException("dataId required")

    val dataUrl = navBackStackEntry.arguments?.getString("dataUrl")


    val catDetailViewModel =
        viewModel<CatDetailViewModel>(factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CatDetailViewModel(catId = dataId) as T
            }
        })

    val state = catDetailViewModel.state.collectAsState()

    dataUrl?.let {
        CatDetailScreen(
            data = state.value,
            url = it,
            onClose = onClose,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailScreen(
    data: CatDetailState, url: String, onClose: () -> Unit
) {
    Scaffold(topBar = {
        Column {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = topBarContainerColor,
                    scrolledContainerColor = topBarContainerColor,
                ),
                navigationIcon = {
                    AppIconButton(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        onClick = onClose,
                    )
                },
                title = {
                    Text(text = data.catDetail.name)
                },
            )
            //CenterAlignedTopAppBar(title = { Text(text = "Cat Details") })
            Divider()
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
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(
                        width = 400.dp, height = 300.dp
                    )
                    .padding(8.dp),
                model = url,
                contentDescription = null,
            )
            Divider()
            CatDetail(name = "Breed", desc = data.catDetail.name)
            CatDetail(
                name = "Description", desc = data.catDetail.description
            )
            CatDetail(name = "Country of Origin", desc = data.catDetail.countryofOrigin)
            CatDetail(name = "Life Expectancy", desc = data.catDetail.lifeExpectancy)
            CatDetail(name = "Averatge Weight", data.catDetail.avgWeight.metric)
            CatDetail(name = "Rare", desc = data.catDetail.rare)
            CatDetailWiki(name = "Wiki", desc = data.catDetail.wiki)
        }

    })
}

@Composable
private fun CatDetailWiki(
    name: String, desc: String
) {
    val context = LocalContext.current // Obtain the context

    Row(
        modifier = Modifier.padding(5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = name + ":",
            style = TextStyle(),
            modifier = Modifier.weight(0.3f) // Optional: Adjust weight for "Text" portion
        )

        ClickableText(
            text = AnnotatedString(desc),
            onClick = { offset ->
                // Handle click event, for example, open the URL
                val uri = Uri.parse(desc)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent) // Use the context to launch the intent
            },
            modifier = Modifier.weight(0.7f)

        )

    }
    Divider()
}

@Composable
private fun CatDetail(
    name: String, desc: Any
) {
    Row(
        modifier = Modifier.padding(5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = name + ":",
            style = TextStyle(),
            modifier = Modifier.weight(0.3f) // Optional: Adjust weight for "Text" portion
        )

        when (desc) {
            is String -> {
                Text(
                    text = desc, style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 14.sp
                    ), modifier = Modifier.weight(0.7f)
                )
            }

            is Int -> {
                if (desc == 1) {
                    Text(
                        text = "Yes", style = TextStyle(
                            fontWeight = FontWeight.Bold, fontSize = 14.sp
                        ), modifier = Modifier.weight(0.7f)
                    )
                } else {
                    Text(
                        text = "No", style = TextStyle(
                            fontWeight = FontWeight.Bold, fontSize = 14.sp
                        ), modifier = Modifier.weight(0.7f)
                    )

                }
            }
        }
    }
    Divider()
}


@Composable
fun AppIconButton(
    imageVector: ImageVector,
    onClick: () -> Unit,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified,
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint,
        )
    }
}


//@Preview(showSystemUi = false)
//@Composable
//fun ComposeAppPreview() {
//    Kolokvijum1Theme {
//        CatDetailScreen(
////            data = CatData(
////                id = "",
////                name = "raf.rs",
////                description = "ailic@raf.rs",
////                altNames = "Lozinka123",
////                temperament = ""
////            ),
//            data = ,
//            onClose = {},
//        )
//    }
//}
