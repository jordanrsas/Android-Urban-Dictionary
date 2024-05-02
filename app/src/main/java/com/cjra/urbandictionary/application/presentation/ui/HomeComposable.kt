package com.cjra.urbandictionary.application.presentation.ui

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cjra.urbandictionary.application.presentation.HomeIntent
import com.cjra.urbandictionary.application.presentation.HomeState
import com.cjra.urbandictionary.application.presentation.HomeViewModel
import com.cjra.urbandictionary.application.presentation.Definition

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(myViewModel: HomeViewModel) {
    val uiState by myViewModel.uiState.collectAsState()

    // Collect the word and call handleIntent
    val (word, setWord) = remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopAppBar(
            title = { Text(text = "Urban Dictionary") },
            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer)
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Text Field to enter word
        TextField(
            value = word,
            onValueChange = setWord,
            label = { Text("Enter a word") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Button to define word
        Button(
            onClick = {
                keyboardController?.hide()
                myViewModel.handleIntent(HomeIntent.DefineWord(word))
            },
            enabled = word.isNotBlank()
        ) {
            Text("Define")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Observe the UI state and render accordingly
        when (val state = uiState) {
            is HomeState.Empty -> EmptyScreen()

            is HomeState.Loading -> LoadingScreen()

            is HomeState.Success -> DictionaryView(definitions = state.definitions)

            is HomeState.Error -> ErrorScreen()
        }
    }
}

@Composable
fun DefinitionItem(definition: Definition) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Word: ${definition.word}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Definition: ${definition.definition}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.ThumbUp,
                    contentDescription = null, // Decorative element
                    tint = Color.Green,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "${definition.likes}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Rounded.Info,
                    contentDescription = null, // Decorative element
                    tint = Color.Red,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = "${definition.dislikes}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Example: ${definition.example}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun DictionaryView(definitions: List<Definition>) {
    var sortAscending by remember { mutableStateOf(true) }
    val sortedDefinitions = remember(definitions, sortAscending) {
        if (sortAscending) {
            definitions.sortedBy { it.likes }
        } else {
            definitions.sortedByDescending { it.likes }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        // Button to toggle sorting order
        Button(onClick = { sortAscending = !sortAscending }) {
            Text(if (sortAscending) "Sort by Likes Ascending" else "Sort by Likes Descending")
        }

        // Mostrar las definiciones en una lista lazy
        LazyColumn {
            itemsIndexed(sortedDefinitions) { index, definition ->
                DefinitionItem(definition = definition)
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Column {
        CircularProgressIndicator(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
        )
        Text(
            "Loading",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ErrorScreen() {
    Column {
        Icon(
            Icons.Rounded.Warning,
            contentDescription = "Localized description",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
        )
        Text(
            "Something went wrong. Please try again later",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun EmptyScreen() {
    Column {
        Icon(
            Icons.Rounded.List,
            contentDescription = "Localized description",
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)
        )
        Text(
            "We couldn't find any definition",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}