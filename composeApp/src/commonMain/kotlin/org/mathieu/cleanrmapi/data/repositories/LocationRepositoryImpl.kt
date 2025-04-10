package org.mathieu.cleanrmapi.data.repositories

import org.mathieu.cleanrmapi.data.local.dao.LocationDAO
import org.mathieu.cleanrmapi.data.mappers.toDBObject
import org.mathieu.cleanrmapi.data.mappers.toDomain
import org.mathieu.cleanrmapi.data.mappers.toModel
import org.mathieu.cleanrmapi.data.remote.LocationApi
import org.mathieu.cleanrmapi.domain.location.LocationRepository
import org.mathieu.cleanrmapi.domain.location.models.Location
import org.mathieu.cleanrmapi.domain.location.models.LocationPreview

/**
 * Implementation of LocationRepository, which manages the cache of locations via Room.
 *
 * @param dao Data Access Object for locations.
 */
internal class LocationRepositoryImpl(
    private val dao: LocationDAO,
    private val locationApi: LocationApi
) : LocationRepository {

    /**
     * Retrieves a lightweight preview of a location as a [LocationPreview].
     *
     * The function first tries to fetch the location from the local cache.
     * If it exists, it converts the cached object to a [LocationPreview] domain model.
     * Otherwise, it creates a new [LocationPreview] using the provided ID and name, caches this new preview,
     * and returns it.
     *
     * @param id The unique identifier of the location.
     * @param name The name of the location to use if it is not present in the cache.
     * @return A [LocationPreview] representing the location's minimal information.
     */
    override suspend fun getLocation(id: Int, name: String): LocationPreview {
        // Tries to retrieve the cached location
        val cached = dao.getById(id)
        return if (cached != null) {
            cached.toModel()
        } else {
            val model = LocationPreview(id, name)
            dao.insert(model.toDBObject())
            model
        }
    }

    /**
     * Retrieves detailed location information as a [Location] domain model.
     *
     * This function first checks the local cache for detailed location data.
     * If the data is available, it converts the cached data to a [Location] domain model.
     * If it is not in the cache, it calls the remote API (via [LocationApi]) to fetch the data, updates the cache,
     * and returns the detailed information.
     *
     * @param locationId The unique identifier of the location.
     * @return A [Location] object containing detailed information about the location.
     * @throws Exception if the location cannot be found via the remote API.
     */
    override suspend fun getLocationDetails(locationId: Int): Location {
        val cached = dao.getById(locationId)
        return if (cached != null) {
            cached.toDomain()
        } else {
            val response = locationApi.getLocation(locationId)
            if (response != null) {
                val locationObject = response.toDBObject()
                dao.insert(locationObject)
                locationObject.toDomain()
            } else {
                throw Exception("Location not found in remote")
            }
        }
    }
}