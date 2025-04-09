package org.mathieu.cleanrmapi.ui.screens.locationdetails

import org.koin.core.component.inject
import org.mathieu.cleanrmapi.domain.location.LocationRepository
import org.mathieu.cleanrmapi.domain.location.models.Location
import org.mathieu.cleanrmapi.ui.core.Destination
import org.mathieu.cleanrmapi.ui.core.ViewModel
import org.mathieu.cleanrmapi.domain.character.models.Character

class LocationDetailsViewModel : ViewModel<LocationDetailsState>(LocationDetailsState.Loading) {

    private val locationRepository: LocationRepository by inject()

    fun init(locationId: Int) {
        fetchData(
            source = { locationRepository.getLocationDetails(locationId) }
        ) {
            onSuccess { location ->
                updateState {
                    LocationDetailsState.Loaded(location = location)
                }
            }
            onFailure { ex ->
                updateState { LocationDetailsState.Error(ex.message ?: "Unknown Error") }
            }
        }
    }

    fun handleAction(action: LocationDetailsAction) {
        when(action) {
            is LocationDetailsAction.SelectedCharacter ->
                sendEvent(Destination.CharacterDetails(action.character.id.toString()))
        }
    }
}

sealed interface LocationDetailsState {
    object Loading : LocationDetailsState
    data class Error(val message: String) : LocationDetailsState
    data class Loaded(val location: Location) : LocationDetailsState
}

sealed interface LocationDetailsAction {
    data class SelectedCharacter(val character: Character) : LocationDetailsAction
}
