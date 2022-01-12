package org.fh.cfct.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChildResourcesDao {


    @Query("SELECT * from child_resources")
    fun getAll(): Flow<List<ChildResources>>

    @Query("SELECT * FROM child_resources WHERE id = :id ")
    fun getChildResourcesById(id: Long): Flow<List<ChildResources>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(childResources: ChildResources)

    @Delete
    fun delete(childResources: ChildResources)

    @Query("DELETE FROM child_resources where id =:id")
    fun deleteByID(id:Long)

    @Query("UPDATE child_resources SET file_path =:file_path, type =:type, additional_info=:additional_info where id=:id")
    fun update(file_path: String, type:String, additional_info:String, id :Long)


}