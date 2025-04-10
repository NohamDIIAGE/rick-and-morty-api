package org.mathieu.cleanrmapi.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import org.mathieu.cleanrmapi.data.remote.responses.LocationResponse

/**
 * Object used to access the Rick and Morty API location endpoint.
 *
 * This class is declared with the `internal` modifier to restrict access to it
 * only within the `data` module. It uses Ktor to communicate with the remote service.
 *
 * @property client Instance of [HttpClient] used to make network calls.
 */
internal class LocationApi(private val client: HttpClient) {

    /**
     * Retrieves detailed information about a location by querying
     * the API at the ‘location/{id}’ endpoint.
     *
     * @param id The unique identifier of the location.
     * @return A [LocationResponse] object containing the location details,
     * or `null` if the request returns no result (or if there is a serialization error).
     */

    suspend fun getLocation(id: Int): LocationResponse? =
        client.get("location/$id")
            .accept(HttpStatusCode.OK)
            .body()
}