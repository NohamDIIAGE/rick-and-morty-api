package org.mathieu.cleanrmapi.data.mappers

import org.mathieu.cleanrmapi.data.local.objects.LocationObject
import org.mathieu.cleanrmapi.data.remote.responses.CharacterLocationResponse
import org.mathieu.cleanrmapi.data.remote.responses.LocationResponse
import org.mathieu.cleanrmapi.domain.location.models.Location
import org.mathieu.cleanrmapi.domain.location.models.LocationPreview

fun LocationObject.toModel() = LocationPreview(id = id, name = name)
fun LocationPreview.toDBObject() = LocationObject(
    id = id,
    name = name,
    type = "",
    dimension = "",
    residentsIds = ""
)
fun CharacterLocationResponse.toLocationPreview(): LocationPreview {
    val id = this.url.split("/").lastOrNull()?.toIntOrNull() ?: -1
    return LocationPreview(id = id, name = name)
}
fun LocationObject.toDomain() = Location(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residents = emptyList()
)

internal fun LocationResponse.toDBObject(): LocationObject = LocationObject(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    residentsIds = residents.joinToString(separator = ",")
)