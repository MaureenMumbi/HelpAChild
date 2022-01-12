package org.fh.cfct.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface DonationDao {

    @Query("SELECT * from donation")
    fun getAllDonations(): Flow<List<Donation>>

    @Query("SELECT * FROM donation WHERE id = :id ")
    fun getDonationById(id: Long): List<Donation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(donation: Donation)

    @Delete
    fun delete(donation: Donation)

    @Query("DELETE FROM donation where id =:id")
    fun deleteByID(id:Long)

    @Query("UPDATE donation SET child_id =:child_id, sponsor_id =:sponsor_id, frequency =:frequency, amount =:amount, payment_type=:payment_type, currency=:currency  WHERE id =:donationid")
    fun update(child_id: String, sponsor_id:String, frequency:String, amount:String, payment_type:String, currency :String, donationid :Long)


}