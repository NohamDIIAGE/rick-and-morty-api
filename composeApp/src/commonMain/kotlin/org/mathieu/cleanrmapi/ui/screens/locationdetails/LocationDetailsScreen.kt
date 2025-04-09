package org.mathieu.cleanrmapi.ui.screens.locationdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.mathieu.cleanrmapi.ui.core.composables.Screen
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.mathieu.cleanrmapi.domain.location.models.Location
import org.mathieu.cleanrmapi.ui.core.composables.BackArrow
import org.mathieu.cleanrmapi.domain.character.models.Character
import androidx.compose.foundation.lazy.items
import org.mathieu.cleanrmapi.ui.core.managers.SoundManager



@Composable
fun LocationDetailsScreen(
    navController: NavController,
    id: Int
) {
    Screen(
        viewModel = viewModel { LocationDetailsViewModel() },
        navController = navController
    ) { state, viewModel ->

        LaunchedEffect(Unit) {
            viewModel.init(id)
        }

        when (state) {
            is LocationDetailsState.Loading -> { }
            is LocationDetailsState.Error -> { Text("Error: ${state.message}") }
            is LocationDetailsState.Loaded -> {
                Content(
                    location = state.location,
                    onCharacterClick = { character ->
                        viewModel.handleAction(LocationDetailsAction.SelectedCharacter(character))
                    },
                    onBack = navController::popBackStack
                )
            }
        }
    }
}

@Composable
fun Content(
    location: Location,
    onCharacterClick: (Character) -> Unit,
    onBack: () -> Unit
) {
    Column {
        Text(text = location.name)
        Text(text = location.type)
        Text(text = location.dimension)

        BackArrow(onClick = onBack)

        LazyColumn {
            items(location.residents) { character ->
                Row(
                    modifier = Modifier.clickable {
                        SoundManager.playClickSound()
                        onCharacterClick(character)
                    }
                ) {
                    Text(character.name)
                }
            }
        }
    }
}
