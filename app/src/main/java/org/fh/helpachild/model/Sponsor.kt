package org.fh.cfct.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize
import org.fh.cfct.db.Converters
import java.util.*


@Parcelize
@Entity(tableName="sponsor")
@TypeConverters(Converters::class)
data class Sponsor(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "sponsorId") var id: Long = 0,
    @ColumnInfo(name = "full_name")var full_name: String,
    @ColumnInfo(name="country") var country : String,
    @ColumnInfo(name ="phone_no") var phone_no: String,
    @ColumnInfo(name ="email") var email: String,
    @ColumnInfo(name="bio") var bio:String,
    @ColumnInfo(name="payment_card") var payment_card:String,
    @ColumnInfo(name="dateJoined") var dateJoined: Calendar = Calendar.getInstance(),
) : Parcelable