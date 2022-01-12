package org.fh.cfct.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface ChildDao {

    @Query("SELECT * from child")
     fun getAllChildren(): Flow<List<Child>>

    @Query("SELECT * FROM child WHERE childId = :id ")
     fun getChildById(id: Long):Child

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(child: Child)

    @Delete
    suspend fun delete(child: Child)

//    @Delete
//    fun deleteAll()

    @Query("DELETE FROM child where childId =:id")
    fun deleteByID(id:Long)

    @Query("UPDATE child SET full_name =:full_name, gender =:gender, country =:country, location =:location, profile_photo=:profile_photo, parent_idno =:parent_idno WHERE childId =:childid")
    fun update(full_name: String, gender:String, country:String, location:String, profile_photo:String, parent_idno :String,  childid: Long )

    @Transaction
    @Query("SELECT * FROM child")
    fun getChildwithSponsor(): List<ChildWithSponsors>
}