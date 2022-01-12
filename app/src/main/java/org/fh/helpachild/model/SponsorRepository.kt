package org.fh.cfct.model

import javax.inject.Inject

class SponsorRepository @Inject constructor(private val sponsorDao: SponsorDao) {

    fun getAllSponsors() = sponsorDao.getAllSponsors()

    fun getAllSponsorWithChildren() = sponsorDao.getSponsorWithChildren()

    fun getSponsor(sponsorId: Long) = sponsorDao.getSponsorById(sponsorId)

    fun addSponsor(sponsor: Sponsor) {
        sponsorDao.insert(sponsor)
    }
}