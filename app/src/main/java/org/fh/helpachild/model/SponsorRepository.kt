package org.fh.cfct.model

import javax.inject.Inject

class SponsorRepository @Inject constructor(private val sponsorDao: SponsorDao) {

    fun getAllSponsors() = sponsorDao.getAllSponsors()

    fun getAllSponsorWithChildren() = sponsorDao.getSponsorWithChildren()

    suspend fun getSponsor(sponsorId: Long) = sponsorDao.getSponsorById(sponsorId)

    suspend fun addSponsor(sponsor: Sponsor) {
        sponsorDao.insert(sponsor)
    }
    suspend fun deleteSponsor(sponsor: Long) {
        sponsorDao.deleteByID(sponsor)
    }
}