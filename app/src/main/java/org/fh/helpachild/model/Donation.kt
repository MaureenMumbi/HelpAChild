package org.fh.cfct.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import org.fh.cfct.db.Converters
import java.util.*

@Parcelize
@Entity(tableName = "donation", foreignKeys = [
    ForeignKey(entity = Child::class, parentColumns = ["childId"], childColumns = ["child_id"]),
    ForeignKey(entity = Sponsor::class, parentColumns = ["sponsorId"], childColumns = ["sponsor_id"])
])
@TypeConverters(Converters::class)
data class Donation (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "child_id")var child_id: Long,
    @ColumnInfo(name = "sponsor_id")var sponsor_id: Long,
    @ColumnInfo(name = "frequency")var frequency: String,
    @ColumnInfo(name = "amount")var amount: Float,
    @ColumnInfo(name ="payment_type") var  payment_type:String,
    @ColumnInfo(name="currency") var currency:String,
    @ColumnInfo(name="payment_date") var payment_date:Date,
) : Parcelable
