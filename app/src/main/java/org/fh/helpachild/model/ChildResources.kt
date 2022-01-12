package org.fh.cfct.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import org.fh.cfct.db.Converters

@Parcelize
@Entity(tableName="child_resources", foreignKeys = [
    ForeignKey(entity = Child::class, parentColumns = ["childId"], childColumns = ["child_id"])
])
@TypeConverters(Converters::class)
data class ChildResources (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "child_id")var child_id: Long,
    @ColumnInfo(name ="file_path") var file_path: String,
    @ColumnInfo(name="type") var type:String,
    @ColumnInfo (name="additional_info") var additional_info:String) : Parcelable