package org.mathieu.cleanrmapi.data.local.dao

import androidx.room.*
import org.mathieu.cleanrmapi.data.local.RMDatabase
import org.mathieu.cleanrmapi.data.local.objects.LocationObject

@Dao
interface LocationDAO {

    /**
     * Retrieves a location from its identifier.
     *
     * @param id Location identifier.
     * @return The LocationObject object or null if not found.
     */
    @Query("select * from ${RMDatabase.LOCATION_TABLE} where id = :id")
    suspend fun getById(id: Int): LocationObject?

    /**
     * Inserts or updates a location in the database.
     *
     * @param location The LocationObject to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: LocationObject)
}