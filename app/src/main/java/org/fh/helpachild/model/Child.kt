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
@Entity(tableName="child")
@TypeConverters(Converters::class)

data class Child(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "childId") var id: Long = 0,
    @ColumnInfo(name = "full_name")var full_name: String,
    @ColumnInfo(name="gender") var gender : String,
    @ColumnInfo(name ="dob") var dob:  Calendar = Calendar.getInstance(),
    @ColumnInfo(name ="country") var country: String,
    @ColumnInfo(name ="location") var location: String,
    @ColumnInfo(name ="profile_photo") var profile_photo: String,
    @ColumnInfo(name ="parent_idno") var parent_idno: String,
    @ColumnInfo(name ="bio") var bio: String,
    @ColumnInfo(name="dateJoined") var dateJoined: Calendar = Calendar.getInstance(),
    ) : Parcelable