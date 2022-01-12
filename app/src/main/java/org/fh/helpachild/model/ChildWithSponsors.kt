package org.fh.cfct.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

/**
 * This class captures the relationship between a [Child] and a their sponsors [Sponsor], which is
 * used by Room to fetch the related entities.
 */
data class ChildWithSponsors(
    @Embedded
    val child: Child,
    @Relation(
        parentColumn = "childId",
        entityColumn = "sponsorId",
        associateBy = Junction(ChildSponsorCrossRef::class)
    )
    val sponsors: List<Sponsor>
)
