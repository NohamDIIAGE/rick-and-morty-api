package org.mathieu.cleanrmapi.data.repositories

import org.mathieu.cleanrmapi.data.local.dao.LocationDAO
import org.mathieu.cleanrmapi.data.mappers.toDBObject
import org.mathieu.cleanrmapi.data.mappers.toDomain
import org.mathieu.cleanrmapi.data.mappers.toModel
import org.mathieu.cleanrmapi.domain.location.LocationRepository
import org.mathieu.cleanrmapi.domain.location.models.Location
import org.mathieu.cleanrmapi.domain.location.models.LocationPreview

/**
 * Implementation of LocationRepository, which manages the cache of locations via Room.
 *
 * @param dao Data Access Object for locations.
 */
class LocationRepositoryImpl(
    private val dao: LocationDAO) : LocationRepository {

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

    override suspend fun getLocationDetails(locationId: Int): Location {
        // Tries to retrieve the cached Location object
        val cached = dao.getById(locationId)
        return if (cached != null) {
            cached.toDomain()
        } else {
            throw Exception("Location not found in cache and remote fetch is not implemented")
        }
    }
}