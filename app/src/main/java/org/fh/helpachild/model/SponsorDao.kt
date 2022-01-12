package org.fh.cfct.model

import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface SponsorDao {

    @Query("SELECT * from sponsor")
    fun getAllSponsors(): Flow<List<Sponsor>>

    @Query("SELECT * FROM sponsor WHERE sponsorId = :id ")
    fun getSponsorById(id: Long): Sponsor

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sponsor: Sponsor)

    @Delete
    fun delete(sponsor: Sponsor)

    @Query("DELETE FROM sponsor where sponsorId =:id")
    fun deleteByID(id:Long)

    @Query("UPDATE sponsor SET full_name =:full_name, country =:country, phone_no =:phone_no, email =:email, bio=:bio, payment_card=:payment_card WHERE sponsorId =:sponsorID")
    fun update(full_name: String, country:String, phone_no:String, email:String, bio: String, payment_card:String, sponsorID :Long)



    @Transaction
    @Query("SELECT * FROM sponsor")
    fun getSponsorWithChildren(): List<SponsorWithChild>



}