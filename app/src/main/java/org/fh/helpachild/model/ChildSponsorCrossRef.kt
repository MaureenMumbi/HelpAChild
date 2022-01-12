package org.fh.cfct.model

import androidx.room.Entity


@Entity(tableName="childsponsorcrossref",primaryKeys = ["childId", "sponsorId"])
data class ChildSponsorCrossRef(
    val childId: Long,
    val sponsorId: Long
)
