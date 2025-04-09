package org.mathieu.cleanrmapi.domain.location

import org.mathieu.cleanrmapi.domain.location.models.Location
import org.mathieu.cleanrmapi.domain.location.models.LocationPreview

/**
 * Interface for the location repository.
 * Allows you to retrieve a preview of a location or full details.
 */
interface LocationRepository {
    /**
     * Retrieves a preview of the location based on an identifier and a name.
     *
     * @param id Location identifier.
     * Name to use if no cache data is found.
     * @return A LocationPreview object.
     */
    suspend fun getLocation(id: Int, name: String): LocationPreview

    /**
     * Retrieves full details of a location.
     *
     * @param locationId The location identifier.
     * @return A Location object containing full details.
     */
    suspend fun getLocationDetails(locationId: Int): Location

}