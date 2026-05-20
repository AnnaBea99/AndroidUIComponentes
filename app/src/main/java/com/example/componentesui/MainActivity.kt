package com.exemplo.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreenPreview()  // Usando a mesma função do Preview
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenPreview() {
    var textFieldValue by remember { mutableStateOf("") }
    var itemsList by remember { mutableStateOf(listOf<String>()) }

    // Dados para o grid (fixos para o preview)
    val gridItems = listOf(
        GridItem("Item 1", Color(0xFFFFCDD2)),
        GridItem("Item 2", Color(0xFFC8E6C9)),
        GridItem("Item 3", Color(0xFFBBDEFB)),
        GridItem("Item 4", Color(0xFFFFF9C4)),
        GridItem("Item 5", Color(0xFFD1C4E9)),
        GridItem("Item 6", Color(0xFFFFCCBC))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Demonstração de Componentes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo de texto
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            label = { Text("Digite algo aqui") },
            placeholder = { Text("Ex: Comprar pão") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão
        Button(
            onClick = {
                if (textFieldValue.isNotBlank()) {
                    itemsList = itemsList + textFieldValue
                    textFieldValue = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar à Lista")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Título da Lista
        Text(
            text = "📋 Lista de Itens",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // LazyColumn (Lista vertical)
        if (itemsList.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhum item adicionado ainda",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.height(200.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(itemsList) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            TextButton(
                                onClick = { itemsList = itemsList - item }
                            ) {
                                Text("Remover", color = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Título do Grid
        Text(
            text = "🔲 Grid de Itens",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // LazyVerticalGrid (Grid)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 120.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(300.dp)
        ) {
            items(gridItems) { gridItem ->
                GridItemCard(gridItem = gridItem)
            }
        }
    }
}

@Composable
fun GridItemCard(gridItem: GridItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = gridItem.color
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = gridItem.title,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
    }
}

// Classe de dados para o grid
data class GridItem(
    val title: String,
    val color: Color
)


@Preview(
    name = "Preview Principal",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewMainScreen() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreenPreview()
        }
    }
}

// Preview com lista vazia
@Preview(
    name = "Lista Vazia",
    showBackground = true
)
@Composable
fun PreviewEmptyList() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Lista Vazia - Preview",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Nenhum item na lista")
                    }
                }
            }
        }
    }
}

// Preview só do Grid
@Preview(
    name = "Apenas Grid",
    showBackground = true
)
@Composable
fun PreviewGridOnly() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val gridItems = listOf(
                GridItem("Item 1", Color(0xFFFFCDD2)),
                GridItem("Item 2", Color(0xFFC8E6C9)),
                GridItem("Item 3", Color(0xFFBBDEFB)),
                GridItem("Item 4", Color(0xFFFFF9C4))
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 120.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(gridItems) { gridItem ->
                    GridItemCard(gridItem = gridItem)
                }
            }
        }
    }
}