package com.example.kolokvijum1.cats.details

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.kolokvijum1.R
import com.example.kolokvijum1.cats.model.CatData
import com.example.kolokvijum1.cats.repository.CatRepository
import com.example.kolokvijum1.cats.repository.SampleData
import com.example.kolokvijum1.core.theme.Kolokvijum1Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID

private val topBarContainerColor = Color.LightGray

fun NavGraphBuilder.catDetailScreen(
    route: String,
    arguments: List<NamedNavArgument>,
    navController: NavController,
) = composable(
    route = route,
    arguments = arguments,
) { navBackStackEntry ->
    val dataId = navBackStackEntry.arguments?.getString("dataId")

    val data = if (dataId != null) {
        CatRepository.getCatById(id = dataId)
    } else {
        CatData(
            id = UUID.randomUUID().toString(),
            name = "",
            description = "",
            altNames = "",
            temperament = ""
        )
    }

    if (data != null) {
        CatDetailScreen(
            data = data,
            onClose = {
                navController.navigateUp()
            }
        )
    } else {
//        NoDataContent(id = dataId.toString())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailScreen(
    data: CatData,
    onClose: () -> Unit
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
                    Text(text = "Cat Detail")
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
            Image(
                painter = painterResource(id = R.drawable.pic), // Replace with your image resource ID
                contentDescription = "Image description", // Provide a description for accessibility
                modifier = Modifier
                    .size(
                        width = 400.dp, height = 300.dp
                    ) // Optional: Set image size
                    .padding(8.dp) // Optional: Add padding around the image
            )
            Divider()
            CatDetail(name = "Breed", desc = "Syamise")
            CatDetail(
                name = "Description",
                desc = "American Bobtails are loving and incredibly intelligent cats possessing a distinctive wild appearance. They are extremely interactive cats that bond with their human family with great devotion."
            )
            CatDetail(name = "Country of Origin", desc = "Egypt")
            CatDetail(name = "Life Expectancy", desc = "14 - 15")
            CatDetail(name = "Averatge Weight", desc = "3 - 5")
            CatDetail(name = "Rare", desc = "/")
            //CatDetail(name = "Rare", desc = Icons.Default.Add)
            CatDetail(name = "Wiki", desc = "Syamise")
        }

    })
}

@Composable
private fun CatDetail(
    name: String, desc: String
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

        Text(
            text = desc, style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 14.sp
            ), // Optional: Bold for "Text"
            modifier = Modifier.weight(0.7f) // Adjust weight for "Description" portion
        )
        // Divider()
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


@Preview(showSystemUi = false)
@Composable
fun ComposeAppPreview() {
    Kolokvijum1Theme {
        CatDetailScreen(
            data = CatData(
                id = "",
                name = "raf.rs",
                description = "ailic@raf.rs",
                altNames = "Lozinka123",
                temperament = ""
            ),
            onClose = {},
        )
    }
}
