package org.fh.cfct.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
/**
 * This class captures the relationship between a [Sponsor] and the children they sponsor [Child], which is
 * used by Room to fetch the related entities.
 */
data class SponsorWithChild (
    @Embedded val sponsor: Sponsor,
    @Relation(
        parentColumn = "sponsorId",
        entityColumn = "childId",
        associateBy = Junction(ChildSponsorCrossRef::class)
    )
    val children: List<Child>

)